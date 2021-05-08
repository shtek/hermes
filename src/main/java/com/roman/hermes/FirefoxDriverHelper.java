package com.roman.hermes;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
public class FirefoxDriverHelper {
    public static boolean isWindowsOperatingSystem() {
        String os = System.getProperty("os.name");
        return os.contains("Windows");

    }
    public static FirefoxDriver build() {
        String driverPath = new File(HermesApplication.class.getClassLoader().getResource("static/Linux/geckodriver").getFile()).getAbsolutePath();
        if (isWindowsOperatingSystem()) {
            System.setProperty("webdriver.gecko.driver", "target/classes/static/Windows/geckodriver.exe");
        }
        else
        {
            System.setProperty("webdriver.gecko.driver", "classes/static/Linux/geckodriver");
        }


        FirefoxProfile profile = new FirefoxProfile();
       // profile.setPreference("network.proxy.no_proxies_on", "localhost");
        profile.setPreference("javascript.enabled", true);

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        FirefoxOptions options = new FirefoxOptions();
        options.merge(capabilities);
//        options.setLogLevel(Level.FINEST);
 //      options.addPreference("browser.link.open_newwindow", 3);
  //      options.addPreference("browser.link.open_newwindow.restriction", 0);

        FirefoxDriver driver = new FirefoxDriver(options);



        return driver;
    }
}
