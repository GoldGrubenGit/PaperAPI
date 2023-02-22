package net.goldgruben.paperapi.managers.chat;

import net.goldgruben.paperapi.api.PrimePlayer;
import net.goldgruben.paperapi.managers.chat.objects.ChatFormatter;
import net.goldgruben.paperapi.managers.chat.objects.DefaultChatFormatter;
import lombok.Getter;

@Getter
public class ChatManager {

	public ChatFormatter chatFormatter;


	public ChatManager() {
		chatFormatter = new DefaultChatFormatter();

	}

	public String format(PrimePlayer p, String message) {
		return chatFormatter.formatString(p, message);
	}

}
