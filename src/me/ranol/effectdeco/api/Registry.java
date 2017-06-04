package me.ranol.effectdeco.api;

import com.avaje.ebean.validation.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class Registry<K, V> {
    private final Map<K, V> registry = new HashMap<>();
    private final Function<V, K> keyGenerator;

    public Registry(Function<V, K> keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @NotNull
    public Optional<K> keyOf(V value) {
        return registry.entrySet()
                       .stream()
                       .filter(e -> Objects.equals(e.getValue(), value))
                       .findFirst()
                       .map(Map.Entry::getKey);
    }

    @NotNull
    public Optional<V> valueOf(K key) {
        return Optional.ofNullable(registry.get(key));
    }

    @NotNull
    public boolean isKeyRegistered(K key) {
        return registry.containsKey(key);
    }

    @NotNull
    public boolean isValueRegistered(V value) {
        return registry.containsValue(value);
    }

    @NotNull
    public boolean register(V value) {
        if (!isValueRegistered(value)) {
            registry.put(keyGenerator.apply(value), value);
            return true;
        }
        return false;
    }
}