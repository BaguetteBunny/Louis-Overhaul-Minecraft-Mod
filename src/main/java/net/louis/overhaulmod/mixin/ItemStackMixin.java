package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.utils.EnchantmentCapRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    ItemStack stack = (ItemStack)(Object)this;

    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void addEnchantmentDescriptions(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        if (!(stack.getItem() instanceof EnchantedBookItem)) return;

        ItemEnchantmentsComponent enchantments = stack.get(DataComponentTypes.STORED_ENCHANTMENTS);
        if (enchantments == null || enchantments.isEmpty()) return;

        List<Text> tooltip = cir.getReturnValue();
        List<Text> newTooltip = new ArrayList<>();

        for (Text line : tooltip) {
            newTooltip.add(line);
            String lineString = line.getString();

            enchantments.getEnchantments().forEach(enchantment -> {
                String enchantName = Enchantment.getName(enchantment, enchantments.getLevel(enchantment)).getString();

                if (lineString.contains(enchantName)) {
                    Identifier id = enchantment.getKey().get().getValue();
                    String descKey = "enchantment." + id.getNamespace() + "." + id.getPath() + ".desc";

                    newTooltip.add(Text.literal("• ")
                            .append(Text.translatable(descKey))
                            .formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
                }
            });
        }

        tooltip.clear();
        tooltip.addAll(newTooltip);
    }

    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void addSeasoningDescription(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        if (!stack.getComponents().contains(ModComponents.SEASONING)) return;

        List<Text> tooltip = cir.getReturnValue();
        List<Text> newTooltip = new ArrayList<>();

        newTooltip.add(Text.literal("• Seasoning: ").append(stack.getComponents().get(ModComponents.SEASONING).getName()).formatted(Formatting.GOLD));

        tooltip.addAll(newTooltip);
    }

    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void addGlowPulsateTrimTooltip(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        if (!stack.getComponents().contains(ModComponents.GLOW_AND_PULSATE)) return;

        List<Text> tooltip = cir.getReturnValue();
        Text newTooltip;

        if (Boolean.TRUE.equals(stack.getComponents().get(ModComponents.GLOW_AND_PULSATE))) newTooltip = Text.literal(" Pulsate").formatted(Formatting.LIGHT_PURPLE);
        else newTooltip = Text.literal(" Glow").formatted(Formatting.BLUE);

        tooltip.add(4, newTooltip);
    }

    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void addEnchantmentCapTooltip(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        if (!EnchantmentCapRegistry.hasCap(stack.getItem())) return;

        int cap = EnchantmentCapRegistry.getCap(stack.getItem());
        ItemEnchantmentsComponent enchantments = stack.get(DataComponentTypes.ENCHANTMENTS);
        int current = enchantments != null ? enchantments.getSize() : 0;

        List<Text> tooltip = cir.getReturnValue();
        tooltip.add(1, Text.literal("Enchantments: " + current + "/" + cap)
                .formatted(current >= cap ? Formatting.RED : Formatting.DARK_GRAY));
    }

    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void obfuscateCurseNames(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        if (stack.getItem() instanceof EnchantedBookItem) return;

        List<Text> tooltip = cir.getReturnValue();
        ItemEnchantmentsComponent enchantments = stack.get(DataComponentTypes.ENCHANTMENTS);

        if (enchantments == null || enchantments.isEmpty()) return;

        for (int i = 0; i < tooltip.size(); i++) {
            Text line = tooltip.get(i);
            String lineString = line.getString();

            for (var entry : enchantments.getEnchantmentEntries()) {
                RegistryEntry<Enchantment> enchantment = entry.getKey();

                if (enchantment.isIn(EnchantmentTags.CURSE)) {
                    String enchantName = Enchantment.getName(enchantment, enchantments.getLevel(enchantment)).getString();
                    if (lineString.contains(enchantName)) {
                        MutableText newText = Text.literal("Curse of ")
                                .formatted(Formatting.RED)
                                .append(Text.literal("Something")
                                        .setStyle(Style.EMPTY
                                                .withColor(Formatting.RED)
                                                .withObfuscated(true)));

                        tooltip.set(i, newText);
                        break;
                    }
                }
            }
        }
    }
}
