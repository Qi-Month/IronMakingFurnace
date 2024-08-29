package com.QiMonth.IronMakingFurnace;

import com.QiMonth.IronMakingFurnace.Registers.ModBlock.ModBlock;
import com.QiMonth.IronMakingFurnace.Registers.ModCreativeModeTab;
import com.QiMonth.IronMakingFurnace.Registers.ModItem.ModItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(IronMakingFurnace.MODID)
public class IronMakingFurnace {
    public static final String MODID = "iron_making_furnace";

    public IronMakingFurnace() {
        ModItem.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlock.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModCreativeModeTab.CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}