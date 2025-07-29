package net.louis.overhaulmod.item;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.mixin.WolfAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;


public class PetRecoveryCompass extends Item {
    public PetRecoveryCompass(Settings settings) {
        super(settings);
    }

    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(
                ModItems.PET_RECOVERY_COMPASS,
                Identifier.of("full"),
                (stack, world, entity, seed) -> {
                    String uuid = stack.get(ModComponents.MOB_UUID);
                    return (uuid != null && !uuid.isEmpty()) ? 1.0f : 0.0f;
                }
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.get(ModComponents.MOB_UUID) != null) {
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.literal("Stored Pet: " + stack.get(ModComponents.MOB_NAME)).formatted(Formatting.BLUE));
            tooltip.add(Text.literal(" "));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();

        if (world.getBlockState(pos).isOf(Blocks.LODESTONE)) {
            String uuidString = stack.get(ModComponents.MOB_UUID);

            if (uuidString != null && !uuidString.isEmpty()) {
                try {
                    UUID petUUID = UUID.fromString(uuidString);
                    if (isPetDead(world, petUUID)) {
                        if (!world.isClient && world instanceof ServerWorld serverWorld) {
                            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                            double x = pos.getX() + 0.5;
                            double y = pos.getY() + 1;
                            double z = pos.getZ() + 0.5;
                            lightning.refreshPositionAfterTeleport(x, y, z);
                            world.spawnEntity(lightning);


                            WolfEntity wolf = EntityType.WOLF.create(world);
                            wolf.setTamed(true, true);
                            wolf.setUuid(UUID.fromString(uuidString));
                            wolf.setOwnerUuid(player.getUuid());
                            wolf.setBaby(stack.get(ModComponents.MOB_IS_BABY));
                            wolf.setVariant(stack.get(ModComponents.WOLF_VARIANT));
                            ((WolfAccessor) wolf).callSetCollarColor(stack.get(ModComponents.MOB_COLLAR_COLOR));

                            String targetName = stack.get(ModComponents.MOB_NAME);
                            wolf.setCustomName(Text.literal(targetName));
                            if (!targetName.equals("Wolf")) {
                                wolf.setCustomNameVisible(true);
                            }

                            wolf.refreshPositionAndAngles(x, y, z, -player.getYaw(), 90.0f);

                            world.spawnEntity(wolf);
                            player.setStackInHand(context.getHand(), ItemStack.EMPTY);
                            world.playSound(null, pos, SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.BLOCKS, 1f, 1f);
                            world.playSound(null, pos, SoundEvents.ITEM_TOTEM_USE, SoundCategory.BLOCKS, 1f, 1f);
                        }

                        return ActionResult.SUCCESS;
                    }
                } catch (IllegalArgumentException e) {
                }
            } else {
                world.playSound(null, pos, SoundEvents.BLOCK_CONDUIT_DEACTIVATE, SoundCategory.BLOCKS, 1f, 1f);
            }
        }

        return ActionResult.PASS;
    }
    private boolean isPetDead(World world, UUID petUUID) {
        if (world instanceof ServerWorld serverWorld) {
            return serverWorld.getEntity(petUUID) == null;
        }
        return false;
    }
}

