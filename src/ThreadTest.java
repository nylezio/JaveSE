/**
 * @author: codeJerry
 * @description:
 * @date: 2020/04/21 22:15
 */

class Array implements Runnable{

    private int[] nums ={1,2,5,6,7,1,2,5,6,7,1,2,5,6,7,1,2,5,6,7,1,2,5,6,7,1,2,5,6,7,1,2,5,6,7,1,2,5,6,7,1,2,5,6,7,1,2,5,6,7};
    private int index = 0;

    @Override
    public void run() {
        while (index < nums.length){
            show();
        }
    }

    synchronized void show(){
        if (index < nums.length){
            System.out.print(Thread.currentThread().getName()+ ":");

            System.out.println(nums[index]);
            index++;
        }
    }
}

public class ThreadTest {

    public static void main(String[] args) {
        Array array = new Array();
        Thread t1 = new Thread(array);
        Thread t2 = new Thread(array);
        Thread t3 = new Thread(array);

        t1.start();
        t2.start();
        t3.start();
    }
}
