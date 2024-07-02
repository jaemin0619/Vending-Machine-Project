package Action;

import Can.CanArray;
import Person.Admin;

public class ReturnProfit {
    public static int getProfit() {
        int totalProfit = 0;
        for (int i = 0; i < CanArray.canList.size(); i++) {
            int canNum = CanArray.canList.get(i).getCanNum();
            int canPrice = CanArray.canList.get(i).getCanPrice();
            int sales = (CanArray.canList.get(i).getCanNum() - canNum) * canPrice;
            totalProfit += sales;
        }
        return totalProfit;
    }
}
