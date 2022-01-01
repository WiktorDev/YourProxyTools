package tech.wiktor.plugins.proxy.managers;

import lombok.SneakyThrows;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import tech.wiktor.plugins.proxy.Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigManager {
    private Main instance;

    public ConfigManager(Main instance){
        this.instance = instance;
    }
    @SneakyThrows
    public void saveConfig(){
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(instance.getConfig(), new File(instance.getDataFolder(), "config.yml"));
    }

    public void loadConfiguration(){
        if (!instance.getDataFolder().exists()) instance.getDataFolder().mkdir();
        File file = new File(instance.getDataFolder(), "config.yml");

        if (!file.exists()) {
            try (InputStream in = instance.getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
