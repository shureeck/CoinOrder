import org.openqa.selenium.WebDriver;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Order {
    public static boolean order(ArrayList <String> region, NodeList coins, WebDriver driver){
        boolean state = false;
        int i=0;

        //Select Region
        while(!state && i<region.size()) {
            boolean stateRegion = Region.selectRegion(region.get(i), driver);

            //Select Coins
            if (stateRegion) {
                state = SelectCoin.selectCoin(coins, driver);
            }

            i++;
        }

        return state;
    }
}
