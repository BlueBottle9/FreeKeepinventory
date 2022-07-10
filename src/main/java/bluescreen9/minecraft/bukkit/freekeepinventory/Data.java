package bluescreen9.minecraft.bukkit.freekeepinventory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Data {
				private static HashMap<UUID, Boolean> map = new HashMap<UUID, Boolean>();
				private static byte[] data;
				public static void saveData() {
					try {
						File dataFile = new File(Main.FreeKeepinventory.getDataFolder(), "data.json");
						if (!dataFile.exists()) {
							dataFile.createNewFile();
						}
						if (!dataFile.isFile()) {
							dataFile.delete();
							dataFile.createNewFile();
						}
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dataFile));
						out.write(data);
						out.flush();
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				public static void loadData() {
					try {
						File dataFile = new File(Main.FreeKeepinventory.getDataFolder(), "data.json");
						if (!dataFile.exists()) {
							dataFile.createNewFile();
						}
						if (!dataFile.isFile()) {
							dataFile.delete();
							dataFile.createNewFile();
						}
						BufferedInputStream in = new BufferedInputStream(new FileInputStream(dataFile));
						data = in.readAllBytes();
						in.close();
						if (data == null) {
							return;
						}
						if (data.length == 0) {
							return;
						}
						JSONObject json = JSON.parseObject(new String(data,Charset.forName("utf-8")));
						if (json == null) {
							return;
						}
						for (String s:json.keySet()) {
								map.put(UUID.fromString(s), json.getBoolean(s));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				public static void upload() {
					JSONObject json = new JSONObject();
					for (UUID uuid:map.keySet()) {
						json.put(uuid.toString(), map.get(uuid));
					}
					data = json.toJSONString().getBytes(Charset.forName("utf-8"));
				}
				
				public static boolean get(Player player) {
					if (!map.containsKey(player.getUniqueId())) {
						return false;
					}
					return map.get(player.getUniqueId());
				}
				
				public static void set(Player player,boolean value) {
						map.put(player.getUniqueId(), value);
				}
				
				public static boolean isUnset(Player player) {
						if (map.containsKey(player.getUniqueId())) {
							return false;
						}
						return true;
				}
}
