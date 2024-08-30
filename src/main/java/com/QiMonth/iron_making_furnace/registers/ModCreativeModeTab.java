package com.QiMonth.iron_making_furnace.registers;

import com.QiMonth.iron_making_furnace.registers.modblock.ModBlocks;
import com.QiMonth.iron_making_furnace.registers.moditem.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.QiMonth.iron_making_furnace.IronMakingFurnace.MODID;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .title(Component.translatable("itemGroup.iron_making_furnace.tab"))
                    .icon(() -> ModBlocks.IRON_MAKING_FURNACE_CONTROLLER.get().asItem().getDefaultInstance())
                    // 添加
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.IRON_MAKING_FURNACE_CONTROLLER.get());
                        output.accept(ModBlocks.IRON_MAKING_FURNACE_BRICKS.get());
                        output.accept(ModItems.IRON_MAKING_FURNACE_BRICK.get());
                    }).build());
}