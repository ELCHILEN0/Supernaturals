package com.TeamNovus.Supernaturals.Util;

import java.util.Map;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class DamageUtil {
	private static Random random = new Random();
	private static boolean randomness = true;

	private static int returnChance(int aStart, int aEnd) {
		if (aStart > aEnd) {
			aEnd = aStart;
		}
		long range = (long) aEnd - (long) aStart + 1;
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}

	public void toggleRandom(boolean toggle) {
		randomness = toggle;
	}

	public static int getDamage(ItemStack helmet, ItemStack chestplate,
			ItemStack leggings, ItemStack boots, Integer dmg,
			DamageCause cause, boolean blocking) {
		ItemStack[] items = { helmet, chestplate, leggings, boots };
		int[] armReduce = getArmorReduce(items, cause);
		int[] encReduce = getEnchantmentsReducement(items, cause);
		double armour = 0;
		double damage = dmg;
		double enc = 0;
		for (int in : armReduce)
			armour = armour + in;
		for (int in : encReduce)
			enc = enc + in;
		if (enc > 25)
			enc = 25;
		damage = damage - (damage * armour / 25);
		if (randomness == true)
			enc = Math.ceil(returnChance((int) ((enc) / 2) * 10,
					(int) (enc) * 10));
		if (enc > 20)
			enc = 20;
		enc = enc * 4;
		damage = damage - (damage * enc / 100);
		if (blocking == true)
			damage = damage / 2;
		return (int) Math.round(damage);
	}

	public static int getDamage(Player player, Integer damage, DamageCause cause) {
		return getDamage(player.getInventory().getHelmet(), player
				.getInventory().getChestplate(), player.getInventory()
				.getLeggings(), player.getInventory().getBoots(), damage,
				cause, player.isBlocking());
	}

	private static int[] getArmorReduce(ItemStack[] item, DamageCause dmg) {
		int[] damage = { 0, 0, 0, 0 };
		if (dmg.equals(DamageCause.ENTITY_ATTACK)
				|| dmg.equals(DamageCause.FIRE)
				|| dmg.equals(DamageCause.ENTITY_EXPLOSION)
				|| dmg.equals(DamageCause.BLOCK_EXPLOSION)
				|| dmg.equals(DamageCause.CONTACT)
				|| dmg.equals(DamageCause.LAVA)
				|| dmg.equals(DamageCause.PROJECTILE)) {
			int cur = 0;
			for (ItemStack stack : item) {
				if (stack != null)
					damage[cur] = getArmorValue(stack);
				cur = cur + 1;
			}
		}
		return damage;
	}

	private static int[] getEnchantmentsReducement(ItemStack[] item,
			DamageCause dmg) {
		int[] damage = { 0, 0, 0, 0 };
		int cur = 0;
		for (ItemStack stack : item) {
			if (stack != null) {
				Map<Enchantment, Integer> enchants = stack.getEnchantments();
				for (Enchantment enchant : enchants.keySet()) {
					if ((dmg.equals(DamageCause.BLOCK_EXPLOSION) || dmg
							.equals(DamageCause.ENTITY_EXPLOSION)
							&& enchant
									.equals(Enchantment.PROTECTION_EXPLOSIONS)))
						damage[cur] = damage[cur]
								+ (int) Math.floor((6 + enchants.get(enchant)
										* enchants.get(enchant)) / 2);
					if (dmg.equals(DamageCause.FALL)
							&& enchant.equals(Enchantment.PROTECTION_FALL))
						damage[cur] = damage[cur] + enchants.get(enchant) * 2;
					if (dmg.equals(DamageCause.PROJECTILE)
							&& enchant
									.equals(Enchantment.PROTECTION_PROJECTILE))
						damage[cur] = damage[cur]
								+ (int) Math.floor((6 + enchants.get(enchant)
										* enchants.get(enchant)) / 2);
					if ((dmg.equals(DamageCause.LAVA)
							|| dmg.equals(DamageCause.LIGHTNING)
							|| dmg.equals(DamageCause.FIRE) || dmg
								.equals(DamageCause.LAVA))
							&& enchant.equals(Enchantment.PROTECTION_FIRE))
						damage[cur] = damage[cur]
								+ (int) Math.floor((6 + enchants.get(enchant)
										* enchants.get(enchant)) / 2);
					if (enchant.equals(Enchantment.PROTECTION_ENVIRONMENTAL))
						damage[cur] = damage[cur]
								+ (int) Math.floor((6 + enchants.get(enchant)
										* enchants.get(enchant)) / 2);
				}
			}
		}
		return damage;
	}

	private static int getArmorValue(ItemStack armor) {
		Material mat = armor.getType();
		if (mat == Material.LEATHER_HELMET || mat == Material.LEATHER_BOOTS
				|| mat == Material.GOLD_BOOTS
				|| mat == Material.CHAINMAIL_BOOTS)
			return 1;
		if (mat == Material.LEATHER_LEGGINGS || mat == Material.GOLD_HELMET
				|| mat == Material.CHAINMAIL_HELMET
				|| mat == Material.IRON_HELMET || mat == Material.IRON_BOOTS)
			return 2;
		if (mat == Material.LEATHER_CHESTPLATE || mat == Material.GOLD_LEGGINGS
				|| mat == Material.DIAMOND_BOOTS
				|| mat == Material.DIAMOND_HELMET)
			return 3;
		if (mat == Material.CHAINMAIL_LEGGINGS)
			return 4;
		if (mat == Material.GOLD_CHESTPLATE
				|| mat == Material.CHAINMAIL_CHESTPLATE
				|| mat == Material.IRON_LEGGINGS)
			return 5;
		if (mat == Material.IRON_LEGGINGS || mat == Material.DIAMOND_LEGGINGS)
			return 6;
		if (mat == Material.DIAMOND_CHESTPLATE)
			return 8;
		return 0;
	}

}
