package net.goldgruben.paperapi.api.plugin;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.managers.rest.PluginInfo;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Lukas S. PrimeAPI
 * created on 31.05.2021
 * crated for PrimePlugins
 */
@Getter
public class RestPlugin {

	private final String name;
	public JavaPlugin plugin;
	@Setter
	public String license = "";

	public RestPlugin(String name, JavaPlugin plugin) {
		this.name = name;
		this.plugin = plugin;
		GoldCore.getInstance().getRestManager().registerPlugin(this);
	}

	public boolean isNewUpdateAvailable() {
		try {
			return GoldCore.getInstance()
			                .getRestManager()
			                .getPlugininfo(name)
			                .isNeverVersion(plugin.getDescription().getVersion());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean downloadLatestVersion(String path) {
		return GoldCore.getInstance().getRestManager().downloadPlugin(getPluginInfo(), license, path);
	}

	public PluginInfo getPluginInfo() {
		return GoldCore.getInstance().getRestManager().getPlugininfo(name);
	}


}
