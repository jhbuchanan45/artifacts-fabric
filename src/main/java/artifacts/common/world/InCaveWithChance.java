package artifacts.common.world;

import artifacts.Artifacts;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorContext;

import java.util.Random;
import java.util.stream.Stream;

public class InCaveWithChance extends Decorator<ChanceDecoratorConfig> {

	public InCaveWithChance(Codec<ChanceDecoratorConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(DecoratorContext context, Random random, ChanceDecoratorConfig config, BlockPos pos) {
		if (random.nextFloat() < 1F / config.chance) {
			int x = random.nextInt(16);
			int z = random.nextInt(16);
			pos = new BlockPos(pos.getX() + x, Artifacts.CONFIG.campsite.minY, pos.getZ() + z);
			while (pos.getY() <= Artifacts.CONFIG.campsite.maxY) {
				// TODO: why is that isAir() deprecated in Forge?
				if (context.getBlockState(pos).isAir() && context.getBlockState(pos.down()).getMaterial().blocksMovement()) {
					return Stream.of(pos);
				}
				pos = pos.up();
			}
		}
		return Stream.empty();
	}
}
