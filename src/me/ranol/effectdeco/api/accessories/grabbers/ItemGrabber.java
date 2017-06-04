package me.ranol.effectdeco.api.accessories.grabbers;

import me.ranol.effectdeco.abstraction.Accessory;
import me.ranol.effectdeco.abstraction.Grabber;
import me.ranol.effectdeco.api.accessories.EmptyAccessory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class ItemGrabber implements Grabber<ItemStack> {
    public static final ItemGrabber INSTANCE = new ItemGrabber();

    private ItemGrabber() {
    }

    @Override
    public String name() {
        return "ItemGrabber";
    }

    @Override
    public Collection<? extends Accessory> grabAccessories(ItemStack stack) {
        if (stack == null) {
            return EmptyAccessory.SET_INSTANCE;
        }
        return null;
    }
}