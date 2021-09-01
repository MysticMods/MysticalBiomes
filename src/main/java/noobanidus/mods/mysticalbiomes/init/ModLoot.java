package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.loot.LootFunctionType;
import net.minecraft.util.registry.Registry;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.loot.functions.RandomPotion;

public class ModLoot {
  public static final ConfiguredRegistry<LootFunctionType> REGISTRY = new ConfiguredRegistry<>(MysticalBiomes.MODID, Registry.LOOT_FUNCTION_TYPE);

  public static final LootFunctionType RANDOM_POTION = REGISTRY.register("random_potion", new LootFunctionType(new RandomPotion.Serializer()));

  public static void load () {
  }
}
