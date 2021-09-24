package noobanidus.mods.mysticalbiomes.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.loot.functions.RandomPotion;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.EnchantRandomly;
import net.minecraft.loot.functions.EnchantWithLevels;
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
  public static ResourceLocation TOTEM = new ResourceLocation(MysticalBiomes.MODID, "totem");
  public static ResourceLocation HENGE = new ResourceLocation(MysticalBiomes.MODID, "henge");

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
    map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_));
  }

  public static class ChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
      consumer.accept(
          MUSHROOM_HUT,
          LootTable.lootTable()
              .withPool(
                  LootPool.lootPool()
                      .setRolls(ConstantRange.exactly(4))
                      .add(ItemLootEntry.lootTableItem(Items.SPONGE).setWeight(1))
                      .add(ItemLootEntry.lootTableItem(Items.CACTUS).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(2.0F, 6.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.JACK_O_LANTERN).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.HAY_BLOCK).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.DRIED_KELP_BLOCK).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.LILY_PAD).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(3.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.VINE).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(1.0F, 12.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BEETROOT).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(4.0F, 15.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.HONEYCOMB).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(2.0F, 5.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.HONEY_BOTTLE).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(1.0f, 4.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.SWEET_BERRIES).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(5.0F, 16.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.FEATHER).setWeight(15).apply(SetCount.setCount(RandomValueRange.between(3.0F, 7.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BAMBOO).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SLIME_BALL).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SUGAR_CANE).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.AUBERGINE.get()).setWeight(30).apply(SetCount.setCount(RandomValueRange.between(2.0F, 8.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.APPLE).setWeight(18).apply(SetCount.setCount(RandomValueRange.between(3.0F, 8.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).setWeight(21).apply(SetCount.setCount(RandomValueRange.between(3.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).setWeight(21).apply(SetCount.setCount(RandomValueRange.between(3.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.COBWEB).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SEA_PICKLE).setWeight(9).apply(SetCount.setCount(RandomValueRange.between(1.0f, 9.0f))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(2, 4))
                      .add(ItemLootEntry.lootTableItem(Items.EGG).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.NAME_TAG).setWeight(2))
                      .add(ItemLootEntry.lootTableItem(Items.CHARCOAL).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.FLOWER_POT).setWeight(8))
                      .add(ItemLootEntry.lootTableItem(ModItems.PELT.get()).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.VEGETABLE_JUICE.get()).setWeight(15).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.APPLE_CORDIAL.get()).setWeight(15).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.DANDELION_CORDIAL.get()).setWeight(15).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.LEAD).setWeight(2))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 2))
                      .add(ItemLootEntry.lootTableItem(ModItems.CACTUS_AXE.get()).setWeight(6))
                      .add(ItemLootEntry.lootTableItem(ModItems.BEETLE_MASK.get()).setWeight(3))
                      .add(ItemLootEntry.lootTableItem(ModItems.ANTLER_HAT.get()).setWeight(3))
                      .add(ItemLootEntry.lootTableItem(ModItems.YOUNG_PEARL.get()).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(2.0F, 8.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.FISHING_ROD).setWeight(15).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(EmptyLootEntry.emptyItem().setWeight(18))
              )
      );
      consumer.accept(
          TOTEM,
          LootTable.lootTable()
              .withPool(
                  LootPool.lootPool()
                      .setRolls(ConstantRange.exactly(2))
                      .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(2.0f, 12f))))
                      .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(2.0f, 12f))))
                      .add(ItemLootEntry.lootTableItem(Items.WITHER_ROSE).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(1.0f, 3.0f))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 3))
                      .add(ItemLootEntry.lootTableItem(Items.EMERALD).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(1.0f, 5.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.GOLDEN_CARROT).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(3, 6))))
                      .add(ItemLootEntry.lootTableItem(Items.GUNPOWDER).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(6, 12))))
                      .add(ItemLootEntry.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(2, 5))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 1))
                      .add(ItemLootEntry.lootTableItem(Items.WITHER_SKELETON_SKULL).setWeight(1))
                      .add(ItemLootEntry.lootTableItem(Items.FIREWORK_ROCKET).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(2, 6))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(1, 3))
                      .add(ItemLootEntry.lootTableItem(Items.ARMOR_STAND).setWeight(4))
                      .add(ItemLootEntry.lootTableItem(Items.SCAFFOLDING).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(12, 24))))
                      .add(ItemLootEntry.lootTableItem(Items.OBSIDIAN).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(6, 12))))
                      .add(ItemLootEntry.lootTableItem(Items.SOUL_CAMPFIRE).setWeight(1))
              )
      );
      consumer.accept(
          HENGE,
          LootTable.lootTable()
              .withPool(
                  LootPool.lootPool()
                      .setRolls(ConstantRange.exactly(2))
                      .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(2.0f, 12f))))
                      .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(2.0f, 12f))))
                      .add(ItemLootEntry.lootTableItem(Items.WITHER_ROSE).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(1.0f, 3.0f))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(ConstantRange.exactly(1))
                      .add(ItemLootEntry.lootTableItem(Items.SOUL_SAND).apply(SetCount.setCount(RandomValueRange.between(1, 6))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(1, 5))
                      .add(ItemLootEntry.lootTableItem(Items.GLOWSTONE_DUST).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4, 18))))
                      .add(ItemLootEntry.lootTableItem(Items.REDSTONE).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(8, 21))))
                      .add(ItemLootEntry.lootTableItem(Items.SUGAR).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(10, 32))))
                      .add(ItemLootEntry.lootTableItem(Items.STICK).setWeight(14).apply(SetCount.setCount(RandomValueRange.between(14, 21))))
                      .add(ItemLootEntry.lootTableItem(Items.GLASS_BOTTLE).setWeight(18).apply(SetCount.setCount(RandomValueRange.between(16, 32))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(1, 3))
                      .add(ItemLootEntry.lootTableItem(Items.MAGMA_CREAM).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(3, 6))))
                      .add(ItemLootEntry.lootTableItem(Items.GLISTERING_MELON_SLICE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1, 5))))
                      .add(ItemLootEntry.lootTableItem(Items.FERMENTED_SPIDER_EYE).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(1, 3))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 2))
                      .add(ItemLootEntry.lootTableItem(Items.RAIL).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(6, 18))))
                      .add(ItemLootEntry.lootTableItem(Items.LANTERN).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1, 4))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 1))
                      .add(ItemLootEntry.lootTableItem(Items.DIAMOND_SHOVEL).setWeight(20).apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(20, 30))))
                      .add(ItemLootEntry.lootTableItem(Items.NETHERITE_SHOVEL).setWeight(1).apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(20, 30))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(1, 3))
                      .add(ItemLootEntry.lootTableItem(Items.POTION).apply(RandomPotion.builder()))
              )
      );
    }
  }
}
