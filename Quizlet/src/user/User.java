package user;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private String username;
	private byte[] passwordHash;
	private int ID;
	private HashSet<Integer> friends;
	
	public User(String username, byte[] password) {
		this.username = username;
		this.passwordHash = password;
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
	
	public String getUserName() {
		return username;
	}
	
}


