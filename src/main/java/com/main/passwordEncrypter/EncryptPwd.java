/**
 * 
 */
package com.main.passwordEncrypter;

import java.util.Base64;

/**
 * @author krishnamurthy.r
 *
 */
public class EncryptPwd {
	
	   Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
	   Base64.Decoder mimeDecoder = Base64.getMimeDecoder();

	public  String encrypt(String pwd, String key) {
		try {
			if(pwd==null || key ==null)
				return null;
			char[] keys =key.toCharArray();
			char[] password =pwd.toCharArray();
			
			int keyLength = keys.length;
			int pwdLength = password.length;
			char[] newpwd = new char[pwdLength];
			
			for (int i = 0; i < pwdLength; i++) {
				newpwd[i] =(char)(password[i]^keys[i%keyLength]);
			}
			keys=null;
			password =null;
			String encodedString = mimeEncoder.encodeToString(new String(newpwd).getBytes());
					return encodedString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public  String decrypt( String pwd, String key) {
		try {
			if(pwd==null || key ==null)
				return null;
			char[] keys =key.toCharArray();
			char[] password = new String(mimeDecoder.decode(pwd)).toCharArray();
			
			int keyLength = keys.length;
			int pwdLength = password.length;
			char[] newpwd = new char[pwdLength];
			
			for (int i = 0; i < pwdLength; i++) {
				newpwd[i] =(char)(password[i]^keys[i%keyLength]);
			}
			keys=null;
			password =null;
			return new String(newpwd);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
