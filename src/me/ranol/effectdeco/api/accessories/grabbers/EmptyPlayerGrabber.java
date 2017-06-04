package me.ranol.effectdeco.api.accessories.grabbers;

import me.ranol.effectdeco.abstraction.Accessory;
import me.ranol.effectdeco.abstraction.PlayerGrabber;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Collections;

public class EmptyPlayerGrabber implements PlayerGrabber {
    public static final EmptyPlayerGrabber INSTANCE = new EmptyPlayerGrabber();

    private EmptyPlayerGrabber() {
    }

    @Override
    public String name() {
        return "EmptyPlayerGrabber";
    }

    @Override
    public Collection<Accessory> grabAccessories(Player player) {
        return Collections.emptySet();
    }
}