package com.TeamNovus.Bounties.Player;

import java.io.Serializable;

import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.Id;


public class BContributor implements Serializable {
	private static final long serialVersionUID = 1L;
			
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "amount")
	private int amount;
	
	public BContributor(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
