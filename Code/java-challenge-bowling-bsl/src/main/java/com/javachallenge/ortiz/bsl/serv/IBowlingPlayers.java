package com.javachallenge.ortiz.bsl.serv;

import java.util.List;

public interface IBowlingPlayers {

	List<Object> getPlayers(List<String> data, String delimiter);

	void playerScore(List<String> moves);

}