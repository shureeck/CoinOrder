import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class CoinName {
    public static StringBuffer getCoinName(WebDriver driver, Node coin){
        StringBuffer temp = new StringBuffer();

        driver.findElement(By.linkText("Особистий кабінет")).click();
        driver.findElement(By.linkText("Мої замовлення")).click();

        ArrayList<WebElement> rows = new ArrayList<>();
        rows.addAll(driver.findElements(By.cssSelector("html body section div div div div div div div div div#zamovlennya_wrapper table tbody tr")));

            int i = 0;
            while (i < rows.size()) {

                if ((rows.get(i).findElements(By.tagName("td")).get(1).getText()).toLowerCase().contains(coin.getTextContent())) {
                    temp.append( rows.get(i).findElements(By.tagName("td")).get(4).getText().trim()+"\t");
                    temp.append((rows.get(i).findElements(By.tagName("td")).get(1).getText())+"\n");
                }
                i++;
            }
        return temp;
    }
}
