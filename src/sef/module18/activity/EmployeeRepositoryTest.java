package sef.module18.activity;

import junit.framework.TestCase;

public class EmployeeRepositoryTest extends TestCase {
    EmployeeRepositoryTest1 runTest = new EmployeeRepositoryTest1();

    protected void setUp() throws Exception{
        runTest.setUp();
    }

    public void testFindEmployeeID(){
        runTest.testFindEmployeeByID();
    }
    public void testFindEmployeeByName(){
        runTest.testFindEmployeeByName();
    }
    public void testFindEmployeeByProfelLevel(){runTest.testFindEmployeeByProfelLevel();}
    public void  testFindEmployeeByProject(){runTest.testFindEmployeeByProject();}
    public void testInsertEmployee(){runTest.testInsertEmployee();}
    public void testUpdateEmployee(){runTest.testUpdateEmployee();}
    protected void tearDown() throws Exception {
        runTest.tearDown();
    }

}
