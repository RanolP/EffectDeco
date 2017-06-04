package me.ranol.effectdeco.abstraction;

public interface Checker {
    String name();

    void work(long id, EffectData data);

    void disable(long id, EffectData data);

    void register(Effect effect);

    default void register(Effect... effects) {
        for (Effect effect : effects) {
            register(effect);
        }
    }
}