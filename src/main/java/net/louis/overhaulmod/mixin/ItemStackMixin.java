package net.louis.overhaulmod.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
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

    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void addEnchantmentDescriptions(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        ItemStack stack = (ItemStack)(Object)this;

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
}
