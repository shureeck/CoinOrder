import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Region {
    public static boolean selectRegion(String region, WebDriver driver){
        boolean state=false;


        try {
           // driver.findElement(By.id("C_OBL")).click();
            Select select = new Select(driver.findElement(By.id("C_OBL")));
            select.selectByVisibleText(region);
            driver.findElement(By.cssSelector("html body section div div div form table.tables"));
            state=true;
            System.out.println("Есть доступные для покупки монеты");
            Loger.setLog("Есть доступные для покупки монеты");
        }
        catch (NoSuchElementException e){
            state = false;
            System.out.println("Нет доступных для покупки монет");
            Loger.setLog("Нет доступных для покупки монет");
        }
        catch (TimeoutException e){
            state = false;
            System.out.println("Нет доступных для покупки монет");
            Loger.setLog("Нет доступных для покупки монет");
        }
        return state;
    }
}
