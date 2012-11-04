package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.EntityWolf;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.block.Action;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Train implements SNSpell {
	
	@Override
	public String name() {
		return "WolfSummon";
	}

	@Override
	public String desc() {
		return "Summon a wolf to aid you in your struggles!";
	}

	@Override
	public List<Action> actions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(Action.LEFT_CLICK_AIR);
		actions.add(Action.LEFT_CLICK_BLOCK);		
		return actions;
	}

	@Override
	public List<Material> bindings() {
		List<Material> bindings = new ArrayList<Material>();
		bindings.add(Material.BONE);
		return bindings;
	}

	@Override
	public Integer power() {
		return 20;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player sender) {
		Wolf wolf = (Wolf)sender.getWorld().spawnEntity(sender.getLocation(), EntityType.WOLF);
		wolf.setTamed(true);
		wolf.setOwner(sender);
		wolf = setColor(wolf, DyeColor.YELLOW);
		return true;
	}
	
    public static Wolf setColor(Wolf wolf, DyeColor color){
        EntityWolf ent = (EntityWolf) ((CraftLivingEntity)wolf).getHandle();
        ent.setCollarColor(color.getData());
        return wolf;
    }
}
