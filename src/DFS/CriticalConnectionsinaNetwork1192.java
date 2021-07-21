package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnectionsinaNetwork1192 {

    int[] parent;
    int[] low;
    int[] dfn;
    boolean[] seen;
    int i = 0;
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        // this problem is solved by Tarjan algorithm;
        // use four arrays to memorize;
        // low , dfn, parents
        // mark;
        // low is the recursion lowest timestamp;
        // dfn is the DFS traversal time;
        // parents the last nodes for DFS order
        // mark is for DFS duplication checking;

        // 其实这道题的逻辑就是一定要清晰，首先明确 割边的定义 指的是这条边没有在一个cycle里面；
        // 如果一条边不在一个cycle里面，则对子节点的DFS不会回溯到子节点的父节点和祖先结点上；
        // 即如果有(U,V), 从U 访问 V，若low[v] <= dfs[u],则（u,v）在cycle里面；反之 low[v] > dfs[u], 则（u,v）是割边；
        // 唯一需要注意的是DFS 可 以 访 问 祖 先 结 点 ， 但 不 能 访 问 父 节 点 ；

        // store as an adjacent list;
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        for (List<Integer> connection : connections) {
            int a = connection.get(0);
            int b = connection.get(1);
            adjList[a].add(b);
            adjList[b].add(a);
        }

        parent = new int[n];
        low = new int[n];
        dfn = new int[n];
        seen = new boolean[n];

        // the next step is to do the bfs with 4 arrays;
        parent[0] = -1;
        dfs(0,adjList);
        return res;
    }

    private int dfs(int curr, List<Integer>[] adjList) {
        if (!seen[curr]) {
            seen[curr] = true;
            dfn[curr] = i;
            low[curr] = i++;
            for (Integer next : adjList[curr]) {
                if (!seen[next]) {
                    parent[next] = curr;
                }
                if (parent[curr] != next) {
                    int temp = dfs(next, adjList);
                    low[curr] = Math.min(low[curr], temp);
                }
            }
            if (low[curr] > dfn[parent[curr]]) {
                res.add(new ArrayList<>(Arrays.asList(curr, parent[curr])));
            }
        }
        return low[curr];
    }

  public static void main(String[] args) {
    //
      CriticalConnectionsinaNetwork1192 test = new CriticalConnectionsinaNetwork1192();
      List<List<Integer>> temp = new ArrayList<>();
      temp.add(new ArrayList<>(Arrays.asList(0,1)));
      test.criticalConnections(2, temp);
  }
}
