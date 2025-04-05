package com.example.grieferlogger;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ChestInteractionListener {
    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClient() || !(player instanceof ServerPlayerEntity)) return ActionResult.PASS;

            BlockPos pos = hitResult.getBlockPos();
            if (world.getBlockEntity(pos) instanceof ChestBlockEntity chest) {
                UndoManager.snapshot(pos, chest);
                for (int i = 0; i < chest.size(); i++) {
                    ItemStack stack = chest.getStack(i);
                    if (!stack.isEmpty()) {
                        ChestLogger.log((ServerPlayerEntity) player, pos, "saw", stack);
                    }
                }
            }

            return ActionResult.PASS;
        });
    }
}
