package DFS;

import java.util.*;

/**
 * @author Geng Hui
 */
public class ReconstructItinerary332 {

    List<String> res = null;
    public List<String> findItinerary(List<List<String>> tickets) {

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (List<String> ticket : tickets) {
            String start = ticket.get(0);
            String end = ticket.get(1);
            map.putIfAbsent(start, new ArrayList<>());
            map.get(start).add(end);
        }

        for (String airport : map.keySet()) {
            Collections.sort(map.get(airport));
        }

        // start from the JFK and build the iterney;
        List<String> itinerary = new ArrayList<>();
        String curr = "JFK";
        int length = tickets.size() + 1;
        dfs(curr, map, itinerary, length);
        return res;
    }

    private boolean dfs(String curr, HashMap<String, ArrayList<String>> map, List<String> itinerary, int length) {
        if (curr == null) {
            return false;
        }
        itinerary.add(curr);
        if (itinerary.size() == length) {
            res = itinerary;
            return true;
        }
        if (map.containsKey(curr) && !map.get(curr).isEmpty()) {
            for (int i = 0; i < map.get(curr).size(); i++) {
                String next = map.get(curr).get(i);
                map.get(curr).remove(i);
                if (dfs(next, map, itinerary, length)) {
                    return true;
                }
                map.get(curr).add(i,next);
            }
        }
        itinerary.remove(itinerary.size() - 1);
        return false;
    }



    // a better solution without the repeat searching on the same tickets;
    
    public List<String> findItinerary2(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String start = ticket.get(0);
            String end = ticket.get(1);
            targets.computeIfAbsent(start, k -> new PriorityQueue()).add(end);
        }
        visit("JFK");
        return route;
    }

    Map<String, PriorityQueue<String>> targets = new HashMap<>();
    List<String> route = new LinkedList();

    void visit(String airport) {
        while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }

}
