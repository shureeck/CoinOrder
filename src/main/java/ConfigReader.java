import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Poliakov.A on 12/6/2017.
 */
public class ConfigReader {
     public static Element readConfig(String path){
        Element root=null;
       try {
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(path);
           doc.getDocumentElement().normalize();
           root = doc.getDocumentElement();
       }
       catch (ParserConfigurationException e){
           e.printStackTrace();
       }
       catch (IOException e){
           System.out.println("Ошибка ввода/вывода");
           Loger.setLog("Ошибка ввода/вывода");
           e.printStackTrace();
           Loger.setLog(e.toString());
       }
       catch (SAXException e){
           e.printStackTrace();
       }
       System.out.println("Успешно: Конфигурационный файл config.xml прочитан");
         Loger.setLog("Успешно: Конфигурационный файл config.xml прочитан");
       return root;
    }
}
