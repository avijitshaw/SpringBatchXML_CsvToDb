package com.springsecurity.batch;

import org.springframework.batch.item.ItemProcessor;

import com.springsecurity.domain.Player;

public class CustomFileProcessor implements ItemProcessor<Player, Player>{

	@Override
	public Player process(Player item) throws Exception {
		System.out.println("-------INSIDE CustomFileProcessor ----------");
		return item;
	}

}
