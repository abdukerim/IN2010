public class HeapSort extends Sorter{
    void sort() {
        buildMaxHeap(A,n);
        for (int i = n - 1; i >= 0 ; i--) {
            swap(0,i);
            bubbleDown(A,0,i);
        }
    }
    void bubbleDown(int[] A,int i,int n) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if(lt(left,n) && lt(A[largest],A[left])) {
            largest = left;
        }
        if (lt(right,n) && lt(A[largest],A[right])) {
            largest = right;
        }
        if(i != largest) {
            swap(i,largest);
            bubbleDown(A,largest,n);
        }
    }
    void buildMaxHeap(int[] A,int n) {
        for (int i = n / 2 - 1; i >= 0 ; i--) {
            bubbleDown(A,i,n);
        }
    }
    String algorithmName() {
        return "heap";
    }
}
