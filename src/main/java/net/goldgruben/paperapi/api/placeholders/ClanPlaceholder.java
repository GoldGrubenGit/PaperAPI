package net.goldgruben.paperapi.api.placeholders;

import net.goldgruben.paperapi.GoldCore;
import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.api.plugins.clan.ClanAPI;
import net.goldgruben.paperapi.managers.config.configs.CoreConfig;
import net.goldgruben.paperapi.managers.messages.CoreMessage;
import net.goldgruben.paperapi.sql.clan.SQLClan;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 10.06.2021
 * crated for PrimePlugins
 */
public class ClanPlaceholder extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "clan";
    }

    @Override
    public String getAuthor() {
        return "PrimeAPI";
    }

    @Override
    public String getVersion() {
        return GoldCore.getInstance().getDescription().getVersion();
    }


    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (ClanAPI.getInstance().isOnline()) {
            SQLClan clan = ClanAPI.getInstance().getClanFromPlayer(PrimePlayer.fromPlayer(player)).complete();
            if (clan == null) {
                if (params.equalsIgnoreCase("tag_formatted")) {
                    return CoreConfig.getInstance().getString("clanplaceholder.formattedTag.noClan");
                }
                return CoreMessage.CLANPLACEHOLDER_NOCLAN.getContent();
            }
            switch (params.toLowerCase()) {
                case "name": {
                    return clan.getRealname().complete();
                }
                case "tag": {
                    return clan.getTag().complete();
                }
                case "tag_formatted": {
                    return CoreConfig.getInstance()
                            .getString("clanplaceholder.formattedTag.format")
                            .replaceAll("%tag%", clan.getTag().complete())
                            .replaceAll("%color%", clan.getColor().complete())
                            ;
                }
                case "count": {
                    return String.valueOf(clan.getMembers().complete().size());
                }
                case "color": {
                    return clan.getColor().complete();
                }
            }
        }
        return null;
    }
}
