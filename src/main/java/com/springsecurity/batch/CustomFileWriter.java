package com.springsecurity.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springsecurity.domain.Player;

public class CustomFileWriter implements ItemWriter<Player>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void write(List<? extends Player> items) throws Exception {
		System.out.println("---------INSIDE CustomFileWriter ---------------");
		for(Player player : items) {
			System.out.println(player);
		String sql = "INSERT INTO player_entity(name,age,subject) VALUES (?,?,?)";
		Object[] args = {player.getName(),player.getAge(),player.getSubject()};
		int rowsInserted = jdbcTemplate.update(sql, args);
		System.out.println("No of rows inserted : "+rowsInserted);
		}
	}

}
