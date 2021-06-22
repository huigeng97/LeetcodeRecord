package Sort;

import java.util.Arrays;
import java.util.Comparator;

public class Largest_Number179 {
    public String largestNumber(int[] nums) {
        String[] asStr = new String[nums.length];
        for (int i = 0; i < asStr.length; i ++) {
            asStr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(asStr, new myComparator());

        String res = "";
        if (asStr[0].equals("0")) return asStr[0];
        for (String str : asStr) {
            res += str;
        }
        return res;
    }

    // create a comparator class myComparator
    private static class myComparator implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            String t1 = a + b;
            String t2 = b + a;
            return t2.compareTo(t1);
        }
    }

    // It is very useful to use the different sort algorithm
    // merge sort
    // divide and conquer // recursive is easier
    // sort the left and right; then merge them together;
    public static String largestNumber2(int[] nums) {
        String[] asStr = new String[nums.length];
        for (int i = 0; i < asStr.length; i ++) {
            asStr[i] = String.valueOf(nums[i]);
        }

        asStr = MergeSort(asStr,0,asStr.length-1);

        String res = "";
        if (asStr[0].equals("0")) return asStr[0];
        for (String str : asStr) {
            res += str;
        }
        return res;
    }

    // Notice this is a closed section in the mergesort;
    // 1. base case;
    // 2. recursive with the closed section
    // 3. merge them togehter;
    private static String[] MergeSort(String[] asStr, int i, int length) {
        if (i == length) return new String[]{asStr[i]};
        if (i > length) return null;
        int mid = (i + length) /2;
        String[] left = MergeSort(asStr,i,mid);
        String[] right = MergeSort(asStr,mid + 1,length);
        return Merge(left, right);
    }

    private static String[] Merge(String[] left, String[] right) {
        String[] res = new String[left.length + right.length];
        int i = 0; int j = 0; int k = 0;
        while (i < left.length && j < right.length) {
            if (new myComparator().compare(left[i],right[j]) > 0) {
                res[k++] = right[j++];
            } else {
                res[k++] = left[i++];
            }
        }
        while (j < right.length) res[k++] = right[j++];
        while (i < left.length) res[k++] = left[i++];
        return res;
    }


    // bubble sort
    // swap the neighbors if they not follow the right order
    public String[] BubbleSort(String[] asStr) {
        // two for loops, each time from the zero to begin with;
        for (int i = 0; i < asStr.length; i++) {
            Boolean swap = false;
            for (int j = 0; j< asStr.length - 1; j++) {
                if ( new myComparator().compare(asStr[j],asStr[j+1]) > 0) {
                    String temp = asStr[j];
                    asStr[j] = asStr[j+1];
                    asStr[j+1] = temp;
                    swap = true;
                }
            }
            if (swap==true) break;
        }
        return asStr;
    }

    // selection sort


    // insertion sort


    // quick sort (in place);
    // quick sort is an unstable algo because we don't insert the lower one but swap the lower one with the original one;
    // 1. First select a pivot(n=0);
    // 2. partition the array with small in the left and large in the right
    // 3. divide and conquer;
    public void quickSort(String[] asStr, int l, int r) {
        // 注意是闭区间； 因为是闭区间，所以当左边等于右边；只有一个元素时，不需要重新排序
        if (l >= r) return;
        int lo = l; int hi = r;
        String pivot = asStr[l];
        // 注意一共有三个l<r; 也就是最后partition;
        while (l < r) {
            while (l < r && new myComparator().compare(asStr[r], pivot) >= 0 ) r--;
            asStr[l] = asStr[r];
            while (l < r && new myComparator().compare(asStr[l], pivot) <= 0 ) l++;
            asStr[r] = asStr[l];
        }
        asStr[l] = pivot;
        quickSort(asStr, lo, l-1);
        quickSort(asStr, l+1, hi);
    }


    public static void main(String[] args) {
        System.out.println(largestNumber2(new int[]{90,1,10}));
    }
}
