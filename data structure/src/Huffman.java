import java.util.PriorityQueue;

public class Huffman {
    public static void main(String[] args) {
        PriorityQueue<Freq> huffman = new PriorityQueue<>();
        Freq a = new Freq("A", 4);
        Freq b = new Freq("B", 2);
        Freq c = new Freq("C", 1);
        Freq d = new Freq("D", 2);
        Freq e = new Freq("E", 8);
        huffman.add(a);
        huffman.add(b);
        huffman.add(c);
        huffman.add(d);
        huffman.add(e);
        while (huffman.size() > 1) {
            System.out.println(huffman.peek().name);
            Freq v1 = huffman.poll();
            System.out.println(huffman.peek().name);
            Freq v2 = huffman.poll();
            int f = v1.freq + v2.freq;
            huffman.add(new Freq(String.valueOf(f),f));
        }
        System.out.println(huffman.poll().name);
    }

}
class Freq implements Comparable<Freq>{
    String name;
    int freq;

    public Freq(String name, int freq) {
        this.name = name;
        this.freq = freq;
    }

    @Override
    public int compareTo(Freq o) {
        return this.freq - o.freq;
    }
}
