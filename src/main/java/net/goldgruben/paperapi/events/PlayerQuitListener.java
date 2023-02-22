package net.goldgruben.paperapi.events;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.api.plugins.nick.NickAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		PrimePlayer primePlayer = PrimePlayer.fromPlayer(event.getPlayer());
		GoldCore.getInstance().getScoreboardManager().customScoreboard.remove(primePlayer.getUniqueId());
		GoldCore.getInstance().getThreadPoolExecutor().submit(() -> {
			try {
				NickAPI.getInstance().removeFromDatabase(event.getPlayer());
				NickAPI.getInstance().getIsNicked().unCache(event.getPlayer().getUniqueId());
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});
	}
}
