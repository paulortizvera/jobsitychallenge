package com.javachallenge.ortiz.bsl.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javachallenge.ortiz.bsl.serv.IBowlingUtils;

public class BowlingUtils implements IBowlingUtils {

	@Override
	public File getFile() throws Exception {
		Scanner typeImput = new Scanner(System.in);
		System.out.println("Enter directory for file: ");
		String directory = typeImput.next();
		typeImput.close();
		return new File(directory);
	}

	@Override
	public List<String> getData(File bowlingFile) throws FileNotFoundException {
		List<String> data = new ArrayList<String>();
		Scanner readerBowling = new Scanner(bowlingFile);
		while (readerBowling.hasNextLine()) {
			data.add(readerBowling.nextLine());
		}
		readerBowling.close();
		return data;
	}

	@Override
	public boolean verifyMove(String[] move) {
		if (move.length > 2)
			return false;
		if (!move[1].matches("[0-9]*[F]*"))
			return false;
		return true;
	}
}
