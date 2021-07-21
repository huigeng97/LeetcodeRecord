package DFS;

import java.util.*;

public class CourseSchedule207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        HashMap<Integer, List<Integer>> courseDict = new HashMap<>();
        for (int[] pre : prerequisites) {
            courseDict.putIfAbsent(pre[1], new LinkedList<>());
            courseDict.get(pre[1]).add(pre[0]);
        }

        boolean[] checked = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];

        for (int currCourse = 0; currCourse < numCourses; ++currCourse) {
            if (this.isCyclic(currCourse, courseDict, checked, path))
            {
                return false;
            }
        }

        return true;

    }

    // two step, start from one node, if this node checked, we return no cycle;
    // if this node is in current path, we return true cycle;
    // then we could use DFS to traverse all the children, if there is a cycle in its descendents , return true cycle;;
    // if we finished the DFS, we let curr node as checked; and the path is false;

    private boolean isCyclic(int currCourse, HashMap<Integer, List<Integer>> courseDict, boolean[] checked, boolean[] path) {
        if (checked[currCourse]) {
            return false;
        }
        if (path[currCourse]) {
            return true;
        }

        if (!courseDict.containsKey(currCourse)) {
            return false;
        }
        path[currCourse] = true;
        boolean ret = false;

        for (Integer child : courseDict.get(currCourse)) {
            ret = this.isCyclic(child, courseDict, checked, path);
            if (ret) {
                break;
            }

        }

        path[currCourse] = false;
        checked[currCourse] = true;
        return ret;
    }

    // this problem could also be solved by topological sorting
    // if we could find one, return true;
    // else return false;

    class GNode {
        public Integer inDegrees = 0;
        public List<Integer> outNodes = new LinkedList<Integer>();
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites) {

        HashMap<Integer, GNode> map = new HashMap<>();
        for (int[] pre : prerequisites) {
            // putIfAbsent 只返回 存在的值，不返回后加入的值；
//            GNode out = map.putIfAbsent(pre[1], new GNode());
//            GNode in = map.putIfAbsent(pre[0], new GNode());
            map.putIfAbsent(pre[1], new GNode());
            map.putIfAbsent(pre[0], new GNode());
            map.get(pre[1]).outNodes.add(pre[0]);
            map.get(pre[0]).inDegrees++;
        }

        int totalDeps = prerequisites.length;

        // These are nodes without the in nodes;
        LinkedList<Integer> nodepCourses = new LinkedList<Integer>();
        for (Map.Entry<Integer, GNode> entry : map.entrySet()) {
            GNode node = entry.getValue();
            if (node.inDegrees == 0) {
                nodepCourses.add(entry.getKey());
            }
        }



        int removedEdges = 0;
        while (nodepCourses.size() != 0) {
            Integer course = nodepCourses.pop();
            for (Integer out : map.get(course).outNodes) {
                GNode childNode = map.get(out);
                removedEdges++;
                childNode.inDegrees--;
                if (childNode.inDegrees == 0) {
                    nodepCourses.add(out);
                }
            }
        }


        if (removedEdges != totalDeps)
            // if there are still some edges left, then there exist some cycles
            // Due to the dead-lock (dependencies), we cannot remove the cyclic edges
        { return false; }
        else
        { return true; }

    }
}
