import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;

public class Tasks extends Main{

    static void Comparison(double value, BigDecimal from, BigDecimal to, BigDecimal by, int term, double installment, double remValue){

        //Start chrome
        System.setProperty("webdriver.chrome.driver","C:\\Users\\TwoTap\\IdeaProjects\\SeleniumAutomation\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.seb.lt/eng/private/calculator-leasing");

        //Wait 2 seconds, accept cookies
        try{
             new WebDriverWait(driver,2);
            driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/ul/li[1]/a")).click();
        }catch (Exception e){}

        //Wait for the webpage to load
    driver.switchTo().frame("calculator-frame-06");
        WebDriverWait wait= new WebDriverWait(driver, 5);
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/form/fieldset/div/div[1]/div[1]/div[2]/input")));

       //Send value
         driver.findElement(By.name("summa")).sendKeys(String.valueOf(value));

       //Send first installment
        driver.findElement(By.id("f-maksa")).clear();
        driver.findElement(By.id("f-maksa")).sendKeys(String.valueOf(installment));

        // Send remaining value
        driver.findElement(By.id("f-rest")).clear();
        driver.findElement(By.id("f-rest")).sendKeys(String.valueOf(remValue));

        //select loan year
        Select years = new Select(driver.findElement(By.id("f-termins")));
        years.selectByVisibleText(String.valueOf(term)+" years");


        //Send and increase interest rate
        //Keep clicking comparison to see the full view

        while(from.compareTo(to.add(by)) < 0 ){

            driver.findElement(By.id("f-likme")).clear();
            driver.findElement(By.id("f-likme")).sendKeys(String.valueOf(from));
              from=from.add(by);

            driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[2]/footer/div/div/button[1]")).click();


        }
    }


    static void PaymentCalculation(double value, double rate,int term, double installment, double remValue,double payment){
        //Start chrome
        System.setProperty("webdriver.chrome.driver","C:\\Users\\TwoTap\\IdeaProjects\\SeleniumAutomation\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.seb.lt/eng/private/calculator-leasing");

        //Wait 2 seconds, accept cookies
        try{
            new WebDriverWait(driver,2);
            driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/ul/li[1]/a")).click();
        }catch (Exception e){}

        //Wait to load
        driver.switchTo().frame("calculator-frame-06");
        WebDriverWait wait= new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/form/fieldset/div/div[1]/div[1]/div[2]/input")));

        //Send value
        driver.findElement(By.id("f-summa")).sendKeys(String.valueOf(value));

        //send interest
        driver.findElement(By.id("f-likme")).clear();
        driver.findElement(By.id("f-likme")).sendKeys(String.valueOf(rate));

        //Send first installment
        driver.findElement(By.id("f-maksa")).clear();
        driver.findElement(By.id("f-maksa")).sendKeys(String.valueOf(installment));

        // Send remaining value
        driver.findElement(By.id("f-rest")).clear();
        driver.findElement(By.id("f-rest")).sendKeys(String.valueOf(remValue));

        //select loan year
        Select years = new Select(driver.findElement(By.id("f-termins")));
        years.selectByVisibleText(String.valueOf(term)+" years");

        //Comparison
        driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[1]/footer/div[2]/button")).click();

        double paymentSuggestion = Double.parseDouble(driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[2]/div[3]/div[2]/span[1]")).getText());

        //Adds by 5% for faster compilation
        while (paymentSuggestion>payment){
            installment=installment+5;
            driver.findElement(By.id("f-maksa")).clear();
            driver.findElement(By.id("f-maksa")).sendKeys(String.valueOf(installment));
            driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[1]/footer/div[2]/button")).click();
            paymentSuggestion = Double.parseDouble(driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[2]/div[3]/div[2]/span[1]")).getText());
        }
        WebDriverWait wai= new WebDriverWait(driver, 5);

        //Finds one value above selected monthly payment
        while (paymentSuggestion<payment){
            installment=installment-1;
            driver.findElement(By.id("f-maksa")).clear();
            driver.findElement(By.id("f-maksa")).sendKeys(String.valueOf(installment));
            driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[1]/footer/div[2]/button")).click();
            paymentSuggestion = Double.parseDouble(driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[2]/div[3]/div[2]/span[1]")).getText());
        }
        driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[2]/footer/div/div/button[1]")).click();

        //Finds one value below selected monthly payment
        driver.findElement(By.id("f-maksa")).clear(); driver.findElement(By.id("f-maksa")).sendKeys(String.valueOf(installment+1));
        driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[1]/footer/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/div/form/fieldset/div/div[2]/footer/div/div/button[1]")).click();
    }

}
