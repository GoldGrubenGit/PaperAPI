package net.goldgruben.paperapi.sql.clan;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.sql.SQLPlayer;
import de.primeapi.util.sql.queries.Retriever;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class SQLClanInvitation {
	final int id;

	public static Retriever<SQLClanInvitation> create(SQLPlayer player, SQLClan clan) {
		return GoldCore.getInstance().getDb().update("INSERT INTO prime_clan_requests value (id,?,?)")
		                .parameters(player.retrieveUniqueId().complete().toString(), clan.getId())
		                .returnGeneratedKeys(Integer.class)
		                .get()
		                .map(SQLClanInvitation::new);
	}

	public static Retriever<SQLClanInvitation> fromPlayer(SQLPlayer player, SQLClan clan) {
		return GoldCore.getInstance()
		                .getDb()
		                .select(
				                "SELECT id FROM prime_clan_requests WHERE uuid = ? AND clan = ?"
		                       )
		                .parameters(player.retrieveUniqueId().complete().toString(), clan.getId())
		                .execute(Integer.class)
		                .get()
		                .map(SQLClanInvitation::new);
	}

	public static Retriever<List<SQLClanInvitation>> fromPlayer(SQLPlayer player) {
		return GoldCore.getInstance().getDb().select(
				                "SELECT id FROM prime_clan_requests WHERE uuid=?"
		                                             ).parameters(player.retrieveUniqueId().complete().toString())
		                .execute(Integer.class)
		                .getAsSet()
		                .map(integers ->
				                     integers.stream().map(SQLClanInvitation::new).collect(Collectors.toList())
		                    );
	}

	private <T> Retriever<T> readDatabase(String column, Class<T> type) {
		return GoldCore.getInstance().getDb().select(
				                "SELECT " + column + " FROM prime_clan_requests WHERE id = ?"
		                                             )
		                .parameters(id)
		                .execute(type)
		                .get();
	}


	public Retriever<UUID> getUUID() {
		return new Retriever<>(() -> UUID.fromString(readDatabase("uuid", String.class).complete()));
	}

	public Retriever<SQLClan> getClan() {
		return new Retriever<>(() -> new SQLClan(readDatabase("clan", Integer.class).complete()));
	}


	public void delete() {
		GoldCore.getInstance().getDb().update("DELETE FROM prime_clan_requests WHERE id = ?")
		         .parameters(id).execute();
	}

}
