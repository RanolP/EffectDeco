package me.ranol.effectdeco.api.checkers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public final class RespawnChecker extends DefaultChecker implements Listener {
    public static final RespawnChecker INSTANCE = new RespawnChecker();

    private RespawnChecker() {
        super("Respawn");
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        works(event);
    }
}