package net.duckycraft.server_tools.file;

import java.io.File;

public class FirstInit {
    public FirstInit() {
        if (!new File("plugins/ServerTools/bot").exists()) {
            new File("plugins/ServerTools/bot").mkdir();
            new File("plugins/ServerTools/bot/playerheads").mkdir();
        }
    }
}
