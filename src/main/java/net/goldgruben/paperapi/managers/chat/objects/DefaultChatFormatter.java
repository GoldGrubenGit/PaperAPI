package net.goldgruben.paperapi.managers.chat.objects;

import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.managers.config.configs.CoreConfig;
import org.bukkit.ChatColor;

public class DefaultChatFormatter implements ChatFormatter {
	@Override
	public String formatString(PrimePlayer player, String message) {
		return CoreConfig.getInstance().getString("chatformat.default")
		                 .replaceAll("%name%", player.thePlayer().getName())
		                 .replaceAll("%message%", ChatColor.translateAlternateColorCodes('&', message));
	}
}
