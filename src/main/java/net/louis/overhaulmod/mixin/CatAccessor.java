package net.louis.overhaulmod.mixin;

import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(CatEntity.class)
public interface CatAccessor {
    @Invoker("setCollarColor")
    void callSetCollarColor(DyeColor color);
}
