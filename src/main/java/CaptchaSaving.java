import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CaptchaSaving {
    public static boolean saveCaptcha(WebDriver driver){
        boolean status =false;
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(By.id("CaptchaImage"))).perform();
        try {
            Robot rb = new Robot();
            rb.delay(500);
            rb.keyPress(KeyEvent.VK_DOWN);
            rb.keyRelease(KeyEvent.VK_DOWN);
            rb.keyPress(KeyEvent.VK_DOWN);
            rb.keyRelease(KeyEvent.VK_DOWN);
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
            rb.delay(2500);
            rb.keyPress(KeyEvent.VK_ENTER);;
            rb.keyRelease(KeyEvent.VK_ENTER);
            rb.delay(500);
            System.out.println("Успешно: Изображение Capture сохранено");
            Loger.setLog("Успешно: Изображение Capture сохранено");
            status =true;
        } catch (AWTException ex) {
            System.out.println("Ошибка: Не удалось сохранить изображение Capture");
            Loger.setLog("Ошибка: Не удалось сохранить изображение Capture");
            status =false;
        }
        return status;
    }
}

