package top.moonnight.iron_making_furnace;

import com.simibubi.create.foundation.data.CreateRegistrate;
import top.moonnight.iron_making_furnace.registers.block.ModBlocks;
import top.moonnight.iron_making_furnace.registers.ModCreativeModeTab;
import top.moonnight.iron_making_furnace.registers.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(IronMakingFurnace.MODID)
public class IronMakingFurnace {
    public static final String MODID = "iron_making_furnace";

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    public IronMakingFurnace() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModCreativeModeTab.CREATIVE_MODE_TABS.register(bus);

        REGISTRATE.registerEventListeners(bus);
    }
}