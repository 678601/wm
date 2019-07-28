package com.example.demo.temp;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Random random = new Random();
			SecureRandom secureRandom=null;
			try {
				secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			SecureRandom secureRandom = new SecureRandom();
			Integer i = secureRandom.nextInt(10);
			Integer i1 = random.nextInt(10);
			System.out.println("i="+i+",i1="+i1);
	}

}
