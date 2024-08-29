package com.QiMonth.IronMakingFurnace.Registers;

import com.QiMonth.IronMakingFurnace.Registers.ModBlock.ModBlock;
import com.QiMonth.IronMakingFurnace.Registers.ModItem.ModItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.QiMonth.IronMakingFurnace.IronMakingFurnace.MODID;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .title(Component.translatable("itemGroup.iron_making_furnace.tab"))
                    .icon(() -> ModBlock.IRON_MAKING_FURNACE_CONTROLLER.get().asItem().getDefaultInstance())
                    // 添加
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlock.IRON_MAKING_FURNACE_CONTROLLER.get());
                        output.accept(ModBlock.IRON_MAKING_FURNACE_BRICKS.get());
                        output.accept(ModItem.IRON_MAKING_FURNACE_BRICK.get());
                    }).build());
}