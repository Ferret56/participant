package sef.module18.activity;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryTest1 extends TestCase {

    private Connection conn;
    private String url;
    private String username;
    private String password;
    Log logger = LogFactory.getLog(this.getClass());

    protected void setUp() throws Exception {
        super.setUp();
        username = "sa";
        password = "";
        Class.forName("org.h2.Driver");
        url = "jdbc:h2:tcp://localhost/~/test";
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        System.out.println("Connection successfully established!");
    }

    public void testFindEmployeeByID() {
        EmployeeRepository dao = new EmployeeRepositoryImpl(conn);
        try {
            Employee result1 = dao.findEmployeeByID(1);

            assertEquals("JOHN", result1.getFirstName().toUpperCase());
            assertEquals("DOE", result1.getLastName().toUpperCase());
            assertEquals(1, result1.getProfLevel());

            Employee result2 = dao.findEmployeeByID(4);

            assertEquals("JAMES", result2.getFirstName().toUpperCase());
            assertEquals("DONNELL", result2.getLastName().toUpperCase());
            assertEquals(3, result2.getProfLevel());

        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }

    }

    public void testFindEmployeeByName() {
        EmployeeRepository dao = new EmployeeRepositoryImpl(conn);
        try {
            List<Employee> results = dao.findEmployeeByName("J", "Doe");

            assertEquals(2, results.size());
            assertEquals("JOHN", results.get(0).getFirstName().toUpperCase());
            assertEquals("DOE", results.get(0).getLastName().toUpperCase());
            assertEquals(1, results.get(0).getProfLevel());

            assertEquals("JANE", results.get(1).getFirstName().toUpperCase());
            assertEquals("DOE", results.get(1).getLastName().toUpperCase());
            assertEquals(2, results.get(1).getProfLevel());


        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testFindEmployeeByProfelLevel() {
        EmployeeRepository dao = new EmployeeRepositoryImpl(conn);

        try{
            List<Employee> results1 = dao.findEmployeeByProfLevel(5);

            assertEquals(1,results1.size());
            assertEquals("MICHAEL",results1.get(0).getFirstName().toUpperCase());
            assertEquals("DORN", results1.get(0).getLastName().toUpperCase());

            List<Employee> results2 = dao.findEmployeeByProfLevel(1);

            assertEquals(2,results2.size());
            assertEquals("JOHN", results2.get(0).getFirstName().toUpperCase());
            assertEquals("DOE", results2.get(0).getLastName().toUpperCase());

            assertEquals("SCOTT", results2.get(1).getFirstName().toUpperCase());
            assertEquals("FEIST",results2.get(1).getLastName().toUpperCase());

        }catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }


    }

    public void testFindEmployeeByProject(){
        EmployeeRepository dao = new EmployeeRepositoryImpl(conn);
        try{

            List<Employee> results = dao.findEmployeeByProject(3);

            assertEquals(2,results.size());
            assertEquals(2,results.get(0).getEmployeeID());
            assertEquals(4,results.get(1).getEmployeeID());

        }catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }

    }

    public void testInsertEmployee(){
        EmployeeRepository dao = new EmployeeRepositoryImpl(conn);
        try{
            Employee employee = new EmployeeImpl(0,"Jack", "Dorn",4);
            int resultID = dao.insertEmployee(employee);
            Employee tstEmployee = dao.findEmployeeByID(resultID);
            assertEquals("JACK",tstEmployee.getFirstName().toUpperCase());
            assertEquals("DORN",tstEmployee.getLastName().toUpperCase());

        }catch (HRSSystemException e) {
            System.out.println(e.getCause().getMessage());
            fail();
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
    }

    public void testUpdateEmployee(){
        EmployeeRepository dao = new EmployeeRepositoryImpl(conn);
        try{
            Employee employee = new EmployeeImpl(0,"Sansa", "Stark",20);
            int resultID = dao.insertEmployee(employee);
            Employee resultEmployee = new EmployeeImpl(resultID, "Daenerys", "Targaryen", 40);

            assertTrue(dao.updateEmployee(resultEmployee));

            Employee tstEmployee = dao.findEmployeeByID(resultID);

            assertEquals("DAENERYS",tstEmployee.getFirstName().toUpperCase());
            assertEquals("TARGARYEN",tstEmployee.getLastName().toUpperCase());
            assertEquals(40, tstEmployee.getProfLevel());

        }catch (HRSSystemException e) {
            System.out.println(e.getCause().getMessage());
            fail();
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
    }

    protected void tearDown() throws Exception{
        try{
            super.tearDown();
            conn.close();
            System.out.println("Connection closed!");
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            logger.error(sef.module.percentage.Percentage.setFailedCount(true,4));
        }
    }

}
