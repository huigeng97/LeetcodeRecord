package graph;

public class findTheCelebrity277 {
    public int findCelebrity(int n) {

        int celebrity = 0;
        for (int i = 1; i < n; i++) {
            if (knows(celebrity,i)) {
               celebrity = i;
            }
        }
        if (isCelebrity(celebrity,n)) return celebrity;
        return -1;

    }

    private boolean isCelebrity(int celebrity, int n) {

        for (int i = 0; i < celebrity; i++) {
            if (celebrity == i) continue;
            if (knows(celebrity,i) || !knows(i,celebrity)) return false;
        }
        return true;

    }

    boolean knows(int a, int b) {
        return  true;
    }
}
