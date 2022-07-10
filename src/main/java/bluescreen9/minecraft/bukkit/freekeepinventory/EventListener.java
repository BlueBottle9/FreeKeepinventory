package bluescreen9.minecraft.bukkit.freekeepinventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener{
					@EventHandler
					public void onPlayerDie(PlayerDeathEvent event) {
						Player player = event.getEntity();
						if (Main.blocklistWorlds.contains(player.getWorld())) {
							return;
						}
						if (!Data.get(player)) {
							return;
						}
						if (Main.Config.getBoolean("keepinventory.keepitem")) {
							event.setKeepInventory(true);
							event.getDrops().clear();
						}
						if (Main.Config.getBoolean("keepinventory.keepexp")) {
							event.setKeepLevel(true);
							event.setDroppedExp(0);
						}
					}
					
					@EventHandler
					public void onPlayerJoin(PlayerJoinEvent event) {
						if (!Main.Config.getBoolean("keepinventory.defualt-enable")) {
							return;
						}
						Player player = event.getPlayer();
						if (Data.isUnset(player)) {
							Data.set(player, true);
						}
					}
}
