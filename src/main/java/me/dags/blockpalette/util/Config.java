package me.dags.blockpalette.util;

import me.dags.blockpalette.color.ColorMode;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * @author dags <dags@dags.me>
 */
public class Config {

    public static boolean filter_variants = true;
    public static boolean match_textures = true;
    public static ColorMode color_mode = ColorMode.ADJACENT;
    public static int animation_speed = 5;
    public static int group_size = 3;
    public static int angle = 30;
    public static float leniency = 0.25F;

    private static Configuration cfg = new Configuration();

    public static int paletteModeId() {
        return ColorMode.getId(color_mode);
    }

    public static void init(File file) {
        cfg = new Configuration(file);
        cfg.load();
        match_textures = cfg.get("general", "match_textures", true).getBoolean();
        filter_variants = cfg.get("general", "filter_variants", true).getBoolean();
        animation_speed = cfg.get("general", "filter_variants", animation_speed).getInt();
        color_mode = ColorMode.fromId(cfg.get("color", "color_mode", ColorMode.getId(color_mode)).getInt());
        angle = cfg.get("color", "angle", angle).getInt();
        group_size = cfg.get("color", "group_size", group_size).getInt();
        leniency = (float) cfg.get("color", "leniency", leniency).getDouble();
        cfg.save();
    }

    public static void save() {
        cfg.get("general", "match_textures", match_textures).set(match_textures);
        cfg.get("general", "animation_speed", animation_speed).set(animation_speed);
        cfg.get("general", "filter_variants", filter_variants).set(filter_variants);
        cfg.get("color", "color_mode", paletteModeId()).set(paletteModeId());
        cfg.get("color", "angle", angle).set(angle);
        cfg.get("color", "group_size", group_size).set(group_size);
        cfg.get("color", "leniency", leniency).set(leniency);
        cfg.save();
    }
}