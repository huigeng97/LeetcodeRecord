package UnionFind;


// This is a classical and good exercise;
public class NumberOfProvince547 {

    class UnionFind {
        int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }


        /// Pay attention to this line! This is where I made a mistake;
        public int find(int A) {
//            while (parent[A] != A) parent[A] = parent[parent[A]];
            if (parent[A] != A) parent[A] = find(parent[A]);
            return parent[A];
        }

        public boolean union(int A, int B) {
            int rootA = find(A);
            int rootB = find(B);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                return true;
            }
            return false;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int num = isConnected.length;
        UnionFind unionFind = new UnionFind(num);
        int City = num;
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i+1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    if (unionFind.union(i,j)) {
                        num--;
                    }
                }
            }
        }
        return num;
    }
}
