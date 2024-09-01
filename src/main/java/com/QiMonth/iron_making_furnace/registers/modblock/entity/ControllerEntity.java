package com.qimonth.iron_making_furnace.registers.modblock.entity;

import com.QiMonth.iron_making_furnace.gui.ControllerMenu;
import com.QiMonth.iron_making_furnace.registers.modblock.ModBlocks;
import com.QiMonth.iron_making_furnace.registers.moditem.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ControllerEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    private int progress = 0;

    private int maxProgress = 100;

    public ControllerEntity(BlockPos pos, BlockState state, ContainerData data) {
        super(ModBlocks.IRON_MAKING_FURNACE_CONTROLLER.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ControllerEntity.this.progress;
                    case 1 -> ControllerEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ControllerEntity.this.progress = value;
                    case 1 -> ControllerEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.iron_making_furnace.controller");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new ControllerMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("furnace", this.progress);

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ControllerEntity entity) {
        if (level.isClientSide) {
            return;
        }

        if (tick(entity)) {
            entity.progress++;
            setChanged(level, pos, state);

            if (entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private static void craftItem(ControllerEntity entity) {
        if (hasRecipe(entity)) {
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.setStackInSlot(2, new ItemStack(ModItems.IRON_MAKING_FURNACE_BRICK.get(),
                    entity.itemHandler.getStackInSlot(2).getCount() + 1));
        }

        entity.resetProgress();
    }

    private static boolean hasRecipe(ControllerEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getStackInSlot(1));
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasRawIron = entity.itemHandler.getStackInSlot(1).getItem() == ModItems.IRON_MAKING_FURNACE_BRICK.get();

        return hasRawIron && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertAmountIntoOutputSlot(inventory, new ItemStack(ModItems.IRON_MAKING_FURNACE_BRICK.get(), 1));
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).getCount() + stack.getCount() <= 64;
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    private void resetProgress() {
        this.progress = 0;
    }
}