package dev._2lstudios.announcer;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import dev._2lstudios.announcer.commands.AnnouncerCommand;
import dev._2lstudios.announcer.config.BungeeConfiguration;
import dev._2lstudios.announcer.tasks.AnnouncerTask;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public class Announcer extends Plugin {
    private Configuration config;
    
    public Configuration getConfig() {
        return config;
    }

    public void load() throws IOException {
        final BungeeConfiguration bungeeConfiguration = new BungeeConfiguration(this, "config.yml");

        bungeeConfiguration.saveDefaults();
        config = bungeeConfiguration.load();

        this.getCommand("example").setExecutor(new AnnouncerCommand());

        final long interval = config.getInt("interval");
        final Collection<String> announcements = config.getStringList("announcements");

        this.getProxy().getScheduler().schedule(this, new AnnouncerTask(), interval, interval, TimeUnit.SECONDS);
    }

    @Override
    public void onEnable () {
        Announcer.instance = this;

        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Announcer instance;

    public static Announcer getInstance () {
        return Announcer.instance;
    }
}