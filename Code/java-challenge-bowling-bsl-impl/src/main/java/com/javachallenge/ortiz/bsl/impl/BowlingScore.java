package com.javachallenge.ortiz.bsl.impl;

import com.javachallenge.ortiz.bsl.dto.PlayThrow;
import com.javachallenge.ortiz.bsl.serv.IBowlingScore;

public class BowlingScore implements IBowlingScore {

	@Override
	public int score(int score, PlayThrow playThrow, boolean isStrikeSpare) {
		if (isStrikeSpare)
			return score = score + playThrow.getActualThrow() + playThrow.getFirstNextThrow() + playThrow.getSecondNextThrow();
		else
			return score = score + playThrow.getActualThrow() + playThrow.getFirstNextThrow();
	}

}
