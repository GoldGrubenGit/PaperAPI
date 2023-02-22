package net.goldgruben.paperapi.api.placeholders;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.api.placeholders.cloud.CloudGroupOnlinePlaceholder;
import net.goldgruben.paperapi.api.placeholders.cloud.CloudServerOnlinePlaceholder;
import net.goldgruben.paperapi.api.plugins.clan.ClanAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceholderAPIManager {


	public boolean isActive;

	public PlaceholderAPIManager() {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			isActive = true;
			GoldCore.getInstance().getLogger().info("PlaceholderAPI gefunden");
			new CorePlaceholders().register();
			new CloudServerOnlinePlaceholder().register();
			new CloudGroupOnlinePlaceholder().register();
			if (ClanAPI.getInstance().isOnline()) {
				new ClanPlaceholder().register();
			}
		} else {
			isActive = false;
			GoldCore.getInstance().getLogger().info("PlaceholderAPI wurde nicht aktiviert");
		}
	}

	public String replace(Player p, String s) {
		if (isActive) {
			try {
				return PlaceholderAPI.setPlaceholders(p, s);
			} catch (Exception ex) {
			}
		}
		return s;
	}

}
