package com.example.grieferlogger;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class LoggerCommands {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("trackplayer")
            .then(CommandManager.argument("name", net.minecraft.command.argument.StringArgumentType.string())
            .executes(ctx -> {
                String name = net.minecraft.command.argument.StringArgumentType.getString(ctx, "name");
                GrieferLoggerMod.trackedPlayer = name;
                ctx.getSource().sendFeedback(() -> Text.literal("Now tracking chest interactions for: " + name), false);
                return 1;
            }))
        );
    }
}
