package noobanidus.mods.mysticalbiomes.gen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.EnchantRandomly;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootTableGenerator extends LootTableProvider {
  public static ResourceLocation MUSHROOM_HUT = new ResourceLocation(MysticalBiomes.MODID, "mushroom_hut");

  private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> tables = ImmutableList.of(Pair.of(ChestLootTables::new, LootParameterSets.CHEST));

  public LootTableGenerator(DataGenerator dataGeneratorIn) {
    super(dataGeneratorIn);
  }

  @Override
  protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
    return tables;
  }

  @Override
  protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
    map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validateLootTable(validationtracker, p_218436_2_, p_218436_3_));
  }

  public static class ChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
      consumer.accept(
          MUSHROOM_HUT,
          LootTable.builder()
              .addLootPool(
                  LootPool.builder()
                      .rolls(ConstantRange.of(4))
                      .addEntry(ItemLootEntry.builder(Items.SPONGE).weight(1))
                      .addEntry(ItemLootEntry.builder(Items.CACTUS).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 6.0F))))
                      .addEntry(ItemLootEntry.builder(Items.JACK_O_LANTERN).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.HAY_BLOCK).weight(1).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.DRIED_KELP_BLOCK).weight(1).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.LILY_PAD).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.VINE).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 12.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BEETROOT).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 15.0F))))
                      .addEntry(ItemLootEntry.builder(Items.HONEYCOMB).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 5.0F))))
                      .addEntry(ItemLootEntry.builder(Items.HONEY_BOTTLE).weight(1).acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 4.0f))))
                      .addEntry(ItemLootEntry.builder(Items.SWEET_BERRIES).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(5.0F, 16.0F))))
                      .addEntry(ItemLootEntry.builder(Items.FEATHER).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BAMBOO).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SLIME_BALL).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SUGAR_CANE).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.AUBERGINE.get()).weight(30).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 8.0F))))
                      .addEntry(ItemLootEntry.builder(Items.APPLE).weight(18).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 8.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BROWN_MUSHROOM).weight(21).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.RED_MUSHROOM).weight(21).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.COBWEB).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SEA_PICKLE).weight(9).acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 9.0f))))
              )
              .addLootPool(
                  LootPool.builder()
                      .rolls(RandomValueRange.of(2, 4))
                      .addEntry(ItemLootEntry.builder(Items.EGG).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.NAME_TAG).weight(2))
                      .addEntry(ItemLootEntry.builder(Items.CHARCOAL).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.FLOWER_POT).weight(8))
                      .addEntry(ItemLootEntry.builder(ModItems.PELT.get()).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.VEGETABLE_JUICE.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.APPLE_CORDIAL.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.DANDELION_CORDIAL.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.LEAD).weight(2))
              )
              .addLootPool(
                  LootPool.builder()
                      .rolls(RandomValueRange.of(0, 2))
                      .addEntry(ItemLootEntry.builder(ModItems.CACTUS_AXE.get()).weight(6))
                      .addEntry(ItemLootEntry.builder(ModItems.BEETLE_MASK.get()).weight(3))
                      .addEntry(ItemLootEntry.builder(ModItems.ANTLER_HAT.get()).weight(3))
                      .addEntry(ItemLootEntry.builder(ModItems.YOUNG_PEARL.get()).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 8.0F))))
                      .addEntry(ItemLootEntry.builder(Items.FISHING_ROD).weight(15).acceptFunction(EnchantRandomly.func_215900_c()))
                      .addEntry(EmptyLootEntry.func_216167_a().weight(18))
              )
      );
    }
  }
}
