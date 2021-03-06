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
        quickSort(arrs, 0, arrs.length - 1);
    }

    private static void quickSort(int[] arrs, int l, int r) {
        if (l >= r) {return;}
        int base = arrs[l];
        int i = l;
        int j = r;
        while (true) {
            while (i < r && arrs[i] <= base) {
                i++;
            }
            while (j > l && arrs[j] >= base) {
                j--;
            }
            if (i >= j){
                break;
            }
            swap(arrs, i, j);
        }
        swap(arrs, l, j);
        quickSort(arrs, l, j - 1);
        quickSort(arrs, j + 1, r);
    }

    private static void swap(int[] arrs, int low, int high) {
        int temp = arrs[low];
        arrs[low] = arrs[high];
        arrs[high] = temp;
    }
}
