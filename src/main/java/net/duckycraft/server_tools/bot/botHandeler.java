package net.duckycraft.server_tools.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.configuration.file.FileConfiguration;

public class botHandeler {
    String TOKEN;
    JDA bot;
    public void createBot(FileConfiguration config) {
        TOKEN = config.getString("bot-token");
        JDABuilder builder = JDABuilder.createDefault(TOKEN);
        bot = builder.build();
    }
}
