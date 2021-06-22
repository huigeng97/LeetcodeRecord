package UnionFind;

public class Number_of_Connected_Components_in_an_Undirected_Graph323 {
    public int countComponents(int n, int[][] edges) {
        int res = n;
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            int A = edge[0];
            int B = edge[1];
            if (unionFind.union(A, B)) {
                res--;
            }
        }
        return res;

    }


    public class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int A) {
            if (parent[A]==A) {
                return A;
            }
            return parent[A] = find(parent[A]);
        }

        public boolean union(int A, int B) {
            int rootA = find(A);
            int rootB = find(B);
            if ( rootA == rootB ) return false;
            else parent[rootA] =  rootB;
            return true;
        }
    }
}
