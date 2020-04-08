package com.javachallenge.ortiz.bsl.serv;

import com.javachallenge.ortiz.bsl.dto.PlayThrow;

public interface IBowlingScore {

	int score(int score, PlayThrow playThrow, boolean isStrike);

}
