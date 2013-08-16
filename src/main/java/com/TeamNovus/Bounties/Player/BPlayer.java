package com.TeamNovus.Bounties.Player;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.Bounties.BBounties;
import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.Id;

@Table(name = "b_players")
public class BPlayer implements Serializable {
	private static final long serialVersionUID = 1L;
			
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "bounty_target")
	private String bountyTarget;
	
	public BPlayer() {
		
	}
	
	public BPlayer(Player p) { 
		this();
		this.name = p.getName();
	}
	
	/**
	 * Get the players id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the players id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the players name.
	 * 
	 * @return The players name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the players name.
	 * 
	 * @param name - The players new name. 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBountyTarget() {
		return bountyTarget;
	}
	
	public BPlayer setBountyTarget(String bountyTarget) {
		this.bountyTarget = bountyTarget;
		
		return this;
	}
	
	public BBounty getBounty() {
		return BBounties.i.get(bountyTarget);
	}
	
	public BPlayer setBounty(BBounty bounty) {
		this.bountyTarget = bounty.getTarget();
		
		return this;
	}
	
	// Bukkit:
	public Player getPlayer() {
		return Bukkit.getPlayerExact(name);
	}

	public Boolean isOnline() {
		return getPlayer() != null;
	}	

	public Boolean isOffline() {
		return !(isOnline());
	}

	public void sendMessage(String message) {
		if (isOnline()) {
			getPlayer().sendMessage(message);
		}
	}

	public void sendMessage(String[] messages) {
		for (String message : messages) {
			sendMessage(message);
		}
	}

	// Java:
	public String toString() {
		return "SNPlayer [id=" + id + ", name=" + name + "]";
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BPlayer other = (BPlayer) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
