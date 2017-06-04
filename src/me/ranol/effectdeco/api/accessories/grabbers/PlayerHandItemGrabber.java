package me.ranol.effectdeco.api.accessories.grabbers;

import me.ranol.effectdeco.abstraction.Accessory;
import me.ranol.effectdeco.abstraction.PlayerGrabber;
import org.bukkit.entity.Player;

import java.util.Collection;

public class PlayerHandItemGrabber implements PlayerGrabber {
    public static final PlayerHandItemGrabber INSTANCE = new PlayerHandItemGrabber();

    private PlayerHandItemGrabber() {
    }

    @Override
    public String name() {
        return "PlayerHandItemGrabber";
    }

    @Override
    public Collection<? extends Accessory> grabAccessories(Player player) {
        return ItemGrabber.INSTANCE.grabAccessories(player.getInventory().getItemInMainHand());
    }
}