package com.qimonth.iron_making_furnace.registers.modblock.entity;

import com.QiMonth.iron_making_furnace.registers.modblock.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.QiMonth.iron_making_furnace.IronMakingFurnace.MODID;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<BlockEntityType<ControllerEntity>> IRON_MAKING_FURNACE_CONTROLLER_ENTITY = BLOCK_ENTITY.register("controller_entity", () ->
            BlockEntityType.Builder.of(ControllerEntity::new, ModBlocks.IRON_MAKING_FURNACE_CONTROLLER.get())
                    .build(null));
}