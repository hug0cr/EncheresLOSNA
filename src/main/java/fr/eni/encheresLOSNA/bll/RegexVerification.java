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
	public static String VALID_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	public static String ONLY_LETTERS_AND_NUMBERS = "^[a-zA-Z0-9]+$";
	public static String ONLY_FIVE_DIGITS = "^[0-9]{5,5}$";
	
	public static boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile(VALID_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean isOnlyLettersAndNumbers(String string) {
		Pattern pattern = Pattern.compile(ONLY_LETTERS_AND_NUMBERS);
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}
	
	public static boolean isOnlyFiveDigits(String string) {
		Pattern pattern = Pattern.compile(ONLY_FIVE_DIGITS);
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}
}
