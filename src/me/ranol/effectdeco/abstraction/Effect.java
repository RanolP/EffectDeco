package me.ranol.effectdeco.abstraction;

import me.ranol.effectdeco.api.Effects;

public interface Effect {
    default long id() {
        return Effects.registerAndGetId(this);
    }

    String name();

    void work(EffectData data);

    default void disable(EffectData data) {

    }
}