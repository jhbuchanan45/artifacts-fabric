package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class SuperstitiousHatModel extends BipedEntityModel<LivingEntity> {

    public SuperstitiousHatModel() {
        super(0, 0, 64, 32);
        head = new ModelPart(this, 0, 0);
        head.addCuboid(-4, -16, -4, 8, 8, 8);
        ModelPart brim = new ModelPart(this, 0, 16);
        brim.addCuboid(-5, -9, -5, 10, 1, 10);
        head.addChild(brim);
        setVisible(false);
        head.visible = true;
    }
}
