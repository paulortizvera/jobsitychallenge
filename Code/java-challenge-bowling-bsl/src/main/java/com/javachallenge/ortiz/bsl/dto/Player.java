package com.javachallenge.ortiz.bsl.dto;

import java.util.List;

public class Player {

	private String name;
	private List<Integer> score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getScore() {
		return score;
	}

	public void setScore(List<Integer> score) {
		this.score = score;
	}
}
