package net.goldgruben.paperapi.managers.scoreboard.objects;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.managers.config.configs.CoreConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DefaultScoreboard implements ScoreboardSettings {

	@Override
	public String getTitle() {
		return ChatColor.translateAlternateColorCodes(
				'&', CoreConfig.getInstance().getString("scoreboard.default.title"));
	}

	@Override
	public List<String> apply(Player p) {
		List<String> list = CoreConfig.getInstance().getStringList("scoreboard.default.content");
		List<String> returnValue = new ArrayList<>();
		for (String s :
				list) {
			returnValue.add(ChatColor.translateAlternateColorCodes('&', GoldCore.getInstance()
			                                                                     .getPlaceholderAPIManager()
			                                                                     .replace(p, s)));
		}
		return returnValue;
	}
}
