package net.goldgruben.paperapi.events;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.managers.config.configs.CoreConfig;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		PrimePlayer primePlayer = PrimePlayer.fromPlayer(e.getPlayer());
		if (CoreConfig.getInstance().getBoolean("chatformat.use")) {
			String s = e.getMessage();
			if (primePlayer.hasPermission("chat.color")) {
				s = ChatColor.translateAlternateColorCodes('&', s);
			}
			e.setFormat(GoldCore.getInstance().getChatManager().format(primePlayer, s));
		}
	}

}
