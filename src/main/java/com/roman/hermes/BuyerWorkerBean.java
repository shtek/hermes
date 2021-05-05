package com.roman.hermes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerWorkerBean {
    private final static Logger LOGGER = LoggerFactory.getLogger(BuyerWorkerBean.class);
    String response;
    public String buy(String url) {
      ChromeDriver driver = ChromeDriverHelper.build();

     try{
         driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // WebElement account = driver.findElement(By.className("button.button-icon.button-not-black"));
        // WebElement search = driver.findElement(By.name("search"));
       //String  aTitle = driver.getPageSource();

        // Thread.sleep(10000);
       List<WebElement > accounts  = driver.findElementsByXPath("//*[contains(@class,'button button-icon button-not-black')]");
      accounts.get(1).click();
       Thread.sleep(2000);

         WebElement username=  driver.findElement(By.id("login-email"));

          WebElement password=driver.findElement(By.id("login-password"));
    //     String inputText = "shtek@yahoo.com";
      //   String pass = "1973roman";
       //  String js = "arguments[0].setAttribute('value','"+inputText+"')" ;
       //              ((JavascriptExecutor) driver).executeScript(js, username);
       //  String js2 = "arguments[0].setAttribute('value','"+pass+"')" ;
      //   ((JavascriptExecutor) driver).executeScript(js2, password);
        System.out.println("ooooooooooooooooooooooooooooo");
           username.sendKeys("shtek@yahoo.com");
         Thread.sleep(2000);



          password.sendKeys("passwprd");
         Thread.sleep(2000);
          password.submit();
//          WebElement submit=driver.findElementByXPath("//*[contains(@class,'button form-submit ripple-effect')]");

  //       submit.click();
        // System.out.println("xxxxxxx" + submit.getText());
         //
         //
         Thread.sleep(30000);
         System.out.println("vvvvvvvvvvvvvvvvv");

         //System.out.println("bzz" + username.s);
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
