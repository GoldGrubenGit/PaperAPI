package net.goldgruben.paperapi.commands.coins.subcommands;

import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.api.command.SubCommand;
import net.goldgruben.paperapi.managers.messages.CoreMessage;
import net.goldgruben.paperapi.sql.SQLPlayer;
import net.goldgruben.paperapi.utils.PrimeUtils;

public class SeeSubCommand extends SubCommand {
	public SeeSubCommand() {
		super("permission");
	}

	@Override
	public boolean execute(PrimePlayer p, String[] args) {
		if (!checkPermission(p)) {
			return true;
		}
		if (args.length != 2) {
			p.sendMessage(CoreMessage.COINS_REMOVE_USAGE);
			return true;
		}
		SQLPlayer.loadPlayerByName(args[1]).submit(target -> {
			if (target == null) {
				p.sendMessage(CoreMessage.COINS_PLAYERNOTFOUND);
				return;
			}
			p.sendMessage(CoreMessage.COINS_SEE_SUCCESS.replace("player", target.retrieveRealName().complete())
			                                           .replace("coins", PrimeUtils.formatInteger(
					                                           target.retrieveCoins().complete())));
		});
		return true;
	}
}
