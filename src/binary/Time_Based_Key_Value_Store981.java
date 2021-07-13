package binary;

import java.util.*;

class TimeMap {

    /** Initialize your data structure here. */

    private Map<String, TreeMap<Integer, String>> map = new HashMap<>();

    public TimeMap() {

    }


    // Or we could use the floor key without changing the comparator in the constructor of the TreeMap
    // map.get(key).floorKey(timestamp);
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<Integer, String>((a, b) -> b - a){

        });
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (map.containsKey(key)) {
            // Find if there is a timestamp
            Integer val = map.get(key).ceilingKey(timestamp);
            if (val == null) return ""; else return map.get(key).get(val);

        } else {
            return null;
        }
    }
}

class TimeMap2 {

    class Node {
        String value;
        int timestamp;
        Node(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    Map<String, List<Node>> map;
    /** Initialize your data structure here. */
    public TimeMap2() {
        map = new HashMap();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList());
        map.get(key).add(new Node(value, timestamp));
    }

    public String get(String key, int timestamp) {
        List<Node> nodes = map.get(key);
        if (nodes == null) return "";

        int left = 0, right = nodes.size() - 1;

        //This part needs to look again!

        // while (left + 1 < right) {
        //     int mid = left + (right - left) / 2;
        //     Node node = nodes.get(mid);
        //     if (node.timestamp == timestamp) {
        //         return node.value;
        //     } else if (node.timestamp < timestamp) {
        //         left = mid;
        //     } else {
        //         right = mid;
        //     }
        // }
        // if (nodes.get(right).timestamp <= timestamp) return nodes.get(right).value;
        // else if (nodes.get(left).timestamp <= timestamp) return nodes.get(left).value;
        // return "";



        // This function is to find the floor number of the target;

        // Notice that if the target value is larger than any number, then left = n
        // right = n -1; vice versa left = 0 right = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Node node = nodes.get(mid);
            if (node.timestamp == timestamp) {
                return node.value;
            }
            if (node.timestamp > timestamp) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (right == -1) return "";
        else return nodes.get(right).value;
    }
}
