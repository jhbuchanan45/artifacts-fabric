package artifacts.mixins.item.diggingclaws;

import artifacts.common.init.Items;
import artifacts.common.item.DiggingClawsItem;
import net.fabricmc.fabric.impl.tool.attribute.handlers.ModdedToolsVanillaBlocksToolHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import top.theillusivec4.curios.api.CuriosApi;

/**
 * Mixin to fabric-tool-attribute-api because it's ass
 * For mining vanilla blocks with modded tools
 */
@Mixin(value = ModdedToolsVanillaBlocksToolHandler.class, remap = false)
public abstract class MixinModdedToolsVanillaBlocksToolHandler {

    /**
     * Allow player to mine any block with mining level <= 2
     */
    @ModifyVariable(method = "isEffectiveOn", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/fabricmc/fabric/api/tool/attribute/v1/DynamicAttributeTool;getMiningLevel(Lnet/minecraft/tag/Tag;Lnet/minecraft/block/BlockState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)I"))
    private int decreaseRequiredMiningLevel(int miningLevel, Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user)  {
        return CuriosApi.getCuriosHelper().findEquippedCurio(Items.DIGGING_CLAWS, user).isPresent() ? Math.max(miningLevel, DiggingClawsItem.NEW_BASE_MINING_LEVEL) : miningLevel;
    }
}
