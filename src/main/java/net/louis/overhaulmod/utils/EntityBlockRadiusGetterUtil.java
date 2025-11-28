package net.louis.overhaulmod.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityBlockRadiusGetterUtil {
    public static List<LivingEntity> getEntitiesInRadius(World world, Vec3d pos, double radius) {
        Box box = new Box(
                pos.x - radius, pos.y - radius, pos.z - radius,
                pos.x + radius, pos.y + radius, pos.z + radius
        );

        return world.getEntitiesByClass(
                LivingEntity.class,
                box,
                entity -> entity.squaredDistanceTo(pos) <= radius * radius
        );
    }

    public static List<BlockPos> getBlocksInRadius(World world, BlockPos center, int radius) {
        List<BlockPos> result = new ArrayList<>();
        int r2 = radius * radius;

        for (int dx = -radius; dx <= radius; dx++)
            for (int dy = -radius; dy <= radius; dy++)
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = center.add(dx, dy, dz);
                    if (center.getSquaredDistance(pos) <= r2) result.add(pos);
                }

        return result;
    }

    public static void replaceBlocksInRadius(World world, BlockPos center, int radius, Map<Block, Block> replacements, boolean disableWaterLog) {
        int r2 = radius * radius;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = center.add(dx, dy, dz);
                    if (center.getSquaredDistance(pos) > r2) continue;
                    BlockState state = world.getBlockState(pos);
                    Block replacement = replacements.get(state.getBlock());

                    if (replacement != null) {
                        if (disableWaterLog)
                            try {world.setBlockState(pos, replacement.getDefaultState().with(Properties.WATERLOGGED, false));}
                            catch (Exception e) {world.setBlockState(pos, replacement.getDefaultState());}

                        else
                            world.setBlockState(pos, replacement.getDefaultState());
                    }
                }
            }
        }
    }
}
