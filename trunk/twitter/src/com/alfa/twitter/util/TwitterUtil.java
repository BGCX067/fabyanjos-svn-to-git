package com.alfa.twitter.util;

import java.util.List;

public class TwitterUtil {
	
	private TwitterUtil() {
	}
	
	public static String findUrl(String text) {
		
		String start = "http://";
		
		List<String> find = StringUtil.findStartWith(start, text);
		
		String url = "<a target=\"_blank\" href=\"$URL$\">$URL$</a>";
		
		for(String s : find) {
			String newUrl = url.replace("$URL$", s);
			text = text.replace(s, newUrl);
		}
		return text;
	}
	
	public static String findUser(String text) {
		String start = "@";
		
		List<String> find = StringUtil.findStartWith(start, text);
		
		String url = "<a href=\"/user?name=$URL$\">$URL$</a>";
		
		for(String s : find) {
			s = s.replace("@", "");
			String newUrl = url.replace("$URL$", s);
			text = text.replace(s, newUrl);
		}
		return text;
	}
	
	public static String findSearch(String text) {
		String start = "#";
		
		List<String> find = StringUtil.findStartWith(start, text);
		
		String url = "<a href=\"/search?q=$URL$\">$URL$</a>";
		
		for(String s : find) {
			s = s.replace("#", "");
			String newUrl = url.replace("$URL$", s);
			text = text.replace(s, newUrl);
		}
		return text;
	}
	
	public static void main(String[] args) {
		System.out.println(findSearch("The Chipophone #teste"));
	}

//	public static void main(String[] args) {
//		
//		String in = "Joguinho tosco de True Blood http://goo.gl/fb/Qs1GD #GirlsOfWar #teste";
//		
//		String url = "http://+[A-Z0-9\\/._-]*";
//		String search = "#+[A-Z0-9\\/._-]*";
//		String user = "@+[A-Z0-9\\/._-]*";
//		
//		List<String> find = findToken(url, in);
//		
//		for(String s : find) {
//			in = in.replace(s, "new");
//		}
//		
//		System.out.println(in);
//	}
}
