package user;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class UserTest {
	
	private byte[] generate(String toHash) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(toHash.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();	
			return null;
		}
	}

	@Test
	public void test() {
//		User test = new User("Molly", generate("a"));
//		assert(test.getPasswordHash().equals("86f7e437faa5a7fce15d1ddcb9eaeaea377667b8"));
//		
//		User test1 = new User("Bob", generate("fm"));
//		assert(test1.getPasswordHash().equals("adeb6f2a18fe33af368d91b09587b68e3abcb9a7"));
		
		
	}

}
