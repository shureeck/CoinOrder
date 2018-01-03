import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.NodeList;

/**
 * Created by Poliakov.A on 1/2/2018.
 */
public class Reporter {
    public static void getReport(WebDriver driver, NodeList coinsList, String login_password){
        StringBuffer buffer;
        String full_name=Names.getFIO(driver);

        driver.findElement(By.linkText("Особистий кабінет")).click();
        driver.findElement(By.linkText("Мої замовлення")).click();

        driver.findElement(By.name("zamovlennya_length")).click();
        Select zamovlennya_length = new Select(driver.findElement(By.name("zamovlennya_length")));
        zamovlennya_length.selectByVisibleText("50");
        System.out.println("Успешно: Установлено отображение 50 монет на странице");
        Loger.setLog("Успешно: Установлено отображение 50 монет на странице");

        int i=0;
        while (i<coinsList.getLength()){
            buffer=CoinName.getCoinName(driver,coinsList.item(i));
            if (buffer.length()>5){
                buffer.insert(0,full_name);
                buffer.insert(0,login_password);
                Loger.setReport(buffer);
            }//if
            i++;
        }
    }
}
