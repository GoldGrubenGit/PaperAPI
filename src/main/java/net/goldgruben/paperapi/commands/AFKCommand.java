package net.goldgruben.paperapi.commands;

import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.api.plugins.bungee.BungeeAPI;
import net.goldgruben.paperapi.managers.messages.CoreMessage;
import net.goldgruben.paperapi.sql.utils.OnlineStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 02.06.2021
 * crated for PrimePlugins
 */
public class AFKCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
		if (!(commandSender instanceof Player)) {
			return false;
		}
		PrimePlayer p = new PrimePlayer((Player) commandSender);

		if (!BungeeAPI.getInstance().isOnline()) {
			p.thePlayer().sendMessage("§bSystem §7● §cDiese Funktion ist nur mit dem BungeeSystem kompatibel!");
			return true;
		}

		OnlineStats.getAFK(p.getUniqueId()).submit(aBoolean -> {
			OnlineStats.setAFK(p.getUniqueId(), !aBoolean);
			p.sendMessage(aBoolean ? CoreMessage.AFK_OFF : CoreMessage.AFK_ON);
		});

		return true;
	}
}
