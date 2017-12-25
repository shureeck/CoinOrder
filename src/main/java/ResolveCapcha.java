import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Element;

public class ResolveCapcha {
    public static void  resolveCapcha(WebDriver driver, boolean orderState, Element config){
        //Resolve captcha
        String autocapcha = config.getElementsByTagName("autocapcha").item(0).getTextContent();
        if (autocapcha.equalsIgnoreCase("yes")) {
            System.out.println("Каптча будет решена автоматически");
            Loger.setLog("Каптча будет решена автоматически");
            String captchaAnswer = null;
            if (orderState) {
                captchaAnswer = Captcha.resolveCaptcha(driver);
            }
            if (captchaAnswer != null) {
                driver.findElement(By.id("CaptchaCode")).click();
                driver.findElement(By.id("CaptchaCode")).sendKeys(captchaAnswer);

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
    }
}
