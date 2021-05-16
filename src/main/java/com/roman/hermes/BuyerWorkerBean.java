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
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

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

         WebDriverWait wait = new WebDriverWait(driver, 40);

         driver.get("https://www.hermes.com/uk/en/privacy-and-cookies-uk/");
         wait.until(ExpectedConditions.elementToBeClickable (new By.ById("onetrust-reject-all-handler")));
         WebElement rejectCookies = driver.findElement(By.id("onetrust-reject-all-handler"));
         rejectCookies.click();
         driver.get("https://www.hermes.com/uk/en");

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
         //wait for confirmation of succesful login
         wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByClassName("notification-message-title")));
        WebElement welcome =  driver.findElementByClassName("notification-message-title");
      //   wait.until(ExpectedConditions.textToBePresentInElement(welcome,"Welcome,"));
         Pattern pattern = Pattern.compile("^Welcome");
         wait.until( ExpectedConditions.textMatches(new By.ByClassName("notification-message-title"),pattern));

         ((JavascriptExecutor)driver).executeScript("window.open()");
         ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(1));
         driver.get(url);
         Thread.sleep(5000);

         //it is nit just clickable , it is someting else that needs to happen
         // if put timer delay it works!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath("//button[@id='add-to-cart-button-in-stock']")));
         //trigger the reaload of the page
//         driver.findElementByXPath("//button[@id='add-to-cart-button-in-stock']");


         // wait the element "Add Item" to become stale
//        wait.until(ExpectedConditions.stalenessOf(driver.findElementByXPath("//button[@id='add-to-cart-button-in-stock']")));

     //     wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//button[@id='add-to-cart-button-in-stock']")));
     //    WebElement addToCart = driver.findElementByXPath("//button[@id='add-to-cart-button-in-stock']");
     //    wait.until(ExpectedConditions.stalenessOf(driver.findElementByXPath("//button[@id='add-to-cart-button-in-stock']")));
         wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//button[@id='add-to-cart-button-in-stock']")));
         WebElement  addToCart = driver.findElementByXPath("//button[@id='add-to-cart-button-in-stock']");

         addToCart.click();

         //wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@class='button-dark width-80']")));

      //   Thread.sleep(5000);
         //navigate to cart
         //!!!!!!!!!!!!!!!!!!!!!!!!!!!!! too fast item is not in the cart
         //sometimes when it it is too fast the item is not in the cart yet

//perhaps better than navigating to another page, is just clicking on view card button
      // wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByClassName("items-counter ng-tns-c50-2 ng-star-inserted")));
       wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ById("add-to-cart-title")));
       wait.until(ExpectedConditions.textToBe(new By.ById("add-to-cart-title"),"This item has been added to the cart"));


         //
 //        WebElement viewCart = driver.findElementByXPath("//button[@class='button-dark width-80']");
 //        viewCart.click();

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
         //wait here , or it shows something wrong with address
         //perhaps instead of waiting i need to check if shpping address is OK . the radion button selected
//         Thread.sleep(5000);
         wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ById("shipping-addresses-label")));

         wait.until(ExpectedConditions.elementToBeClickable(new By.ById("checkoutPaiementButton")));

         WebElement continueSubmit = driver.findElementById("checkoutPaiementButton");
         continueSubmit.click();
        // Thread.sleep(5000); //too muc try to slow lesd
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


         //now populate name of card holder
          wait.until(ExpectedConditions.visibilityOfElementLocated (new By.ByXPath("//*[contains(@class,'adyen-checkout__input adyen-checkout__input--text adyen-checkout__input--large')]")));
          WebElement cardHolderName = driver.findElementByXPath("//*[contains(@class,'adyen-checkout__input adyen-checkout__input--text adyen-checkout__input--large')]");
          cardHolderName.sendKeys("M PARVU");



         //now populate card number
         //first change to iframe
       //  js-iframe
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(new  By.ByXPath("//iframe[@class='js-iframe']")));
         wait.until(ExpectedConditions.visibilityOfElementLocated ( new By.ByXPath("//*[contains(@id,'encryptedCardNumber')]")));

         WebElement cardNumber = driver.findElementByXPath("//*[contains(@id,'encryptedCardNumber')]");
         cardNumber.sendKeys("174207646141043");
//populate expiry date



         driver.switchTo().defaultContent();


         driver.switchTo().frame(1);


         wait.until(ExpectedConditions.visibilityOfElementLocated ( new By.ByXPath("//*[contains(@id,'encryptedExpiryDate')]")));

        WebElement cardExpiryDate = driver.findElementByXPath("//*[contains(@id,'encryptedExpiryDate')]");
        cardExpiryDate.sendKeys("0124");

        //switch to another frame
         //in order to populate security code
         driver.switchTo().defaultContent();
         driver.switchTo().frame(2);
         wait.until(ExpectedConditions.visibilityOfElementLocated ( new By.ByXPath("//*[contains(@id,'encryptedSecurityCode')]")));

         WebElement securityCode = driver.findElementByXPath("//*[contains(@id,'encryptedSecurityCode')]");
         securityCode.sendKeys("2436");

         driver.switchTo().defaultContent();
         wait.until(ExpectedConditions.visibilityOfElementLocated ( new By.ByXPath("//input[@class='ng-untouched ng-pristine ng-invalid']")));
         WebElement acceptTerms = driver.findElementByXPath("//input[@class='ng-untouched ng-pristine ng-invalid']");
         //have to click through with js script as it is not visible
         js.executeScript("arguments[0].click();", acceptTerms);




         Thread.sleep(10000);
         System.out.println("ended ok");
     }


     catch (Throwable t){
        System.out.println("I am at arroor");
       // System.out.println(driver.getPageSource());
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
