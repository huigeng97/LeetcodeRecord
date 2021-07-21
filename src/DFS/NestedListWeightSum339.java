package DFS;

import Stack.NestedInteger;

import java.util.List;

public class NestedListWeightSum339 {

    public int depthSum(List<NestedInteger> nestedList) {
        return depthSumHelper(nestedList, 1);
    }

    private int depthSumHelper(List<NestedInteger> nestedList, int level) {
        int sum = 0;
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                sum += level * item.getInteger();
            } else {
                sum += depthSumHelper(item.getList(), level + 1);
            }
        }
        return sum;
    }
}
