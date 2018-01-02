import org.openqa.selenium.WebDriver;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

/**
 * Created by Poliakov.A on 1/2/2018.
 */
public class Reporter {
    public static void getReport(WebDriver driver, NodeList coinsList, String login_password){
        StringBuffer buffer;
        String full_name=Names.getFIO(driver);
        try {
            Files.deleteIfExists(new File("Order.csv").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
