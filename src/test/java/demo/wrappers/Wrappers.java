package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public static void enterText(WebElement element,String text){
        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void radioButton(ChromeDriver driver, String radiobuttontext){
        //passing paramaterized xpath
        try {
            WebElement radioBtn = driver.findElement(
            By.xpath("//span[contains(@class,'OvPDhc') and contains(text(),'" + radiobuttontext + "')]/../../..//div[@class='vd3tt']"));
        radioBtn.click();


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void checkBox(List<WebElement> elements, String checkbuttontext){
        //passing paramaterized xpath
        try {
            for (WebElement ele : elements) {
                if(ele.getText().equals(checkbuttontext)){
                    ele.click();
                    break;
                }
                
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void selectDropDown(ChromeDriver driver, String dropdowntext){
        //passing paramaterized xpath
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Google Forms option structure
        String xpath = "//div[contains(@class,'QXL7Te')]//span[normalize-space()='" + dropdowntext + "']";

        WebElement option = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath(xpath))
        );

        option.click();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    /// use js to click on element which is present anywhere on the page.
    public static void clickOnElement(ChromeDriver driver,WebElement element){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    public static String getDatesDaysAgo(int days){
       LocalDate date = LocalDate.now();
       LocalDate date7DaysAgo = date.minusDays(days);
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM//DD//yyyy");
       String formattedDate = date7DaysAgo.format(formatter);
       return formattedDate;

    }

    public static String getEpochTime(){
        long epochTime = System.currentTimeMillis()/1000;
        String epochTimeString = String.valueOf(epochTime);
        return epochTimeString;
    }

    public static boolean handleAlert(ChromeDriver driver){
     boolean status = false;
     driver.switchTo().alert().dismiss();
     status = true;
     return status;
    }
}
