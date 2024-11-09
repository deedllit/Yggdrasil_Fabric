package com.yggdrasil.init;

import com.jcraft.jorbis.Block;
import com.yggdrasil.YggdrasilMod;
import com.yggdrasil.list.FoodList;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ItemInit {


    public static final Item EXAMPLE_ITEM = register("example_item") ;

    public static final Item MUSPELHEIM_ASH_CARAMBOLA_BERRIES = register("muspelheim_ash_carambola_berries",
            Item::new,
            new Item.Settings().food(FoodList.FOOD_MUSPELHEIM_ASH_CARAMBOLA_BERRIES));

    //public static final Item EXAMPLE_ITEM = register("example_item", Item::new, new Item.Settings());

    public static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, YggdrasilMod.id(name));
        return Items.register(registryKey, factory, settings);
    }

    private static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registries.ITEM, YggdrasilMod.id(name), item);
    }

    public static Item register(String name) {
        return registerItem(name, new Item(new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, YggdrasilMod.id(name)))));
    }

    public static <T extends Item> T register(String name, Function<Item.Settings, T> constructor, Function<Item.Settings, Item.Settings> settingsApplier) {
        return registerItem(name, constructor.apply(
                settingsApplier.apply(new Item.Settings().registryKey(
                        RegistryKey.of(RegistryKeys.ITEM, YggdrasilMod.id(name))))));
    }

    public static void load() {}


}
