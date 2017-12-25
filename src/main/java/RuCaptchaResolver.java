import net.marketer.RuCaptcha;

import java.io.File;

public class RuCaptchaResolver {
    public static String resolveCaptcha(File img){
        //UserKey
        RuCaptcha.API_KEY = "d24a847a84de9fc61828b6b34cde8cc1";
        //init resolver
        String CAPCHA_ID;
        String decryption = null;
        String response = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Resolve capcha
        try {
            System.out.println("Баланс на сервисе RuCaptcha "+RuCaptcha.getBalance()+"руб.");
            Loger.setLog("Баланс на сервисе RuCaptcha "+RuCaptcha.getBalance()+"руб.");

            System.out.println("Отправка запроса на сервис RuCaptcha");
            Loger.setLog("Отправка запроса на сервис RuCaptcha");
            response = RuCaptcha.postCaptcha(img);

            if (response.startsWith("OK")) {
                CAPCHA_ID = response.substring(3);
                while (true) {
                    response = RuCaptcha.getDecryption(CAPCHA_ID);
                    System.out.println("Статус решения Capcha: "+response);
                    Loger.setLog("Статус решения Capcha: "+response);

                    if (response.equals(RuCaptcha.Responses.CAPCHA_NOT_READY.toString())) {
                        Thread.sleep(1500);
                        continue;
                    } else if (response.startsWith("OK")) {
                        decryption = response.substring(3);
                        break;
                    }
                }
            }
        }//try
         catch (Exception e) {
            System.out.println("Ошибка: Не удалось решить Capcha.");
             Loger.setLog("Ошибка: Не удалось решить Capcha.");
            System.out.println(e);
        }//catch
        System.out.println("Успешно: Ответ на Capcha:"+decryption);
        Loger.setLog("Успешно: Ответ на Capcha:"+decryption);
        return decryption;
    }
}
