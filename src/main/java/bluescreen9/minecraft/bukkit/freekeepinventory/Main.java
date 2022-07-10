package bluescreen9.minecraft.bukkit.freekeepinventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import bluescreen9.minecraft.bukkit.lang.Lang;

public class Main extends JavaPlugin{
	protected static Plugin FreeKeepinventory;
	protected static FileConfiguration Config;
	protected static Lang Language;
	protected static ArrayList<World> blocklistWorlds = new ArrayList<World>();
					@Override
					public void onEnable() {
						FreeKeepinventory = Main.getPlugin(Main.class);
						saveDefaultConfig();
						reloadConfig();
						Config = getConfig();
						Language = new Lang(FreeKeepinventory);
						Language.copyDeafultLangFile();
						Language.loadLanguages();
						Language.setDefaultLang(Config.getString("default-language"));
						Data.loadData();
						getCommand("fkp").setExecutor(new FKPCommand());
						getCommand("fkp").setTabCompleter(new FKPCommand());
						getServer().getPluginManager().registerEvents(new EventListener(), FreeKeepinventory);
						
						for (String s:Config.getStringList("keepinventory.blacklist-worlds")) {
								blocklistWorlds.add(Bukkit.getWorld(s));
						}
						
						new BukkitRunnable() {
							public void run() {
								Data.upload();
							}
						}.runTaskTimerAsynchronously(FreeKeepinventory, 20L, 10L);
					}
					
					@Override
					public void onDisable() {
						Data.saveData();
					}
}
