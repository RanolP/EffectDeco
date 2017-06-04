package me.ranol.effectdeco.abstraction;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public final class EffectKey<T> {
    private final String name;
    private final Class<T> type;

    public EffectKey(@NotNull String name, @NotNull Class<T> type) {
        this.name = name;
        this.type = type;
    }


    @NotNull
    public static EffectKey<String> ofString(@NotNull String name) {
        return new EffectKey<>(name, String.class);
    }

    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> EffectKey<List<T>> ofList(@NotNull String name) {
        return new EffectKey<List<T>>(name, (Class<List<T>>) ((Class) List.class));
    }

    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> EffectKey<Set<T>> ofSet(@NotNull String name) {
        return new EffectKey<Set<T>>(name, (Class<Set<T>>) ((Class) Set.class));
    }

    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> EffectKey<Collection<T>> ofCollection(@NotNull String name) {
        return new EffectKey<Collection<T>>(name, (Class<Collection<T>>) ((Class) Collection.class));
    }


    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> EffectKey<T> ofDynamic(@NotNull String name, @NotNull T object) {
        return new EffectKey<T>(name, (Class<T>) object.getClass());
    }

    @NotNull
    public static EffectKey<Integer> ofInt(@NotNull String name) {
        return new EffectKey<>(name, Integer.class);
    }

    @NotNull
    public static EffectKey<Long> ofLong(@NotNull String name) {
        return new EffectKey<>(name, Long.class);
    }

    @NotNull
    public static EffectKey<Double> ofDouble(@NotNull String name) {
        return new EffectKey<>(name, Double.class);
    }

    @NotNull
    public static EffectKey<Boolean> ofBoolean(@NotNull String name) {
        return new EffectKey<>(name, Boolean.class);
    }

    @NotNull
    @Contract(pure = true)
    public String name() {
        return name;
    }

    @NotNull
    @Contract(pure = true)
    public Class<T> keyType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        EffectKey<?> effectKey = (EffectKey<?>) o;

        return name.equals(effectKey.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "EffectKey{" + "name='" + name + '\'' + ", type=" + type + '}';
    }
}