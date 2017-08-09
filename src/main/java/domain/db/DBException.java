package domain.db;

import domain.*;
import domain.model.Patient;

/**
 *
 * @author Marie
 */
public class DBException extends RuntimeException {

    public DBException() {
	super();
    }

    public DBException(String message) {
	super(message);
    }

    public static void checkIfAlreadyInDB(Object object, Object DB) {
	if (object instanceof Patient && DB instanceof PatientDB) {
            PatientDB db = (PatientDB) DB;
            Patient patient = (Patient) object;
            if(db.find(patient.getId())!=null) {
                throw new DBException("There is already a Patient in de DB with id: " + Long.toString(patient.getId()));
            }
        } else {
            throw new DBException("This is not a Patient object or a Patient DB.");
        }
    }   

}
