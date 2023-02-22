package net.goldgruben.paperapi.managers.chat.objects;

import net.goldgruben.paperapi.api.PrimePlayer;

public interface ChatFormatter {

	String formatString(PrimePlayer player, String message);
}
