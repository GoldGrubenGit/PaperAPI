package net.goldgruben.paperapi.managers.scoreboard.objects;

import net.goldgruben.paperapi.managers.config.configs.CoreConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DefaultPrefixScoreboard implements PrefixScoreboardSetting {

	@Override
	public List<ScoreboradTeam> getTeams(Player p) {
		List<ScoreboradTeam> list = new ArrayList<>();
		for (Player all : Bukkit.getOnlinePlayers()) {
			list.add(new ScoreboradTeam(
					all,
					CoreConfig.getInstance().getString("prefix.defaultPrefix"),
					CoreConfig.getInstance().getString("prefix.defaultSuffix"),
					CoreConfig.getInstance().getString("prefix.defaultColor")
			));
		}
		return list;
	}
}
