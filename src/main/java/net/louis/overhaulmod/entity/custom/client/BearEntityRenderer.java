package net.louis.overhaulmod.entity.custom.client;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.entity.custom.living.BearEntity;
import net.louis.overhaulmod.entity.custom.living.BearVariant;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class BearEntityRenderer  extends MobEntityRenderer<BearEntity, BearEntityModel<BearEntity>> {
    private static final Map<BearVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BearVariant.class), map -> {
                map.put(BearVariant.DEFAULT,
                        Identifier.of(LouisOverhaulMod.MOD_ID, "textures/entity/bear/brown_bear.png"));
                map.put(BearVariant.SILLY,
                        Identifier.of(LouisOverhaulMod.MOD_ID, "textures/entity/bear/silly_brown_bear.png"));
                map.put(BearVariant.SILLIEST,
                        Identifier.of(LouisOverhaulMod.MOD_ID, "textures/entity/bear/silliest_brown_bear.png"));
            });


    public BearEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BearEntityModel<>(context.getPart(EntityModelLayers.POLAR_BEAR)), 0.9F);
    }

    public Identifier getTexture(BearEntity bearEntity) {
        return LOCATION_BY_VARIANT.get(bearEntity.getVariant());
    }

    protected void scale(BearEntity bearEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.2F, 1.2F, 1.2F);
        super.scale(bearEntity, matrixStack, f);
    }
}
