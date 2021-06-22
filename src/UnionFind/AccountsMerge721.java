package UnionFind;

import java.util.*;

public class AccountsMerge721 {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
            ///
        UnionFind unionFind = new UnionFind(10000);
        Map<String, String> emailToName = new HashMap();
        Map<String, Integer> emailToID = new HashMap();
        int id = 0;

        // Store all the accounts information; //
        // Put it into the id;
        // UnionFind on the id;

        for (List<String> account :accounts) {
            for (String email : account) {
                String name = "";
                if (name == "") {
                    email = name;
                    continue;
                }
                // start dealing with the email;
                emailToName.put(email,name);
                if (emailToID.containsKey(email)) {
                    unionFind.union(id,emailToID.get(email));
                }
                emailToID.put(email,id);
            }
            id++;
        }

        /// traverse all the elements in the email to ID; and find its ID;

        // This part requires a very high understanding of the data structure;
        // what is the best for storing the result?
        // The hashMap! Why, because the valid id is not consecutive;
        // Also there is no require on the ordering the accounts;
        // therefore the HashMap is the best candidate;


        Map<Integer, List<String>> newAccounts = new HashMap();
        for (String email : emailToName.keySet()) {
            int index = unionFind.find(emailToID.get(email));
            newAccounts.computeIfAbsent(index, x -> new ArrayList<String>()).add(email);
        }
        for (List<String> acc : newAccounts.values()) {
            Collections.sort(acc);
            acc.add(0,emailToName.get(acc.get(0)));
        }
        return  new ArrayList(newAccounts.values());
    }
}
