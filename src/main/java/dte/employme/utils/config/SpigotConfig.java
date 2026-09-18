package dte.employme.utils.config;

import dte.employme.EmployMe;
import dte.employme.job.Job;
import dte.employme.rewards.ItemsReward;
import dte.employme.rewards.MoneyReward;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SpigotConfig {

    private YamlConfiguration config = new YamlConfiguration();
    private File path;

    protected SpigotConfig(Builder builder) {
        this.config = builder.build();
        try {
            if (!builder.getFile().exists()) {
                builder.getFile().getParentFile().mkdirs();
                builder.getFile().createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SpigotConfig(File file) {
        this.path = file;
    }

    public static SpigotConfig byPath(EmployMe employMe, String file) {
        File path = new File(employMe.getDataFolder(), file + ".yml");

        if (!path.exists()) {
            try {
                path.getParentFile().mkdirs();
                path.createNewFile();
            } catch (IOException e) {e.printStackTrace();}
        }

        return new SpigotConfig(path);
    }

    public static void register(Class<Job> jobClass, Class<MoneyReward> moneyRewardClass, Class<ItemsReward> itemsRewardClass) {
        ConfigurationSerialization.registerClass(jobClass);
        ConfigurationSerialization.registerClass(moneyRewardClass);
        ConfigurationSerialization.registerClass(itemsRewardClass);
    }

    public Object get(String key) {
        return config.get(key);
    }


    public String getString(String key) {
        return this.config.getString(key);
    }

    public ConfigurationSection getSection(String key) {
        return config.getConfigurationSection(key);
    }

    public double getDouble(String key) {
        return config.getDouble(key);
    }
    @SuppressWarnings(value="unchecked")
    public <T> List<T> getList(String key, Class<T> clazz) {
        return (List<T>) config.getList(key,new ArrayList<T>());
    }

    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }


    public void set(String key, Object val) {
        config.set(key,val);
    }

    public void save() throws IOException {
        config.save(path);
    }

    public void delete(String key) {
        config.set(key, null);
    }

    public ItemStack getItemStack(String key) {
        return new ItemStack(Material.AIR);
    }

    public Map<String, Object> getValues(boolean deep) {
        return config.getValues(deep);
    }

    public void clear() {
        config.set("", null);
    }
}
