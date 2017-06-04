package me.ranol.effectdeco.api;

import com.avaje.ebean.validation.NotNull;
import me.ranol.effectdeco.abstraction.PlayerGrabber;
import me.ranol.effectdeco.api.accessories.grabbers.EmptyPlayerGrabber;
import me.ranol.effectdeco.api.accessories.grabbers.MultiPlayerGrabber;
import me.ranol.effectdeco.api.accessories.grabbers.PlayerHandItemGrabber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Accessories {
    private Accessories() {
    }

    private Registry<String, PlayerGrabber> registry = new Registry<>(PlayerGrabber::name);
    private List<String> grabbers = new ArrayList<>();
    private PlayerGrabber cache = EmptyPlayerGrabber.INSTANCE;

    private interface Singleton {
        Accessories INSTANCE = new Accessories();
    }

    public static PlayerGrabber grabber() {
        return Singleton.INSTANCE.cache;
    }

    private static void cachingGrabber() {
        List<PlayerGrabber> grabbers = selectedGrabbers();
        if (grabbers.size() == 0) {
            Singleton.INSTANCE.cache = EmptyPlayerGrabber.INSTANCE;
        } else if (grabbers.size() == 1) {
            Singleton.INSTANCE.cache = grabbers.get(0);
        } else {
            MultiPlayerGrabber tmp = new MultiPlayerGrabber();
            for (PlayerGrabber grabber : grabbers) {
                tmp.with(grabber);
            }
            Singleton.INSTANCE.cache = tmp;
        }
    }

    public static void selectGrabbers(String... strings) {
        Singleton.INSTANCE.grabbers.addAll(Arrays.asList(strings));
        cachingGrabber();
    }

    public static void deselectGrabbers(String... strings) {
        Singleton.INSTANCE.grabbers.removeAll(Arrays.asList(strings));
        cachingGrabber();
    }

    public static List<String> selectedGrabberNames() {
        return Singleton.INSTANCE.grabbers;
    }

    public static List<PlayerGrabber> selectedGrabbers() {
        return selectedGrabberNames().stream()
                                     .map(Accessories::grabberByName)
                                     .filter(Optional::isPresent)
                                     .map(Optional::get)
                                     .collect(Collectors.toList());
    }

    public static void installGrabbers() {
        register(EmptyPlayerGrabber.INSTANCE);
        register(PlayerHandItemGrabber.INSTANCE);
    }

    @NotNull
    public static Optional<PlayerGrabber> grabberByName(String name) {
        return Singleton.INSTANCE.registry.valueOf(name);
    }

    @NotNull
    public static boolean isRegistered(String name) {
        return Singleton.INSTANCE.registry.isKeyRegistered(name);
    }


    @NotNull
    public static boolean isRegistered(PlayerGrabber grabber) {
        return Singleton.INSTANCE.registry.isValueRegistered(grabber);
    }

    @NotNull
    public static boolean register(PlayerGrabber grabber) {
        return Singleton.INSTANCE.registry.register(grabber);
    }
}