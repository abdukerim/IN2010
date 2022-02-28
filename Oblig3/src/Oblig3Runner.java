import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Oblig3Runner {
    static final Sorter[] ALGS1 = { new Insertion(), new Quick(), new BubbleSort(),new HeapSort() };
    static final Sorter[] ALGS2 = { new Insertion(), new Quick(), new BubbleSort(), new HeapSort() };
    static final long TIME_LIMIT_MS = 100;
    static final int INCREMENT = 1;

    static void runAlgsPart1(int[] A,String infilename) throws Exception {
        for(Sorter alg : ALGS1) {
            String outfilename = infilename + "_" +alg.algorithmName() + ".out";
            File outfile = new File(outfilename);
            BufferedWriter writer = new BufferedWriter(new PrintWriter(outfile));

            alg.initializePart1(A);
            alg.sort();

            for (int i = 0; i < alg.n; i++) {
                writer.write(alg.A[i] + "\n");
            }
            writer.close();
        }
    }
    static void runAlgsPart2(int[] A,String infilename) throws Exception {
        String outfilename = infilename + "_results.csv";
        File outfile = new File(outfilename);
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outfile));

        for (Sorter alg : ALGS2) {
            alg.initializePart2(A);
        }
        int digits = numberOfDigits(A.length);
        String header = makeHeader(ALGS2,digits);
        writer.write(header + "\n");
        System.out.println(header);
        String rowfmt = "%" + digits + "d";
        long printTime = System.nanoTime();
        for (int i = 0; i < A.length; i += INCREMENT) {
            String row = String.format(rowfmt,i);
            for (Sorter alg : ALGS2) {
                row += ", " + alg.runResetAndIncBy(INCREMENT);
            }
            if(row.matches("\\s*\\d+,(\\s*,)*\\s*")) {
                break;
            }
            writer.write(row + "\n");
            long now = System.nanoTime();
            if(now - printTime > 1000000000) {
                System.out.println(row);
                printTime = now;
                writer.flush();
            }
        }
        writer.close();
    }
    static int numberOfDigits(int n) {
        return (int) (Math.log10(n) + 1);
    }

    static String makeHeader(Sorter[] algs,int digits) {
        String headers = Arrays.stream(algs)
                .map(alg -> alg.headerString())
                .collect(Collectors.joining(", "));
        String fmt = "%" + digits + "s, %s";
        return String.format(fmt,"n",headers);
    }
}
