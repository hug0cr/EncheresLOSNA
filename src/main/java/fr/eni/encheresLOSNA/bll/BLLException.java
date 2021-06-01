/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 16:28:16
 */
public class BLLException extends Exception {
	private static final long serialVersionUID = -6496110309655647143L;
	
	public BLLException() {
		super();
	}
	
	public BLLException(String message) {
		super(message);
	}
	
	public BLLException(String message, Throwable exception) {
		super(message, exception);
	}

	@Override
	public String getMessage() {
		
		return "BLL - " + super.getMessage();
	}
}
