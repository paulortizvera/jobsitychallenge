package com.javachallenge.ortiz.bsl.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javachallenge.ortiz.bsl.serv.IPlayBowling;

public class PlayBowling implements IPlayBowling {
	
	/*** USE ***/
	private static final String U_YES = "Y";
	private static final String U_NO = "N";
	private static final String U_FILE = "bowling.txt";
	/*** PRINT ***/
	private static final String P_CHOOSE = "Do you want to choose your own file (Y) or the default (N)?: ";
	private static final String P_FRAME = "Frame";
	private static final String P_TAB = "	";
	private static final String P_RUNNING = "------------------RUNNING------------------";
	/*** ERRORS ***/
	private static final String E_FORMAT = "Incorrect format";
	private static final String E_INPUT = "The input is not an option.";
	private static final String E_FILE = "File not found.";
	private static final String E_GENERAL = "An error has ocurred";
	
	@Override
	public void playBowling() {
		File bowlingFile = null;
		List<String> data = new ArrayList<String>();
		String delimiter = " ";
		Scanner typeImput = null;
		try {
			BowlingUtils bowlingUtils = new BowlingUtils();
			typeImput = new Scanner(System.in);
			System.out.println(P_CHOOSE);
			String type = typeImput.next().toUpperCase();

			if (U_YES.equals(type)) {
				bowlingFile = bowlingUtils.getFile();
				data = bowlingUtils.getData(bowlingFile);
			} else if (U_NO.equals(type)) {
				InputStream is = BowlingUtils.class.getResourceAsStream(U_FILE);
				InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader);
				for (String line; (line = reader.readLine()) != null;) {
					data.add(line);
				}
			} else {
				System.out.println(E_INPUT);
				throw new Exception(E_INPUT);
			}
			System.out.println(P_RUNNING);
			BowlingPlayers bowlingPlayers = new BowlingPlayers();
			List<Object> players = bowlingPlayers.getPlayers(data, delimiter);
			playScore(players, data, delimiter);
			
		} catch (FileNotFoundException e) {
			System.out.println(E_FILE);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(E_GENERAL);
			e.printStackTrace();
		} finally {
			if (typeImput != null)
				typeImput.close();
		}
	}

	private static void playScore(List<Object> players, List<String> data, String delimiter) {
		System.out.print(P_FRAME);
		for (int i = 1; i < 11; i++) {
			System.out.print(P_TAB + P_TAB + i);
		}
		System.out.println("");
		BowlingPlayers bowlingPlayers = new BowlingPlayers();
		BowlingUtils bowlingUtils = new BowlingUtils();

		players.forEach(player -> {
			List<String> moves = new ArrayList<String>();
			data.forEach(dat -> {
				String move[] = dat.split(delimiter);
				if (!bowlingUtils.verifyMove(move))
					System.out.println(E_FORMAT);
				if (player.toString().equals(move[0]))
					moves.add(move[1].toString());
			});
			System.out.println(player);
			bowlingPlayers.playerScore(moves);
		});
	}

}
