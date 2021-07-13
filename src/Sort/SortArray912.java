package Sort;

import java.util.Arrays;
import java.util.Map;

public class SortArray912 {


    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        MergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int L = left;
        int R = right;
        int curr = nums[right];
        while (left < right) {
            while (nums[left] <= curr && left < right) {
                left++;
            } nums[right] = nums[left];
            while (nums[right] >= curr && left < right) {
                right--;
            } nums[left] = nums[right];
        }
        nums[right] = curr;
        quickSort(nums, L, right - 1);
        quickSort(nums, right + 1, R);
        return;
    }

    private void MergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right ) / 2;
        MergeSort(nums, left, mid);
        MergeSort(nums, mid + 1, right);
        Merge(nums, left, mid, right);
        return;
    }

    private void Merge(int[] nums, int left, int mid, int right) {
        int gap = right - left + 1;
        int[] tmp = new int[gap];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid || j <= right) {
            if (i > mid || j <= right && nums[j] < nums[i]) {
                tmp[k++] = nums[j++];
            } else {
                tmp[k++] = nums[i++];
            }
        }
        System.arraycopy(tmp, 0, nums, left, gap);
    }

    private void MergeSort2(int[] nums, int left, int right) {
        int interval = 1;
        while (left + interval <= right) {
            for (int i = left; i + interval <= right; i = i + interval * 2) {
                Merge(nums, i, i + interval - 1, Math.min(i + 2 * interval - 1, right));
            }
            interval *= 2;
        }
    }

    private void HeapSort(int[] nums) {

        // FIRST max heapify all the numbers for the half of the nums;
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            heapify(nums, i, nums.length - 1);
        }

        // SECOND each time release the max item into the end of the array,
        // and reheapify the top item;

        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i - 1);
        }

        return;
    }

    private void heapify(int[] nums, int root, int end) {
        // add the nums[root] as root of the heapify;
        // reheapify the to place the root;
        while (root <= end) {
            int l = 2 * root + 1, r = 2 * root + 2;
            int maxIdx = root;
            if (l <= end && nums[maxIdx] < nums[l]) {
                maxIdx = l;
            }
            if (r <= end && nums[maxIdx] < nums[r]) {
                maxIdx = r;
            }
            if (maxIdx == root) { break; }
            swap(nums, root, maxIdx);
            root = maxIdx;
        }
    }

    private void swap(int[] nums, int i, int i1) {
        int temp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = temp;
    }

    private int[] InsertSort(int[] nums) {
        return null;
    }






    private void RadixSort(int[] nums) {

        // m is the max of the nums;
        int m = getMax(nums);

        // we could get the max digit from m;
        // then we run max digit times countSort to get the arr sort;

        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(nums, nums.length, exp);
        }
        return;
    }

    private void countSort(int[] nums, int n, int exp) {
        // if the nums contains negative number,
        // add 9 bucket in the count;
        int[] output = new int[n];
        int[] count = new int[10 + 9];
        Arrays.fill(count, 0);

        // count the last digit;
        for (int i = 0; i < n; i++) {
            count[(nums[i] / exp) % 10 + 9]++;
        }

        // cumulative of the count;
        for (int i = 1; i < 10 + 9; i++) {
            count[i] += count[i - 1];
        }

        // fill the output;
        for (int i = n - 1; i >= 0; i--) {
            output[count[(nums[i] / exp) % 10 + 9] - 1] = nums[i];
            count[(nums[i] / exp) % 10 + 9]--;
        }

        // copy of the output;
        System.arraycopy(output, 0, nums, 0, n);
    }

    private int getMax(int[] nums) {
        int max = nums[0];
        for (int i : nums) {
            if (Math.abs(i) > Math.abs(max)) {
                max = Math.abs(i);
            }
        }
        return max;
    }

    public int[] countSort(int[] nums) {
        int min_ = Integer.MAX_VALUE, max_ = Integer.MIN_VALUE;
        for (int ele : nums) {
            min_ = Math.min(min_, ele);
            max_ = Math.max(max_, ele);
        }
        int[] freq = new int[max_ - min_ + 1];
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i] - min_]++;
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i - 1] + freq[i];
        }
        int[] ans = new int[nums.length];
        for (int i = nums.length-1; i >= 0; i--) {
            int idx = freq[nums[i] - min_] - 1;
            ans[idx] = nums[i];
            freq[nums[i] - min_]--;
        }
        return ans;
    }

}
