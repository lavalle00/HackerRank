import java.io.*;
import java.util.*;
import java.util.Arrays;


public class Solution {
    int n, d;

    public static void main(String[] args) {
        Solution solution = new Solution();
//        Scanner in = new Scanner(new InputStreamReader(System.in));
        Scanner in = new Scanner("9 5\n2 3 4 2 3 6 8 4 5");
        solution.init(in);
    }
    public void init(Scanner sc) {
        int[] arrSpending = new int[1];
        int tempIndex = 0;
        String line;
        try{
            n = sc.nextInt();
            arrSpending = new int[n];
            d = sc.nextInt();
            while(sc.hasNext()){
                arrSpending[tempIndex] = sc.nextInt();
                tempIndex++;
            }
        }
        catch(Exception e){
            System.out.println("Input data not found...");
        }
        computeFraud(arrSpending, d);
    }
    private void computeFraud(int[] arrSpending, int d) {
        int notifCount = 0;
        for(int i = d; i < arrSpending.length; i++){
            double runningMedian = computeMedian(arrSpending, i-d, i);
            // System.out.println(runningMedian);
            if( i < d ) {

            }
            else {
                //determine if spending[i] >= 2*d
                if( (double)arrSpending[i] >= 2*runningMedian) {
                    //notify
                    notifCount++;
                }
            }
        }
        System.out.println(notifCount);
    }
    private double computeMedian(int[] arrSpending, Integer startIndex, Integer endIndex) {
        System.out.println("Start Index:" + startIndex + " endIndex: " + endIndex);
        int[] tempArray = Arrays.copyOfRange(arrSpending, startIndex, endIndex);
        Arrays.sort(tempArray);
        System.out.println(Arrays.toString(tempArray));
        double median = -1.0;
        double half = Math.floor(tempArray.length/2);

        if(tempArray.length % 2 == 1) {
            return tempArray[(int)half];
        }
        else {
            return (tempArray[(int)half-1] + tempArray[(int)half]) / 2.0;
        }
    }
}