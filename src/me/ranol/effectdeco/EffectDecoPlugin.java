package me.ranol.effectdeco;

import me.ranol.effectdeco.api.Effects;
import org.bukkit.plugin.java.JavaPlugin;

public class EffectDecoPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Util.console("&a[EffectDeco] &b플러그인이 활성화되었습니다. v" + getDescription().getVersion());
        Effects.install();
    }

    @Override
    public void onDisable() {
        Util.console("&a[EffectDeco] &b플러그인이 비활성화되었습니다. v" + getDescription().getVersion());
    }
}