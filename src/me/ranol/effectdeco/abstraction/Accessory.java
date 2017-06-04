package me.ranol.effectdeco.abstraction;


import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface Accessory {
    @NotNull EffectData getData();

    @NotNull Collection<Long> getBindedEffects();

    boolean isEquiped();
}