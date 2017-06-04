package me.ranol.effectdeco.abstraction;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class EffectData {
    public static final EffectData EMPTY = new EffectData(null);
    private final Map<String, Object> map = new HashMap<>();
    private final Player player;

    public EffectData(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return this == EMPTY || player == null;
    }

    public Player player() {
        return player;
    }

    @NotNull
    public <T> Optional<T> get(@Nullable EffectKey<T> key) {
        if (key == null || !map.containsKey(key.name())) {
            return Optional.empty();
        }
        return Optional.ofNullable(map.get(key.name())).map(key.keyType()::cast);
    }

    @Contract("null -> false")
    public boolean has(@Nullable EffectKey<?> key) {
        if (key == null) { return false; }
        return map.containsKey(key.name());
    }

    @NotNull
    public Optional<String> getString(@Nullable EffectKey<String> key) {
        return get(key);
    }

    @NotNull
    public Optional<Integer> getInt(@Nullable EffectKey<Integer> key) {
        return get(key);
    }

    @NotNull
    public Optional<Long> getLong(@Nullable EffectKey<Long> key) {
        return get(key);
    }

    @NotNull
    public Optional<Double> getDouble(@Nullable EffectKey<Double> key) {
        return get(key);
    }

    @NotNull
    public <T> Optional<T[]> getArray(@Nullable EffectKey<T[]> key) {
        return get(key);
    }

    @NotNull
    public <T> Optional<List<T>> getList(@Nullable EffectKey<List<T>> key) {
        return get(key);
    }

    @NotNull
    public Optional<Boolean> getBoolean(@Nullable EffectKey<Boolean> key) {
        return get(key);
    }

    public <T> void set(@NotNull EffectKey<T> key, @Nullable T t) {
        if (t == null) {
            map.remove(key.name());
        } else {
            map.put(key.name(), t);
        }
    }
}