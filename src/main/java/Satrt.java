import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Poliakov.A on 11/23/2017.
 */
public class Satrt {
    public static void main(String[] args){

        //Read config
        Element config = ConfigReader.readConfig("config.xml");

        //Get users list
        NodeList userList = config.getElementsByTagName("user");

        //Get coin list
        NodeList coinList = config.getElementsByTagName("coin");

        try {
            Files.deleteIfExists(new File("Order.csv").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (i<userList.getLength()) {
            //Set User Settings
            String login = User.getLogin(userList.item(i));
            String password = User.getPassword(userList.item(i));
            ArrayList<String> region = User.getRegion(userList.item(i));

            System.out.println("Используются коннекты пользователя: " + login);
            Loger.setLog(">>>>>>>>>>Используются коннекты пользователя: " + login);

            //Start Chrome configure
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            driver.get("https://bank.gov.ua/webselling");

                //Go to Web-site and login
                boolean stateLogin;
                stateLogin = Logining.login(login, password, driver);

                if (stateLogin) {
                    if(config.getElementsByTagName("report").item(0).getTextContent().equalsIgnoreCase("yes")) {
                        //Make report
                        System.out.println("Используется режим создания списка заказов");
                        Loger.setLog("Используется режим создания списка заказов");
                        Reporter.getReport(driver, coinList, login + "\t" + password + "\t");
                    }
                    else {
                        System.out.println("Используется режим автозаказа");
                        Loger.setLog("Используется режим автозаказа");
                        //Make order
                        driver.findElement(By.id("zamovbtn")).click();
                        boolean orderState = Order.order(region, coinList, driver);
                        ResolveCapcha.resolveCapcha(driver, orderState, config);
                    }
                }//if
                driver.quit();
                i++;
            }//while
    }

}
