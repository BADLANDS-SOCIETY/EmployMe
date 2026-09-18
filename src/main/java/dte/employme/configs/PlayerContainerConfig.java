package dte.employme.configs;

import dte.employme.utils.config.Builder;
import dte.employme.utils.config.ConfigLoadException;
import dte.employme.utils.config.SpigotConfig;
import org.bukkit.plugin.Plugin;


public class PlayerContainerConfig extends SpigotConfig
{
	public PlayerContainerConfig(Plugin plugin, String containerName) throws ConfigLoadException
	{
		super(new Builder(plugin)
				.byPath(String.format("containers/%s containers", containerName)));
	}

}
