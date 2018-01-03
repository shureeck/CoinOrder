import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Names {
    public static  String getFIO(WebDriver driver){
        String FIO;
        driver.findElement(By.linkText("Особистий кабінет")).click();
        driver.findElement(By.linkText("Персональні дані")).click();
        String lastName = driver.findElement(By.id("LastName")).getAttribute("value");
        String firstName = driver.findElement(By.id("FirstName")).getAttribute("value");
        String middleName = driver.findElement(By.id("MidlName")).getAttribute("value");

        FIO = lastName+" "+firstName+" "+middleName+"\t";

        System.out.println("Успешно: Получино ФИО"+FIO);
        Loger.setLog("Успешно: Получино ФИО"+FIO);

        return FIO;
    }
}
