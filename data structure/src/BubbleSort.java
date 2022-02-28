public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {998,23432,868234,12,32072834};
        bubbleSortModified(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    //runtime (n * (n -1)) / 2 = n * n
    static void bubbleSort(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 0; j < A.length - i - 1; j++) {
                if(A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = temp;
                }
            }
        }
    }
    static void bubbleSort2(int[] A) {
        for (int i = A.length - 1; i > 0 ; i--) {
            for (int j = 0; j < i - 1; j++) {
                if(A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j+ 1] = temp;
                }
            }
        }
    }
    //modified version run O(n)
    static void bubbleSortModified(int[] A) {
        int i,j,temp,swapped = 1;
        for (i = A.length - 1; i > 0; i--) {
            swapped = 0;
            for (j = 0; j <= i - 1; j++) {
                if(A[j] > A[j +1]) {
                    temp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = temp;
                    swapped = 1;
                }
            }
        }
    }
}

