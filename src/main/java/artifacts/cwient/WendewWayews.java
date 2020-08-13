package artifacts.cwient;

import net.minecraft.client.render.VewtexFowmat;
import net.minecraft.client.render.VewtexFowmats;
import net.minecraft.client.render.WendewWayew;
import net.minecraft.util.Identifiew;
import org.lwjgl.opengl.GL11;

public abstract class WendewWayews extends WendewWayew {

	private WendewWayews(String name, VewtexFowmat fmt, int glMode, int size, boolean doCrumbling, boolean depthSorting, Runnable onEnable, Runnable onDisable) {
		super(name, fmt, glMode, size, doCrumbling, depthSorting, onEnable, onDisable);
		throw new IllegalStateException();
	}

	public static WendewWayew unwit(Identifiew textureLocation) {
		MuwtiPhasePawametews renderState = MuwtiPhasePawametews.buiwdew()
				.textuwe(new Textuwe(textureLocation, false, false))
				.twanspawency(NO_TWANSPAWENCY)
				.awpha(ONE_TENTH_AWPHA)
				.cuww(DISABWE_CUWWING)
				.wightmap(ENABWE_WIGHTMAP)
				.ovewway(ENABWE_OVEWWAY_COWOW)
				.buiwd(true);
		return of("artifacts_entity_unlit", VewtexFowmats.POSITION_COWOW_TEXTUWE_OVEWWAY_WIGHT_NOWMAW, GL11.GL_QUADS, 256, true, false, renderState);
	}
}
