public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {998,23432,868234,12,32072834};
        selectionSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }

    }
    static void selectionSort(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            int min = i;
            for (int j = i+1; j < A.length; j++) {
                if(A[j] < A[min]) {
                    min = j;
                }
            }
            int temp = A[min];
            A[min] = A[i];
            A[i] = temp;
        }
    }
}
