package bluescreen9.minecraft.bukkit.freekeepinventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class FKPCommand implements TabExecutor{

	private static ArrayList<String> emptyComplete = new ArrayList<String>();
	private static ArrayList<String> complete1 = new ArrayList<String>();
	static {
		complete1.add("true");
		complete1.add("false");
	}
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
			if (args.length == 1) {
				return complete1;
			}
		return emptyComplete;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.Language.get(Main.Language.getDefaultLang(), "command.playeronly")));
			return true;
		}
		Player player = (Player) sender;
		if (args.length > 1) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.Language.get(player, "command.wrongusage")));
			return true;
		}
		if (args.length == 1 && !complete1.contains(args[0])) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.Language.get(player, "command.wrongusage")));
			return true;
		}
		
		if (args.length == 0) {
			if (Data.get(player)) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.Language.get(player, "command.get.true")));
				return true;
			}else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.Language.get(player, "command.get.false")));
				return true;
			}
		}
		
		if (args.length == 1) {
				if (args[0].equals("true")) {
					if (Data.get(player)) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.Language.get(player, "command.true")));
						return true;
					}
					Data.set(player, true);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.Language.get(player, "command.set.true")));
				}
				if (args[0].equals("false")) {
					if (!Data.get(player)) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.Language.get(player, "command.false")));
						return true;
					}
					Data.set(player, false);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.Language.get(player, "command.set.false")));
				}
			return true;
		}
		return true;
	}

}
