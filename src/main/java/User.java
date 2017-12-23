import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * Created by Poliakov.A on 12/6/2017.
 */
public class User {
    public static String getLogin(Node user){
        String login=(((Element)user).getElementsByTagName("login")).item(0).getTextContent();
        return  login;
    }
    public static String getPassword(Node user){
        String password=(((Element)user).getElementsByTagName("password")).item(0).getTextContent();
        return  password;
    }
    public static ArrayList<String> getRegion(Node user){
       ArrayList<String> region=new ArrayList<>();
       int i=0;
       int lenth= ((Element)user).getElementsByTagName("region").getLength();
       while (i<lenth) {
           region.add((((Element) user).getElementsByTagName("region")).item(i).getTextContent());
           i++;
       }
        return  region;
    }
}
