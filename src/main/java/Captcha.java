import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Captcha {
    public static String resolveCaptcha(WebDriver driver){
        File captchaImg = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads\\Generate.gif");
        String answer=null;

        //Drop capture image if exist
        try {
            Files.deleteIfExists(captchaImg.toPath());
            System.out.println("Успешно: Файл с изображением Captcha удален");
            Loger.setLog("Успешно: Файл с изображением Captcha удален");
        }
        catch (IOException e){
            System.out.println("Ошибка: Не удалось удалить изображение Captcha");
            Loger.setLog("Ошибка: Не удалось удалить изображение Captcha");
            System.out.println(e);
            Loger.setLog(e.toString());
        }
        //Save captcha
        boolean stateCaptchaSaving = CaptchaSaving.saveCaptcha(driver);

        //Resolve captcha
        if (stateCaptchaSaving){
            answer= RuCaptchaResolver.resolveCaptcha(captchaImg);
        }
        return answer;

    }
}
