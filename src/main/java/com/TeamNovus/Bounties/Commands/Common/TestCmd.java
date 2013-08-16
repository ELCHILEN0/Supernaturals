package com.TeamNovus.Bounties.Commands.Common;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.TeamNovus.Bounties.Commands.Command;

public class TestCmd extends Command {

	public TestCmd() throws InstantiationException {
		super("test", "t");		
	}

	@Override
	public void onCommand(String[] labels, CommandSender sender, String[] args) {
		sender.sendMessage("Hello!");
		
	}

	@Override
	public List<String> onTabComplete() {
		return new ArrayList<String>();
	}



}
