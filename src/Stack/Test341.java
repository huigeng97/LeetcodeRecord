package Stack;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class Test341 {
    public class NestedIterator implements Iterator<Integer> {

        Stack<ListIterator<NestedInteger>> stack = new Stack<>();
        Integer peek;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack.push(nestedList.listIterator());
            peek = null;
        }

        @Override
        public Integer next() {
            Integer res = peek;
            peek = null;
            return res;
        }

        @Override
        public boolean hasNext() {
            setPeek2();
            return peek != null;
        }

        private void setPeek2() {
            while (!stack.isEmpty()) {
                if (!stack.peek().hasNext()) {
                    stack.pop();
                    continue;
                }

                NestedInteger next = stack.peek().next();
                if (next.isInteger()) {
                    peek = next.getInteger();
                    break;
                }

                // next is a list;
                stack.push(next.getList().listIterator());
            }
        }
    }


    // we could also use the stack to only store the nestedList,
    // and it also works!!!!!



}
