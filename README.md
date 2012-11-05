SupernaturalRaces
=================

TODO:
* Add more races and improve spells to feature instant spells, delayed spells, distance spells, and effect spells!

SupernaturalRaces is a plugin developed for NovusCraft (MC-NC.CO).
It features Races, Spells and Event classes.  Each race is highly configurable and can have multiple skills and events
attached to it.  Each player starts out as a Human which is a representation of the Player class from Bukkit.
Players then have the option of converting to another type of class with the /convert command.

## Races
* Angel: 10/1000
   - Takes 75% damage during DAY
   - Takes 125% damage during NIGHT
   - Takes 0 damage from falling
   - Soar: Jump higher than normal. Requires 100 Power + 1 FEAHTER.
   - Luminatus: Night vision for 30 seconds. Requires 150 Power + 1 GLOWSTONE_DUST. 
   - Blind: Blind nearby players for 30 seconds. Requires 200 Power + 1 SUGAR.
* Demon
   - Takes 0 damage from FIRE/LAVA
   - Takes 125% damage when hurt in WATER
   - FireRing: Cast a ring of fire around you. Requires 300 Power + 1 FIREBALL.
   - Explode: Damage nearby players by 5.  Radius of 5.  Requires 200 Power + SULPHUR.
   - ShockHeal: Get healed 50-75%.  Requires 100 Power + GLOWSTONE_DUST.
* Priest
   - Takes 80% damage.
   - Deals 125% damage against UNDEAD races.
   - HolySpirit: 65% chance to avoid attacks for 30 SECONDS. Requires 400 Power + SUGAR.
   - Cure: Cure yourself of all maladies. Requires 100 Power + CLAY_BALL.
   - Renew: Buffer nearby players. Radius of 5. Requires 150 Power + GLOWSTONE_DUST.
* Vampire
   - Has a 2% chance to burn for 1 SECOND during DAY.
   - Has a 25% chance to make ENTITIES bleed for 30 SECONDS during NIGHT.
   - Deals 120% damage during NIGHT.
   - Receives SPEED_POTION_EFFECT during NIGHT.
   - Vanish: Vanish for 60 SECONDS. Requires 300 Power + SUGAR.
* Werewolf
   - Deals 125% damage during NIGHT
   - Scent: Track a player for 5 MINUTES. Requires 500 Power + COMPASS.
   - Train: Summon a wolf.  Requires 50 Power + BONE.
