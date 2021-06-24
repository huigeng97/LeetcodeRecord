package Stack;

import java.util.*;

public class Simplify_Path71 {
    public String simplifyPath(String path) {

        String cPath = "";
        Stack<String> stack = new Stack<>();

        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        while (!stack.isEmpty()) {
            cPath = "/" + stack.pop() + cPath;
        }
        return cPath.length() == 0 ? "/" : cPath;
    }



    // A slight revison is to use th StringBuilder to avoid the duplicate copy;
    public String simplifyPath2(String path) {

        StringBuilder cPath = new StringBuilder();
        Stack<String> stack = new Stack<>();

        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        for (String dir : stack) {
            cPath.append("/");
            cPath.append(dir);
        }
        return cPath.length() == 0 ? "/" : cPath.toString();
    }
}
