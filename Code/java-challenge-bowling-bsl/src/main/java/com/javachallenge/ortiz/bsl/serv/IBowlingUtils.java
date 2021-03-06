package com.javachallenge.ortiz.bsl.serv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface IBowlingUtils {

	File getFile() throws Exception;

	List<String> getData(File bowlingFile) throws FileNotFoundException;

	void verifyData(List<String> data, String delimiter) throws Exception;

	boolean verifyMove(String[] move);

}