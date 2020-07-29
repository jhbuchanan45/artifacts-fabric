package artifacts.common.world;

import artifacts.common.config.ModConfig;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import java.util.Random;
import java.util.stream.Stream;

public class InCaveWithChance extends Decorator<ChanceDecoratorConfig> {

    public InCaveWithChance(Codec<ChanceDecoratorConfig> codec) {
        super(codec);
    }

    @Override
    public Stream<BlockPos> getPositions(WorldAccess world, ChunkGenerator generator, Random random, ChanceDecoratorConfig config, BlockPos pos) {
        if (random.nextFloat() < 1F / config.chance) {
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            pos = new BlockPos(pos.getX() + x, ModConfig.campsite.minY, pos.getZ() + z);
            while (pos.getY() <= ModConfig.campsite.maxY) {
                // TODO: why is that isAir() deprecated in Forge?
                if (world.getBlockState(pos).isAir() && world.getBlockState(pos.down()).getMaterial().blocksMovement()) {
                    return Stream.of(pos);
                }
                pos = pos.up();
            }
        }
        return Stream.empty();
    }
}
