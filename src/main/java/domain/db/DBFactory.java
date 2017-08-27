/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import java.text.ParseException;

/**
 *
 * @author Marie
 */
public class DBFactory {
    
    public static PatientJpaDB getJpaDB() {
        return new PatientJpaDB();
    }
    
    public static PatientSingletonDB getSingletonDB() throws ParseException {
        return PatientSingletonDB.getDB();
    }
    
}
