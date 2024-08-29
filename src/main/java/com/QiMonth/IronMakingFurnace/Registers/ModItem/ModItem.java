package com.QiMonth.IronMakingFurnace.Registers.ModItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.QiMonth.IronMakingFurnace.IronMakingFurnace.MODID;

public class ModItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister
            .create(ForgeRegistries.ITEMS, MODID);

    // 耐热砖
    public static final RegistryObject<Item> IRON_MAKING_FURNACE_BRICK = ITEMS.register("iron_making_furnace_brick", () ->
            new Item(new Item.Properties()));
}