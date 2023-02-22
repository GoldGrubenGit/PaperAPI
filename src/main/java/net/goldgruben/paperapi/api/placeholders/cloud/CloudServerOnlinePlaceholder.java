package net.goldgruben.paperapi.api.placeholders.cloud;

import net.goldgruben.paperapi.GoldCore;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class CloudServerOnlinePlaceholder extends PlaceholderExpansion {
	@Override
	public String getIdentifier() {
		return "cloudserveronline";
	}

	@Override
	public String getAuthor() {
		return "PrimeAPI";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public String onPlaceholderRequest(Player p, String params) {
		return String.valueOf(GoldCore.getInstance().getCloudManager().getPlayersOnServer(params));
	}
}
