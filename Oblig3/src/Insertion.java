public class Insertion  extends Sorter{
    void sort() {
        for (int i = 0; i < n; i++) {
            int j = i;
            while (gt(j,0) && gt(A[j-1],A[j])){
                swap(j-1,j);
                j--;
            }
        }
    }
    String algorithmName() {
        return "insertion";
    }
}
