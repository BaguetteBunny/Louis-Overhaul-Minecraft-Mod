package net.louis.overhaulmod.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Items.class)
public abstract class ItemsMixin {
    @Shadow @Final @Mutable public static Item MUSHROOM_STEW;
    @Shadow @Final @Mutable public static Item BEETROOT_SOUP;
    @Shadow @Final @Mutable public static Item RABBIT_STEW;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void makeStewsStackable(CallbackInfo ci) {
        Registry.register(Registries.ITEM, Identifier.ofVanilla("mushroom_stew"),
                MUSHROOM_STEW = new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                        .nutrition(6)
                        .saturationModifier(0.6f)
                        .snack()
                        .build()))
        );
        Registry.register(Registries.ITEM, Identifier.ofVanilla("beetroot_soup"),
                BEETROOT_SOUP = new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                        .nutrition(6)
                        .saturationModifier(0.6f)
                        .snack()
                        .build()))
        );
        Registry.register(Registries.ITEM, Identifier.ofVanilla("rabbit_stew"),
                RABBIT_STEW = new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                        .nutrition(10)
                        .saturationModifier(0.6f)
                        .snack()
                        .build()))
        );
    }
}
