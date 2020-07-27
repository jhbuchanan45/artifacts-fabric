package artifacts.client.render.model.curio;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;

public class SteadfastSpikesModel extends PlayerEntityModel<LivingEntity> {

    public SteadfastSpikesModel() {
        super(0.5F, false);

        ModelPart clawLeftLower1 = new ModelPart(this, 32, 0);
        ModelPart clawRightLower1 = new ModelPart(this, 32, 32);
        ModelPart clawLeftLower2 = new ModelPart(this, 44, 0);
        ModelPart clawRightLower2 = new ModelPart(this, 44, 32);
        ModelPart clawLeftUpper1 = new ModelPart(this, 32, 8);
        ModelPart clawRightUpper1 = new ModelPart(this, 32, 40);
        ModelPart clawLeftUpper2 = new ModelPart(this, 44, 8);
        ModelPart clawRightUpper2 = new ModelPart(this, 44, 40);
        clawLeftLower1.addCuboid(-1.5F, 10, -7, 1, 3, 5);
        clawRightLower1.addCuboid(-1.5F, 10, -7, 1, 3, 5);
        clawLeftLower2.addCuboid(0.5F, 10, -7, 1, 3, 5);
        clawRightLower2.addCuboid(0.5F, 10, -7, 1, 3, 5);
        clawLeftUpper1.addCuboid(-1.5F, 9, -6, 1, 1, 4);
        clawRightUpper1.addCuboid(-1.5F, 9, -6, 1, 1, 4);
        clawLeftUpper2.addCuboid(0.5F, 9, -6, 1, 1, 4);
        clawRightUpper2.addCuboid(0.5F, 9, -6, 1, 1, 4);
        leftLeg.addChild(clawLeftLower1);
        rightLeg.addChild(clawRightLower1);
        leftLeg.addChild(clawLeftLower2);
        rightLeg.addChild(clawRightLower2);
        leftLeg.addChild(clawLeftUpper1);
        rightLeg.addChild(clawRightUpper1);
        leftLeg.addChild(clawLeftUpper2);
        rightLeg.addChild(clawRightUpper2);

        setVisible(false);
        leftLeg.visible = true;
        rightLeg.visible = true;
        leftPantLeg.visible = true;
        rightPantLeg.visible = true;
    }
}
