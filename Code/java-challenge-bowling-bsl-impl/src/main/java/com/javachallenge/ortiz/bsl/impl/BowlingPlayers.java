package com.javachallenge.ortiz.bsl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.javachallenge.ortiz.bsl.dto.PlayThrow;
import com.javachallenge.ortiz.bsl.serv.IBowlingPlayers;
import com.javachallenge.ortiz.bsl.utils.StaticVariables;

public class BowlingPlayers implements IBowlingPlayers {

	@Override
	public List<Object> getPlayers(List<String> data, String delimiter) {
		List<String> item = new ArrayList<String>();
		data.forEach(element -> {
			item.add(element.substring(0, element.indexOf(delimiter)));
		});
		return item.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public void playerScore(List<String> moves) {
		List<Integer> scoreFinal = new ArrayList<Integer>();
		int score = 0;

		List<Integer> scoreMoves = new ArrayList<Integer>();

		moves.forEach(mov -> {
			scoreMoves.add(StaticVariables.Use.U_FOUL.equals(mov) ? 0 : Integer.parseInt(mov));
		});

		int frame = 1;
		BowlingScore bowlingScore = new BowlingScore();
		BowlingThrow bowlingThrow = new BowlingThrow();
		StringBuilder printScore = new StringBuilder();
		try {
			for (int i = 0; i < moves.size(); i++) {
				if (frame > 10)
					break;
	
				PlayThrow playThrow = new PlayThrow();
				playThrow.setActualThrow(scoreMoves.get(i));
				playThrow.setFirstNextThrow(scoreMoves.get(i + 1));
				playThrow.setSecondNextThrow(scoreMoves.get(i + 2));
				playThrow.setActualThrowOrg(moves.get(i));
				playThrow.setFirstNextThrowOrg(moves.get(i + 1));
				playThrow.setSecondNextThrowOrg(moves.get(i + 2));
	
				if (bowlingThrow.isStrike.test(playThrow)) {
					if (frame == 10)
						printScore.append(StaticVariables.Print.P_STRIKE + StaticVariables.Print.P_TAB + playThrow.getFirstNextThrowOrg() + StaticVariables.Print.P_TAB + finalFrame(playThrow, frame));
					else
						printScore.append(StaticVariables.Print.P_TAB + StaticVariables.Print.P_STRIKE + StaticVariables.Print.P_TAB);
					score = bowlingScore.score(score, playThrow, true);
					scoreFinal.add(score);
				} else if (bowlingThrow.isSpare.test(playThrow)) {
					printScore.append(playThrow.getActualThrowOrg() + StaticVariables.Print.P_TAB + StaticVariables.Print.P_SPARE + StaticVariables.Print.P_TAB + finalFrame(playThrow, frame));
					score = bowlingScore.score(score, playThrow, true);
					scoreFinal.add(score);
					i++;
				} else {
					printScore.append(playThrow.getActualThrowOrg() + StaticVariables.Print.P_TAB + playThrow.getFirstNextThrowOrg() + StaticVariables.Print.P_TAB + finalFrame(playThrow, frame));
					score = bowlingScore.score(score, playThrow, false);
					scoreFinal.add(score);
					i++;
				}
				frame++;
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(StaticVariables.Error.E_MOVES);
		}
		System.out.print(StaticVariables.Print.P_PINFALLS + StaticVariables.Print.P_TAB);
		System.out.println(printScore);
		System.out.print(StaticVariables.Print.P_SCORE + StaticVariables.Print.P_TAB);
		scoreFinal.forEach(sco -> System.out.print(StaticVariables.Print.P_TAB + sco + StaticVariables.Print.P_TAB));
		System.out.println("");
	}

	private static String finalFrame(PlayThrow playThrow, int frame) {
		if (frame == 10)
			return playThrow.getSecondNextThrowOrg();
		return "";
	}
}
