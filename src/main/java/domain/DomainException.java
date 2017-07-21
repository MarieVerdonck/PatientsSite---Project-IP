package domain;

/**
 *
 * @author Marie
 */
public class DomainException extends RuntimeException {

    public DomainException() {
	super();
    }

    public DomainException(String message) {
	super(message);
    }

    public static void checkIfNumberAndPositive(String value, String key) {
	if (!(Integer.valueOf(value) instanceof Integer)) {
            //throw new DomainException(key + " should be a number.");
	} else {
            if(Integer.valueOf(value) <= 0) {
		//throw new DomainException(key + " has to be positive.");
            }
	}
    }
    
    public static void checkStringNotEmpty(String value, String key) {
	if (value.trim().isEmpty()) {
            //throw new DomainException(key + " can't be empty.");
	}
    }
    
    public static void checkNotNull(Object object, String key) {
        if (object == null) {
            //throw new DomainException(key + " can't be null.");
	}
    }    

}
