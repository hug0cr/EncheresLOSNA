/**
 * 
 */
package fr.eni.encheresLOSNA.bo;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 31 mai 2021 - 15:42:42
 */
public class TestClass {
	private String message;

	/**
	 * Constructeur
	 */
	public TestClass() {
		super();
		
	}

	/**
	 * Constructeur
	 * @param message
	 */
	public TestClass(String message) {
		super();
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void fonctionAdrien() {
		System.out.println("Cette fonction est créée par Adrien depuis chez lui :D");
	}
	
}
