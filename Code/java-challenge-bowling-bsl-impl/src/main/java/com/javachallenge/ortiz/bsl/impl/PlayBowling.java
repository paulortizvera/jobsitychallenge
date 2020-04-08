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
import com.javachallenge.ortiz.bsl.utils.StaticVariables;

public class PlayBowling implements IPlayBowling {

	@Override
	public void playBowling() {
		File bowlingFile = null;
		List<String> data = new ArrayList<String>();
		String delimiter = " ";
		Scanner typeImput = null;
		try {
			BowlingUtils bowlingUtils = new BowlingUtils();
			typeImput = new Scanner(System.in);
			System.out.println(StaticVariables.Print.P_CHOOSE);
			String type = typeImput.next().toUpperCase();

			if (StaticVariables.Use.U_YES.equals(type)) {
				bowlingFile = bowlingUtils.getFile();
				data = bowlingUtils.getData(bowlingFile);
			} else if (StaticVariables.Use.U_NO.equals(type)) {
				InputStream is = BowlingUtils.class.getResourceAsStream(StaticVariables.Use.U_FILE);
				InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader);
				for (String line; (line = reader.readLine()) != null;) {
					data.add(line);
				}
			} else {
				System.out.println(StaticVariables.Error.E_INPUT);
				throw new Exception(StaticVariables.Error.E_INPUT);
			}
			System.out.println(StaticVariables.Print.P_RUNNING);
			BowlingPlayers bowlingPlayers = new BowlingPlayers();
			List<Object> players = bowlingPlayers.getPlayers(data, delimiter);
			playScore(players, data, delimiter);

		} catch (FileNotFoundException e) {
			System.out.println(StaticVariables.Error.E_FILE);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(StaticVariables.Error.E_GENERAL);
			e.printStackTrace();
		} finally {
			if (typeImput != null)
				typeImput.close();
		}
	}

	private static void playScore(List<Object> players, List<String> data, String delimiter) throws Exception {
		System.out.print(StaticVariables.Print.P_FRAME);
		for (int i = 1; i < 11; i++) {
			System.out.print(StaticVariables.Print.P_TAB + StaticVariables.Print.P_TAB + i);
		}
		System.out.println("");
		BowlingPlayers bowlingPlayers = new BowlingPlayers();
		BowlingUtils bowlingUtils = new BowlingUtils();
		bowlingUtils.verifyData(data, delimiter);
		players.forEach(player -> {
			List<String> moves = new ArrayList<String>();
			data.forEach(dat -> {
				String move[] = dat.split(delimiter);
				if (player.toString().equals(move[0]))
					moves.add(move[1].toString());
			});
			System.out.println(player);
			bowlingPlayers.playerScore(moves);
		});
	}

}
