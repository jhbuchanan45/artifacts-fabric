package artifacts.mixins.item.diggingclaws;

import artifacts.common.init.Items;
import artifacts.common.item.DiggingClawsItem;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.fabricmc.fabric.impl.tool.attribute.handlers.ModdedToolsModdedBlocksToolHandler;
import net.fabricmc.fabric.impl.tool.attribute.handlers.VanillaToolsModdedBlocksToolHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.theillusivec4.curios.api.CuriosApi;

/**
 * Mixin to fabric-tool-attribute-api because it's ass
 * For mining modded blocks with vanilla/modded tools
 */
@Mixin(value = {VanillaToolsModdedBlocksToolHandler.class, ModdedToolsModdedBlocksToolHandler.class}, remap = false)
public abstract class MixinModdedBlocksToolHandlers {

    /**
     * Allow player to mine any block with mining level <= 2
     */
    // TODO: user is always null
    @Inject(method = "isEffectiveOn", at = @At(value = "RETURN", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void decreaseRequiredMiningLevel(Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user, CallbackInfoReturnable<ActionResult> info, ToolManagerImpl.Entry entry, int miningLevel) {
        if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.DIGGING_CLAWS, user).isPresent() && miningLevel < DiggingClawsItem.NEW_BASE_MINING_LEVEL) {
            info.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
