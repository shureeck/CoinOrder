import org.openqa.selenium.WebDriver;
import java.io.File;


public class Captcha {
    public static String resolveCaptcha(WebDriver driver){
        File captchaImg = new File("capcha.png");
        String answer=null;

        //Save captcha
        boolean stateCaptchaSaving = ScreenShot.takeCapchaScreen(driver);

        //Resolve captcha
        if (stateCaptchaSaving){
            answer= RuCaptchaResolver.resolveCaptcha(captchaImg);
        }
        return answer;

    }
}
