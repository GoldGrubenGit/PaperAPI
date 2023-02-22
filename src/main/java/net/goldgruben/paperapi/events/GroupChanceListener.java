package net.goldgruben.paperapi.events;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.managers.events.GroupChanceEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GroupChanceListener implements Listener {

	@EventHandler
	public void onGroupChance(GroupChanceEvent event) {
		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			PrimePlayer.fromPlayer(onlinePlayer).sendScoreboard();
			GoldCore.getInstance().getScoreboardManager().sendTeams();
		}
	}
}
