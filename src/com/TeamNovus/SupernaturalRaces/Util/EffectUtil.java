package com.TeamNovus.SupernaturalRaces.Util;

import net.minecraft.server.DataWatcher;
import net.minecraft.server.EntityLiving;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;

public class EffectUtil {
	
	public static void addPotionGraphicalEffect(LivingEntity entity, int color, int duration) {
        final EntityLiving el = ((CraftLivingEntity) entity).getHandle();
        final DataWatcher dw = el.getDataWatcher();
        
        dw.watch(8, Integer.valueOf(color));
 
        Bukkit.getScheduler().scheduleSyncDelayedTask(SupernaturalRaces.getPlugin(), new Runnable() {
            public void run() {
                int c = 0;
                if (!el.effects.isEmpty()) {
                    c = net.minecraft.server.PotionBrewer.a(el.effects.values());
                }
                dw.watch(8, Integer.valueOf(c));
            }
        }, duration);
    }
	
	public static void playEffect(Entity entity, Effect effect) {
		entity.getWorld().playEffect(entity.getLocation(), effect, 0);
	}

}
