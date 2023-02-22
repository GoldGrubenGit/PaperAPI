package net.goldgruben.paperapi.api.plugins.bungee;

import net.goldgruben.paperapi.GoldCore;
import lombok.Getter;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.logging.Level;

/**
 * @author Lukas S. PrimeAPI
 * created on 02.06.2021
 * crated for PrimePlugins
 */
@Getter
public class BungeeAPI {

	private static BungeeAPI instance;
	boolean online;

	public BungeeAPI() {
		instance = this;
		online = false;
		try {
			DatabaseMetaData md = GoldCore.getInstance().getConnection().getMetaData();
			ResultSet rs = md.getTables(null, null, "prime_bungee_online", null);
			online = rs.next();
			rs.close();
		} catch (Exception throwables) {
			throwables.printStackTrace();
		}
		if (online) {
			GoldCore.getInstance().getLogger().log(Level.INFO, "BungeeSystemAPI wurde geladen");
		} else {
			GoldCore.getInstance().getLogger().log(Level.INFO, "BungeeSystemAPI wurde NICHT geladen");
		}
	}

	public static BungeeAPI getInstance() {
		return instance;
	}

}
