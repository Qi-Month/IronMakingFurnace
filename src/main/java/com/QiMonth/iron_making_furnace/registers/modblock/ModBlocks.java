package com.qimonth.iron_making_furnace.registers.modblock;

import com.QiMonth.iron_making_furnace.registers.modblock.custom.Controller;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.QiMonth.iron_making_furnace.IronMakingFurnace.MODID;
import static com.QiMonth.iron_making_furnace.registers.moditem.ModItems.ITEMS;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, MODID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends net.minecraft.world.level.block.Block> RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn);
        return toReturn;
    }

    // 控制器
    public static final RegistryObject<Block> IRON_MAKING_FURNACE_CONTROLLER = registerBlock("iron_making_furnace_controller", () ->
            new Controller(BlockBehaviour.Properties
                    .copy(Blocks.BRICKS)
                    .sound(SoundType.STONE)
                    // 硬度为2, 爆炸抗性为6
                    .strength(2.0f, 6.0f)
                    // 这边是一个比较运算符, 当条件符合ON的时候返回15的亮度,否则为3
                    .lightLevel(state -> state.getValue(Controller.ON) ? 15 : 3)
            ));


    // 砖块
    public static final RegistryObject<Block> IRON_MAKING_FURNACE_BRICKS = registerBlock("iron_making_furnace_bricks", () ->
            new Block(BlockBehaviour.Properties
                    .copy(Blocks.BRICKS)
                    .sound(SoundType.STONE)
                    .strength(2.0f, 6.0f)
            ));
}