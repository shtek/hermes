package com.roman.hermes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuyerWorkerBean {
    private final static Logger LOGGER = LoggerFactory.getLogger(BuyerWorkerBean.class);
    String response;
    public String buy(String url) {
      ChromeDriver driver = ChromeDriverHelper.build();

     try{
         driver.get(url);
       //  WebDriverWait wait = new WebDriverWait(driver, 100);

         WebElement account = driver.findElement(By.className("button.button-icon.button-not-black"));
         Thread.sleep(5000);
        // WebElement account  = driver.findElementByClassName("button.button-icon.button-not-black");
        //account.click();
         //By by = new By.ByClassName("button.button-icon.button-not-black");
      //   WebElement username=  driver.findElement(By.id("login-email"));

        // WebElement password=driver.findElement(By.id("login-password"));
        // WebElement login=driver.findElement(By.className("button.form-submit.ripple-effect"));

         //username.sendKeys("username");
        // password.sendKeys("password");
       //  password.submit();
        // Thread.sleep(10000);
         System.out.println("bzz");
         // response= driver.getPageSource();
     }
     catch (Throwable t){
         t.printStackTrace();
         LOGGER.error(t.getMessage());
     }
     finally {
         driver.quit();
     }

      return  response;
  }
}
