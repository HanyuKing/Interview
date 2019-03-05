public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {1213, 3, 4, 6, 2, 3, 4};
        heapSort(arr);

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void heapSort(int[] arr) {
        for(int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for(int i = 0; i < arr.length; i++) {
            swap(arr, 0, arr.length - i - 1);
            adjustHeap(arr, 0, arr.length - i - 1);
        }
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        int currData = arr[i];
        for(int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            if(j + 1 < length && arr[j] < arr[j + 1]) {
                j++; // bigger
            }
            if(arr[j] > currData) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = currData;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
