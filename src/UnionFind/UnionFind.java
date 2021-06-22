package UnionFind;


public class UnionFind {
    private int[] parent;
    public UnionFind(int n) {
        parent = new int[n];
        for (int node = 0; node < n; node++) {
            parent[node] = node;
        }
    }
    // Path Compressor;
    public int find(int i) { // path compression
        if (parent[i] != i) parent[i] = find(parent[i]);
        return parent[i];
    }

    public boolean union(int A, int B) {
        int rootA = find(A);
        int rootB = find(B);
        if (rootA == rootB) {
            return false;
        }
        parent[rootA] = rootB;
        return true;
    }

    public boolean connected(int A, int B) {
        return find(A) == find(B);
    }

    // This implementation without the level comparison;
    // But I think with the path compressor, we don't need the level comparison;
}
