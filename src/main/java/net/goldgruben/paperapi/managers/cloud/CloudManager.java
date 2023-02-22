package net.goldgruben.paperapi.managers.cloud;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.managers.cloud.adapter.CloudAdapter;
import net.goldgruben.paperapi.managers.cloud.adapter.version.CloudNetV3;
import net.goldgruben.paperapi.managers.cloud.types.CloudTypes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Jonas | Exceptionpilot#5555
 * Created on 28.05.2021 «» 14:51
 * Class «» CloudManager
 **/

public class CloudManager implements CloudAdapter {

	private CloudTypes found = null;

	public CloudManager() {
		check();
	}

	private void check() {
		GoldCore.getInstance().getLogger().log(Level.INFO, "Versuche Cloud zu finden...");
		for (CloudTypes cloudTypes : CloudTypes.values()) {
			if (Bukkit.getPluginManager().getPlugin(cloudTypes.getPlugin()) != null) {
				GoldCore.getInstance()
				         .getLogger()
				         .log(Level.INFO, "Die Cloud [" + cloudTypes.toString().toLowerCase() + "] wurde gefunden!");
				found = cloudTypes;
				return;
			}
		}
		GoldCore.getInstance().getLogger().log(Level.WARNING, "Es wurde keine Cloud gefunden!");
		found = CloudTypes.NOCLOUD;
	}

	@Override
	public int getPlayersInGroup(String name) {
		switch (found) {
			case CLOUDNETV3:
				return new CloudNetV3().getPlayersInGroup(name);
			default:
				return -1;
		}
	}

	@Override
	public List<String> getAllLobbies(String name) {
		List<String> lobbyList = new ArrayList<>();
		switch (found) {
			case CLOUDNETV3:
				lobbyList = new CloudNetV3().getAllLobbies(name);
				break;
		}
		Collections.sort(lobbyList);
		return lobbyList;
	}

	@Override
	public int getPlayersOnServer(String name) {
		switch (found) {
			case CLOUDNETV3:
				return new CloudNetV3().getPlayersOnServer(name);
			default:
				return -1;
		}
	}

	@Override
	public String getServerState(String name) {
		switch (found) {
			case CLOUDNETV3:
				return new CloudNetV3().getServerState(name);
			default:
				return Bukkit.getServer().getName();
		}
	}

	public void sendPlayerToServer(Player player, String server) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
			dataOutputStream.writeUTF("Connect");
			dataOutputStream.writeUTF(server);
			player.sendPluginMessage(GoldCore.getInstance(), "BungeeCord", byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
			dataOutputStream.close();
		} catch (Exception exception) {
			player.sendMessage("§cEs gab ein Fehler mit der Server connection!");
		}
	}
}
