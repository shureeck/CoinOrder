import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
    public static  String getRegion(Node user){
        String region=(((Element)user).getElementsByTagName("region")).item(0).getTextContent();
        return  region;
    }
}
