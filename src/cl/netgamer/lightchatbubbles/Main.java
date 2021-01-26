package cl.netgamer.lightchatbubbles;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener
{
	private boolean disableChatWindow;
	private ChatBuffer buffer;
	ChatBubbles bubbles;
	
	// enabler
	public void onEnable()
	{
		saveDefaultConfig();
		disableChatWindow = getConfig().getBoolean("disableChatWindow");
		bubbles = new ChatBubbles(this);
		buffer = new ChatBuffer(this);
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	// chat event listener, highest priority so it would be run at last
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent e)
	{
		if (!e.isCancelled())
		{
			buffer.receiveChat(e.getPlayer(), e.getMessage(), e.getFormat());
			
			if(disableChatWindow)
				e.setCancelled(true);
		}
	}
	
}
