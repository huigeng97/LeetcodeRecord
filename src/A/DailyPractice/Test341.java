package A.DailyPractice;

import Stack.NestedInteger;

import java.util.*;

public class Test341 {
    public class NestedIterator implements Iterator<Integer> {


        // kind of like the tree dfs;
        // could use the list iterator
        // or we could store the next idx into the stack;
        // try to use the iterator;

        Deque<ListIterator<NestedInteger>> stack = new ArrayDeque<>();
        Integer curr = null;
        public NestedIterator(List<NestedInteger> nestedList) {
            // Make an iterator with the input and put it on the stack.
            // Note that creating a list iterator is an O(1) operation.
            stack.push(nestedList.listIterator());
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                Integer res = curr;
                curr = null;
                return res;
            } else {
                return null;
            }
        }

        @Override
        public boolean hasNext() {
            setPeeked(); // similar with the same integer at the top; (this is to set the curr to next integer);

            return curr != null;
        }

        private void setPeeked() {
            if (curr != null) {
                return;
            }

            //  to find the next integer;
            while (!stack.isEmpty()) {


                // go the end of the stack;
                if (!stack.peek().hasNext()) {
                    stack.pop();
                    continue;
                }

                // if the next is not an integer
                NestedInteger next = stack.peek().next();
                if (next.isInteger()) {
                    curr = next.getInteger();
                    break;
                } else {
                    stack.push(next.getList().listIterator());
                }

            }
        }
    }

    public class NestedIterator2 implements Iterator<Integer> {

        private List<Integer> integers = new ArrayList<Integer>();
        private Integer curr = 0;
        public NestedIterator2(List<NestedInteger> nestedList) {

            flattenList(nestedList);
        }

        private void flattenList(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    integers.add(nestedInteger.getInteger());
                } else {
                    flattenList(nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return integers.get(curr++);
        }

        @Override
        public boolean hasNext() {
            return curr < integers.size();
        }
    }


    public class NestedIterator3 implements Iterator<Integer> {

        private Deque<NestedInteger> stack;

        public NestedIterator3(List<NestedInteger> nestedList) {

            // This constructor allow to let the first item at the top of stack;
            stack = new ArrayDeque(nestedList);
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {

            makeStackTopInteger();
            return !stack.isEmpty();
        }

        private void makeStackTopInteger() {

            while (!stack.isEmpty() && !stack.peek().isInteger()) {
                List<NestedInteger> curr = stack.pop().getList();
                for (int i = curr.size() - 1; i >= 0; i--) {
                    stack.push(curr.get(i));
                }
            }
        }
    }

    public class NestedIterator4 implements Iterator<Integer> {

        Deque<ListIterator<NestedInteger>> stack = new ArrayDeque<>();
        Integer peeked = null;
        public NestedIterator4(List<NestedInteger> nestedList) {

            stack.push(nestedList.listIterator());
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                Integer res = peeked;
                peeked = null;
                return res;
            } else {
                return null;
            }
        }

        @Override
        public boolean hasNext() {

            setPeekedInteger();
            return peeked != null;
        }

        private void setPeekedInteger() {
            if (peeked != null) {
                return;
            }
            while (!stack.isEmpty()) {
                if (!stack.peek().hasNext()) {
                    stack.pop();
                    continue;
                }
                NestedInteger next = stack.peek().next();

                if (next.isInteger()) {
                    peeked = next.getInteger();
                    return;
                } else {
                    stack.push(next.getList().listIterator());
                }
            }
        }
    }
}
