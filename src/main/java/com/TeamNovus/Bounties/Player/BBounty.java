package com.TeamNovus.Bounties.Player;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.Id;
import com.TeamNovus.Persistence.Annotations.Relationships.OneToMany;

@Table(name = "b_bounties")
public class BBounty {
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "target")
	private String target;
	
	@OneToMany
	private ArrayList<BContributor> contributors = new ArrayList<BContributor>();
	
	@Column(name = "start_date")
	private Timestamp startTime;
	
	public BBounty(String target, BContributor contributor) {
		this.target = target;
		contributors.add(contributor);
		
		startTime = new Timestamp(System.currentTimeMillis());
	}
	
	public String getTarget() {
		return target;
	}
	
	public BBounty addContributor(BContributor contributor) {
		contributors.add(contributor);
		
		return this;
	}

	public BContributor getContributor(String name) {
		for(BContributor contributor : contributors) {
			if(contributor.getName().equals(name)) {
				return contributor;
			}
		}
		
		return null;
	}
	
	public int getTotalReward() {
		int total = 0;
		
		for(BContributor contributor : contributors) {
			total += contributor.getAmount();
		}
		
		return total;
	}
}
