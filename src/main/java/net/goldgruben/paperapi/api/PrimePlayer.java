package net.goldgruben.paperapi.api;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.managers.config.configs.CoreConfig;
import net.goldgruben.paperapi.managers.messages.CoreMessage;
import net.goldgruben.paperapi.managers.scoreboard.objects.ScoreboardSettings;
import net.goldgruben.paperapi.sql.SQLPlayer;
import net.goldgruben.paperapi.sql.party.PlayerParty;
import net.goldgruben.paperapi.sql.utils.OnlineStats;
import de.primeapi.util.sql.queries.Retriever;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PrimePlayer extends SQLPlayer {


	private static final Set<PrimePlayer> onlinePlayers = new HashSet<>();

	final Player p;

	/**
	 * @param player
	 * @deprecated use {@link PrimePlayer#fromPlayer(Player)}
	 */
	@Deprecated
	public PrimePlayer(Player player) {
		super(player.getUniqueId());
		this.p = player;
	}

	public static PrimePlayer fromPlayer(Player p) {
		return onlinePlayers
				.stream()
				.filter(primePlayer -> primePlayer.getUniqueId().equals(p.getUniqueId()))
				.findFirst()
				.orElseGet(() -> new PrimePlayer(p));
	}

	public Player getPlayer() {
		return p;
	}

	public Player thePlayer() {
		return p;
	}

	public void sendMessage(CoreMessage message) {
		thePlayer().sendMessage(message.getContent());
	}

	public void sendNoPerm(String permission) {
		sendMessage(CoreMessage.NO_PERMS.replace("permission", permission.toLowerCase()));
	}

	public void setScoreboard(ScoreboardSettings scoreboardSettings) {
		GoldCore.getInstance().getScoreboardManager().customScoreboard.put(p.getUniqueId(), scoreboardSettings);
	}

	public void sendScoreboard() {
		if (CoreConfig.getInstance().getBoolean("scoreboard.use")) {
			GoldCore.getInstance().getScoreboardManager().sendScoreboard(p);
		}
	}

	public UUID getUniqueId() {
		return thePlayer().getUniqueId();
	}

	public boolean hasPermission(String permission) {
		return thePlayer().hasPermission(permission);
	}

	public boolean checkPermission(String permission) {
		if (!thePlayer().hasPermission(permission)) {
			sendNoPerm(permission);
			return false;
		} else {
			return true;
		}
	}

	public Retriever<Boolean> getAFK() {
		return OnlineStats.getAFK(getUniqueId());
	}

	public void setAFK(boolean b) {
		OnlineStats.setAFK(getUniqueId(), b);
	}

	public Retriever<PlayerParty> getParty() {
		return OnlineStats.getParty(getUniqueId());
	}

	public void setParty(PlayerParty party) {
		OnlineStats.setParty(getUniqueId(), party.getOwner());
	}


}
