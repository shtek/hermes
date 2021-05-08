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


        List<WebElement> accounts;
     try {

         //Creating the JavascriptExecutor interface object by Type casting
        // JavascriptExecutor js = (JavascriptExecutor)driver;
        //login from home page
         driver.get("https://www.hermes.com");
       //  System.out.println("------");
    //    Thread.sleep(5000); // this is enough time to solve puzzle
         //else it throws me to exception
        // js.executeAsyncScript("document.write(\"My message\");");

         WebDriverWait wait = new WebDriverWait(driver, 10);
       // wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//*[contains(@class,'button button-icon button-not-black ng-tns-c50-2')]")));
       // wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[contains(@class,'button button-icon button-not-black ng-tns-c50-2')]")));
         wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(new By.ByXPath("//*[contains(@class,'button button-icon button-not-black')]")));

         // WebElement account = driver.findElementByXPath("//*[contains(@class,'button button-icon button-not-black ng-tns-c50-2')]");
         accounts = driver.findElementsByXPath("//*[contains(@class,'button button-icon button-not-black')]");

         // WebElement account = driver.findElement(By.className("button button-icon button-not-black ng-tns-c50-2"));
          accounts.get(1).click();
          //now this will open a login page:
         wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ById("login-email")));
         WebElement username=  driver.findElement(By.id("login-email"));
         wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ById("login-password")));
         WebElement password=driver.findElement(By.id("login-password"));
         username.sendKeys("shtek@yahoo.com");

         password.sendKeys("pass");

         password.submit();
         System.out.println("succesfully loged in");
         ((JavascriptExecutor)driver).executeScript("window.open()");
         ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(1));
         driver.get(url);
         wait.until(ExpectedConditions.elementToBeClickable (new By.ById("add-to-cart-button-in-stock")));
         WebElement addToCart = driver.findElement(By.id("add-to-cart-button-in-stock"));
       //  wait.until(ExpectedConditions.elementToBeClickable (new By.ById("add-to-cart-button-in-stock")));
       //  WebElement addToCart =driver.findElement(By.id("add-to-cart-button-in-stock"));
         addToCart.click();
         //navigate to cart
         ((JavascriptExecutor)driver).executeScript("window.open()");
         tabs = new ArrayList<String>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(2));
         driver.get("https://www.hermes.com/uk/en/cart/");
         //submit the cart content
         wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//*[contains(@class,'button-dark width-auto button-angular')]")));

         // wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByClassName("button-dark width-auto button-angular")));
       //  wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByName("Checkout")));
         WebElement submitCart = driver.findElementByXPath("//*[contains(@class,'button-dark width-auto button-angular')]");
        // WebElement submitCart = driver.findElementByName("Checkout");

        // wait.until(ExpectedConditions.elementToBeClickable (new By.ByClassName("button-dark width-auto button-angular")));
       submitCart.click();


         Thread.sleep(12000);
         System.out.println("ended ok");
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
