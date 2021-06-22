package UnionFind;

public class Graph_Valid_Tree261 {



    public boolean validTree(int n, int[][] edges) {

        // Use the union find to solve this problem;
        // found the root for two nodes;
        // If none is in the root; return false;
        // EISE IF BOTH IN THE ROOT, RUTURN FALSE;
        // ELSE CONTINUE;
        if (edges.length != n - 1) return false;

        UnionFind unionFind = new UnionFind(n);

        for (int[] edge : edges) {
            int A = edge[0];
            int B = edge[1];
            if (!unionFind.union(A, B)) {
                return false;
            }
        }

        return true;
    }

}
