package DFS;

import java.util.*;

public class CourseScheduleII210 {


    public int[] findOrder(int numCourses, int[][] prerequisites) {

        class Course {
            int indegree;
            ArrayList<Integer> next;

            public Course() {
                indegree = 0;
                next = new ArrayList<>();
            }
        }

        HashMap<Integer, Course> map = new HashMap<>();

        for (int[] pair : prerequisites) {
            map.putIfAbsent(pair[0], new Course());
            map.putIfAbsent(pair[1], new Course());
            map.get(pair[0]).indegree++;
            map.get(pair[1]).next.add(pair[0]);
        }

        Queue<Integer> removeCourse = new LinkedList<>();
        int[] res = new int[numCourses];
        int i = 0;

        for (int j = 0; j < numCourses; j++) {
            if (!map.containsKey(j)) {
                res[i++] = j;
            } else {
                if (map.get(j).indegree == 0) {
                    removeCourse.add(j);
                    res[i++] = j;
                }
            }
        }

        while (!removeCourse.isEmpty()) {
            Integer curr = removeCourse.poll();
            for (Integer next : map.get(curr).next) {
                int temp = --map.get(next).indegree;
                if (temp == 0) {
                    removeCourse.offer(next);
                    res[i++] = next;
                }
            }
        }

        return i == numCourses ? res : new int[0];
    }
}
