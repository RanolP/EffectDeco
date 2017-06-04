package me.ranol.effectdeco.api.accessories;

import me.ranol.effectdeco.abstraction.Accessory;
import me.ranol.effectdeco.abstraction.EffectData;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DefaultAccessory implements Accessory {
    private boolean equiped;
    private EffectData data;
    private Set<Long> effectSet = new HashSet<>();

    public DefaultAccessory(boolean equiped, EffectData data, @Nullable Set<Long> effectSet) {
        this.equiped = equiped;
        this.data = data == null ? EffectData.EMPTY : data;
        if (effectSet != null && !effectSet.isEmpty()) {
            this.effectSet.addAll(effectSet);
        }
    }

    protected final void setEquiped(boolean equiped) {
        this.equiped = equiped;
    }

    protected final void setData(EffectData effectData) {
        this.data = effectData;
    }

    protected final void addEffect(long id) {
        effectSet.add(id);
    }

    protected final void addEffects(long... ids) {
        for (long id : ids) {
            addEffect(id);
        }
    }

    protected final void removeEffect(long id) {
        effectSet.remove(id);
    }

    protected final void removeEffects(long... ids) {
        for (long id : ids) {
            removeEffect(id);
        }
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public final EffectData getData() {
        return data;
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public final Collection<Long> getBindedEffects() {
        return effectSet;
    }

    @Contract(pure = true)
    @Override
    public final boolean isEquiped() {
        return equiped;
    }
}
