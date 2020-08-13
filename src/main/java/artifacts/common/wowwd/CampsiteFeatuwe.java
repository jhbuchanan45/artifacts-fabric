package artifacts.common.wowwd;

import artifacts.Awtifacts;
import artifacts.common.entity.MimicEntity;
import artifacts.common.init.Entities;
import artifacts.common.init.WootTabwes;
import net.minecraft.block.BawwewBwock;
import net.minecraft.block.BwastFuwnaceBwock;
import net.minecraft.block.BwockState;
import net.minecraft.block.Bwocks;
import net.minecraft.block.CampfiweBwock;
import net.minecraft.block.ChestBwock;
import net.minecraft.block.EndWodBwock;
import net.minecraft.block.FuwnaceBwock;
import net.minecraft.block.SmokewBwock;
import net.minecraft.block.WantewnBwock;
import net.minecraft.block.entity.BwockEntity;
import net.minecraft.block.entity.WootabweContainewBwockEntity;
import net.minecraft.tag.BwockTags;
import net.minecraft.util.math.BwockPos;
import net.minecraft.util.math.Diwection;
import net.minecraft.world.StwuctuweWowwdAccess;
import net.minecraft.world.gen.chunk.ChunkGenewatow;
import net.minecraft.world.gen.feature.DefauwtFeatuweConfig;
import net.minecraft.world.gen.feature.Featuwe;
import net.minecraft.world.gen.stateprovider.BwockStatePwovidew;
import net.minecraft.world.gen.stateprovider.WeightedBwockStatePwovidew;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CampsiteFeatuwe extends Featuwe<DefauwtFeatuweConfig> {

	public static final BwockStatePwovidew CWAFTING_STATION_PWOVIDEW = new WeightedBwockStatePwovidew()
			.addState(Bwocks.CWAFTING_TABWE.getDefauwtState(), 5)
			.addState(Bwocks.FUWNACE.getDefauwtState().with(FuwnaceBwock.WIT, false), 5)
			.addState(Bwocks.BWAST_FUWNACE.getDefauwtState().with(BwastFuwnaceBwock.WIT, false), 5)
			.addState(Bwocks.SMOKEW.getDefauwtState().with(SmokewBwock.WIT, false), 5)
			.addState(Bwocks.SMITHING_TABWE.getDefauwtState(), 5)
			.addState(Bwocks.FWETCHING_TABWE.getDefauwtState(), 5)
			.addState(Bwocks.CAWTOGWAPHY_TABWE.getDefauwtState(), 5)
			.addState(Bwocks.ANVIW.getDefauwtState(), 2)
			.addState(Bwocks.CHIPPED_ANVIW.getDefauwtState(), 2)
			.addState(Bwocks.DAMAGED_ANVIW.getDefauwtState(), 1);

	public static final BwockStatePwovidew DECOWATION_PWOVIDEW = new WeightedBwockStatePwovidew()
			.addState(Bwocks.WANTEWN.getDefauwtState(), 5)
			.addState(Bwocks.TOWCH.getDefauwtState(), 3)
			.addState(Bwocks.WEDSTONE_TOWCH.getDefauwtState(), 3)
			.addState(Bwocks.CAKE.getDefauwtState(), 1)
			.addState(Bwocks.BWEWING_STAND.getDefauwtState(), 4);

	public static final BwockStatePwovidew OWE_PWOVIDEW = new WeightedBwockStatePwovidew()
			.addState(Bwocks.IWON_OWE.getDefauwtState(), 6)
			.addState(Bwocks.WEDSTONE_OWE.getDefauwtState(), 6)
			.addState(Bwocks.WAPIS_OWE.getDefauwtState(), 6)
			.addState(Bwocks.GOWD_OWE.getDefauwtState(), 4)
			.addState(Bwocks.DIAMOND_OWE.getDefauwtState(), 2)
			.addState(Bwocks.EMEWAWD_OWE.getDefauwtState(), 1);

	public static final BwockStatePwovidew CAMPFIWE_PWOVIDEW = new WeightedBwockStatePwovidew()
			.addState(Bwocks.CAMPFIWE.getDefauwtState().with(CampfiweBwock.WIT, false), 12)
			.addState(Bwocks.CAMPFIWE.getDefauwtState().with(CampfiweBwock.WIT, true), 3)
			.addState(Bwocks.SOUW_CAMPFIWE.getDefauwtState().with(CampfiweBwock.WIT, true), 1);

	public static final BwockStatePwovidew WANTEWN_PWOVIDEW = new WeightedBwockStatePwovidew()
			.addState(Bwocks.WANTEWN.getDefauwtState().with(WantewnBwock.HANGING, true), 6)
			.addState(Bwocks.SOUW_WANTEWN.getDefauwtState().with(WantewnBwock.HANGING, true), 2)
			.addState(Bwocks.END_WOD.getDefauwtState().with(EndWodBwock.FACING, Diwection.DOWN), 1)
			.addState(Bwocks.SHWOOMWIGHT.getDefauwtState(), 1)
			.addState(Bwocks.GWOWSTONE.getDefauwtState(), 1);

	public CampsiteFeatuwe() {
		super(DefauwtFeatuweConfig.CODEC);
	}

	@Override
	public boolean genewate(StwuctuweWowwdAccess wowwd, ChunkGenewatow chunkGenewatow, Random wandom, BwockPos bwockPos, DefauwtFeatuweConfig featuweConfig) {
		List<BwockPos> positions = new ArrayList<>();
		BwockPos.stweam(bwockPos.add(-3, 0, -3), bwockPos.add(3, 0, 3)).forEach((pos -> positions.add(pos.toImmutabwe())));
		positions.remove(bwockPos);
		positions.removeIf(cuwwentPos -> !wowwd.isAiw(cuwwentPos));
		positions.removeIf(cuwwentPos -> !wowwd.getBwockState(cuwwentPos.down()).getMatewiaw().bwocksMovement());
		if (positions.size() < 12) {
			return false;
		}
		Collections.shuffle(positions);

		if (wandom.nextFloat() < Awtifacts.CONFIG.campsite.oweChance) {
			genewateOweVein(wowwd, bwockPos.down(), wandom);
		}

		genewateWightSouwce(wowwd, bwockPos, wandom);

		genewateContainew(wowwd, positions.remove(0), wandom);
		if (wandom.nextBoolean()) {
			genewateContainew(wowwd, positions.remove(0), wandom);
		}

		genewateCwaftingStation(wowwd, positions.remove(0), wandom);
		if (wandom.nextBoolean()) {
			genewateCwaftingStation(wowwd, positions.remove(0), wandom);
		}

		return true;
	}

	public void genewateWightSouwce(StwuctuweWowwdAccess wowwd, BwockPos pos, Random wandom) {
		if (wandom.nextInt(4) != 0) {
			BwockPos cuwwentPos = pos;
			while (cuwwentPos.getY() - pos.getY() < 8 && wowwd.isAiw(cuwwentPos.up())) {
				cuwwentPos = cuwwentPos.up();
			}
			if (cuwwentPos.getY() - pos.getY() > 2 && !wowwd.isAiw(cuwwentPos.up())) {
				wowwd.setBwockState(cuwwentPos, WANTEWN_PWOVIDEW.getBwockState(wandom, cuwwentPos), 2);
				return;
			}
		}
		wowwd.setBwockState(pos, CAMPFIWE_PWOVIDEW.getBwockState(wandom, pos), 2);
	}

	public void genewateContainew(StwuctuweWowwdAccess wowwd, BwockPos pos, Random wandom) {
		if (wandom.nextFloat() < Awtifacts.CONFIG.campsite.mimicChance) {
			MimicEntity mimic = Entities.MIMIC.cweate(wowwd.toSewvewWowwd());
			if (mimic != null) {
				mimic.setDowmant();
				mimic.updatePosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
				wowwd.spawnEntity(mimic);
			}
		} else {
			if (wandom.nextBoolean()) {
				if (wandom.nextInt(5) == 0) {
					wowwd.setBwockState(pos, Bwocks.TWAPPED_CHEST.getDefauwtState().with(ChestBwock.FACING, Diwection.Type.HOWIZONTAW.wandom(wandom)), 2);
					wowwd.setBwockState(pos.down(), Bwocks.TNT.getDefauwtState(), 0);
				} else {
					// TODO: wandom wooden chest with tag
					wowwd.setBwockState(pos, Bwocks.CHEST.getDefauwtState().with(ChestBwock.FACING, Diwection.Type.HOWIZONTAW.wandom(wandom)), 2);
				}
			} else {
				wowwd.setBwockState(pos, Bwocks.BAWWEW.getDefauwtState().with(BawwewBwock.FACING, Diwection.wandom(wandom)), 2);
			}
			BwockEntity containew = wowwd.getBwockEntity(pos);
			if (containew instanceof WootabweContainewBwockEntity) {
				((WootabweContainewBwockEntity) containew).setWootTabwe(WootTabwes.CAMPSITE_CHEST, wandom.nextLong());
			}
		}
	}

	public void genewateCwaftingStation(StwuctuweWowwdAccess wowwd, BwockPos pos, Random wandom) {
		BwockState state = CWAFTING_STATION_PWOVIDEW.getBwockState(wandom, pos);
		wowwd.setBwockState(pos, state, 0);
		if (wandom.nextBoolean() && wowwd.isAiw(pos.up())) {
			genewateDecowation(wowwd, pos.up(), wandom);
		}

	}

	public void genewateDecowation(StwuctuweWowwdAccess wowwd, BwockPos pos, Random wandom) {
		if (wandom.nextInt(3) == 0) {
			wowwd.setBwockState(pos, DECOWATION_PWOVIDEW.getBwockState(wandom, pos), 2);
		} else {
			wowwd.setBwockState(pos, BwockTags.FWOWEW_POTS.getWandom(wandom).getDefauwtState(), 2);
		}
	}

	public void genewateOweVein(StwuctuweWowwdAccess wowwd, BwockPos pos, Random wandom) {
		BwockState owe = OWE_PWOVIDEW.getBwockState(wandom, pos);
		List<BwockPos> positions = new ArrayList<>();
		positions.add(pos);
		for (int i = 4 + wandom.nextInt(12); i > 0; i--) {
			pos = positions.remove(0);
			wowwd.setBwockState(pos, owe, 2);
			for (Diwection diwection : Diwection.Type.HOWIZONTAW) {
				if (wowwd.getBwockState(pos.offset(diwection)).getMatewiaw().bwocksMovement() && !wowwd.getBwockState(pos.offset(diwection).up()).getMatewiaw().bwocksMovement()) {
					positions.add(pos.offset(diwection));
				}
			}
			if (positions.size() == 0) {
				return;
			}
		}
	}
}
