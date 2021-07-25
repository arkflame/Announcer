package dev._2lstudios.announcer;

import java.util.concurrent.TimeUnit;

import dev._2lstudios.announcer.commands.AnnouncerCommand;
import dev._2lstudios.announcer.tasks.AnnouncerTask;
import net.md_5.bungee.api.plugin.Plugin;

public class Announcer extends Plugin {
    
    @Override
    public void onEnable () {
        // Save default config
        this.saveDefaultConfig();

        // Set static instance
        Announcer.instance = this;

        // Register the example command
        this.getCommand("example").setExecutor(new AnnouncerCommand());

        // Register the example task
        final long interval = this.getConfig().getInt("interval");
        this.getProxy().getScheduler().schedule(this, new AnnouncerTask(), interval, interval, TimeUnit.SECONDS);
    }

    private static Announcer instance;

    public static Announcer getInstance () {
        return Announcer.instance;
    }
}