package net.goldgruben.paperapi.managers.commands;

import net.goldgruben.paperapi.GoldCore;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CommandsManager {


	public ArrayList<Command> registeredCommands = new ArrayList<>();

	public CommandsManager() {
		File file = new File("plugins/primeplugins/core/commands.yml");
		GoldCore.getInstance().getConfigManager().generateDirs("plugins/primeplugins/core/commands.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		String active = "";
		int activeCount = 0;
		String deactive = "";
		int deactiveCount = 0;

		for (Command c : Command.values()) {
			boolean b;

			if (cfg.contains("commands." + c.getName())) {
				b = cfg.getBoolean("commands." + c.getName());
			} else {
				b = true;
				GoldCore.getInstance().getLogger().info("Command '" + c.getName() + "' wurde eingetragen!");
				cfg.set("commands." + c.getName(), true);
			}

			if (b) {
				GoldCore.getInstance().getCommand(c.getName()).setExecutor(c.getCommand());
				if (c.getCompleter() != null) {
					GoldCore.getInstance().getCommand(c.getName()).setTabCompleter(c.getCompleter());
				}
				registeredCommands.add(c);
				active += c.getName() + ", ";
				activeCount++;
			} else {
				deactive += c.getName() + ", ";
				deactiveCount++;
			}
		}
		GoldCore.getInstance().getLogger().info("Aktivierte Commands (" + activeCount + "): " + active);
		GoldCore.getInstance().getLogger().info("Deaktivierte Commands (" + deactiveCount + "): " + deactive);


		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
