/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.encheresLOSNA.bo.Utilisateur;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 3 juin 2021 - 14:04:05
 */
public class AppBLLTest {

	/**
	 * Methode en charge de
	 * @param args
	 */
	public static void main(String[] args) {
		String email1 = "user@domain.com";
		String string = "jojo44";
		String cp = "44000";
		
		System.out.println(RegexVerification.isValidEmail(email1));
		System.out.println(RegexVerification.isOnlyLettersAndNumbers(string));
		
		
	}

}