package BackTracking;

import javax.print.DocFlavor;
import java.util.*;

public class WordLadderII126 {

    //

    /// 1. build the adjacency list
    //  2. Use the BFS to search for the result;
    //  3. Use the backtracking to traverse all the solution;

    Map<String, List<String>> adjList = new HashMap<String, List<String>>();
    List<String> currPath = new ArrayList<String>();
    List<List<String>> shortestPaths = new ArrayList<List<String>>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> copyWordList = new HashSet<>(wordList);

        boolean sequence = bfs(beginWord,endWord,copyWordList);

        if (!sequence) return shortestPaths;

        currPath.add(beginWord);

        backtrack(beginWord,endWord);

        return shortestPaths;
    }

    private void backtrack(String beginWord, String endWord) {
        // TODO:
    }

    private boolean bfs(String beginWord, String endWord, Set<String> wordList) {
        if (!wordList.contains(endWord)) return false;

        if (wordList.contains(beginWord)) wordList.remove(beginWord);

        Set<String> forwardQueue =  new HashSet<String>();
        Set<String> backwardQueue = new HashSet<String>();

        forwardQueue.add(beginWord);
        backwardQueue.add(endWord);

        boolean found = false;
        int direction = 1;

        while (forwardQueue.isEmpty() != true)  {

            Set<String> visited = new HashSet<>();
            // swap the queues because we are always extending the forwardQueue
            if (forwardQueue.size() > backwardQueue.size()) {
                Set<String> temp = forwardQueue;
                forwardQueue = backwardQueue;
                backwardQueue = temp;
                direction ^= 1;
            }

            // TODO:


        }
        return false;
    }


    List<List<String>> results;
    List<String> list;
    Map<String,List<String>> map;
    public List<List<String>> findLadders2(String start, String end, List<String> dict) {
        results= new ArrayList<List<String>>();

        if (dict.size() == 0 || !dict.contains(end))
            return results;

        int curr=1,next=0;
        boolean found=false;
        list = new LinkedList<String>();
        map = new HashMap<String,List<String>>();

        Queue<String> queue= new ArrayDeque<String>();
        Set<String> unvisited = new HashSet<String>(dict);
        Set<String> visited = new HashSet<String>();

        queue.add(start);
        unvisited.add(end);
        unvisited.remove(start);
        //BFS
        while (!queue.isEmpty()) {

            String word = queue.poll();
            curr--;
            for (int i = 0; i < word.length(); i++){
                StringBuilder builder = new StringBuilder(word);
                for (char ch='a';  ch <= 'z'; ch++){
                    builder.setCharAt(i,ch);
                    String new_word=builder.toString();
                    if (unvisited.contains(new_word)){
                        //Handle queue
                        if (visited.add(new_word)){//Key statement,Avoid Duplicate queue insertion
                            next++;
                            queue.add(new_word);
                        }

                        if (map.containsKey(new_word))//Build Adjacent Graph
                            map.get(new_word).add(word);
                        else{
                            List<String> l= new LinkedList<String>();
                            l.add(word);
                            map.put(new_word, l);
                        }
                        if (new_word.equals(end)&&!found) found=true;
                    }

                }//End:Iteration from 'a' to 'z'
            }//End:Iteration from the first to the last
            if (curr==0){
                if (found) break;
                curr=next;
                next=0;
                unvisited.removeAll(visited);
                visited.clear();
            }
        }//End While

        backTrace(end,start);

        return results;
    }

    private void backTrace(String word,String start){
        if (word.equals(start)){
            list.add(0,start);
            results.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0,word);
        if (map.get(word)!=null)
            for (String s:map.get(word))
                backTrace(s,start);
        list.remove(0);
    }
}



