public class Quick extends Sorter{
    void sort() {
        int low = 0;
        int high = n - 1;
        quickSort(A,low,high);
    }
    void quickSort(int[] A,int low,int high) {
        if(low >= high) {
            return;
        }
        int p = partition(A,low,high);
        quickSort(A,low,p - 1);
        quickSort(A,p + 1,high);
    }
    int partition(int[] A,int low,int high) {
        int m = choosePivot(low,high);
        swap(m,high);
        int pivot = A[high];
        int left = low;
        int right = high - 1;
        while (leq(right,left)) {
            while (leq(left, right) && leq(A[right], pivot)) {
                left++;
            }
            while (geq(right, left) && geq(A[right], pivot)) {
                right--;
            }
            if (lt(left, right)) {
                swap(left, right);
            }
        }
        swap(left,right);
        return left;

    }
    int choosePivot(int low,int right) {
        return (low + right) / 2;
    }
    String algorithmName() {
        return "quick";
    }
}
