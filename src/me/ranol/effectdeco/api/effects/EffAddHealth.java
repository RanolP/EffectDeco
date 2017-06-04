package me.ranol.effectdeco.api.effects;

import me.ranol.effectdeco.abstraction.Effect;
import me.ranol.effectdeco.abstraction.EffectData;
import me.ranol.effectdeco.api.Keys;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;

public class EffAddHealth implements Effect {
    public static final EffAddHealth INSTANCE = new EffAddHealth();

    private EffAddHealth() {
    }

    @Override
    public String name() {
        return "AddHealth";
    }

    @Override
    public void work(EffectData data) {
        data.get(Keys.AMOUNT).ifPresent(d -> {
            data.player()
                .getAttribute(Attribute.GENERIC_MAX_HEALTH)
                .addModifier(
                        new AttributeModifier("EffectDeco_EffectAddHealth", d, AttributeModifier.Operation.ADD_NUMBER));
        });
    }

    @Override
    public void disable(EffectData data) {
        AttributeInstance attribute = data.player().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attribute.getModifiers()
                 .stream()
                 .filter(mod -> mod.getName().equals("EffectDeco_EffectAddHealth"))
                 .forEach(attribute::removeModifier);
    }
}
