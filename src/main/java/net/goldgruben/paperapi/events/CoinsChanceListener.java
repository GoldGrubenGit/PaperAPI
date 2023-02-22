package net.goldgruben.paperapi.events;

import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.managers.events.CoinsChanceEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CoinsChanceListener implements Listener {

	@EventHandler
	public void onCoinsChance(CoinsChanceEvent event) {
		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			PrimePlayer.fromPlayer(onlinePlayer).sendScoreboard();
		}
	}
}
