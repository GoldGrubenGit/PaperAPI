package net.goldgruben.paperapi.managers.scoreboard.objects;

import org.bukkit.entity.Player;

import java.util.List;

public interface PrefixScoreboardSetting {

	List<ScoreboradTeam> getTeams(Player p);


}
