package net.duckycraft.server_tools.file;

import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileHandler {
    public File getImage(Player player) throws IOException {
        if (new File("plugins\\ServerTools\\bot\\playerheads\\" + player.getName() + ".png").exists()) {
            return new File("plugins\\ServerTools\\bot\\playerheads\\" + player.getName() + ".png");
        } else {
            File file = new File("plugins\\ServerTools\\bot\\playerheads\\" + player.getName() + ".png");
            URL url = new URL("https://minotar.net/avatar/" + player.getName() + ".png");
            BufferedImage image = ImageIO.read(url);
            ImageIO.write(image, "png", file);
            return file;
        }
    }
}
