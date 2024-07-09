package net.duckycraft.server_tools.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Objects;

public class BotHandeler {
    FileConfiguration config;
    String TOKEN;
    JDA bot;

    HashMap<Player, Webhook> webhooks = new HashMap<>();

    public void createBot(FileConfiguration configFile) throws InterruptedException {
        config = configFile;
        TOKEN = config.getString("bot-token");
        JDABuilder builder = JDABuilder.createDefault(TOKEN);
        bot = builder/*.enableIntents(EnumSet.allOf(GatewayIntent.class))*/.build().awaitReady();
    }

    private Webhook getWebhookByName(String name) {
        for (Webhook webhook : bot.getTextChannelById(config.getString("channel-id")).retrieveWebhooks().complete()) {
            if (Objects.equals(webhook.getName(), name)) {
                return webhook;
            }
        }
        return null;
    }

    private void createWebhook(String name, Player player) {
        bot.getTextChannelById(config.getString("channel-id")).createWebhook(name).queue();
        webhooks.put(player, getWebhookByName(name));
    }
    public void sendMessage(String message, Player player) {
        if (!webhooks.containsKey(player)) {
            createWebhook(player.getName(), player);
        }
        webhooks.get(player).sendMessage(message).queue();
    }

    public void clearWebhooks() {
        for (Webhook webhook : bot.getTextChannelById(config.getString("channel-id")).retrieveWebhooks().complete()) {
            webhook.delete().queue();
        }
    }
}
