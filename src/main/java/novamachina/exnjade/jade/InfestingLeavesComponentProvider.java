package novamachina.exnjade.jade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.world.level.block.entity.InfestingLeavesBlockEntity;
import novamachina.novacore.util.StringUtils;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class InfestingLeavesComponentProvider
    implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig) {
    CompoundTag tag = accessor.getServerData().getCompound("Infesting Leaves");
    tooltip.add(Component.literal(tag.getString("progress")));
  }

  @Override
  public ResourceLocation getUid() {
    return new ResourceLocation(ExNihiloSequentia.MOD_ID, "infesting_leaves");
  }

  @Override
  public void appendServerData(CompoundTag compoundTag, BlockAccessor accessor) {
    CompoundTag tag = new CompoundTag();
    BlockEntity blockEntity = accessor.getBlockEntity();
    if (blockEntity instanceof InfestingLeavesBlockEntity infestingLeavesBlockEntity) {
      tag.putString(
          "progress",
          Component.translatable(
                  "waila.progress",
                  StringUtils.formatPercent((float) infestingLeavesBlockEntity.getProgress() / 100))
              .getString());
    }
    compoundTag.put("Infesting Leaves", tag);
  }
}
