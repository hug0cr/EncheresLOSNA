/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 3 juin 2021 - 14:44:59
 */
public class RegexVerification {
	private String ONLY_LETTERS_AND_NUMBERS = "^[A-Za-Z0-9]+$";
	private String VALID_EMAIL = "^[\\\\w!#$%&'*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";

	public static boolean isValidEmail(String email) {
		boolean isValid = false;
		String regex = "^[\\\\w!#$%&'*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
	 
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(email);
		isValid = matcher.matches();
		return isValid;
	}
	
}
