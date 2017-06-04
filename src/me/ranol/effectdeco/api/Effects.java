package me.ranol.effectdeco.api;

import com.avaje.ebean.validation.NotNull;
import me.ranol.effectdeco.abstraction.Effect;
import me.ranol.effectdeco.api.effects.EffAddHealth;

import java.util.Optional;

public class Effects {
    private long id = 0;

    private Effects() {
    }

    private final Registry<Long, Effect> registry = new Registry<>(e -> id++);

    public static void install() {
        register(EffAddHealth.INSTANCE);
    }

    private interface Singleton {
        Effects INSTANCE = new Effects();
    }

    @NotNull
    public static Optional<Effect> byId(long id) {
        return Singleton.INSTANCE.registry.valueOf(id);
    }

    @NotNull
    public static long registerAndGetId(Effect effect) {
        register(effect);
        return idOf(effect).orElse(-1L);
    }

    @NotNull
    public static Optional<Long> idOf(Effect effect) {
        return Singleton.INSTANCE.registry.keyOf(effect);
    }

    @NotNull
    public static boolean isRegistered(long id) {
        return Singleton.INSTANCE.registry.isKeyRegistered(id);
    }


    @NotNull
    public static boolean isRegistered(Effect effect) {
        return Singleton.INSTANCE.registry.isValueRegistered(effect);
    }

    @NotNull
    public static boolean register(Effect effect) {
        return Singleton.INSTANCE.registry.register(effect);
    }
}