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
            int runningMedian = fastMedian(arrSpending, i-d, i);
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
    private int fastMedian(int[] arrSpending, int startIndex, int endIndex) {
        QuickSelect qs = new QuickSelect();
        int[] tempArray = Arrays.copyOfRange(arrSpending, startIndex, endIndex);
        if(tempArray.length % 2 == 1){
            return qs.selectIterative(tempArray, (tempArray.length/2)-1);
        }
        else {
            //TODO: Average 2 qs.selectIterative attempts here
        }
    }
    private double computeMedian(int[] arrSpending, Integer startIndex, Integer endIndex) {
//        System.out.println("Start Index:" + startIndex + " endIndex: " + endIndex);
        int[] tempArray = Arrays.copyOfRange(arrSpending, startIndex, endIndex);
        /*
            Can be done quicker with a QuickSelect algo
         */
        Arrays.sort(tempArray);
//        System.out.println(Arrays.toString(tempArray));
        double median = -1.0;
        double half = Math.floor(tempArray.length/2);

        if(tempArray.length % 2 == 1) {
            return tempArray[(int)half];
        }
        else {
            return (tempArray[(int)half-1] + tempArray[(int)half]) / 2.0;
        }
    }

    class QuickSelect {
        public int selectIterative(int[] array, int n) {
            return iterative(array, 0, array.length - 1, n);
        }

        private int iterative(int[] array, int left, int right, int n) {
            if(left == right) {
                return array[left];
            }
            for(;;) {
                int pivotIndex = randomPivot(left, right);
                pivotIndex = partition(array, left, right, pivotIndex);

                if(n == pivotIndex) {
                    return array[n];
                } else if(n < pivotIndex) {
                    right = pivotIndex - 1;
                } else {
                    left = pivotIndex + 1;
                }
            }
        }
        public int selectRecursive(int[] array, int n) {
            return recursive(array, 0, array.length - 1, n);
        }
        private int recursive(int[] array, int left, int right, int n) {
            if (left == right) { // If the list contains only one element,
                return array[left]; // return that element
            }
            int pivotIndex = randomPivot(left, right);
            pivotIndex = partition(array, left, right, pivotIndex);
            // The pivot is in its final sorted position
            if (n == pivotIndex) {
                return array[n];
            } else if (n < pivotIndex) {
                return recursive(array, left, pivotIndex - 1, n);
            } else {
                return recursive(array, pivotIndex + 1, right, n);
            }
        }
        private int partition(int[] array, int left, int right, int pivotIndex) {
            int pivotValue = array[pivotIndex];
            swap(array, pivotIndex, right); // move pivot to end
            int storeIndex = left;
            for(int i = left; i < right; i++) {
                if(array[i] < pivotValue) {
                    swap(array, storeIndex, i);
                    storeIndex++;
                }
            }
            swap(array, right, storeIndex); // Move pivot to its final place
            return storeIndex;
        }

        private void swap(int[] array, int a, int b) {
            int tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
        }

        private int randomPivot(int left, int right) {
            return left + (int) Math.floor(Math.random() * (right - left + 1));
        }
    }
}