package artifacts.cwient.wendew;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.entity.MimicModew;
import artifacts.common.entity.MimicEntity;
import net.minecraft.client.render.entity.EntityWendewDispatchew;
import net.minecraft.client.render.entity.MobEntityWendewew;
import net.minecraft.util.Identifiew;

public class MimicWendewew extends MobEntityWendewew<MimicEntity, MimicModew> {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/mimic.png");

	public MimicWendewew(EntityWendewDispatchew dispatchew) {
		super(dispatchew, new MimicModew(), 0.45F);
	}

	@Override
	public Identifiew getTextuwe(MimicEntity entity) {
		return TEXTUWE;
	}
}
