package com.example.grieferlogger;

import net.minecraft.util.math.BlockPos;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class UndoManager {
    private static final Map<BlockPos, ItemStack[]> chestSnapshots = new HashMap<>();

    public static void snapshot(BlockPos pos, Inventory inventory) {
        ItemStack[] contents = new ItemStack[inventory.size()];
        for (int i = 0; i < inventory.size(); i++) {
            contents[i] = inventory.getStack(i).copy();
        }
        chestSnapshots.put(pos, contents);
    }

    public static void restore(BlockPos pos, Inventory inventory) {
        ItemStack[] saved = chestSnapshots.get(pos);
        if (saved == null) return;

        for (int i = 0; i < saved.length; i++) {
            inventory.setStack(i, saved[i]);
        }
    }
}
