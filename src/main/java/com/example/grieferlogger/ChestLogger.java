package com.example.grieferlogger;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChestLogger {
    private static final Path LOG_DIR = Paths.get("griefer-logger/logs");

    public static void log(ServerPlayerEntity player, BlockPos pos, String action, ItemStack stack) {
        if (!player.getName().getString().equalsIgnoreCase(GrieferLoggerMod.trackedPlayer)) return;

        try {
            Files.createDirectories(LOG_DIR);
            Path logFile = LOG_DIR.resolve(player.getName().getString() + "_chestlog.txt");
            BufferedWriter writer = Files.newBufferedWriter(logFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("[" + time + "] " + action + " " + stack.getCount() + "x " + stack.getItem() +
                         " at chest " + pos.toShortString() + "\n");
            writer.close();
        } catch (IOException e) {
            GrieferLoggerMod.LOGGER.error("Failed to write to chest log.", e);
        }
    }
}
