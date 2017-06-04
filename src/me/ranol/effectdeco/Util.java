package me.ranol.effectdeco;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Util {
    public static void console(String msg) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
