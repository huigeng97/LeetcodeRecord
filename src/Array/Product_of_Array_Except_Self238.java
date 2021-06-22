package Array;

public class Product_of_Array_Except_Self238 {

    // The result contains the products except itself;
    // Just two pass to process all the left ones and right ones into the list;
    public static int[] productExceptSelf(int[] nums) {
        if (nums.length == 0 || nums.length == 0) return null;
        int[] res = new int[nums.length];

        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i-1]*nums[i-1];
        }

        int R = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            R = R*nums[i+1];
            res[i] = res[i]*R;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(productExceptSelf(new int[]{1,2,3,4}));
    }
}
