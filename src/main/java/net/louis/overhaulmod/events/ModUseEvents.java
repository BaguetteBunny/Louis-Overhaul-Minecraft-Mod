package net.louis.overhaulmod.events;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
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
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

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
                if (!(entity instanceof CowEntity cow)) continue;
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

                if (!player.getAbilities().creativeMode) stack.decrement(1);
                world.playSound(null, targetCow.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                        SoundCategory.PLAYERS, 1.0F, 2.0F);

                double x = targetCow.getX();
                double y = targetCow.getY();
                double z = targetCow.getZ();
                float pitch = targetCow.getPitch();
                float yaw = targetCow.getYaw();
                boolean isBaby = targetCow.isBaby();
                targetCow.discard();

                MooshroomEntity mooshroom = EntityType.MOOSHROOM.create(world);
                mooshroom.refreshPositionAndAngles(x, y, z, yaw, pitch);
                mooshroom.setBaby(isBaby);
                world.spawnEntity(mooshroom);

                return TypedActionResult.success(stack, world.isClient());
            }
        }
        return TypedActionResult.pass(player.getStackInHand(hand));

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

