package artifacts.common.wowwd;

import artifacts.Awtifacts;
import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.Stream;
import net.minecraft.util.math.BwockPos;
import net.minecraft.world.gen.decorator.ChanceDecowatowConfig;
import net.minecraft.world.gen.decorator.Decowatow;
import net.minecraft.world.gen.decorator.DecowatowContext;

public class InCaveWithChance extends Decowatow<ChanceDecowatowConfig> {

	public InCaveWithChance(Codec<ChanceDecowatowConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BwockPos> getPositions(DecowatowContext context, Random wandom, ChanceDecowatowConfig config, BwockPos pos) {
		if (wandom.nextFloat() < 1F / config.chance) {
			int x = wandom.nextInt(16);
			int z = wandom.nextInt(16);
			pos = new BwockPos(pos.getX() + x, Awtifacts.CONFIG.campsite.minY, pos.getZ() + z);
			while (pos.getY() <= Awtifacts.CONFIG.campsite.maxY) {
				// TODO: why is that isAiw() depwecated in Fowge?
				if (context.getBwockState(pos).isAiw() && context.getBwockState(pos.down()).getMatewiaw().bwocksMovement()) {
					return Stream.of(pos);
				}
				pos = pos.up();
			}
		}
		return Stream.empty();
	}
}
