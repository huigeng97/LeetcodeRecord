package TwoPointer;

public class Two_SumII167 {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) return new int[]{left,right};
            if (sum > target) {
                right--;
            } else left++;
        }
        return null;
    }

    // some one optimization this problem with the binary search;
    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right){
            int mid = left + (right-left)/2;
            int sum = numbers[left] + numbers[right];
            if (sum == target){
                return new int[]{left,right};
            }
            else if (sum < target){
                left = (numbers[mid] + numbers[right] < target)?mid:left+1;
            }else{
                right = (numbers[mid] + numbers[left] > target)?mid:right-1;
            }
        }
        return null;
    }
}
