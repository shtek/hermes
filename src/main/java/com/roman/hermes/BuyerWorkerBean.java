package com.roman.hermes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerWorkerBean {
    private final static Logger LOGGER = LoggerFactory.getLogger(BuyerWorkerBean.class);
    String response;
    public String buy(String url) {
      ChromeDriver driver = ChromeDriverHelper.build();
     //   FirefoxDriver driver = FirefoxDriverHelper.build();

        List<WebElement> accounts;
     try {

         //Creating the JavascriptExecutor interface object by Type casting
        // JavascriptExecutor js = (JavascriptExecutor)driver;
         driver.get(url);
       //  System.out.println("------");
    //    Thread.sleep(5000); // this is enough time to solve puzzle
         //else it throws me to exception
        // js.executeAsyncScript("document.write(\"My message\");");

         WebDriverWait wait = new WebDriverWait(driver, 10);
       // wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//*[contains(@class,'button button-icon button-not-black ng-tns-c50-2')]")));
       // wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[contains(@class,'button button-icon button-not-black ng-tns-c50-2')]")));
         wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(new By.ByXPath("//*[contains(@class,'button button-icon button-not-black')]")));
         System.out.println("-------------------------------------------wating 1");

         // WebElement account = driver.findElementByXPath("//*[contains(@class,'button button-icon button-not-black ng-tns-c50-2')]");
         accounts = driver.findElementsByXPath("//*[contains(@class,'button button-icon button-not-black')]");
         System.out.println("--------------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx-----wating 2");

         //System.out.println(accounts.size());
         // WebElement account = driver.findElement(By.className("button button-icon button-not-black ng-tns-c50-2"));
          accounts.get(1).click();
          //continue login here, somehow more forgiving
         // and then navigate to a new page from a new tab
         ((JavascriptExecutor)driver).executeScript("window.open()");
         ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(1));
         driver.get("https://www.hermes.com/uk/en/product/bolide-1923-30-craft-bag-H073491CKAA/");

         Thread.sleep(5000);
    //     System.out.println("inside usual");
     }
       /* catch(org.openqa.selenium.StaleElementReferenceException ex){
         System.out.println("inside stale");
         accounts = driver.findElementsByXPath("//*[contains(@class,'button button-icon button-not-black')]");
            accounts.get(1).click();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }
*/

     catch (Throwable t){
        System.out.println("I am at arroor");
         t.printStackTrace();
        LOGGER.error(t.getMessage());

    }
     finally {
        driver.quit();
        response = "driver quit";
    }

    //     WebElement username=  driver.findElement(By.id("login-email"));

    //      WebElement password=driver.findElement(By.id("login-password"));
       //  String js = "arguments[0].setAttribute('value','"+inputText+"')" ;
       //              ((JavascriptExecutor) driver).executeScript(js, username);
       //  String js2 = "arguments[0].setAttribute('value','"+pass+"')" ;
      //   ((JavascriptExecutor) driver).executeScript(js2, password);
    //       username.sendKeys("shtek@yahoo.com");
    //     Thread.sleep(2000);



    //      password.sendKeys("pass");
    //     Thread.sleep(2000);
       //   password.submit();

//          WebElement submit=driver.findElementByXPath("//*[contains(@class,'button form-submit ripple-effect')]");

  //       submit.click();



      return  response;
  }
}
