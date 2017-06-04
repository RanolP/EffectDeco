package me.ranol.effectdeco.api.accessories;

import me.ranol.effectdeco.abstraction.Accessory;
import me.ranol.effectdeco.abstraction.EffectData;

import java.util.Collections;
import java.util.Set;

public class EmptyAccessory extends DefaultAccessory {
    public static final EmptyAccessory INSTANCE = new EmptyAccessory();
    public static final Set<? extends Accessory> SET_INSTANCE = Collections.singleton(INSTANCE);

    private EmptyAccessory() {
        super(false, EffectData.EMPTY, null);
    }
}