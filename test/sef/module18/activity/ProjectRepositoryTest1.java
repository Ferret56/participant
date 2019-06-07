package sef.module18.activity;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryTest1 extends TestCase {
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

    public void testFindProjectById() {
        ProjectRepository dao = new ProjectRepositoryImpl(conn);
        try {
            Project result = dao.findProjectByID(2);
            int ExpectedId = 2;
            String ExpectedName = "Time Report System";
            String ExpectedDescription = "A stand-alone application that records and generates time reports.";

            assertEquals(ExpectedId, result.getProjectID());
            assertEquals(ExpectedName, result.getProjectName());
            assertEquals(ExpectedDescription, result.getProjectDescription());

        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }

    }

    public void testFindProjectByName() {
        ProjectRepository dao = new ProjectRepositoryImpl(conn);
        List<Project> results = new ArrayList<Project>();

        try {
            final String name = "Online Insurance System";
            results = dao.findProjectByName(name);
            final int ExpectedId = 1;
            final String ExpectedName = "Online Insurance System";
            final String ExpectedDescription = "A web application that automates insurance transactions.";

            assertEquals(1, results.size());
            assertEquals(1, results.get(0).getProjectID());
            assertEquals(ExpectedName, results.get(0).getProjectName());
            assertEquals(ExpectedDescription, results.get(0).getProjectDescription());


        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testFindProjectByEmployee() {
        ProjectRepository dao = new ProjectRepositoryImpl(conn);
        List<Project> results = new ArrayList<Project>();
        try {
            results = dao.findProjectByEmployee(4);
            final int ExpectedId = 3;
            final String ExpectedName = "Real Estate Search System";
            final String ExpectedDescription = "An online search engine specifically for real estates.";

            assertEquals(1, results.size());
            assertEquals(3, results.get(0).getProjectID());
            assertEquals(ExpectedName, results.get(0).getProjectName());
            assertEquals(ExpectedDescription, results.get(0).getProjectDescription());

        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testInsertProject() {
        ProjectRepository dao = new ProjectRepositoryImpl(conn);
        try {
            Project tstProject = new ProjectImpl(0, "TestName", "TestDescription");
            int resultID = dao.insertProject(tstProject);
            Project project = dao.findProjectByID(resultID);

            assertEquals(resultID, project.getProjectID());
            assertEquals("TestName", project.getProjectName());
            assertEquals("TestDescription", project.getProjectDescription());


        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    public void testUpdateProject() {
        ProjectRepository dao = new ProjectRepositoryImpl(conn);
        try {
            Project project = new ProjectImpl(0, "TestName", "TestDescription");
            int resultID = dao.insertProject(project);

            Project resultProject = new ProjectImpl(resultID, "AfterUpdateName", "AfterUpdateDescription");
            assertTrue(dao.updateProject(resultProject));

            resultProject = dao.findProjectByID(resultID);
            assertEquals("AfterUpdateName", resultProject.getProjectName());
            assertEquals("AfterUpdateDescription", resultProject.getProjectDescription());

        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        } catch (HRSSystemException e) {
            fail();
        }
    }

    protected void tearDown() throws Exception {
        try {
            super.tearDown();
            conn.close();
            System.out.println("Connection closed!");
        } catch (AssertionFailedError e) {
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            logger.error(sef.module.percentage.Percentage.setFailedCount(true, 4));
        }
    }
}
