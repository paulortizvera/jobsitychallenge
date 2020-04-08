package com.javachallenge.ortiz.bsl.utils;

public class StaticVariables {
	
	public static class Use {
		public static final String U_YES = "Y";
		public static final String U_NO = "N";
		public static final String U_FILE = "bowling.txt";
		public static final int U_MIN_DATA = 12;
		public static final int U_MAX_MOVE = 2;
		public static final String U_REGULAR = "[F]*[0-9]*";
		public static final int U_MAX_VALUE = 10;
		public static final String U_FOUL = "F";
	}
	
	public static class Print {
		public static final String P_CHOOSE = "Do you want to choose your own file (Y) or the default (N)?: ";
		public static final String P_FILE = "Enter directory for file: ";
		public static final String P_FRAME = "Frame";
		public static final String P_TAB = "	";
		public static final String P_RUNNING = "------------------RUNNING------------------";
		public static final String P_PINFALLS = "Pinfalls";
		public static final String P_SCORE = "Score";
		public static final String P_STRIKE = "X";
		public static final String P_SPARE = "/";
	}
	
	public static class Error {
		public static final String E_FORMAT = "Incorrect format";
		public static final String E_INPUT = "The input is not an option.";
		public static final String E_FILE = "File not found.";
		public static final String E_MOVES = "Some player lacks movements";
		public static final String E_GENERAL = "An error has ocurred";
	}
}
