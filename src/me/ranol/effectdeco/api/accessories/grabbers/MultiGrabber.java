package me.ranol.effectdeco.api.accessories.grabbers;

import me.ranol.effectdeco.abstraction.Accessory;
import me.ranol.effectdeco.abstraction.Grabber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MultiGrabber<T> implements Grabber<T> {
    private List<Grabber<T>> grabbers = new ArrayList<>();
    private String cachedName;

    public MultiGrabber with(Grabber<T> grabber) {
        if (grabber != this && grabber != null) {
            grabbers.add(grabber);
            cachingName();
        }
        return this;
    }

    private void cachingName() {
        cachedName = grabbers.stream().map(Grabber::name).collect(Collectors.joining(", "));
    }

    public MultiGrabber without(Grabber<T> grabber) {
        if (grabber != null) {
            grabbers.remove(grabber);
            cachingName();
        }
        return this;
    }

    @Override
    public String name() {
        return "MultiGrabber{" + cachedName + '}';
    }

    @Override
    public Collection<? extends Accessory> grabAccessories(T t) {
        return grabbers.stream()
                       .map(grabber -> grabber.grabAccessories(t))
                       .flatMap(Collection::stream)
                       .collect(Collectors.toSet());
    }
}