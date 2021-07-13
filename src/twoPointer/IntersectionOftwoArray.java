package twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IntersectionOftwoArray349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int l = 0;
        int r = 0;
        List<Integer> res = new ArrayList<>();
        while (l < nums1.length && r < nums2.length) {
            if (nums1[l] == nums2[r]) {
                res.add(nums1[l]);
                l++;
                r++;
                while (l < nums1.length && nums1[l] == nums1[l-1]) l++;
                while (r < nums2.length && nums2[r] == nums2[r-1]) r++;
            }
            
            // three case situation, if there doesn't exist a return in the first one , we have to be very careful for 
            // the latter two case since the l and r could be changed;
            else if (nums1[l] < nums2[r]) l++;
            else if (nums2[r] < nums1[l]) r++;
        }
        //  Foo[] array = list.toArray(new Foo[0]); this only works for reference type but not the primitive type;
        int[] array = new int[res.size()];
        for(int i = 0; i < res.size(); i++) array[i] = res.get(i);
        return array;
    }
}
