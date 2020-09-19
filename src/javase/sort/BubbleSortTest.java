package javase.sort;

import java.util.Arrays;

/**
 * @author: codeJerry
 * @description: 冒泡排序
 * @date: 2020/03/31 13:50
 */
public class BubbleSortTest {
    public static void main(String[] args) {
        int[] arrs = new int[]{43,32,76,98,0,64,33,37,53,111};
        //bubble sort
        //每一轮将最大的冒泡上去
        for (int i = 0; i < arrs.length - 1; i++) {
            for (int j = 0; j < arrs.length - 1 - i; j++) {
                if (arrs[j] > arrs[j+1]){
                    swap(arrs, j, j + 1);
                }
            }
        }
        System.out.println(Arrays.toString(arrs));
    }
    private static void swap(int[] arrs, int low, int high) {
        int temp = arrs[low];
        arrs[low] = arrs[high];
        arrs[high] = temp;
    }

}
