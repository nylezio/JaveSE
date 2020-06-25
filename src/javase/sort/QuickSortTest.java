package javase.sort;

import java.util.Arrays;

/**
 * @author: codeJerry
 * @description: 快速排序
 * 平均情况：快速排序最佳
 * @date: 2020/03/31 14:13
 */
public class QuickSortTest {
    public static void main(String[] args) {
        int[] arrs = new int[]{43,32,76,98,0,64,33,37,53,111};
        quickSort(arrs);
        System.out.println(Arrays.toString(arrs));
    }

    private static void quickSort(int[] arrs){
        subSort(arrs, 0, arrs.length - 1);
    }

    private static void subSort(int[] arrs, int left, int right) {
        if (left >= right) {return;}
        int base = arrs[left];
        int i = left;
        int j = right;
        while (true) {
            while (i < right && arrs[i] <= base) {
                i++;
            }
            while (j > left && arrs[j] >= base) {
                j--;
            }
            if (i >= j){
                break;
            }
            swap(arrs, i, j);
        }
        swap(arrs, left, j);
        subSort(arrs, left, j - 1);
        subSort(arrs, j + 1, right);
    }

    private static void swap(int[] arrs, int low, int high) {
        int temp = arrs[low];
        arrs[low] = arrs[high];
        arrs[high] = temp;
    }
}
