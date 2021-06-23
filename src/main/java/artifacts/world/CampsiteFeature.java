package artifacts.world;

import artifacts.Artifacts;
import artifacts.entity.MimicEntity;
import artifacts.init.Entities;
import artifacts.init.LootTables;
import net.minecraft.block.*;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
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
	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig featureConfig) {
		List<BlockPos> positions = new ArrayList<>();
		BlockPos.stream(blockPos.add(-3, 0, -3), blockPos.add(3, 0, 3)).forEach((pos -> positions.add(pos.toImmutable())));
		positions.remove(blockPos);
		positions.removeIf(currentPos -> !world.isAir(currentPos));
		positions.removeIf(currentPos -> !world.getBlockState(currentPos.down()).getMaterial().blocksMovement());
		if (positions.size() < 12) {
			return false;
		}
		Collections.shuffle(positions);

		if (random.nextInt(100) < Artifacts.CONFIG.worldgen.campsite.oreChance) {
			generateOreVein(world, blockPos.down(), random);
		}

		generateLightSource(world, blockPos, random);

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

	public void generateLightSource(StructureWorldAccess world, BlockPos pos, Random random) {
		if (random.nextInt(4) != 0) {
			BlockPos currentPos = pos;
			while (currentPos.getY() - pos.getY() < 8 && world.isAir(currentPos.up())) {
				currentPos = currentPos.up();
			}
			if (currentPos.getY() - pos.getY() > 2 && !world.isAir(currentPos.up())) {
				this.setBlockState(world, currentPos, LANTERN_PROVIDER.getBlockState(random, currentPos));
				return;
			}
		}
		this.setBlockState(world, pos, CAMPFIRE_PROVIDER.getBlockState(random, pos));
	}

	public void generateContainer(StructureWorldAccess world, BlockPos pos, Random random) {
		if (random.nextInt(100) < Artifacts.CONFIG.worldgen.campsite.mimicChance) {
			MimicEntity mimic = Entities.MIMIC.create(world.toServerWorld());
			if (mimic != null) {
				mimic.setDormant();
				mimic.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
				world.spawnEntity(mimic);
			}
		} else {
			if (random.nextBoolean()) {
				if (random.nextInt(5) == 0) {
					this.setBlockState(world, pos, Blocks.TRAPPED_CHEST.getDefaultState().with(ChestBlock.FACING, Direction.Type.HORIZONTAL.random(random)));
					this.setBlockState(world, pos.down(), Blocks.TNT.getDefaultState());
				} else {
					// TODO: random wooden chest with tag
					this.setBlockState(world, pos, Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.Type.HORIZONTAL.random(random)));
				}
			} else {
				this.setBlockState(world, pos, Blocks.BARREL.getDefaultState().with(BarrelBlock.FACING, Direction.random(random)));
			}
			LootableContainerBlockEntity.setLootTable(world, random, pos, LootTables.CAMPSITE_CHEST);
		}
	}

	public void generateCraftingStation(StructureWorldAccess world, BlockPos pos, Random random) {
		BlockState state = CRAFTING_STATION_PROVIDER.getBlockState(random, pos);
		this.setBlockState(world, pos, state);
		if (random.nextBoolean() && world.isAir(pos.up())) {
			generateDecoration(world, pos.up(), random);
		}

	}

	public void generateDecoration(StructureWorldAccess world, BlockPos pos, Random random) {
		if (random.nextInt(3) == 0) {
			this.setBlockState(world, pos, DECORATION_PROVIDER.getBlockState(random, pos));
		} else {
			this.setBlockState(world, pos, BlockTags.FLOWER_POTS.getRandom(random).getDefaultState());
		}
	}

	public void generateOreVein(StructureWorldAccess world, BlockPos pos, Random random) {
		BlockState ore = ORE_PROVIDER.getBlockState(random, pos);
		List<BlockPos> positions = new ArrayList<>();
		positions.add(pos);
		for (int i = 4 + random.nextInt(12); i > 0; i--) {
			pos = positions.remove(0);
			this.setBlockState(world, pos, ore);
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
