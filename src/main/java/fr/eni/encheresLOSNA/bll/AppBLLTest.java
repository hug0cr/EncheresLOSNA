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
		
		System.out.println(RegexVerification.isValidEmail(email1));
		
		System.out.println();
		
		List<String> emails = new ArrayList<>();
		emails.add(email1);
		emails.add("user@domain.com");
		emails.add("user@domain.co.in");
		emails.add("user.name@domain.com");
		emails.add("user_name@domain.com");
		emails.add("username@yahoo.corporate.in");
		 
		//Invalid emails
		emails.add(".username@yahoo.com");
		emails.add("username@yahoo.com.");
		emails.add("username@yahoo..com");
		emails.add("username@yahoo.c");
		emails.add("username@yahoo.corporate");
		 
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 
		Pattern pattern = Pattern.compile(regex);
		 
		for(String email : emails){
		    Matcher matcher = pattern.matcher(email);
		    System.out.println(email +" : "+ matcher.matches());
		}
		
		System.out.println();
		Matcher matcher = pattern.matcher(email1);
		System.out.println(matcher.matches());
		
	}

}
