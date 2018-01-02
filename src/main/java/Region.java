import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;


public class Region {
    public static boolean selectRegion(String region, WebDriver driver){
        boolean state=false;


        try {
           // driver.findElement(By.id("C_OBL")).click();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            Select select = new Select(driver.findElement(By.id("C_OBL")));
            select.selectByVisibleText(region);
            System.out.println("Успешно: Выбран регион" +region);
            Loger.setLog("Успешно:Выбран регион" +region);
            driver.findElement(By.cssSelector("html body section div div div form table.tables"));
            state=true;
            System.out.println("Успешно:Есть доступные для покупки монеты");
            Loger.setLog("Успешно:Есть доступные для покупки монеты");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        catch (NoSuchElementException e){
            state = false;
            System.out.println("Ошибка:Нет доступных для покупки монет");
            Loger.setLog("Ошибка:Нет доступных для покупки монет");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }
        return state;
    }
}
