package dev._2lstudios.announcer.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeConfiguration {
    private final Plugin plugin;
    private final String fileName;

    public BungeeConfiguration(final Plugin plugin, final String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
    }

    public Configuration load() throws IOException {
        return ConfigurationProvider.getProvider(YamlConfiguration.class)
                .load(new File(plugin.getDataFolder(), "config.yml"));
    }

    public void saveDefaults() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        File file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            try (InputStream in = plugin.getResourceAsStream(fileName)) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
