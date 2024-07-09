package net.duckycraft.server_tools.chatEvent;

import net.duckycraft.server_tools.bot.BotHandeler;
import net.dv8tion.jda.api.JDA;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.checkerframework.checker.units.qual.C;

public class ChatEvent implements Listener {

    BotHandeler BOT;
    public ChatEvent(BotHandeler bot) {
        BOT = bot;
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        BOT.sendMessage(message, event.getPlayer());
    }
}
