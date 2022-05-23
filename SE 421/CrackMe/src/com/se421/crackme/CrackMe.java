package com.se421.crackme;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class CrackMe {

	public static void main(String[] args) throws Exception {
		long sleepy = 1;
			long seed = new Random().nextLong();
			Random rnd = new Random(seed);
			final int range = 26;
			String suffix = "";
			for (int i = 0; i < 20; i++) {
				int v = rnd.nextInt(range) + 1;
				char c = (char) ('`' + v);
				suffix += c;
			}
				suffix = suffix.replace("s", "-");
				String secretKey = suffix.substring(0, 15);
				System.out.println("Success: Here's your secret key - " + secretKey);
	}

}