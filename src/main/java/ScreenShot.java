import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ScreenShot {
    public static void takeCapchaScreen (WebDriver driver) {
        //Take Sceenshot
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        WebElement capcha = driver.findElement(By.id("ZamovCaptcha_CaptchaImageDiv"));
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            BufferedImage img = ImageIO.read(screen);

        // Получаем размеры элемента
        int width = capcha.getSize().getWidth();
        int height = capcha.getSize().getHeight();

        // Получаем координаты элемента
        Point p = capcha.getLocation();
        // Создаем прямоуголник (Rectangle) с размерами элемента
        Rectangle rect = new Rectangle(p.getX(), p.getY(),width,height);
        // Вырезаем изображенеи элемента из общего изображения
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
        // Перезаписываем File screen
        ImageIO.write(dest, "png", screen);
        // Возвращаем File c изображением элемента*/
            Files.copy(screen.toPath(), new File("screen.png").toPath(), REPLACE_EXISTING);
    }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
