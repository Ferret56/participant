package sef.module18.activity;

import junit.framework.TestCase;

public class ProjectRepositoryTest extends TestCase {
    ProjectRepositoryTest1 runTest = new ProjectRepositoryTest1();

    protected void setUp() throws Exception{
        runTest.setUp();
    }

    public void testFindProjectById(){runTest.testFindProjectById();}
    public void testFindProjectByName(){runTest.testFindProjectByName();}
    public void testFindProjectByEmployee(){runTest.testFindProjectByEmployee();}
    public void testInsertProject(){runTest.testInsertProject();}
    public void testUpdateProject(){runTest.testUpdateProject();}

}
