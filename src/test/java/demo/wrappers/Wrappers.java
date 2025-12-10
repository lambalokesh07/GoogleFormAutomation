package demo.wrappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {

    public static void enterText(WebElement element,String text){
        try {
            if (element == null) {
                System.out.println("enterText FAILED: Element is NULL");
                return;
            }
            System.out.println("Typing text: " + text);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("enterText Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void radioButton(ChromeDriver driver, String radiobuttontext){
        try {
            System.out.println("Selecting radio button: " + radiobuttontext);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement radioBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(@class,'OvPDhc') and contains(text(),'" 
                            + radiobuttontext + "')]/../../..//div[@class='vd3tt']")
                )
            );
            radioBtn.click();

        } catch (Exception e) {
            System.out.println("radioButton Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void checkBox(List<WebElement> elements, String checkbuttontext){
        try {
            System.out.println("Selecting checkbox: " + checkbuttontext);

            for (WebElement ele : elements) {
                if (ele.getText().equals(checkbuttontext)) {
                    ele.click();
                    System.out.println("Checkbox clicked: " + checkbuttontext);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("checkBox Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void selectDropDown(ChromeDriver driver, String dropdowntext){
        try {
            System.out.println("Selecting dropdown: " + dropdowntext);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String xpath = "//div[contains(@class,'QXL7Te')]//span[normalize-space()='" + dropdowntext + "']";

            WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath))
            );

            option.click();
        } catch (Exception e) {
            System.out.println("selectDropDown Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void clickOnElement(ChromeDriver driver,WebElement element){
        try {
            System.out.println("Performing JS Click");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("JS Click Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getDatesDaysAgo(int days){
        try {
            LocalDate date = LocalDate.now();
            LocalDate date7DaysAgo = date.minusDays(days);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            String formattedDate = date7DaysAgo.format(formatter);
            System.out.println("Generated Date: " + formattedDate);
            return formattedDate;

        } catch (Exception e) {
            System.out.println("getDatesDaysAgo Exception: " + e.getMessage());
            return null;
        }
    }

    public static String getEpochTime(){
        long epochTime = System.currentTimeMillis()/1000;
        String epochTimeString = String.valueOf(epochTime);
        System.out.println("Epoch time: " + epochTimeString);
        return epochTimeString;
    }

    public static boolean handleAlert(ChromeDriver driver){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.alertIsPresent());

            driver.switchTo().alert().dismiss();
            System.out.println("Alert dismissed successfully");
            return true;

        } catch (Exception e) {
            System.out.println("No alert found: " + e.getMessage());
            return false;
        }
    }
}
