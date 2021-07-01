package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class DrinkingHatModel extends BipedEntityModel<LivingEntity> {

	protected final ModelPart hatShade;

	public DrinkingHatModel(ModelPart root) {
		super(root);

		setVisible(false);
		head.visible = true;
		hat.visible = true;

		hatShade = root.getChild("head").getChild("hat_shade");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder hatShade = ModelPartBuilder.create().uv(0, 52).cuboid(-4, -6, -8, 8, 1, 4);
		ModelPartBuilder straw = ModelPartBuilder.create().uv(0, 50).cuboid(-6, -1, -5, 12, 1, 1);
		ModelPartBuilder canLeft = ModelPartBuilder.create().uv(0, 41).cuboid(4, -11, -1, 3, 6, 3);
		ModelPartBuilder canRight = ModelPartBuilder.create().uv(12, 41).cuboid(-7, -11, -1, 3, 6, 3);
		ModelPartBuilder strawLeft = ModelPartBuilder.create().uv(0, 32).cuboid(5, -4, -3, 1, 1, 8);
		ModelPartBuilder strawRight = ModelPartBuilder.create().uv(17, 32).cuboid(-6, -4, -3, 1, 1, 8);

		ModelPartData headData = root.getChild("head");
		headData.addChild("hat_shade", hatShade, ModelTransform.NONE);
		headData.addChild("straw", straw, ModelTransform.NONE);
		headData.addChild("can_left", canLeft, ModelTransform.NONE);
		headData.addChild("can_right", canRight, ModelTransform.NONE);
		headData.addChild("straw_left", strawLeft, ModelTransform.rotation(0.7853F, 0 , 0));
		headData.addChild("straw_right", strawRight, ModelTransform.rotation(0.7853F, 0 , 0));

		return TexturedModelData.of(modelData, 64, 64);
	}
}
