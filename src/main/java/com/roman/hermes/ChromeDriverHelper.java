package com.roman.hermes;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
public class ChromeDriverHelper {
    public static boolean isWindowsOperatingSystem() {
        String os = System.getProperty("os.name");
        return os.contains("Windows");

    }
    public static ChromeDriver build() {
        String driverPath = new File(HermesApplication.class.getClassLoader().getResource("static/Linux/chromedriver").getFile()).getAbsolutePath();
        if (isWindowsOperatingSystem()) {
            System.setProperty("webdriver.chrome.driver", "target/classes/static/Windows/chromedriver.exe");
        }
        else
        {
            System.setProperty("webdriver.chrome.driver", "classes/static/Linux/chromedriver");
        }


        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-blink-features",
                "--disable-blink-features=AutomationControlled",
                "--no-sandbox",
           //     "--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 11_3_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36",
                "--window-size=1080,800",
             //   "--window-size=1080,1200",

                "--ignore-certificate-errors",
                "--test-type=webdriver");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("applicationCacheEnabled", false);
        options.merge(desiredCapabilities);

        ChromeDriver driver = new ChromeDriver(options);


        Map<String, Object> params = new HashMap<>();
        params.put("source", "Object.defineProperty(navigator, 'webdriver', { get: () => false })");
        params.put("source", "Object.defineProperty(navigator, 'languages', { get: () => ['en-GB', 'en', 'it'] })");
        params.put("source", "Object.defineProperty(navigator, 'plugins', { get: () => [0, 1, 2] })");
        // driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", params);
        return driver;
    }
}
