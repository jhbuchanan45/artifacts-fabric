package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;

public class ClawsModel extends GloveModel {

    public ClawsModel(boolean smallArms) {
        super(smallArms);

        ModelPart clawLeftUpper1 = new ModelPart(this, 0, 6);
        ModelPart clawRightUpper1 = new ModelPart(this, 0, 38);
        ModelPart clawLeftUpper2 = new ModelPart(this, 8, 6);
        ModelPart clawRightUpper2 = new ModelPart(this, 8, 38);
        ModelPart clawLeftLower1 = new ModelPart(this, 0, 0);
        ModelPart clawRightLower1 = new ModelPart(this, 0, 32);
        ModelPart clawLeftLower2 = new ModelPart(this, 8, 0);
        ModelPart clawRightLower2 = new ModelPart(this, 8, 32);
        clawLeftUpper1.addCuboid(3, 10, -1.5F, 1, 4, 1);
        clawRightUpper1.addCuboid(-4, 10, -1.5F, 1, 4, 1);
        clawLeftUpper2.addCuboid(3, 10, 0.5F, 1, 4, 1);
        clawRightUpper2.addCuboid(-4, 10, 0.5F, 1, 4, 1);
        clawLeftLower1.addCuboid(0, 10, -1.5F, 3, 5, 1);
        clawRightLower1.addCuboid(-3, 10, -1.5F, 3, 5, 1);
        clawLeftLower2.addCuboid(0, 10, 0.5F, 3, 5, 1);
        clawRightLower2.addCuboid(-3, 10, 0.5F, 3, 5, 1);
        leftArm.addChild(clawLeftUpper1);
        rightArm.addChild(clawRightUpper1);
        leftArm.addChild(clawLeftUpper2);
        rightArm.addChild(clawRightUpper2);
        leftArm.addChild(clawLeftLower1);
        rightArm.addChild(clawRightLower1);
        leftArm.addChild(clawLeftLower2);
        rightArm.addChild(clawRightLower2);
    }
}
