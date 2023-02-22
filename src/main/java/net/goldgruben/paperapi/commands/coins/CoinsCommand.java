package net.goldgruben.paperapi.commands.coins;

import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.commands.coins.subcommands.AddSubCommand;
import net.goldgruben.paperapi.commands.coins.subcommands.RemoveSubCommand;
import net.goldgruben.paperapi.commands.coins.subcommands.SeeSubCommand;
import net.goldgruben.paperapi.commands.coins.subcommands.SetSubCommand;
import net.goldgruben.paperapi.managers.messages.CoreMessage;
import net.goldgruben.paperapi.utils.PrimeUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
		if (!(commandSender instanceof Player)) {
			return false;
		}
		PrimePlayer p = new PrimePlayer((Player) commandSender);

		if (args.length == 0) {
			p.retrieveCoins().submit(coins -> {
				p.sendMessage(CoreMessage.COINS_AMOUNT.replace("coins", PrimeUtils.formatInteger(coins)));
			});
			return true;
		}

		switch (args[0].toLowerCase()) {
			case "add": {
				return new AddSubCommand().execute(p, args);
			}
			case "set": {
				return new SetSubCommand().execute(p, args);
			}
			case "remove": {
				return new RemoveSubCommand().execute(p, args);
			}
			case "see":
			case "get": {
				return new SeeSubCommand().execute(p, args);
			}
			default:
				p.sendMessage(CoreMessage.COINS_USAGE);

		}


		return true;
	}
}
