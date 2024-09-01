package com.qimonth.iron_making_furnace.registers.moditem;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.QiMonth.iron_making_furnace.IronMakingFurnace.MODID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister
            .create(ForgeRegistries.ITEMS, MODID);

    // 耐热砖
    public static final RegistryObject<Item> IRON_MAKING_FURNACE_BRICK = ITEMS.register("iron_making_furnace_brick", () ->
            new Item(new Item.Properties()));
}