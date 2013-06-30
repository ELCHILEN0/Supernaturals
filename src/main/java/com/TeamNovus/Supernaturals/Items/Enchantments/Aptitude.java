package com.TeamNovus.Supernaturals.Items.Enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Custom.Enchantment.CustomEnchantment;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Models.ItemBag;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;
import com.TeamNovus.Supernaturals.Player.Statistics.Cooldown;

public class Aptitude extends CustomEnchantment {

	public Aptitude(int id) {
		super(id);
	}

	@Override
	public boolean canEnchantItem(ItemStack itemStack) {
		if(itemStack.getType().equals(Material.BLAZE_ROD))
			return true;
		
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment ench) {
		return false;
	}

	@Override
	public String getEnchantmentName(int level) {
		switch (level) {
		case 1:
			return ChatColor.GRAY + "Aptitude I";
			
		case 2:
			return ChatColor.GRAY + "Aptitude II";
			
		case 3:
			return ChatColor.GRAY + "Aptitude III";
			
		case 4:
			return ChatColor.GRAY + "Aptitude IV";
			
		case 5:
			return ChatColor.GRAY + "Aptitude V";
			
		default:
			return ChatColor.GRAY + "Aptitude enchantment.level." + level;
		}
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ALL;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public void onInteract(PlayerInteractEvent event, int level) {	
		SNPlayer player = SNPlayers.i.get(event.getPlayer());
		
		if(Supernaturals.getPlugin().getConfig().getStringList("settings.disabled-worlds").contains(player.getPlayer().getWorld().getName().toLowerCase())) {
			player.sendMessage(ChatColor.RED + "Supernaturals is disabled in " + ChatColor.YELLOW + player.getPlayer().getWorld().getName() + ChatColor.RED + "!");
			return;
		}
		
		// Bind/Switch:
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			player.setNextBinding();

			if (player.getSelectedPower() != null) {
				player.sendMessage(ChatColor.GREEN + "Wand bound to " + ChatColor.YELLOW + player.getSelectedPower().getName() + ChatColor.GREEN + "!");
			}
		}

		// Cast:
		if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (player.getSelectedPower() != null) {
				Power power = player.getSelectedPower();
					
				Reagent required = new Reagent(power.getRequired());
				Reagent consume = new Reagent(power.getConsume());
				
				required.setManaCost(required.getManaCost() - level);
				consume.setManaCost(required.getManaCost() - level);
				
				if(player.getRemainingCooldownTicks(power) > 0) {
					player.sendMessage(ChatColor.RED + "You must wait " + ChatColor.YELLOW + player.getRemainingCooldownTicks(power) / 20.0 + ChatColor.RED + " seconds to cast this spell!");
				} else if (required.has(player)) {
					if (power.cast(player)) {
						consume.consume(player);
						player.setCooldown(new Cooldown(power, power.getCooldown()));
					}
				} else {
					player.sendMessage(ChatColor.BLUE + "Requires:");
					if (required.getExpCost() != 0)
						player.sendMessage(ChatColor.BLUE + "   Experience: " + ChatColor.YELLOW + required.getExpCost());
					if (required.getHealthCost() != 0)
						player.sendMessage(ChatColor.BLUE + "   Health: " + ChatColor.YELLOW + required.getHealthCost());
					if (required.getHungerCost() != 0)
						player.sendMessage(ChatColor.BLUE + "   Hunger: " + ChatColor.YELLOW + required.getHungerCost());
					if (required.getManaCost() != 0)
						player.sendMessage(ChatColor.BLUE + "   Mana: " + ChatColor.YELLOW + required.getManaCost());
					if (required.getItemBagCost() != new ItemBag())
						player.sendMessage(ChatColor.BLUE + "   Items: " + ChatColor.YELLOW + required.getItemBagCost().toString());
				}
			} else {
				player.sendMessage(ChatColor.RED + "Your wand is not bound to a power!");
			}
		}		
	}

	@Override
	public void onInteractEntity(PlayerInteractEntityEvent event, int level) {
		
	}

	@Override
	public void onBlockDamage(BlockDamageEvent event, int level) {
	
	}

	@Override
	public void onBlockBreak(BlockBreakEvent event, int level) {
		
	}

	@Override
	public void onDamageEntity(EntityDamageEntityEvent event, int level) {
		
	}


}
