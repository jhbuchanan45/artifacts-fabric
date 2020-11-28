package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class WhoopeeCushionModel extends BipedEntityModel<LivingEntity> {

    public WhoopeeCushionModel() {
        super(0, 0, 32, 16);

        setVisible(false);
        head.visible = true;

        head = new ModelPart(this);
        head.addCuboid(-3, -10, -3, 6, 2, 6);
        head.setTextureOffset(0, 8).addCuboid(-2, -9.25F, 3, 4, 0, 4);
    }
}
