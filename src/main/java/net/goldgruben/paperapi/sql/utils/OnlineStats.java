package net.goldgruben.paperapi.sql.utils;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.sql.party.PlayerParty;
import de.primeapi.util.sql.queries.Retriever;
import lombok.NonNull;

import java.util.UUID;

public class OnlineStats {

	public static void flush() {
		GoldCore.getInstance().getDb().update("DELETE FROM `prime_bungee_online`").execute();
	}

	public static void insertPlayer(@NonNull UUID uuid) {
		GoldCore.getInstance().getDb().update("INSERT INTO `prime_bungee_online` VALUES (?,?,?,?)")
		         .parameters(uuid.toString(), null, 0, null)
		         .execute();
	}

	public static void removePlayer(@NonNull UUID uuid) {
		GoldCore.getInstance().getDb().update("DELETE FROM prime_bungee_online WHERE uuid = ?")
		         .parameters(uuid.toString())
		         .execute();
	}

	public static void setServer(@NonNull UUID uuid, String s) {
		GoldCore.getInstance().getDb().update("UPDATE prime_bungee_online SET server = ? WHERE uuid = ?")
		         .parameters(s, uuid.toString())
		         .execute();
	}

	public static void setAFK(@NonNull UUID uuid, boolean b) {
		GoldCore.getInstance().getDb().update("UPDATE prime_bungee_online SET afk = ? WHERE uuid = ?")
		         .parameters((b ? 1 : 0), uuid.toString())
		         .execute();
	}

	public static void setParty(@NonNull UUID uuid, UUID party) {
		if (party == null) {
			GoldCore.getInstance().getDb().update("UPDATE prime_bungee_online SET party = null WHERE uuid = ?")
			         .parameters(uuid.toString())
			         .execute();
		} else {
			GoldCore.getInstance().getDb().update("UPDATE prime_bungee_online SET party = ? WHERE uuid = ?")
			         .parameters(party.toString(), uuid.toString())
			         .execute();
		}
	}

	public static Retriever<String> getServer(@NonNull UUID uuid) {
		return GoldCore.getInstance().getDb().select("SELECT server FROM prime_bungee_online WHERE uuid = ?")
		                .parameters(uuid.toString())
		                .execute(String.class)
		                .get();
	}

	public static Retriever<Boolean> getAFK(@NonNull UUID uuid) {
		return GoldCore.getInstance().getDb().select("SELECT afk FROM prime_bungee_online WHERE uuid = ?")
		                .parameters(uuid.toString())
		                .execute(Integer.class)
		                .get()
		                .map(integer -> integer == 1);
	}

	public static Retriever<PlayerParty> getParty(@NonNull UUID uuid) {
		return GoldCore.getInstance().getDb().select("SELECT party FROM prime_bungee_online WHERE uuid = ?")
		                .parameters(uuid.toString())
		                .execute(String.class)
		                .get()
		                .map(s -> new PlayerParty(UUID.fromString(s)));
	}


}
