import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
                    //Make report
                    Reporter.getReport(driver,coinList,login+"\t"+password+"\t");

                    //Make order
                    driver.findElement(By.id("zamovbtn")).click();
                    boolean orderState = Order.order(region, coinList, driver);
                    ResolveCapcha.resolveCapcha(driver, orderState ,config);
                }//if
            
                driver.quit();
                i++;
            }//while
    }

}
