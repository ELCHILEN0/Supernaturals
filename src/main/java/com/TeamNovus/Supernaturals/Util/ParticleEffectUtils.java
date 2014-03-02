package com.TeamNovus.Supernaturals.Util;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class ParticleEffectUtils {

	public static void fireworkParticleShower(Location loc, Color color) {
        loc.setPitch(-90);

        Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta meta = firework.getFireworkMeta();
        FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(color).with(Type.BALL_LARGE).trail(true).build();
   
        meta.addEffect(effect);
        meta.addEffect(effect);
        meta.setPower(0);
        
        firework.setFireworkMeta(meta);
    }

}
