package me.ranol.effectdeco.abstraction;

import java.util.Collection;

public interface Grabber<T> {
    String name();

    Collection<? extends Accessory> grabAccessories(T t);
}