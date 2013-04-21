package com.TeamNovus.Supernaturals.Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TestCmd extends SubCommand {

	public TestCmd() {
		super("test", "test1", "test2");
	}

	@Override
	protected ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
//		if(args.length == 0) {
//			list.add("test");
//		} else {
//			list.add("1");
//			list.add("2");
//			list.add("3");
//		}
		
		list.add("1");
		
		return list;
	}

	@Override
	protected boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 1) {
			sender.sendMessage("daf");
		} else {
			sender.sendMessage(args[1]);
		}
		
		return true;
	}

}
