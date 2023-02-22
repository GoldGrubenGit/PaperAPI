package net.goldgruben.paperapi.gui;

import org.bukkit.entity.Player;

public interface AcceptCallback {

	void accepted(Player p);

	void declined(Player p);

}
