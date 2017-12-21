import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Logining {
    public static boolean login(String login, String password, WebDriver driver){
        boolean status = false;
        try {
        driver.findElement(By.id("vhid")).click();
        driver.findElement(By.id("UserName")).sendKeys(login);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("html body header div div div ul form div button")).click();

        String successLogin = driver.findElement(By.cssSelector("html body header div div div ul div")).getText().trim();

            String check = successLogin.substring(0, successLogin.indexOf(","));
            if (check.equalsIgnoreCase("Вітаємо")) {
                status = true;
                System.out.println("Успешно: Пользователь " + login + " успешно вошел в ситсему");
                Loger.setLog("Успешно: Пользователь " + login + " успешно вошел в ситсему");
            }
            else {
                System.out.println("Ошибка: Пользователю " + login + " не удалось войти в систему");
                Loger.setLog("Ошибка: Пользователю " + login + " не удалось войти в систему");
                status = false;
            }
        }//try
        catch(IndexOutOfBoundsException e) {
            System.out.println("Ошибка: Пользователю " + login + " не удалось войти в систему");
            Loger.setLog("Ошибка: Пользователю " + login + " не удалось войти в систему");
            status = false;
        }
        catch (NoSuchElementException e){
            System.out.println("Ошибка: Не удалось перейти на страницу https://bank.gov.ua/webselling ");
            Loger.setLog("Ошибка: Не удалось перейти на страницу https://bank.gov.ua/webselling ");
        }
        catch(Exception e) {
            System.out.println("Ошибка: Пользователю " + login + " не удалось войти в систему");
            Loger.setLog("Ошибка: Пользователю " + login + " не удалось войти в систему");
            status = false;
        }
        return status;
    }
}
