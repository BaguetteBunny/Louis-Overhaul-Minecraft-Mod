package net.louis.overhaulmod.recipe;

import net.minecraft.recipe.CuttingRecipe;

public class SawmillRecipeSerializer extends CuttingRecipe.Serializer<SawmillRecipe> {
    public SawmillRecipeSerializer() {
        super(SawmillRecipe::new);
    }
}
