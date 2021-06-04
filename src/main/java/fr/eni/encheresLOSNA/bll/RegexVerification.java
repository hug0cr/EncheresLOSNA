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
	public static final String VALID_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	public static final String VALID_EMAIL_DESCRIPTION = "Adresse email correcte requise.";
	
	public static final String ONLY_LETTERS_AND_NUMBERS = "^[a-zA-Z0-9]{3,12}$";
	public static final String ONLY_LETTERS_AND_NUMBERS_DESCRIPTION = "Doit contenir que des lettres et des chiffres. 3 minimum et 12 maximum.";
	
	public static final String ONLY_FIVE_DIGITS = "^[0-9]{5}$";
	public static final String ONLY_FIVE_DIGITS_DESCRIPTION = "5 chiffres requis.";
	
	public static final String ONLY_TEN_DIGITS = "^[0-9]{10}$";
	public static final String ONLY_TEN_DIGITS_DESCRIPTION = "10 chiffres requis.";
	
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
	
	public static boolean isOnlyTenDigits(String string) {
		Pattern pattern = Pattern.compile(ONLY_TEN_DIGITS);
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}
}
