package net.louis.overhaulmod.events;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtOps;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Uuids;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class ModUseEvents {

    public static void registerMain() {
        UseBlockCallback.EVENT.register(ModUseEvents::oxidizeCopperWithClock);
        UseBlockCallback.EVENT.register(ModUseEvents::retexturePlayerHead);
        UseItemCallback.EVENT.register(ModUseEvents::getLlamaSpitBottle);
    }

    public static void registerStew() {
        UseItemCallback.EVENT.register(ModUseEvents::useMushroomStew);
        UseItemCallback.EVENT.register(ModUseEvents::useRabbitStew);
        UseItemCallback.EVENT.register(ModUseEvents::useFishStew);
        UseEntityCallback.EVENT.register(ModUseEvents::useRottenStew);
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

    private static TypedActionResult<ItemStack> useMushroomStew(PlayerEntity player, World world, Hand hand) {
        if (world.isClient) return TypedActionResult.pass(player.getStackInHand(hand));

        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(Items.MUSHROOM_STEW)) {
            double reach = 3.0D;
            Vec3d start = player.getCameraPosVec(1.0F);
            Vec3d look = player.getRotationVec(1.0F);
            Vec3d end = start.add(look.multiply(reach));
            Box box = player.getBoundingBox().stretch(look.multiply(reach)).expand(1.0D);
            CowEntity targetCow = null;
            double closestDistance = reach * reach;

            for (Entity entity : world.getOtherEntities(player, box)) {
                if (!(entity instanceof CowEntity cow) || entity instanceof MooshroomEntity) continue;
                Box entityBox = cow.getBoundingBox().expand(0.3D);
                Optional<Vec3d> optional = entityBox.raycast(start, end);
                if (optional.isPresent()) {
                    double distance = start.squaredDistanceTo(optional.get());
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        targetCow = cow;
                    }
                }
            }

            if (targetCow != null) {
                int randomNum = (int)(Math.random() * 11);
                double x = targetCow.getX();
                double y = targetCow.getY();
                double z = targetCow.getZ();
                if (!player.getAbilities().creativeMode) stack.decrement(1);

                if (randomNum == 1) {
                    world.playSound(null, targetCow.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                            SoundCategory.PLAYERS, 1.0F, 2.0F);

                    float pitch = targetCow.getPitch();
                    float yaw = targetCow.getYaw();
                    boolean isBaby = targetCow.isBaby();
                    targetCow.discard();

                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.HEART,
                            x + 0,
                            y + 1,
                            z + 0,
                            10,
                            0.5, 0.5, 0.5,
                            0.2
                    );

                    MooshroomEntity mooshroom = EntityType.MOOSHROOM.create(world);
                    mooshroom.refreshPositionAndAngles(x, y, z, yaw, pitch);
                    mooshroom.setBaby(isBaby);
                    world.spawnEntity(mooshroom);

                    return TypedActionResult.success(stack, world.isClient());
                }
                else {
                    world.playSound(null, targetCow.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
                            SoundCategory.PLAYERS, 1.0F, 2.0F);
                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.SMOKE,
                            x + 0,
                            y + 1,
                            z + 0,
                            10,
                            0.5, 0.5, 0.5,
                            0.05
                    );
                }
            }
        }
        return TypedActionResult.pass(player.getStackInHand(hand));

    }

    private static TypedActionResult<ItemStack> useRabbitStew(PlayerEntity player, World world, Hand hand) {
        if (world.isClient) return TypedActionResult.pass(player.getStackInHand(hand));

        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(Items.RABBIT_STEW)) {
            double reach = 3.0D;
            Vec3d start = player.getCameraPosVec(1.0F);
            Vec3d look = player.getRotationVec(1.0F);
            Vec3d end = start.add(look.multiply(reach));
            Box box = player.getBoundingBox().stretch(look.multiply(reach)).expand(1.0D);
            RabbitEntity targetRabbit = null;
            double closestDistance = reach * reach;

            for (Entity entity : world.getOtherEntities(player, box)) {
                if (!(entity instanceof RabbitEntity rabbit)) continue;
                if (rabbit.getVariant() == RabbitEntity.RabbitType.EVIL) continue;
                Box entityBox = rabbit.getBoundingBox().expand(0.3D);
                Optional<Vec3d> optional = entityBox.raycast(start, end);
                if (optional.isPresent()) {
                    double distance = start.squaredDistanceTo(optional.get());
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        targetRabbit = rabbit;
                    }
                }
            }

            if (targetRabbit != null) {
                int randomNum = (int)(Math.random() * 2);
                double x = targetRabbit.getX();
                double y = targetRabbit.getY();
                double z = targetRabbit.getZ();
                if (!player.getAbilities().creativeMode) stack.decrement(1);

                if (randomNum == 1) {
                    world.playSound(null, targetRabbit.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                            SoundCategory.PLAYERS, 1.0F, 2.0F);

                    targetRabbit.setVariant(RabbitEntity.RabbitType.EVIL);

                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.ANGRY_VILLAGER,
                            x + 0,
                            y + 1,
                            z + 0,
                            10,
                            0.5, 0.5, 0.5,
                            0.2
                    );
                    return TypedActionResult.success(stack, world.isClient());
                }
                else {
                    world.playSound(null, targetRabbit.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
                            SoundCategory.PLAYERS, 1.0F, 2.0F);
                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.SMOKE,
                            x + 0,
                            y + 1,
                            z + 0,
                            10,
                            0.5, 0.5, 0.5,
                            0.05
                    );
                }
            }
        }
        return TypedActionResult.pass(player.getStackInHand(hand));
    }

    private static TypedActionResult<ItemStack> useFishStew(PlayerEntity player, World world, Hand hand) {
        if (world.isClient) return TypedActionResult.pass(player.getStackInHand(hand));

        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(ModItems.FISH_STEW)) {
            double reach = 3.0D;
            Vec3d start = player.getCameraPosVec(1.0F);
            Vec3d look = player.getRotationVec(1.0F);
            Vec3d end = start.add(look.multiply(reach));
            Box box = player.getBoundingBox().stretch(look.multiply(reach)).expand(1.0D);
            DolphinEntity targetDolphin = null;
            double closestDistance = reach * reach;

            for (Entity entity : world.getOtherEntities(player, box)) {
                if (!(entity instanceof DolphinEntity dolphin)) continue;
                Box entityBox = dolphin.getBoundingBox().expand(0.3D);
                Optional<Vec3d> optional = entityBox.raycast(start, end);
                if (optional.isPresent()) {
                    double distance = start.squaredDistanceTo(optional.get());
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        targetDolphin = dolphin;
                    }
                }
            }

            if (targetDolphin != null) {
                int randomNum = (int)(Math.random() * 3);
                double x = targetDolphin.getX();
                double y = targetDolphin.getY();
                double z = targetDolphin.getZ();
                if (!player.getAbilities().creativeMode) stack.decrement(1);

                if (randomNum == 1) {
                    world.playSound(null, targetDolphin.getBlockPos(), SoundEvents.ENTITY_DOLPHIN_PLAY,
                            SoundCategory.PLAYERS, 1.0F, 0.5F);
                    world.playSound(null, targetDolphin.getBlockPos(), SoundEvents.ENTITY_DOLPHIN_SPLASH,
                            SoundCategory.PLAYERS, 0.5F, 0.5F);

                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.DOLPHIN,
                            x + 0,
                            y + 0.5,
                            z + 0,
                            20,
                            0.5, 0.5, 0.5,
                            0.2
                    );
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 36000, 0));
                    return TypedActionResult.success(stack, world.isClient());
                }
                else {
                    world.playSound(null, targetDolphin.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
                            SoundCategory.PLAYERS, 1.0F, 2.0F);
                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.SMOKE,
                            x + 0,
                            y + 1,
                            z + 0,
                            10,
                            0.5, 0.5, 0.5,
                            0.05
                    );
                }
            }
        }
        return TypedActionResult.pass(player.getStackInHand(hand));
    }

    private static ActionResult useRottenStew(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (world.isClient) return ActionResult.PASS;

        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(ModItems.ROTTEN_STEW) && entity instanceof VillagerEntity targetVillager) {
            int randomNum = (int) (Math.random() * 5);
            double x = targetVillager.getX();
            double y = targetVillager.getY();
            double z = targetVillager.getZ();
            if (!player.getAbilities().creativeMode) stack.decrement(1);

            if (randomNum == 1) {
                world.playSound(null, targetVillager.getBlockPos(), SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED,
                        SoundCategory.PLAYERS, 1.0F, 0.5F);
                world.playSound(null, targetVillager.getBlockPos(), SoundEvents.ENTITY_VILLAGER_NO,
                        SoundCategory.PLAYERS, 0.5F, 0.5F);

                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.ANGRY_VILLAGER,
                        x + 0,
                        y + 0.5,
                        z + 0,
                        10,
                        0.5, 0.5, 0.5,
                        0.2
                );
                ZombieVillagerEntity zombieVillagerEntity = targetVillager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
                if (zombieVillagerEntity != null) {
                    ServerWorldAccess access = (ServerWorldAccess) world;

                    zombieVillagerEntity.initialize(
                            access, world.getLocalDifficulty(zombieVillagerEntity.getBlockPos()), SpawnReason.CONVERSION, new ZombieEntity.ZombieData(false, true));
                    zombieVillagerEntity.setVillagerData(targetVillager.getVillagerData());
                    zombieVillagerEntity.setGossipData(targetVillager.getGossip().serialize(NbtOps.INSTANCE));
                    zombieVillagerEntity.setOfferData(targetVillager.getOffers().copy());
                    zombieVillagerEntity.setXp(targetVillager.getExperience());
                    zombieVillagerEntity.refreshPositionAndAngles(x, y, z, targetVillager.getYaw(), targetVillager.getPitch());

                    targetVillager.discard();

                    return ActionResult.SUCCESS;
                } else {
                    world.playSound(null, targetVillager.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
                                SoundCategory.PLAYERS, 1.0F, 2.0F);
                    ((ServerWorld) world).spawnParticles(
                            ParticleTypes.SMOKE,
                            x + 0,
                            y + 1,
                            z + 0,
                            10,
                            0.5, 0.5, 0.5,
                            0.05
                    );
                }
            }
        } return ActionResult.PASS;
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

