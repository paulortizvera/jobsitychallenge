package com.javachallenge.ortiz.bsl.impl;

import java.util.function.Predicate;

import com.javachallenge.ortiz.bsl.dto.PlayThrow;

public class BowlingThrow {

	Predicate<PlayThrow> isStrike = (playThrow) -> playThrow.getActualThrow() == 10;
	Predicate<PlayThrow> isSpare = (playThrow) -> (playThrow.getActualThrow() + playThrow.getFirstNextThrow()) == 10;

}
