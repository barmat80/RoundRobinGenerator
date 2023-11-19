package com.bm.rrg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.bm.rrg.model.Competitor;

public class Utilities {
	private Utilities() {
	}

	public static int getRandom(int len) {
		int rnd = ThreadLocalRandom.current().nextInt(len);
		return rnd;
	}

	public static ArrayList<Competitor> readTextFile(String file) throws IOException {
		Path p = Paths.get(file);
		ArrayList<Competitor> ll = new ArrayList<>();
		ArrayList<String> l = (ArrayList<String>) Files.readAllLines(p, java.nio.charset.StandardCharsets.UTF_8);
		for (String s : l) {
			Competitor player = new Competitor(s);
			ll.add(player);
		}
		return ll;
	}

	public static void log(Object o) {
		System.out.println(o);
	}

	public static void debug(Object o) {
		if (false) {
			System.out.println(o);
		}
	}
}
