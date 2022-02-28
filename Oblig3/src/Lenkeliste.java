public class Lenkeliste<T> implements Liste<T> {
    private T data;
    private Node forste;
    int antall = 0;
    private class Node{
        T data;
        Node neste;
        public Node(T data) {
            this.data = data;
            neste = null;
        }
    }

    @Override
    public int stoerrelse() {
        return antall;
    }

    @Override
    public void leggTil(int pos, T x) {
        Node ny = new Node(x);
        if(forste == null) {
            forste  = ny;

            antall++;
        }


    }

    @Override
    public void leggTil(T x) {

    }

    @Override
    public void sett(int pos, T x) {

    }

    @Override
    public T hent(int pos) {
        return null;
    }

    @Override
    public T fjern(int pos) {
        return null;
    }

    @Override
    public T fjern() {
        return null;
    }
}
