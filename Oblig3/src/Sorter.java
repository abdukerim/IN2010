import java.util.Arrays;

abstract class Sorter {
    int original[];
    int A[];
    int n = 0;
    int comparisons = 0;
    int swaps = 0;
    boolean discard = false;

    void initializePart1(int[] A) {
        this.original = A;
        this.n = A.length;
        this.A = Arrays.copyOfRange(original,0,n);
    }
    void initializePart2(int[] A) {
        this.original = A;
        this.n = 0;
        this.A = Arrays.copyOfRange(original,0,n);
    }
    abstract void sort();

    abstract String algorithmName();

    void swap(int i,int j) {
        swaps++;
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    boolean lt(int a,int b) {
        comparisons++;
        return a < b;
    }
    boolean leq(int a,int b) {
        comparisons++;
        return a <= b;
    }
    boolean gt(int a,int b) {
        comparisons++;
        return a > b;
    }
    boolean geq(int a,int b) {
        comparisons++;
        return a >= b;
    }
    boolean eq(int a,int b) {
        comparisons++;
        return a == b;
    }

    long sortTimed(){
        long t = System.nanoTime();
        sort();
        return (System.nanoTime() - t) / 1000;
    }

    String run() {
        String fmt = runStringFormat();
        if(discard) {
            String res = String.format(fmt,0,0,0);
            return res.replace("0"," ");
        }
        long timeus = sortTimed();
        long timems = timeus / 1000;
        if(timems > Oblig3Runner.TIME_LIMIT_MS) {
            discard = true;
            System.out.println("\nGiving up on " + algorithmName()+ "\n");
        }
        String res = String.format(fmt,comparisons,swaps,timeus);
        return res;
    }
    void reset() {
        comparisons = 0;
        swaps = 0;
        this.A = Arrays.copyOfRange(original,0,n);
    }
    void resetAndIncBy(int increment) {
        n += increment;
        reset();
    }
    String runResetAndIncBy(int increment) {
        String res = run();
        resetAndIncBy(increment);
        return res;
    }
    String headerString() {
        String name = this.algorithmName();
        String headerfmt = "%s_cmp, %s_swaps, %s_time";
        return String.format(headerfmt,name,name,name);
    }
    String runStringFormat() {
        String name = this.algorithmName();
        int cmplen = name.length() + "_cmp".length();
        int swaplen = name.length() + "_swaps".length();
        int timelen = name.length() + "_time".length();
        String fmt = "%%%dd, %%%dd, %%%dd";
        return String.format(fmt,cmplen,swaplen,timelen);
    }
}
