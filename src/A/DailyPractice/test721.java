package A.DailyPractice;

import java.util.*;

public class test721 {

    class unionFind {
        HashMap<Integer, Integer> children;

        public unionFind(int n) {
            children = new HashMap<>();
            for (int i = 0; i < n; i++) {
                children.put(i,i);
            }
        }

        public boolean union(int x, int y) {
            int X = find(x);
            int Y = find(y);
            if (X == Y) { return true; }
            children.put(X,Y);
            return false;
        }

        private int find(int x) {
            if (children.get(x) != x) {
                children.put(x, find(children.get(x)));
            }
            return children.get(x);
        }

    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // we should have a ID of the emails which could merge with unionFind;
        // we should have a HashMap to store email TO find(ID);
        // we should have a new ID to email;
        // we also should have a ID to name;

        HashMap<String, Integer> emailToId = new HashMap<>();
        unionFind IDs = new unionFind(accounts.size());
        int id = 0;
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (emailToId.containsKey(email)) {
                    IDs.union(id, emailToId.get(email));
                }
                emailToId.put(email, id);
            }
            id++;
        }

        HashMap<Integer, List<String>> idToEmail = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToId.entrySet()) {
            int tempID = IDs.find(entry.getValue());
            idToEmail.putIfAbsent(tempID, new ArrayList<>());
            idToEmail.get(tempID).add(entry.getKey());
        }

        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : idToEmail.entrySet()) {
            List<String> account = new ArrayList<>();
            account.addAll(entry.getValue());
            Collections.sort(account);
            account.add(0,accounts.get(entry.getKey()).get(0));
            res.add(account);
        }
        return res;
    }
}
