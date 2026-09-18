package dte.employme.utils.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Function;

public class Builder {

    private final Plugin plugin;
    private final YamlConfiguration config = new YamlConfiguration();
    private File path;


    public Builder(Plugin plugin) {
        this.plugin = plugin;
    }

    public Builder byPath(String key) {
        try {
            this.path = new File(plugin.getDataFolder(), key + ".yml");
            if (!path.exists()) {
                path.getParentFile().mkdirs();
                path.createNewFile();
            }
            config.load(path);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Builder supplyDefaults(Function<YamlConfiguration, Map<String,Object>> function) {
        config.addDefaults(function.apply(config));
        return this;
    }

    public Builder loadDefaults() {
        return this;
    }

    public Builder fromInternalResource(String config) {
        try (InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(config + ".yml"))) {
            this.path = new File(plugin.getDataFolder(), config + ".yml");
            if (!path.exists()) {
                path.getParentFile().mkdirs();
                path.createNewFile();
            }

            this.config.load(reader);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return this;
    }

    public YamlConfiguration build() {
        return config;
    }

    public File getFile() {
        return this.path;
    }
}
