import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class Region {
    public static boolean selectRegion(String region, WebDriver driver){
        boolean state=false;

        driver.findElement(By.id("C_OBL")).click();
        Select select = new Select(driver.findElement(By.id("C_OBL")));
        select.selectByVisibleText(region);
        try {
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
        return state;
    }
}
