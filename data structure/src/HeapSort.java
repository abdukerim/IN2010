public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {998,23432,868234,12,32072834};
        heapSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    static void heapSort(int[] A) {
        for (int i = 0; i < A.length / 2 - 1; i++) {
            heapify(A,A.length,i);
        }
        for (int i = A.length - 1; i > 0 ; i--) {
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            heapify(A,i,0);
        }
    }
    static void heapify(int[] arr,int n,int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 1;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if(largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr,n,largest);
        }
    }

}
