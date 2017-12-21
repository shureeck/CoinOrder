import org.openqa.selenium.WebDriver;
import org.w3c.dom.NodeList;

public class Order {
    public static boolean order(String region, NodeList coins, WebDriver driver){
        boolean state = false;

        //Select Region
        boolean stateRegion = Region.selectRegion(region, driver);
        //Select Coins
        state=SelectCoin.selectCoin(stateRegion,coins,driver);

        return state;
    }
}
