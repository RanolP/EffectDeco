package me.ranol.effectdeco.api.checkers;

import me.ranol.effectdeco.abstraction.*;
import me.ranol.effectdeco.api.Accessories;
import me.ranol.effectdeco.api.Effects;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public abstract class DefaultChecker implements Checker {
    private Set<Long> effects = new HashSet<>();
    private final String name;

    public DefaultChecker(String name) {
        this.name = name;
    }

    @Override
    public final String name() {
        return name;
    }

    @Override
    public final void work(long id, EffectData data) {
        if (effects.contains(id)) {
            Effects.byId(id).ifPresent(effect -> effect.work(data));
        }
    }

    @Override
    public final void disable(long id, EffectData data) {
        if (effects.contains(id)) {
            Effects.byId(id).ifPresent(effect -> effect.disable(data));
        }
    }

    protected final void works(@NotNull Accessory accessory, Player player) {
        accessory.getBindedEffects().forEach(id -> work(id, accessory.getData()));
    }

    protected final void works(@NotNull PlayerGrabber grabber, Player player) {
        grabber.grabAccessories(player).forEach(accessory -> works(accessory, player));
    }

    protected final void works(Player player) {
        works(Accessories.grabber(), player);
    }

    protected final void works(PlayerEvent e) {
        works(e.getPlayer());
    }

    @Override
    public final void register(Effect effect) {
        if (effect != null) {
            effects.add(effect.id());
        }
    }

    @Override
    public final void register(Effect... effects) {
        Checker.super.register(effects);
    }
}