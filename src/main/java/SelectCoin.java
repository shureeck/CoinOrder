import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.NodeList;

import java.util.List;

public class SelectCoin {
    public static boolean selectCoin( NodeList coins, WebDriver driver){
        boolean state=false;
        int count=0;
        int i=0;
            while (i< coins.getLength()){
                String coinName = coins.item(i).getTextContent();
                int j=0;
                List<WebElement> listWebCoin= driver.findElements(By.cssSelector("html body section div div div form table tbody tr td a"));

                while (j<listWebCoin.size()) {
                    String webCoins = listWebCoin.get(j).getText();
                    if (webCoins.toLowerCase().startsWith(coinName.toLowerCase())){
                        try {
                            driver.findElements(By.cssSelector("html body section div div div form table tbody tr td select#coins")).get(j).click();
                            Select select = new Select(driver.findElements(By.cssSelector("html body section div div div form table tbody tr td select#coins")).get(j));
                            select.selectByVisibleText("1");
                            System.out.println("Успешно: Монета " + coinName + " выбрана");
                            Loger.setLog("Успешно: Монета " + coinName + " выбрана");
                            count++;
                            break;
                        }//try
                        catch(ElementNotVisibleException e){
                            System.out.println("Ошибка: Монету " + coinName + " не удалось заказать");
                            Loger.setLog("Ошибка: Монету " + coinName + " не удалось заказать");
                        }//catch
                    }//if
                    j++;
                }//while
                i++;
            }//while
        if (count>0){
            System.out.println("Успешно: Выбрано "+count+" монет из "+coins.getLength());
            Loger.setLog("Успешно: Выбрано "+count+" монет из "+coins.getLength());
            state=true;
        }
        else {
            System.out.println("Ошибка: не удалось выбрать не одной монеты");
            Loger.setLog("Ошибка: не удалось выбрать не одной монеты");
            state=false;
        }
        return state;
    }
}
