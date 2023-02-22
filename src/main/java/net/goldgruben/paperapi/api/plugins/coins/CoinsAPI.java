package net.goldgruben.paperapi.api.plugins.coins;

import net.goldgruben.paperapi.sql.SQLPlayer;
import de.primeapi.util.sql.queries.Retriever;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Lukas S. PrimeAPI
 * created on 10.05.2021
 * crated for PrimePlugins
 */
@Getter
public class CoinsAPI {

	private static CoinsAPI instance;
	boolean online;

	public CoinsAPI() {
		instance = this;
		online = true;
	}

	public static CoinsAPI getInstance() {
		return instance;
	}

	public void setCoins(UUID uuid, int coins) {
		new SQLPlayer(uuid).setCoins(coins);
	}

	public void addCoins(UUID uuid, int coins) {
		new SQLPlayer(uuid).addCoins(coins);
	}

	public Retriever<Integer> getCoins(UUID uuid) {
		return new SQLPlayer(uuid).retrieveCoins();
	}

}
