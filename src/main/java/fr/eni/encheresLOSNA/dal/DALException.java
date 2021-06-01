/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 11:00:01
 */
public class DALException extends Exception {
	private static final long serialVersionUID = 5358647214910957121L;

	//Constructeurs
	public DALException() {
		super();
	}
		
	public DALException(String message) {
		super(message);
	}
	
	public DALException(String message, Throwable exception) {
		super(message, exception);
	}

	//Méthodes
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche DAL - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}

}
