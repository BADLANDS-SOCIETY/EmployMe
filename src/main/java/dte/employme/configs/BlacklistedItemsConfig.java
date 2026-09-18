package dte.employme.configs;

import dte.employme.utils.config.Builder;
import dte.employme.utils.config.SpigotConfig;
import org.bukkit.Material;
import org.bukkit.World;

import dte.employme.EmployMe;


public class BlacklistedItemsConfig extends SpigotConfig
{
	public BlacklistedItemsConfig() 
	{
		super(new Builder(EmployMe.getInstance())
				.fromInternalResource("blacklisted items"));
	}
	
	public boolean isBlacklistedAt(World world, Material material)
	{
		//check if the item is globally blacklisted
		if(getList("Blacklisted Items", Material.class).contains(material))
			return true;

		//check if the item is blacklisted at the specified world
		return getList("Worlds." + world.getName(), Material.class).contains(material);
	}
}