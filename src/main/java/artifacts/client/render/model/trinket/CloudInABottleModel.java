package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class CloudInABottleModel extends BipedEntityModel<LivingEntity> {

    protected final ModelPart cloud;

    public CloudInABottleModel() {
        super(RenderLayer::getEntityTranslucent, 0.5F, 0, 32, 32);

        torso = new ModelPart(this, 0, 0);
        ModelPart jar = new ModelPart(this, 0, 16);
        ModelPart lid = new ModelPart(this, 0, 25);
        cloud = new ModelPart(this).setTextureOffset(8, 25);

        torso.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

        jar.addCuboid(-2, 0, -2, 4, 5, 4);
        lid.addCuboid(-1, -1, -1, 2, 1, 2);
        cloud.addCuboid(-1, 1.5F, -1, 2, 2, 2);

        jar.setPivot(4, 9, -3);
        jar.yaw = -0.5F;

        jar.addChild(lid);
        jar.addChild(cloud);
        torso.addChild(jar);

        setVisible(false);
        torso.visible = true;
    }

    @Override
    public void setAngles(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        cloud.yaw = (animationProgress) / 50;
        cloud.pivotY = MathHelper.cos((animationProgress) / 30) / 2;
    }
}
