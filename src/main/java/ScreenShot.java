import org.openqa.selenium.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ScreenShot {
    public static boolean takeCapchaScreen (WebDriver driver) {
        boolean state =false;
        //Take Sceenshot
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,10000)", "");
        WebElement capcha = driver.findElement(By.id("ZamovCaptcha_CaptchaImageDiv"));

        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            BufferedImage img = ImageIO.read(screen);

        // Get capcha size
        int width = capcha.getSize().getWidth();
        int height = capcha.getSize().getHeight();

        // Get position capcha
        Point p = capcha.getLocation();
        int html = driver.findElement(By.cssSelector("html")).getSize().getHeight();
        int delta = html-img.getHeight();
        //Create Rectangle with element size and position
        Rectangle rect = new Rectangle(p.getX(), (p.getY()-delta),height,width);
        //Cat image from screen
        BufferedImage dest = img.getSubimage(p.getX(), (p.getY()-delta), rect.width, rect.height);
        // Rewrite file
        ImageIO.write(dest, "png", screen);
        // Copy File with capcha image
            Files.copy(screen.toPath(), new File("capcha.png").toPath(), REPLACE_EXISTING);
            state=true;
            System.out.println("Успешно: Скриншот Captcha сохранен");
            Loger.setLog("Успешно: Скриншот Captcha сохранен");

    }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка: Не удалось сохранить скриншот Captcha");
            Loger.setLog("Ошибка: Не удалось сохранить скриншот Captcha");
        }
        return state;

    }
}
