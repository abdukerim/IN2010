import java.util.PriorityQueue;

public class InsertionSort {
    public static void main(String[] args) {
        int[] A = {2,9,3,88,10,};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < A.length; i++) {
            pq.add(A[i]);
        }
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
    static void insertionSort(int[] A) {
        for (int i = 0; i < A.length; i++) {
            int j = i;
            while(j > 0 && (A[j-1] > A[j])) {
                A[j-1] = A[j];
                A[j] = A[j - 1];
                j--;
            }
        }

    }

}
//runtime O(n*n) inner loop n, outer loop n. effective on almost sorted arrays