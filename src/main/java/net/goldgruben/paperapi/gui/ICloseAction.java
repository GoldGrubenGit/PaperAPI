package net.goldgruben.paperapi.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface ICloseAction {

	void onClose(Player p, Inventory inventory);
}
