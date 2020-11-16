package artifacts.mixin.extensions;

public interface LivingEntityExtensions {

	/**
	 * @param speed The original speed
	 * @return The swim speed after it was handled by artifacts
	 */
	double artifacts$getIncreasedSwimSpeed(double speed);
}
