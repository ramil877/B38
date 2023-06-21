package ru.stqa.b38.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final String browser;
  private final Properties properties;
  WebDriver wd;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

      public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


        if (browser.equals("chrome")) {
    } else if (browser.equals("firefox")) {
      wd = new FirefoxDriver();
    }

    System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
  }

  public void stop() {
    wd.quit();
  }

}
