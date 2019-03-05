import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class QuickSort {
    public static void main(String[] args) {
        int arr[] = {1213, 3, 4, 6, 2, 3, 4};
        quickSort3(arr, 0, arr.length - 1);

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void quickSort3(int[] data, int left, int right) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(right);
        stack.push(left);
        while (!stack.isEmpty()) {
            int i = stack.pop();
            int j = stack.pop();

            int p = partition(data, i, j);

            if(p < j) {
                stack.push(j);
                stack.push(p + 1);
            }
            if(i < p) {
                stack.push(p - 1);
                stack.push(i);
            }
        }

    }

    public static void quickSort2(int[] data, int left, int right) {
        if(left > right) return;
        int j = partition(data, left, right);
        quickSort2(data, left, j - 1);
        quickSort2(data, j + 1, right);
    }

    private static int partition(int[] data, int left, int right) {
        int base = data[left];
        while (left < right) {
            while (left < right && base < data[right]) right--;
            data[left] = data[right];
            left++;

            while (left < right && base > data[left]) left++;
            data[right] = data[left];
            right--;

        }
        data[left] = base;
        return left;
    }

    public static void quickSort(int[] data, int left, int right) {
        int tempLeft = left;
        int tempRight = right;
        int currData = data[left];
        while (left < right) {
            while (right > left && currData < data[right]) right--;

            if(left < right) {
                data[left] = data[right];
                left++;
            }

            while (right > left && currData > data[left]) left++;

            if(left < right) {
                data[right] = data[left];
                right--;
            }
        }
        data[left] = currData;

        if(tempLeft < left)
            quickSort(data, tempLeft, left - 1);
        if(left < tempRight)
            quickSort(data, left + 1, tempRight);
    }
}
