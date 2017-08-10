/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view;

import domain.model.Factory;
import domain.model.Patient;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import service.PatientService;

/**
 *
 * @author Marie
 */
public class SeleniumTest {

    private static final String url = "http://localhost:8080/ip-project";
    private static WebDriver driver;
    private static PatientService service;

    private Patient testPatient;
    private Patient testPatient2;

    private Format bdate_formatter;

    private String login_user;
    private String login_pw;

    public SeleniumTest() {
        testPatient = Factory.createPatient("testFname", "testName", new Date(50, 01, 01),
                Factory.createAddress("testStreet", 1, "testZip", "testCity", "testCountry"),
                100, 200);
        testPatient2 = Factory.createPatient("testFname2", "testName2", new Date(55, 03, 05),
                Factory.createAddress("testStreet2", 1, "3B", "testZip", "testCity", "testState", "testCountry"),
                80, 170);
        login_user = "admin";
        login_pw = "admin";
        bdate_formatter = new SimpleDateFormat("MM/dd/yyyy");
    }
    
    @BeforeClass() 
    public static void beforeClass() throws ParseException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        service = new PatientService("Memory");
        
    }

    @Before
    public void setUp() throws ParseException {
        this.login();
    }

    @After
    public void tearDown() {
        //TODO
    }
    
    @AfterClass
    public static void shutDown() {
        driver.quit();
    }

    @Test
    public void testAddPatientFormSucces() {
        driver.get(url + "/form.htm");
        this.login();
        
        String id = driver.findElement(By.id("id")).getAttribute("value");
        this.fillInAndSubmitPatientForm(testPatient);
        
        List<WebElement> tds = driver.findElements(By.tagName("td"));
        assertTrue(this.foundWebElementInCollection(tds, testPatient.getFirstName() + " " + testPatient.getLastName()));
        assertTrue(this.foundWebElementInCollection(tds, Integer.toString(testPatient.getAge())));
        //assertTrue(this.foundWebElementInCollection(tds, testPatient.getAddress().toString()));
        assertTrue(this.foundWebElementInCollection(tds, Integer.toString(testPatient.getWeightInKg())));
        assertTrue(this.foundWebElementInCollection(tds, Integer.toString(testPatient.getHeightInCm())));

        this.deletePatient(Long.parseLong(id));
    }

    @Test
    public void testStatisticsOnIndexPage() {
        driver.get(url + "/index.htm");
        Collection<WebElement> trs = driver.findElements(By.tagName("tr"));
        for (String stat : service.getPatientStatistics().keySet()) {
            WebElement tr = this.findWebElementInCollection(trs, stat);
            assertTrue(tr != null);
            List<WebElement> tdStatValue = tr.findElements(By.cssSelector("td:last-child"));
            assertTrue(tdStatValue.get(0).getText().equals(service.getPatientStatistics().get(stat).toString()));
        }
    }
    
    @Test
    public void testStatisticsAfterPatientAdded() {
        driver.get(url + "/form.htm");
        this.login();
        String id = driver.findElement(By.id("id")).getAttribute("value");
        this.fillInAndSubmitPatientForm(testPatient);
        service.create(testPatient);
        
        driver.get(url + "/index.htm");
        this.testStatisticsOnIndexPage();
        
        service.delete(testPatient.getId());
        this.deletePatient(Long.parseLong(id));
    }

    private void fillInAndSubmitPatientForm(Patient testPatient) {
        this.fillInField(driver.findElement(By.id("firstName")), testPatient.getFirstName());
        this.fillInField(driver.findElement(By.id("lastName")), testPatient.getLastName());
        this.fillInField(driver.findElement(By.id("bdate")), bdate_formatter.format(testPatient.getBdate()));
        this.fillInField(driver.findElement(By.id("address.street")), testPatient.getAddress().getStreet());
        this.fillInField(driver.findElement(By.id("address.houseNumber")), Integer.toString(testPatient.getAddress().getHouseNumber()));
        this.fillInField(driver.findElement(By.id("address.addOn")), testPatient.getAddress().getAddOn());
        this.fillInField(driver.findElement(By.id("address.zipCode")), testPatient.getAddress().getZipCode());
        this.fillInField(driver.findElement(By.id("address.city")), testPatient.getAddress().getCity());
        this.fillInField(driver.findElement(By.id("address.state")), testPatient.getAddress().getState());
        this.fillInField(driver.findElement(By.id("address.country")), testPatient.getAddress().getCountry());
        this.fillInField(driver.findElement(By.id("weightInKg")), Integer.toString(testPatient.getWeightInKg()));
        this.fillInField(driver.findElement(By.id("heightInCm")), Integer.toString(testPatient.getHeightInCm()));
        driver.findElement(By.className("btn")).click();
    }
    
    @Test
    public void testEditPatient_ChangeDataTestPatientInTestPatient2() {
        driver.get(url + "/form.htm");
        this.login();
        String id = driver.findElement(By.id("id")).getAttribute("value");
        this.fillInAndSubmitPatientForm(testPatient);
        
        driver.get(url + "/patients.htm");
        this.login();
        try {
            driver.findElement(By.cssSelector("a[href*='editPatient/" + id + "']")).click();
            this.fillInAndSubmitPatientForm(testPatient2);
        } catch (Exception e) {}
        
        List<WebElement> names = driver.findElements(By.className("patientName"));
        assertFalse(this.foundWebElementInCollection(names, testPatient.getFirstName() + " " + testPatient.getLastName()));
        String lastPatientName = names.get(names.size()-1).getText();
        System.out.println(lastPatientName);
        assertEquals(lastPatientName, testPatient2.getFirstName() + " " + testPatient2.getLastName());
        
        this.deletePatient(Long.parseLong(id));
    }
    
    @Test
    public void testDeleteAddedPatient() {
        driver.get(url + "/form.htm");
        this.login();
        String id = driver.findElement(By.id("id")).getAttribute("value");
        this.fillInAndSubmitPatientForm(testPatient);
        this.deletePatient(Long.parseLong(id));
        
        List<WebElement> IDtds = driver.findElements(By.className("patientID"));
        assertFalse(this.foundWebElementInCollection(IDtds, id));
    }

    private void deletePatient(Long id) {
        driver.get(url + "/patients.htm");
        this.login();
        try {
            driver.findElement(By.cssSelector("a[href*='requestDeletePatient/" + id + "']")).click();
            driver.findElement(By.id("submitButton")).click();
        } catch (Exception e) {
            
        }
    }

    private void login() {
        if (this.foundWebElementInCollection(driver.findElements(By.tagName("h3")), "Login")) {
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            this.fillInField(inputs.get(0), login_user);
            this.fillInField(inputs.get(1), login_pw);
            inputs.get(2).click();
        }
    }

    private void fillInField(WebElement field, String keys) {
        field.clear();
        field.sendKeys(keys);
    }

    private boolean foundWebElementInCollection(Collection<WebElement> collec, String text) {
        return (this.findWebElementInCollection(collec, text) != null);
    }

    private WebElement findWebElementInCollection(Collection<WebElement> collec, String text) {
        for (WebElement el : collec) {
            if (el.getText().contains(text)) {
                return el;
            }
        }
        return null;
    }
}
