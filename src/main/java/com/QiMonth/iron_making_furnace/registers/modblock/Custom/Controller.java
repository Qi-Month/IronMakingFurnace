package com.QiMonth.iron_making_furnace.registers.modblock.Custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class Controller extends Block {
    public static final BooleanProperty ON = BooleanProperty
            .create("on");

    public Controller(Properties properties) {
        super(properties);
        // 让玩家放下后默认关闭
        this.registerDefaultState(this.stateDefinition.any().setValue(ON, false));
    }

    // 玩家右键(use)让其更换状态on or off(只能空手)
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.getItemInHand(hand).isEmpty()) {
            level.setBlock(blockPos, state.cycle(ON), 3);
            return super.use(state, level, blockPos, player, hand, result);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ON);
    }
}