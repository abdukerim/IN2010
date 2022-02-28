import java.util.Arrays;

public class Solution2 {
    public static void main(String[] args) {

    }
    public static int calculateTotalPrice(int[] prices, int discount) {
        Arrays.sort(prices);
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            sum += prices[i];
        }
        int discounted = (int) Math.floor(prices[prices.length - 1] * (double)(discount / 100));
        sum += discounted;
        /*
        int max = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if(prices[i] > max) {
                max = prices[i];
            }
        }

         */

        return sum;
    }
    static boolean a(int i, int j) {
        return (i == 1 || j == 1 || i +j == 1);

    }
}
