package com.alfa.twitter.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	private StringUtil() {
	}

	public static List<String> findStartWith(String regex, String text) {
		List<String> tokens = new ArrayList<String>();

		String[] strings = text.split("\\s");

		for (String s : strings) {
			if (s.startsWith(regex))
				tokens.add(s);
		}
		return tokens;
	}
	
	public static boolean isNull(String text) {
		if(text == null)
			return true;
		if(text.trim().equals(""))
			return true;
		return false;
	}
}
