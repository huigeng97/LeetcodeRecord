package UnionFind;

import java.util.HashMap;
import java.util.List;

public class EvaluateDivision399 {
    class UnionFind {
        private int[] parent;
        private double[] weight;

        public UnionFind(int n) {
            parent = new int[n];
            weight = new double[n];
            for (int i=0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1;
            }
        }

        public int find(int A) {
            int P = parent[A];
            if (P!=A) {
                parent[A] = find(P);
            }
            weight[A] = weight[A] * weight[P];
            return parent[A];
        }

        public boolean union(int large, int small, double value) {
            System.out.println("looking for" + large + "  " + small + " value " + value);
            int rootL = find(large);
            int rootS = find(small);
            if (rootL != rootS) {
                parent[rootL] = rootS;
                // This line is very tricky;;;;
                // Take lots of time to debug
                weight[rootL] = value*weight[small]/weight[large];
                return true;
            }
            return false;
        }

        public double query(int A, int B) {
            System.out.println("query for" + A + "  " + B);
            if (A == B) return 1;
            if (find(A) != find(B)) {
                return -1;
            } else {
                System.out.println("Start query");
                System.out.println("Weight large" + weight[A]);
                System.out.println("Weight small" + weight[B]);
                return weight[A]/weight[B];
            }
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String,Integer> map = new HashMap<>();
        int count = 0;
        for (List<String> eq : equations) {
            for (String str : eq) {
                if (!map.containsKey(str)) {
                    map.put(str,count++);
                }
            }
        }
        UnionFind unionFind = new UnionFind(count);
        for (int i = 0; i < equations.size(); i++) {
            unionFind.union(map.get(equations.get(i).get(0)),map.get(equations.get(i).get(1)), values[i]);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            if (map.containsKey(queries.get(i).get(0)) && map.containsKey(queries.get(i).get(1))) {
                double div = unionFind.query(map.get(queries.get(i).get(0)), map.get(queries.get(i).get(1)));
                res[i] = div;
            } else {
                res[i] = -1;
            }

        }
        return res;
    }
}

