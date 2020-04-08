package com.javachallenge.ortiz.bsl.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javachallenge.ortiz.bsl.serv.IBowlingUtils;
import com.javachallenge.ortiz.bsl.utils.StaticVariables;

public class BowlingUtils implements IBowlingUtils {

	@Override
	public File getFile() throws Exception {
		Scanner typeImput = new Scanner(System.in);
		System.out.println(StaticVariables.Print.P_FILE);
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
	public void verifyData(List<String> data, String delimiter) throws Exception {
		StringBuilder hasError = new StringBuilder();
		// String hasError = StaticVariables.Use.U_NO;
		BowlingUtils bowlingUtils = new BowlingUtils();
		if (data.size() <= StaticVariables.Use.U_MIN_DATA)
			throw new Exception(StaticVariables.Error.E_FORMAT);
		data.forEach(dat -> {
			String move[] = dat.split(delimiter);
			if (!bowlingUtils.verifyMove(move)) {
				hasError.append(StaticVariables.Use.U_YES);
			}
		});
		if (StaticVariables.Use.U_YES.equals(hasError.toString()))
			throw new Exception(StaticVariables.Error.E_FORMAT);
	}

	@Override
	public boolean verifyMove(String[] move) {
		if (move.length > StaticVariables.Use.U_MAX_MOVE)
			return false;
		if (!move[1].matches(StaticVariables.Use.U_REGULAR))
			return false;
		else if (!StaticVariables.Use.U_FOUL.equals(move[1]))
			if (Integer.valueOf(move[1]) > StaticVariables.Use.U_MAX_VALUE)
				return false;
		return true;
	}
}
