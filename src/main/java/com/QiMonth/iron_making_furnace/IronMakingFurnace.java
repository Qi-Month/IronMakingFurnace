package com.qimonth.iron_making_furnace;

import com.QiMonth.iron_making_furnace.registers.modblock.ModBlocks;
import com.QiMonth.iron_making_furnace.registers.ModCreativeModeTab;
import com.QiMonth.iron_making_furnace.registers.moditem.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(IronMakingFurnace.MODID)
public class IronMakingFurnace {
    public static final String MODID = "iron_making_furnace";

    public IronMakingFurnace() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModCreativeModeTab.CREATIVE_MODE_TABS.register(bus);
    }
}