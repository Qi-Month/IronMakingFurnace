package top.moonnight.iron_making_furnace.registers.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.moonnight.iron_making_furnace.registers.ModCreativeModeTab;
import top.moonnight.iron_making_furnace.registers.item.custom.CustomItem;

import static top.moonnight.iron_making_furnace.IronMakingFurnace.MODID;
import static top.moonnight.iron_making_furnace.IronMakingFurnace.REGISTRATE;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister
            .create(ForgeRegistries.ITEMS, MODID);

    // 耐热砖
    public static final RegistryObject<Item> IRON_MAKING_FURNACE_BRICK = ITEMS.register("iron_making_furnace_brick", CustomItem::new);
}