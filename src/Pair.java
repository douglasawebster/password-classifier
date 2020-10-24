
public class Pair<L,R> {

    L first;
    R second;

    public Pair(L first, R second) {
        this.first = first;
        this.second = second;
    }

    public L first() {
        return first;
    }

    public R second() {
        return second;
    }

}