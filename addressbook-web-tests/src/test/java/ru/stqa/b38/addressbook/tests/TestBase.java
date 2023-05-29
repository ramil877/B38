package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import ru.stqa.b38.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", "firefox"));

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @BeforeMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

}
