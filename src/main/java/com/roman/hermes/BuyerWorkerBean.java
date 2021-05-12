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
//         driver.get("https://www.hermes.com");
         WebDriverWait wait = new WebDriverWait(driver, 10);

         driver.get("https://www.hermes.com/uk/en/privacy-and-cookies-uk/");
         wait.until(ExpectedConditions.elementToBeClickable (new By.ById("onetrust-reject-all-handler")));
         WebElement rejectCookies = driver.findElement(By.id("onetrust-reject-all-handler"));
         rejectCookies.click();
         driver.get("https://www.hermes.com");

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

         password.sendKeys("xxx");

         password.submit();
         System.out.println("succesfully loged in");
         ((JavascriptExecutor)driver).executeScript("window.open()");
         ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(1));
         //here we going to reject cookies
        // driver.get("https://www.hermes.com/uk/en/privacy-and-cookies-uk/");
       //  wait.until(ExpectedConditions.elementToBeClickable (new By.ById("onetrust-reject-all-handler")));
       //  WebElement rejectCookies = driver.findElement(By.id("onetrust-reject-all-handler"));
        // rejectCookies.click();
        // tabs = new ArrayList<String>(driver.getWindowHandles());
        // driver.switchTo().window(tabs.get(1));
         driver.get(url);

         wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//*[contains(@id,'add-to-cart-button-in-stock')]")));

       //  wait.until(ExpectedConditions.visibilityOfElementLocated (new By.ById("add-to-cart-button-in-stock")));
         //wait.until(ExpectedConditions.elementToBeClickable (new By.ById("add-to-cart-button-in-stock")));
        // driver.findElementByName(add-to-cart)
         WebElement addToCart = driver.findElementByXPath("//*[contains(@id,'add-to-cart-button-in-stock')]");
        //wait.until(ExpectedConditions.elementToBeClickable (new By.ById("add-to-cart-button-in-stock")));
       //  WebElement addToCart =driver.findElement(By.id("add-to-cart-button-in-stock"));
         addToCart.click();
         //navigate to cart
         //sometimes when it it is too fast the item is not in the cart yet
         //sometimes it throws accept cookis screen , and it messes up things
         //navigating to new screen does not help, if i can not put item in the cart quickly

        ((JavascriptExecutor)driver).executeScript("window.open()");
         tabs = new ArrayList<String>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(2));
         driver.get("https://www.hermes.com/uk/en/cart/");
         //submit the cart content
//         wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[contains(@class,'button-dark width-auto button-angular')]")));
         wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//*[contains(@class,'button-dark width-auto button-angular')]")));

         WebElement submitCart = driver.findElementByXPath("//*[contains(@class,'button-dark width-auto button-angular')]");

         submitCart.click();

    ///this page opens
    //     https://www.hermes.com/uk/en/checkout/?v=2
         //it contains:button id="checkoutPaiementButton"
//it redirects to https://www.hermes.com/uk/en/checkout/?v=2
         //in reality just making some hidden field visible
         ///perform checkout
         wait.until(ExpectedConditions.elementToBeClickable(new By.ById("checkoutPaiementButton")));

         WebElement continueSubmit = driver.findElementById("checkoutPaiementButton");
         continueSubmit.click();

         //select payment method
         //credit card
         //assumed that the address and billing address is already pre populated

         wait.until(ExpectedConditions.elementToBeClickable(new By.ById("radio-button-payment_method-0-input")));

        // wait.until(ExpectedConditions.elementToBeClickable(new By.ById("radio-button-payment_method-0-input")));
         //WebElement creditCardRadioButton = driver.findElementByXPath("//*[contains(@class,'mat-radio-outer-circle')]");

         JavascriptExecutor exe = (JavascriptExecutor) driver;
         int noOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
         System.out.println("No. of iframes on the page are " + noOfFrames);


         WebElement creditCardRadioButton = driver.findElementById("radio-button-payment_method-0-input");
         JavascriptExecutor js = (JavascriptExecutor)driver;
         js.executeScript("arguments[0].click();", creditCardRadioButton);

         exe = (JavascriptExecutor) driver;
         int noOfFrames2 = Integer.parseInt(exe.executeScript("return window.length").toString());
         System.out.println("No. of iframes on the page are 2 " + noOfFrames2);

         //now populate name of card holder
          wait.until(ExpectedConditions.visibilityOfElementLocated (new By.ByXPath("//*[contains(@class,'adyen-checkout__input adyen-checkout__input--text adyen-checkout__input--large')]")));
         //*[contains(@class,'adyen-checkout__input adyen-checkout__input--text adyen-checkout__input--large')]
          WebElement cardHolderName = driver.findElementByXPath("//*[contains(@class,'adyen-checkout__input adyen-checkout__input--text adyen-checkout__input--large')]");
          cardHolderName.sendKeys("12345566");

         int noOfFrames3 = Integer.parseInt(exe.executeScript("return window.length").toString());
         System.out.println("No. of iframes on the page are 3 " + noOfFrames3);


         //now populate card number
         //first change to iframe
       //  js-iframe
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(new  By.ByXPath("//iframe[@class='js-iframe']")));
         wait.until(ExpectedConditions.visibilityOfElementLocated ( new By.ByXPath("//*[contains(@id,'encryptedCardNumber')]")));

         WebElement cardNumber = driver.findElementByXPath("//*[contains(@id,'encryptedCardNumber')]");
         cardNumber.sendKeys("1111222233334444");
//populate expiry date

         int noOfFrames4 = Integer.parseInt(exe.executeScript("return window.length").toString());
         System.out.println("No. of iframes on the page are 4 " + noOfFrames4);


         driver.switchTo().defaultContent();

         int noOfFrames5 = Integer.parseInt(exe.executeScript("return window.length").toString());
         System.out.println("No. of iframes on the page are 5 " + noOfFrames5);

         driver.switchTo().frame(1);
         int noOfFrames6 = Integer.parseInt(exe.executeScript("return window.length").toString());
         System.out.println("No. of iframes on the page are 6 " + noOfFrames6);


         wait.until(ExpectedConditions.visibilityOfElementLocated ( new By.ByXPath("//*[contains(@id,'encryptedExpiryDate')]")));

        WebElement cardExpiryDate = driver.findElementByXPath("//*[contains(@id,'encryptedExpiryDate')]");
        cardExpiryDate.sendKeys("0224");

        //switch to another frame
         //in order to populate security code
         driver.switchTo().defaultContent();
         driver.switchTo().frame(2);
         wait.until(ExpectedConditions.visibilityOfElementLocated ( new By.ByXPath("//*[contains(@id,'encryptedSecurityCode')]")));

         WebElement securityCode = driver.findElementByXPath("//*[contains(@id,'encryptedSecurityCode')]");
         securityCode.sendKeys("333");

         driver.switchTo().defaultContent();
         wait.until(ExpectedConditions.visibilityOfElementLocated ( new By.ByXPath("//*[contains(@class,'ng-dirty ng-valid ng-touched')]")));
         WebElement acceptTerms = driver.findElementByXPath("//*[contains(@class,'ng-dirty ng-valid ng-touched')]");
         acceptTerms.click();





         Thread.sleep(10000);
         System.out.println("ended ok");
     }


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
