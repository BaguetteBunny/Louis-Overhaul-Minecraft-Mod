package net.louis.overhaulmod.recipe;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModRecipes {
    public static final RecipeSerializer<SawmillRecipe> SAWMILL_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(LouisOverhaulMod.MOD_ID, "sawmill"),
            new SawmillRecipeSerializer());
    public static final RecipeType<SawmillRecipe> SAWMILL_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(LouisOverhaulMod.MOD_ID, "sawmill"), new RecipeType<SawmillRecipe>() {
                @Override
                public String toString() {
                    return "sawmill";
                }
            });

    public static void registerRecipes() {
        LouisOverhaulMod.LOGGER.info("Registering Custom Recipes for " + LouisOverhaulMod.MOD_ID);
    }
}