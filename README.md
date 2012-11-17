SupernaturalRaces
=================

TODO:
* [User] Add some cool new races + spells!!! [Adaro, Lich, Ghost, Brute, Knight, Mage]
* [User] Work on shrines to convert.  Block Formations.
* [User] Make spells be cast by a WAND (BLAZE_ROD/STICK).

SupernaturalRaces is a plugin developed for NovusCraft (MC-NC.CO).
It features Races, Spells and Event classes.  Each race is highly configurable and can have multiple skills and events
attached to it.  Each player starts out as a Human which is a representation of the Player class from Bukkit.
Players then have the option of converting to another type of class with the /convert command.

## Races
* Angel:
   - Power: 1250
     - Gains 200% during DAY.
     - Gains 50% during NIGHT.
   - Effects:
     - Takes 75% damage during DAY
     - Takes 125% damage during NIGHT
     - Takes 0 damage from falling
   - Spells:
     - Soar: Jump higher than normal. Requires 100 Power + 3 FEAHTER.
     - Luminatus: Night vision for 30 seconds. Requires 150 Power + 1 GLOWSTONE_DUST. 
     - Blind: Blind nearby players for 30 seconds. Requires 200 Power + 1 SUGAR.
* Demon
   - Power: 750
     - Gains 15 in NETHER.
     - Gains 10 during NIGHT.
     - Gains 0 during DAY
   - Effects:
     - Takes 0 damage from FIRE/LAVA
     - Takes 125% damage when hurt in WATER
   - Spells:
     - FireRing: Cast a ring of fire around you. Requires 300 Power + 5 REDSTONE.
     - Explode: Damage nearby players by 5.  Radius of 5.  Requires 200 Power + 1 SULPHUR.
     - ShockHeal: Get healed 50%.  Requires 100 Power + 1 GLOWSTONE_DUST.
* Priest
   - Power: 1500
     - Gains 13 power ALWAYS.
   - Effects:
     - Takes 80% damage.
     - Deals 125% damage against UNDEAD races.
   - Spells:
     - HolySpirit: 65% chance to avoid attacks for 30 SECONDS. Requires 400 Power + 1 SUGAR.
     - Cure: Cure yourself of all maladies. Requires 100 Power + 1 CLAY_BALL.
     - Renew: Buffer nearby players. Radius of 5. Requires 150 Power + 1 GLOWSTONE_DUST.
* Vampire
   - Power: 1000 
     - 250% during NIGHT.
     - 0% power during DAY.
     - Takes 300% damage from WOODEN_SWORDS.
   - Effects:
     - Has a 10% chance to burn for 1 SECOND during DAY when SPRINTING.
     - Has a 25% chance to make ENTITIES bleed for 30 SECONDS during NIGHT.
     - Deals 120% damage during NIGHT.
     - Receives SPEED_POTION_EFFECT during NIGHT.
   - Spells:
     - Vanish: Vanish for 60 SECONDS. Requires 300 Power + 1 SUGAR.
* Werewolf
   - Power: 750
     - 250% during NIGHT.
     - 0% during DAY.
   - Effects:
     - Gains 25 power per 10 seconds during NIGHT.
     - Gains 0 power per 10 seconds during DAY.
     - Deals 125% damage during NIGHT.
     - Takes 200% damage from IRON_SWORDS.
   - Spells:
     - Scent: Track a player for 5 MINUTES. Requires 500 Power + 1 COMPASS.
     - Train: Summon a wolf.  Requires 50 Power + 4 BONE.
* Mage
   - Power: 500
     - 200% at all times.
   - Spells:
     - Freeze: Freeze a player in ICE for 20 seconds in ice.  Requires 150 Power + 1 COMPASS.
     - Combust: Track a player for 5 MINUTES. Requires 500 Power + 1 COMPASS.
     - IceWalk: Summon a wolf.  Requires 50 Power + 4 BONE.
