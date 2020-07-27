package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ScarfModel;
import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

public class LuckyScarfItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/lucky_scarf.png");

    public LuckyScarfItem() {
        super(new Settings(), "lucky_scarf");
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            @Environment(EnvType.CLIENT)
            protected ScarfModel getModel() {
                if (model == null) {
                    model = new ScarfModel();
                }
                return (ScarfModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        });
    }

    public static class FortuneBonusModifier extends LootModifier {

        protected FortuneBonusModifier(LootCondition[] conditions) {
            super(conditions);
        }

        @Override
        protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
            ItemStack tool = context.get(LootContextParameters.TOOL);

            if (tool == null || tool.getOrCreateTag().getBoolean("HasAppliedFortuneBonus")) {
                return generatedLoot;
            }

            Entity entity = context.get(LootContextParameters.THIS_ENTITY);
            BlockState blockState = context.get(LootContextParameters.BLOCK_STATE);
            if (blockState == null || !(entity instanceof LivingEntity) || !CuriosApi.getCuriosHelper().findEquippedCurio(artifacts.common.init.Items.LUCKY_SCARF, (LivingEntity) entity).isPresent()) {
                return generatedLoot;
            }

            ItemStack fakeTool = tool.isEmpty() ? new ItemStack(Items.BARRIER) : tool.copy();
            fakeTool.getOrCreateTag().putBoolean("HasAppliedFortuneBonus", true);

            fakeTool.addEnchantment(Enchantments.FORTUNE, EnchantmentHelper.getLevel(Enchantments.FORTUNE, fakeTool) + 1);
            LootContext.Builder builder = new LootContext.Builder(context);
            builder.parameter(LootContextParameters.TOOL, fakeTool);
            LootContext newContext = builder.build(LootContextTypes.BLOCK);
            LootTable lootTable = context.getWorld().getServer().getLootManager().getTable(blockState.getBlock().getLootTableId());
            return lootTable.generateLoot(newContext);
        }

        public static class Serializer extends GlobalLootModifierSerializer<FortuneBonusModifier> {

            @Override
            public FortuneBonusModifier read(Identifier location, JsonObject object, LootCondition[] conditions) {
                return new FortuneBonusModifier(conditions);
            }
        }
    }
}
