package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class DrinkingHatModel extends BipedEntityModel<LivingEntity> {

    protected ModelPart hatShade;

    public DrinkingHatModel() {
        super(0.5F, 0, 64, 64);

        setVisible(false);
        head.visible = true;
        helmet.visible = true;

        hatShade = new ModelPart(this, 0, 52);
        ModelPart straw = new ModelPart(this, 0, 50);
        ModelPart canLeft = new ModelPart(this, 0, 41);
        ModelPart canRight = new ModelPart(this, 12, 41);
        ModelPart strawLeft = new ModelPart(this, 0, 32);
        ModelPart strawRight = new ModelPart(this, 17, 32);

        hatShade.addCuboid(-4, -6, -8, 8, 1, 4);
        straw.addCuboid(-6, -1, -5, 12, 1, 1);
        canLeft.addCuboid(4, -11, -1, 3, 6, 3);
        canRight.addCuboid(-7, -11, -1, 3, 6, 3);
        strawLeft.addCuboid(5, -4, -3, 1, 1, 8);
        strawRight.addCuboid(-6, -4, -3, 1, 1, 8);

        head.addChild(hatShade);
        head.addChild(straw);
        head.addChild(canLeft);
        head.addChild(canRight);
        head.addChild(strawLeft);
        head.addChild(strawRight);

        strawLeft.pitch = 0.7853F;
        strawRight.pitch = 0.7853F;
    }
}
