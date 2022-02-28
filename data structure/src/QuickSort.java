public class QuickSort {
    public static void main(String[] args) {
        int[] A = {2,9,3,88,10};
        quickSort(A,0,A.length-1);
        for (int i : A) {
            System.out.print(i + " ");
        }
    }
    static void quickSort(int[] A,int low,int high) {

        if(low < high) {
            int mid = partition(A,low,high);
            quickSort(A,low,mid - 1);
            quickSort(A,mid + 1,high);
        }
    }
    static int partition(int[] A,int low,int high) {
        int mid = A[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(A[j] <= mid) {
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i+1];
        A[i+1] = A[high];
        A[high] = temp;
        return i + 1;
    }
}
