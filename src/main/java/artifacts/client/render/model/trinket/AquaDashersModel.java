package artifacts.client.render.model.trinket;

public class AquaDashersModel extends ShoesModel {

    private static final float DELTA = 1.25F;

    public AquaDashersModel() {
        super(DELTA);
        leftLeg.setTextureOffset(0, 16);
        leftLeg.addCuboid(2 + DELTA, 0, -2 + 3 + DELTA * 3 / 2, 0, 12, 4, 0, DELTA, DELTA);
        rightLeg.setTextureOffset(16, 16);
        rightLeg.addCuboid(-2 - DELTA, 0, -2 + 3 + DELTA * 3 / 2, 0, 12, 4, 0, DELTA, DELTA);
    }
}
