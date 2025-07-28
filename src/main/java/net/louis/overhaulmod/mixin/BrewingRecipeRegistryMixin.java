package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.potion.ModPotions;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegistryMixin {
    @Inject(method = "registerDefaults", at = @At("HEAD"), cancellable = true)
    private static void overrideDefaults(BrewingRecipeRegistry.Builder builder, CallbackInfo ci) {
        ci.cancel();

        // Potion Types
        builder.registerPotionType(Items.POTION);
        builder.registerPotionType(Items.SPLASH_POTION);
        builder.registerPotionType(Items.LINGERING_POTION);

        // Potion Type Recipes
        builder.registerItemRecipe(Items.POTION, Items.GUNPOWDER, Items.SPLASH_POTION);
        builder.registerItemRecipe(Items.SPLASH_POTION, Items.DRAGON_BREATH, Items.LINGERING_POTION);

        // Placeholder Potions
        builder.registerPotionRecipe(Potions.WATER, Items.GLOWSTONE_DUST, Potions.THICK);
        builder.registerPotionRecipe(Potions.WATER, Items.REDSTONE, Potions.MUNDANE);
        builder.registerPotionRecipe(Potions.WATER, Items.NETHER_WART, Potions.AWKWARD);

        // Negative Potion Effects
        builder.registerPotionRecipe(Potions.MUNDANE, Items.TURTLE_SCUTE, Potions.SLOWNESS);
        builder.registerPotionRecipe(Potions.SLOWNESS, Items.REDSTONE, Potions.LONG_SLOWNESS);
        builder.registerPotionRecipe(Potions.SLOWNESS, Items.GLOWSTONE_DUST, Potions.STRONG_SLOWNESS);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
        builder.registerPotionRecipe(Potions.HARMING, Items.GLOWSTONE_DUST, Potions.STRONG_HARMING);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.SPIDER_EYE, Potions.POISON);
        builder.registerPotionRecipe(Potions.POISON, Items.REDSTONE, Potions.LONG_POISON);
        builder.registerPotionRecipe(Potions.POISON, Items.GLOWSTONE_DUST, Potions.STRONG_POISON);

        builder.registerPotionRecipe(Potions.MUNDANE, Items.GLOW_LICHEN, Potions.WEAKNESS); // CHANGE TO STRAY DROP
        builder.registerPotionRecipe(Potions.WEAKNESS, Items.REDSTONE, Potions.LONG_WEAKNESS);
        builder.registerPotionRecipe(Potions.WEAKNESS, Items.GLOWSTONE_DUST, ModPotions.STRONG_WEAKNESS);

        // Mid Potion Effects
        builder.registerPotionRecipe(Potions.THICK, Items.BREEZE_ROD, Potions.WIND_CHARGED);
        builder.registerPotionRecipe(Potions.THICK, Items.SLIME_BLOCK, Potions.OOZING);
        builder.registerPotionRecipe(Potions.THICK, Items.STONE, Potions.INFESTED);
        builder.registerPotionRecipe(Potions.THICK, Items.COBWEB, Potions.WEAVING);

        // Positive Potion Effects
        builder.registerPotionRecipe(Potions.AWKWARD, Items.GLOW_LICHEN, Potions.NIGHT_VISION);
        builder.registerPotionRecipe(Potions.NIGHT_VISION, Items.REDSTONE, Potions.LONG_NIGHT_VISION);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.PHANTOM_MEMBRANE, Potions.INVISIBILITY);
        builder.registerPotionRecipe(Potions.INVISIBILITY, Items.REDSTONE, Potions.LONG_INVISIBILITY);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.MAGMA_CREAM, Potions.FIRE_RESISTANCE);
        builder.registerPotionRecipe(Potions.FIRE_RESISTANCE, Items.REDSTONE, Potions.LONG_FIRE_RESISTANCE);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.RABBIT, Potions.LEAPING);
        builder.registerPotionRecipe(Potions.LEAPING, Items.REDSTONE, Potions.LONG_LEAPING);
        builder.registerPotionRecipe(Potions.LEAPING, Items.GLOWSTONE_DUST, Potions.STRONG_LEAPING);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.TURTLE_HELMET, Potions.TURTLE_MASTER);
        builder.registerPotionRecipe(Potions.TURTLE_MASTER, Items.REDSTONE, Potions.LONG_TURTLE_MASTER);
        builder.registerPotionRecipe(Potions.TURTLE_MASTER, Items.GLOWSTONE_DUST, Potions.STRONG_TURTLE_MASTER);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.SUGAR, Potions.SWIFTNESS);
        builder.registerPotionRecipe(Potions.SWIFTNESS, Items.REDSTONE, Potions.LONG_SWIFTNESS);
        builder.registerPotionRecipe(Potions.SWIFTNESS, Items.GLOWSTONE_DUST, Potions.STRONG_SWIFTNESS);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.PUFFERFISH, Potions.WATER_BREATHING);
        builder.registerPotionRecipe(Potions.WATER_BREATHING, Items.REDSTONE, Potions.LONG_WATER_BREATHING);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.GLISTERING_MELON_SLICE, Potions.HEALING);
        builder.registerPotionRecipe(Potions.HEALING, Items.GLOWSTONE_DUST, Potions.STRONG_HEALING);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.GHAST_TEAR, Potions.REGENERATION);
        builder.registerPotionRecipe(Potions.REGENERATION, Items.REDSTONE, Potions.LONG_REGENERATION);
        builder.registerPotionRecipe(Potions.REGENERATION, Items.GLOWSTONE_DUST, Potions.STRONG_REGENERATION);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.BLAZE_POWDER, Potions.STRENGTH);
        builder.registerPotionRecipe(Potions.STRENGTH, Items.REDSTONE, Potions.LONG_STRENGTH);
        builder.registerPotionRecipe(Potions.STRENGTH, Items.GLOWSTONE_DUST, Potions.STRONG_STRENGTH);

        builder.registerPotionRecipe(Potions.AWKWARD, Items.RABBIT_FOOT, Potions.LUCK);
        builder.registerPotionRecipe(Potions.LUCK, Items.REDSTONE, ModPotions.LONG_LUCK);
        builder.registerPotionRecipe(Potions.LUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_LUCK);

        // DEADBUSH -> BAD LUCK
        // ADD VARIATIONS HERE

        // INK SACK -> BLINDNESS
        // ADD VARIATIONS HERE

        // DECAYED FLESH -> HUNGER
        // ADD VARIATIONS HERE

        // WITHER ROSE -> WITHER
        // ADD VARIATIONS HERE

        // SHULKER SHELL -> LEVITATION
        // ADD VARIATIONS HERE

        // RAW COPPER -> NAUSEA
        // ADD VARIATIONS HERE

        // LLAMA SPIT -> HASTE
        // ADD VARIATIONS HERE

        builder.registerPotionRecipe(Potions.AWKWARD, ModItems.BAT_FANG, Potions.SLOW_FALLING);
        builder.registerPotionRecipe(Potions.SLOW_FALLING, Items.REDSTONE, Potions.LONG_SLOW_FALLING);


    }
}

