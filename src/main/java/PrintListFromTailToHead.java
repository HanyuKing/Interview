import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintListFromTailToHead {
    public static void main(String[] args) {

    }

    @Test
    public void testFin() {
        System.out.println(Fibonacci(5));
    }

    public int Fibonacci(int n) {
        int[] arr = new int[40];
        arr[0] = 0;
        arr[1] = 1;

        int i = 2;
        for(; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        for(int j = 0; j < i; j++) {
            System.out.print(arr[j] + " ");
        }
        return arr[i - 1];
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<Integer>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}
 class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }