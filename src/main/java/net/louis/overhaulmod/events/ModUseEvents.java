package net.louis.overhaulmod.events;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.louis.overhaulmod.entity.projectile.thrown.BrickEntity;
import net.louis.overhaulmod.entity.projectile.thrown.NetherBrickEntity;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.mixin.ArmorStandEntityAccessor;
import net.louis.overhaulmod.mixin.BrushableBlockEntityAccessor;
import net.louis.overhaulmod.utils.GlowManager;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModUseEvents {

    public static void registerMain() {
        UseBlockCallback.EVENT.register(ModUseEvents::useOnSuspiciousBlock);
        UseBlockCallback.EVENT.register(ModUseEvents::oxidizeCopperWithClock);
        UseBlockCallback.EVENT.register(ModUseEvents::retexturePlayerHead);
        UseBlockCallback.EVENT.register(ModUseEvents::useChilledBonemeal);

        UseItemCallback.EVENT.register(ModUseEvents::getLlamaSpitBottle);
        UseItemCallback.EVENT.register(ModUseEvents::useGlowInk);

        UseEntityCallback.EVENT.register(ModUseEvents::changeArmorStandVariant);
        UseEntityCallback.EVENT.register(ModUseEvents::dyeShulkers);
        UseEntityCallback.EVENT.register(ModUseEvents::useBrushOnDyedShulkers);

        AttackEntityCallback.EVENT.register(ModUseEvents::featherDamageFreeKB);
    }

    public static void registerProjectileItems() {
        UseItemCallback.EVENT.register(ModUseEvents::useBrick);
        UseItemCallback.EVENT.register(ModUseEvents::useNetherBrick);
    }

    private static ActionResult oxidizeCopperWithClock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient && stack.getItem() == Items.CLOCK) {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            Map<Block, Block> oxidationMap = Map.ofEntries(
                    Map.entry(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER),
                    Map.entry(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER),
                    Map.entry(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER),

                    Map.entry(Blocks.COPPER_BULB, Blocks.EXPOSED_COPPER_BULB),
                    Map.entry(Blocks.EXPOSED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB),
                    Map.entry(Blocks.WEATHERED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB),

                    Map.entry(Blocks.COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR),
                    Map.entry(Blocks.EXPOSED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR),
                    Map.entry(Blocks.WEATHERED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR),

                    Map.entry(Blocks.COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE),
                    Map.entry(Blocks.EXPOSED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE),
                    Map.entry(Blocks.WEATHERED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE),

                    Map.entry(Blocks.COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR),
                    Map.entry(Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR),
                    Map.entry(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR),

                    Map.entry(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB),

                    Map.entry(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS),

                    Map.entry(Blocks.CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER),
                    Map.entry(Blocks.EXPOSED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER),
                    Map.entry(Blocks.WEATHERED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER),

                    Map.entry(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER)
            );

            BlockState clickedState = world.getBlockState(pos);
            Block clickedBlock = clickedState.getBlock();

            if (oxidationMap.containsKey(clickedBlock)) {
                if (clickedBlock instanceof DoorBlock && clickedState.get(DoorBlock.HALF) == DoubleBlockHalf.UPPER) {
                    return ActionResult.PASS;
                }
            }

            if (oxidationMap.containsKey(block)) {
                Block nextStage = oxidationMap.get(block);
                BlockState newState = nextStage.getDefaultState();

                if (state.contains(Properties.WATERLOGGED)) {
                    newState = newState.with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED));
                }

                world.setBlockState(pos, newState);
                if (Random.create().nextInt(20) == 0) {
                    world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_DESTROY, SoundCategory.BLOCKS, 1f, 2f);
                    stack.decrement(1);
                } else {
                    world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1f, 2f);
                }

                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.WAX_OFF,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        20,
                        0.5, 0.5, 0.5,
                        0.075
                );

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

    private static ActionResult retexturePlayerHead(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (world.isClient()) {
            return ActionResult.PASS;
        }

        ItemStack heldItem = player.getStackInHand(hand);
        BlockPos pos = hitResult.getBlockPos();
        BlockState blockState = world.getBlockState(pos);

        if (heldItem.isOf(Items.NAME_TAG) && isPlayerHead(blockState)) {
            Text nameTagText = heldItem.get(DataComponentTypes.CUSTOM_NAME);

            if (nameTagText != null) {
                String playerName = nameTagText.getString();

                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof SkullBlockEntity skullEntity) {
                    updateSkullTexture(skullEntity, playerName, world);

                    if (!player.getAbilities().creativeMode) {heldItem.decrement(1);}
                    player.sendMessage(Text.literal("Changed head texture to: " + playerName), true);
                    return ActionResult.SUCCESS;
                }
            } else {
                player.sendMessage(Text.literal("Name tag must have a custom name!"), true);
                return ActionResult.FAIL;
            }
        }

        return ActionResult.PASS;
    }

    private static ActionResult useChilledBonemeal(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack heldItem = player.getStackInHand(hand);
        if (world.isClient() || !heldItem.isOf(ModItems.CHILLED_BONE_MEAL)) {
            return ActionResult.PASS;
        }

        BlockPos pos = hitResult.getBlockPos();
        BlockState blockState = world.getBlockState(pos);
        Block targetBlock = blockState.getBlock();

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        // Case 1: CACTUS
        if (blockState.contains(CactusBlock.AGE)) {
            BlockPos checkUpwardPos = pos;
            for (int i = 0; i < 2; i++) {
                checkUpwardPos = checkUpwardPos.up();

                if (world.isAir(checkUpwardPos)) {
                    if (world.getBlockState(pos.down()).contains(CactusBlock.AGE)) {
                        break;
                    }
                    world.setBlockState(checkUpwardPos, targetBlock.getDefaultState());
                    world.setBlockState(checkUpwardPos, blockState.with(CactusBlock.AGE, 0), Block.NOTIFY_LISTENERS);
                    world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE,
                            SoundCategory.PLAYERS, 1.0F, 0.5F);
                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.HAPPY_VILLAGER,
                            x,
                            y,
                            z,
                            10,
                            0.5, 0.5, 0.5,
                            0.2
                    );
                    return ActionResult.SUCCESS;
                }
            }
        }

        // Case 2: SUGAR CANE
        if (blockState.contains(SugarCaneBlock.AGE)) {
            BlockPos checkUpwardPos = pos;
            for (int i = 0; i < 2; i++) {
                checkUpwardPos = checkUpwardPos.up();

                if (world.isAir(checkUpwardPos)) {
                    if (world.getBlockState(pos.down()).contains(SugarCaneBlock.AGE)) {
                        break;
                    }
                    world.setBlockState(checkUpwardPos, targetBlock.getDefaultState());
                    world.setBlockState(checkUpwardPos, blockState.with(SugarCaneBlock.AGE, 0), Block.NOTIFY_LISTENERS);
                    world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE,
                            SoundCategory.PLAYERS, 1.0F, 0.5F);
                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.HAPPY_VILLAGER,
                            x,
                            y,
                            z,
                            10,
                            0.5, 0.5, 0.5,
                            0.2
                    );
                    return ActionResult.SUCCESS;
                }
            }
        }

        // Case 3: NETHER WART
        if (blockState.contains(NetherWartBlock.AGE)) {
            int age = blockState.get(NetherWartBlock.AGE);
            if (age < 3) {
                world.setBlockState(pos, blockState.with(NetherWartBlock.AGE, age + 1), Block.NOTIFY_LISTENERS);
                world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE,
                        SoundCategory.PLAYERS, 1.0F, 0.5F);
                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.HAPPY_VILLAGER,
                        x,
                        y,
                        z,
                        10,
                        0.5, 0.5, 0.5,
                        0.2
                );
                return ActionResult.SUCCESS;
            }
        }

        // Case 4: CHORUS PLANT
        Random random = Random.create();
        if (targetBlock instanceof ChorusFlowerBlock) {
            int age = blockState.get(ChorusFlowerBlock.AGE);
            if (age < 5) {
                ChorusFlowerBlock.generate(world, pos, random, 20);
                world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE,
                        SoundCategory.PLAYERS, 1.0F, 0.5F);
                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.HAPPY_VILLAGER,
                        x,
                        y,
                        z,
                        10,
                        0.5, 0.5, 0.5,
                        0.2
                );
                return ActionResult.SUCCESS;
            }
        }

        // Case 5: VINES
        if (targetBlock instanceof VineBlock) {
            BlockPos checkDownPos = pos;
            for (int j = 0; j < 9; j++) {
                checkDownPos = checkDownPos.down();
                if (world.isAir(checkDownPos)) {
                    world.setBlockState(checkDownPos, blockState, Block.NOTIFY_ALL);
                    world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE,
                            SoundCategory.PLAYERS, 1.0F, 0.5F);
                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.HAPPY_VILLAGER,
                            x,
                            y,
                            z,
                            10,
                            0.5, 0.5, 0.5,
                            0.2
                    );
                    return ActionResult.SUCCESS;
                }
                else if (!world.getBlockState(pos).isOf(Blocks.VINE)) {
                    break;
                }
            }
        }
        return ActionResult.PASS;
    }

    private static TypedActionResult<ItemStack> getLlamaSpitBottle(PlayerEntity player, World world, Hand hand) {
        if (world.isClient) return TypedActionResult.pass(player.getStackInHand(hand));

        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(Items.GLASS_BOTTLE)) {
            double reach = 5.0D;
            Vec3d start = player.getCameraPosVec(1.0F);
            Vec3d look = player.getRotationVec(1.0F);
            Vec3d end = start.add(look.multiply(reach));
            Box box = player.getBoundingBox().stretch(look.multiply(reach)).expand(1.0D);
            LlamaSpitEntity targetSpit = null;
            double closestDistance = reach * reach;

            for (Entity entity : world.getOtherEntities(player, box)) {
                if (!(entity instanceof LlamaSpitEntity spit)) continue;
                Box entityBox = spit.getBoundingBox().expand(0.5D);
                Optional<Vec3d> optional = entityBox.raycast(start, end);
                if (optional.isPresent()) {
                    double distance = start.squaredDistanceTo(optional.get());
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        targetSpit = spit;
                    }
                }
            }

            if (targetSpit != null) {
                if (!player.getAbilities().creativeMode) stack.decrement(1);
                ItemStack result = new ItemStack(ModItems.LLAMAS_SPIT);
                if (!player.getInventory().insertStack(result)) {
                    player.dropItem(result, false);
                }
                world.playSound(null, targetSpit.getBlockPos(), SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH,
                        SoundCategory.PLAYERS, 1.0F, 1.0F);
                targetSpit.discard();
                return TypedActionResult.success(stack, world.isClient());
            }
        }
        return TypedActionResult.pass(player.getStackInHand(hand));

    }

    private static TypedActionResult<ItemStack> useBrick(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(Items.BRICK) || player.getItemCooldownManager().isCoolingDown(Items.BRICK)) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        BrickEntity brickEntity = new BrickEntity(world, player);
        brickEntity.setItem(stack);
        brickEntity.setVelocity(player, player.getPitch(), player.getYaw(), 10.0F, 1.0F, 1.0F);
        world.spawnEntity(brickEntity);

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(Items.BRICK, 20);

        return TypedActionResult.success(stack, world.isClient());
    }

    private static TypedActionResult<ItemStack> useNetherBrick(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(Items.NETHER_BRICK) || player.getItemCooldownManager().isCoolingDown(Items.NETHER_BRICK)) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        NetherBrickEntity brickEntity = new NetherBrickEntity(world, player);
        brickEntity.setItem(stack);
        brickEntity.setVelocity(player, player.getPitch(), player.getYaw(), 10.0F, 1.0F, 1.0F);
        world.spawnEntity(brickEntity);

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(Items.NETHER_BRICK, 20);

        return TypedActionResult.success(stack, world.isClient());
    }

    private static ActionResult changeArmorStandVariant(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getMainHandStack();
        if (world.isClient() || !player.isSneaking() || !(entity instanceof ArmorStandEntity stand)) {
            return ActionResult.PASS;
        }

        if (stack.isOf(Items.STICK) && !stand.shouldShowArms()) {
            stand.setShowArms(true);
            stack.decrementUnlessCreative(1, player);
        } else if (stack.isOf(Items.SMOOTH_STONE_SLAB) && stand.shouldHideBasePlate()) {
            stand.setHideBasePlate(false);
            stack.decrementUnlessCreative(1, player);
        } else if (stack.getItem() instanceof AxeItem && !stand.isSmall()) {
            ((ArmorStandEntityAccessor) stand).callSetSmall(true);
            stack.damage(1, player, EquipmentSlot.MAINHAND);
        } else if (stack.isOf(Items.AIR) && !stand.shouldHideBasePlate()) {
            stand.setHideBasePlate(true);
            ItemEntity itemEntity = new ItemEntity(world, stand.getX(), stand.getY(), stand.getZ(), new ItemStack(Items.SMOOTH_STONE_SLAB));
            world.spawnEntity(itemEntity);
        } else if (stack.isOf(Items.PHANTOM_MEMBRANE) && !stand.isInvisible()) {
            stand.setInvisible(true);
            stack.decrementUnlessCreative(1, player);
        } else if (stack.isOf(Items.GLOW_INK_SAC) && !stand.isGlowing()) {
            stand.setGlowing(true);
            stack.decrementUnlessCreative(1, player);
        } else {
            return ActionResult.PASS;
        }

        world.playSound(null, stand.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.BLOCKS, 1.0F, 1.0F);
        return ActionResult.SUCCESS;
    }

    private static TypedActionResult<ItemStack> useGlowInk(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        if (world.isClient() || item != Items.GLOW_INK_SAC || player.getItemCooldownManager().isCoolingDown(item)) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }

        stack.decrementUnlessCreative(1, player);
        player.getItemCooldownManager().set(stack.getItem(), 200);

        List<Entity> nearby = world.getOtherEntities(
                player,
                player.getBoundingBox().expand(12)
        );

        for (Entity e : nearby) {
            if (e instanceof LivingEntity living && !living.isGlowing()) {
                living.setGlowing(true);
                GlowManager.addGlowingEntity(living, 80);
            }
        }

        world.playSound(
                null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, SoundCategory.PLAYERS,
                1.0F, 1.0F
        );

        player.swingHand(hand, true);
        return TypedActionResult.success(stack, world.isClient());
    }

    private static ActionResult dyeShulkers(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient || !(stack.getItem() instanceof DyeItem dyeItem)) return ActionResult.PASS;

        if (entity instanceof ShulkerEntity shulker) {
            stack.decrementUnlessCreative(1, player);
            shulker.setVariant(Optional.ofNullable(dyeItem.getColor()));

            player.swingHand(hand, true);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private static ActionResult useBrushOnDyedShulkers(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient
                || !(stack.getItem() instanceof BrushItem)
                || !(entity instanceof ShulkerEntity shulker)
                || shulker.getVariant().isEmpty()
        ) return ActionResult.PASS;

        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        world.playSound(null, x, y, z, SoundEvents.ENTITY_ARMADILLO_BRUSH, SoundCategory.NEUTRAL,
                0.75F,
                (world.getRandom().nextFloat() * 0.2F + 0.5F)
        );
        if (!player.isCreative()) stack.damage(16, player, EquipmentSlot.MAINHAND);

        Optional<DyeColor> variant = shulker.getVariant();
        DyeColor color = variant.get();
        Item dyeItem = DyeItem.byColor(color);
        ItemStack dyeStack = new ItemStack(dyeItem);

        ItemEntity dyeItemEntity = new ItemEntity(world, x, y+0.4, z, dyeStack);
        world.spawnEntity(dyeItemEntity);

        player.swingHand(hand, true);
        return ActionResult.SUCCESS;
    }

    private static ActionResult useOnSuspiciousBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient || !player.isSneaking() || stack.isEmpty()) return ActionResult.PASS;

        BlockPos pos = hitResult.getBlockPos();
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof BrushableBlockEntity && ((BrushableBlockEntity) blockEntity).getItem().isEmpty()) {
            ((BrushableBlockEntityAccessor) blockEntity).setItem(stack.copy());
            stack.decrement(stack.getCount());

            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_PLACE,
                    SoundCategory.PLAYERS, 2.0F, 0.5F);

            player.swingHand(hand, true);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private static ActionResult featherDamageFreeKB(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.FEATHER) && !world.isClient && entity instanceof LivingEntity e) {
            if (entity.timeUntilRegen > 0) return ActionResult.FAIL;
            player.swingHand(hand, true);

            double knockbackRes = e.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
            double strength = (knockbackRes > 1 || knockbackRes < 0) ? 0 : 0.4*(1-knockbackRes);

            e.takeKnockback(strength, player.getX() - entity.getX(), player.getZ() - entity.getZ());
            e.timeUntilRegen = 10;
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }

    private static boolean isPlayerHead(BlockState blockState) {
        return blockState.isOf(Blocks.PLAYER_HEAD) || blockState.isOf(Blocks.PLAYER_WALL_HEAD);
    }

    private static void updateSkullTexture(SkullBlockEntity skullEntity, String playerName, World world) {
        GameProfile profile = new GameProfile(Uuids.getOfflinePlayerUuid(playerName), playerName);

        SkullBlockEntity.fetchProfileByName(playerName).thenAccept(optionalProfile -> {
            world.getServer().execute(() -> {
                ProfileComponent profileComponent;
                if (optionalProfile.isPresent()) {
                    GameProfile gameProfile = optionalProfile.get();
                    profileComponent = new ProfileComponent(
                            Optional.of(gameProfile.getName()),
                            Optional.of(gameProfile.getId()),
                            gameProfile.getProperties()
                    );
                } else {
                    profileComponent = new ProfileComponent(
                            Optional.of(profile.getName()),
                            Optional.of(profile.getId()),
                            profile.getProperties()
                    );
                }

                skullEntity.setOwner(profileComponent);
                skullEntity.markDirty();
                world.updateListeners(skullEntity.getPos(), world.getBlockState(skullEntity.getPos()),
                        world.getBlockState(skullEntity.getPos()), 3);
            });
        });
    }
}

