import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class CoinName {
    public static StringBuffer getCoinName(WebDriver driver, Node coin){
        StringBuffer temp = new StringBuffer();


        ArrayList<WebElement> rows = new ArrayList<>();
        rows.addAll(driver.findElements(By.cssSelector("html body section div div div div div div div div div#zamovlennya_wrapper table tbody tr")));

            int i = 0;
            while (i < rows.size()) {

                if ((rows.get(i).findElements(By.tagName("td")).get(1).getText()).toLowerCase().contains(coin.getTextContent().toLowerCase())) {
                    temp.append( rows.get(i).findElements(By.tagName("td")).get(4).getText().trim()+"\t");
                    temp.append((rows.get(i).findElements(By.tagName("td")).get(1).getText()));
                    System.out.println("Успешно: Моанета: "+(rows.get(i).findElements(By.tagName("td")).get(1).getText())+" добавлена в список");
                    Loger.setLog("Успешно: Моанета: "+(rows.get(i).findElements(By.tagName("td")).get(1).getText())+" добавлена в список");
                    break;

                }
                i++;
            }

        return temp;
    }
}
