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
                    //Make order
                    driver.findElement(By.id("zamovbtn")).click();
                    boolean orderState = Order.order(region, coinList, driver);

                    //Resolve captcha
                    String autocapcha = config.getElementsByTagName("autocapcha").item(0).getTextContent();
                    if (autocapcha.equalsIgnoreCase("yes")) {
                        System.out.println("Каптча будет решена автоматически");
                        Loger.setLog("Каптча будет решена автоматически");
                        String captchaAnswer = null;
                        if (orderState) {
                            captchaAnswer = Captcha.resolveCaptcha(driver);
                        }
                        if (captchaAnswer != null && autocapcha.length() <= 3) {
                            driver.findElement(By.id("CaptchaInputText")).click();
                            driver.findElement(By.id("CaptchaInputText")).sendKeys(captchaAnswer);

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            driver.findElement(By.id("submitBtn")).click();

                            try {
                                driver.findElement(By.cssSelector("html body section div div.row address"));
                                System.out.println("Успешно: Заказ выполнен");
                                Loger.setLog("Успешно: Заказ выполнен");
                            } catch (NotFoundException e) {
                                System.out.println("Ошибка: Не удалось дождаться страници заказа. Стутс не известен");
                                Loger.setLog("Ошибка: Не удалось дождаться страници заказа. Стутс не известен");
                            }
                        } else {
                            System.out.println("Ошибка: Не удалось получить решение Captcha");
                            Loger.setLog("Ошибка: Не удалось получить решение Captcha");
                        }
                    }//if
                    else {
                        try {
                            driver.findElement(By.cssSelector("html body section div div.row address"));
                            System.out.println("Успешно: Заказ выполнен");
                            Loger.setLog("Успешно: Заказ выполнен");
                        } catch (NotFoundException e) {
                            System.out.println("Ошибка: Не удалось дождаться страници заказа. Стутс не известен");
                            Loger.setLog("Ошибка: Не удалось дождаться страници заказа. Стутс не известен");
                        }
                    }
                }//if
                driver.quit();
                i++;
            }//while
    }

}
