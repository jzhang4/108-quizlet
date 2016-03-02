package user;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private String username;
	private byte[] passwordHash;
	private int ID;
	private HashSet<Integer> friends;
	
	public User(String username, String password) {
		this.username = username;
		this.passwordHash = generate(password);
	}
	
	private static byte[] generate(String toHash) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(toHash.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();	
			return null;
		}
	}
	
	public String getPasswordHash() {
		return hexToString(passwordHash);
	}
	
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	
}


