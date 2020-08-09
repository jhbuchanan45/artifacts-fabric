package artifacts.common.world;

import artifacts.Artifacts;
import artifacts.common.config.ModConfig;
import artifacts.common.entity.MimicEntity;
import artifacts.common.init.Entities;
import artifacts.common.init.LootTables;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CampsiteFeature extends Feature<DefaultFeatureConfig> {

	public static final BlockStateProvider CRAFTING_STATION_PROVIDER = new WeightedBlockStateProvider()
			.addState(Blocks.CRAFTING_TABLE.getDefaultState(), 5)
			.addState(Blocks.FURNACE.getDefaultState().with(FurnaceBlock.LIT, false), 5)
			.addState(Blocks.BLAST_FURNACE.getDefaultState().with(BlastFurnaceBlock.LIT, false), 5)
			.addState(Blocks.SMOKER.getDefaultState().with(SmokerBlock.LIT, false), 5)
			.addState(Blocks.SMITHING_TABLE.getDefaultState(), 5)
			.addState(Blocks.FLETCHING_TABLE.getDefaultState(), 5)
			.addState(Blocks.CARTOGRAPHY_TABLE.getDefaultState(), 5)
			.addState(Blocks.ANVIL.getDefaultState(), 2)
			.addState(Blocks.CHIPPED_ANVIL.getDefaultState(), 2)
			.addState(Blocks.DAMAGED_ANVIL.getDefaultState(), 1);

	public static final BlockStateProvider DECORATION_PROVIDER = new WeightedBlockStateProvider()
			.addState(Blocks.LANTERN.getDefaultState(), 5)
			.addState(Blocks.TORCH.getDefaultState(), 3)
			.addState(Blocks.REDSTONE_TORCH.getDefaultState(), 3)
			.addState(Blocks.CAKE.getDefaultState(), 1)
			.addState(Blocks.BREWING_STAND.getDefaultState(), 4);

	public static final BlockStateProvider ORE_PROVIDER = new WeightedBlockStateProvider()
			.addState(Blocks.IRON_ORE.getDefaultState(), 6)
			.addState(Blocks.REDSTONE_ORE.getDefaultState(), 6)
			.addState(Blocks.LAPIS_ORE.getDefaultState(), 6)
			.addState(Blocks.GOLD_ORE.getDefaultState(), 4)
			.addState(Blocks.DIAMOND_ORE.getDefaultState(), 2)
			.addState(Blocks.EMERALD_ORE.getDefaultState(), 1);

	public static final BlockStateProvider CAMPFIRE_PROVIDER = new WeightedBlockStateProvider()
			.addState(Blocks.CAMPFIRE.getDefaultState().with(CampfireBlock.LIT, false), 12)
			.addState(Blocks.CAMPFIRE.getDefaultState().with(CampfireBlock.LIT, true), 3)
			.addState(Blocks.SOUL_CAMPFIRE.getDefaultState().with(CampfireBlock.LIT, true), 1);

	public static final BlockStateProvider LANTERN_PROVIDER = new WeightedBlockStateProvider()
			.addState(Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, true), 6)
			.addState(Blocks.SOUL_LANTERN.getDefaultState().with(LanternBlock.HANGING, true), 2)
			.addState(Blocks.END_ROD.getDefaultState().with(EndRodBlock.FACING, Direction.DOWN), 1)
			.addState(Blocks.SHROOMLIGHT.getDefaultState(), 1)
			.addState(Blocks.GLOWSTONE.getDefaultState(), 1);

	public CampsiteFeature() {
		super(DefaultFeatureConfig.CODEC);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor manager, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		List<BlockPos> positions = new ArrayList<>();
		BlockPos.stream(pos.add(-3, 0, -3), pos.add(3, 0, 3)).forEach((blockPos -> positions.add(blockPos.toImmutable())));
		positions.remove(pos);
		positions.removeIf(currentPos -> !world.isAir(currentPos));
		positions.removeIf(currentPos -> !world.getBlockState(currentPos.down()).getMaterial().blocksMovement());
		if (positions.size() < 12) {
			return false;
		}
		Collections.shuffle(positions);

		if (random.nextFloat() < Artifacts.CONFIG.campsite.oreChance) {
			generateOreVein(world, pos.down(), random);
		}

		generateLightSource(world, pos, random);

		generateContainer(world, positions.remove(0), random);
		if (random.nextBoolean()) {
			generateContainer(world, positions.remove(0), random);
		}

		generateCraftingStation(world, positions.remove(0), random);
		if (random.nextBoolean()) {
			generateCraftingStation(world, positions.remove(0), random);
		}

		return true;
	}

	public void generateLightSource(WorldAccess world, BlockPos pos, Random random) {
		if (random.nextInt(4) != 0) {
			BlockPos currentPos = pos;
			while (currentPos.getY() - pos.getY() < 8 && world.isAir(currentPos.up())) {
				currentPos = currentPos.up();
			}
			if (currentPos.getY() - pos.getY() > 2 && !world.isAir(currentPos.up())) {
				world.setBlockState(currentPos, LANTERN_PROVIDER.getBlockState(random, currentPos), 2);
				return;
			}
		}
		world.setBlockState(pos, CAMPFIRE_PROVIDER.getBlockState(random, pos), 2);
	}

	public void generateContainer(WorldAccess world, BlockPos pos, Random random) {
		if (random.nextFloat() < Artifacts.CONFIG.campsite.mimicChance) {
			MimicEntity mimic = Entities.MIMIC.create(world.getWorld());
			if (mimic != null) {
				mimic.setDormant();
				mimic.updatePosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
				world.spawnEntity(mimic);
			}
		} else {
			if (random.nextBoolean()) {
				if (random.nextInt(5) == 0) {
					world.setBlockState(pos, Blocks.TRAPPED_CHEST.getDefaultState().with(ChestBlock.FACING, Direction.Type.HORIZONTAL.random(random)), 2);
					world.setBlockState(pos.down(), Blocks.TNT.getDefaultState(), 0);
				} else {
					// TODO: random wooden chest with tag
					world.setBlockState(pos, Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.Type.HORIZONTAL.random(random)), 2);
				}
			} else {
				world.setBlockState(pos, Blocks.BARREL.getDefaultState().with(BarrelBlock.FACING, Direction.random(random)), 2);
			}
			BlockEntity container = world.getBlockEntity(pos);
			if (container instanceof LootableContainerBlockEntity) {
				((LootableContainerBlockEntity) container).setLootTable(LootTables.CAMPSITE_CHEST, random.nextLong());
			}
		}
	}

	public void generateCraftingStation(WorldAccess world, BlockPos pos, Random random) {
		BlockState state = CRAFTING_STATION_PROVIDER.getBlockState(random, pos);
		world.setBlockState(pos, state, 0);
		if (random.nextBoolean() && world.isAir(pos.up())) {
			generateDecoration(world, pos.up(), random);
		}

	}

	public void generateDecoration(WorldAccess world, BlockPos pos, Random random) {
		if (random.nextInt(3) == 0) {
			world.setBlockState(pos, DECORATION_PROVIDER.getBlockState(random, pos), 2);
		} else {
			world.setBlockState(pos, BlockTags.FLOWER_POTS.getRandom(random).getDefaultState(), 2);
		}
	}

	public void generateOreVein(WorldAccess world, BlockPos pos, Random random) {
		BlockState ore = ORE_PROVIDER.getBlockState(random, pos);
		List<BlockPos> positions = new ArrayList<>();
		positions.add(pos);
		for (int i = 4 + random.nextInt(12); i > 0; i--) {
			pos = positions.remove(0);
			world.setBlockState(pos, ore, 2);
			for (Direction direction : Direction.Type.HORIZONTAL) {
				if (world.getBlockState(pos.offset(direction)).getMaterial().blocksMovement() && !world.getBlockState(pos.offset(direction).up()).getMaterial().blocksMovement()) {
					positions.add(pos.offset(direction));
				}
			}
			if (positions.size() == 0) {
				return;
			}
		}
	}
}
