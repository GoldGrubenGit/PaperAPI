package net.goldgruben.paperapi.api.placeholders;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.utils.PrimeUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class CorePlaceholders extends PlaceholderExpansion {
	@Override
	public String getIdentifier() {
		return "prime";
	}

	@Override
	public String getAuthor() {
		return "PrimeAPI";
	}

	@Override
	public String getVersion() {
		return GoldCore.getInstance().getDescription().getVersion();
	}


	@Override
	public String onPlaceholderRequest(Player player, String params) {
		PrimePlayer p = new PrimePlayer(player);
		switch (params.toLowerCase()) {
			case "coins":
				return PrimeUtils.formatInteger(p.retrieveCoins().complete());
			case "onmins":
				return String.valueOf(p.retrieveOnMins());
			case "ontime_1": {
				int onmins = p.retrieveOnMins().complete();
				int h = onmins / 60;
				int m = onmins % 60;
				return h + ":" + m;
			}
			case "ontime_2": {
				int onmins = p.retrieveOnMins().complete();
				int h = onmins / 60;
				int m = onmins % 60;
				return h + "Stunden " + m + " Minuten";
			}
			case "ontime_3": {
				int onmins = p.retrieveOnMins().complete();
				int h = onmins / 60;
				int m = onmins % 60;
				return h + "h " + m + " m";
			}
			case "ontime_4": {
				int onmins = p.retrieveOnMins().complete();
				int h = onmins / 60;
				int m = onmins % 60;
				return h + "h ";
			}
		}

		return null;
	}
}
