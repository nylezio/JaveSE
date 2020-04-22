package javase.sort;

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
        for (int arr:arrs
             ) {
            System.out.println(arr);
        }
    }

    private static void quickSort(int[] arrs){
        SubSort(arrs, 0, arrs.length - 1);
    }

    private static void SubSort(int[] arrs, int left, int right){
        if (left < right){
            int base = arrs[left];
            int low = left;
            int high = right + 1;
            while (true){
                while (low < right && arrs[++low] <= base ){
                    ;
                }
                while (high > left && arrs[--high] >= base){
                    ;
                }
                if (low < high){
                    swap(arrs, low , high);
                } else {
                    break;
                }
            }
            swap(arrs, left, high);
            SubSort(arrs, left,high - 1);
            SubSort(arrs, high + 1, right);
        }
    }

    private static void swap(int[] arrs, int low, int high) {
        int temp = arrs[low];
        arrs[low] = arrs[high];
        arrs[high] = temp;
    }
}
