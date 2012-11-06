SupernaturalRaces
=================

TODO:
* [Dev] Add a CommandManager that uses the /supernaturals [sn] sub-command.
* [Dev] Add onSpellCast(), onSpellPreprocess(), onSpellPostprocess()
* [Dev/User] Change power regen to be every tick and to check if the player meets the requirements.

SupernaturalRaces is a plugin developed for NovusCraft (MC-NC.CO).
It features Races, Spells and Event classes.  Each race is highly configurable and can have multiple skills and events
attached to it.  Each player starts out as a Human which is a representation of the Player class from Bukkit.
Players then have the option of converting to another type of class with the /convert command.

## Races
* Angel:
   - Power: 1250
     - Gains 20 during DAY.
     - Gains 5 during NIGHT.
   - Effects:  
     - Takes 75% damage during DAY
     - Takes 125% damage during NIGHT
     - Takes 0 damage from falling
   - Spells:
     - Soar: Jump higher than normal. Requires 100 Power + 3 FEAHTER.
     - Luminatus: Night vision for 30 seconds. Requires 150 Power + 1 GLOWSTONE_DUST. 
     - Blind: Blind nearby players for 30 seconds. Requires 200 Power + 1 SUGAR.
* Demon
   - Power: 1000
     - Gains 15 in NETHER.
     - Gains 10 during NIGHT.
     - Gains 0 during DAY
   - Effects:
     - Takes 0 damage from FIRE/LAVA
     - Takes 125% damage when hurt in WATER
   - Spells:
     - FireRing: Cast a ring of fire around you. Requires 300 Power + 5 REDSTONE.
     - Explode: Damage nearby players by 5.  Radius of 5.  Requires 200 Power + 1 SULPHUR.
     - ShockHeal: Get healed 50-75%.  Requires 100 Power + 1 GLOWSTONE_DUST.
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
   - Power: 1000 - 10
     - 250% during NIGHT.
     - 0% power during DAY.
   - Effects:
     - Has a 2% chance to burn for 1 SECOND during DAY.
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
     - Deals 125% damage during NIGHT
   - Spells:
     - Scent: Track a player for 5 MINUTES. Requires 500 Power + 1 COMPASS.
     - Train: Summon a wolf.  Requires 50 Power + 4 BONE.
