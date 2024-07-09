package net.duckycraft.server_tools;

import net.duckycraft.server_tools.bot.BotHandeler;
import net.duckycraft.server_tools.chatEvent.ChatEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerTools extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        BotHandeler bot = new BotHandeler();
        try {
            bot.createBot(getConfig());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getServer().getPluginManager().registerEvents(new ChatEvent(bot), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ServerTools has been enabled!");
    }

    @Override
    public void onDisable() {
        new BotHandeler().clearWebhooks();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ServerTools has been enabled!");
    }
}
