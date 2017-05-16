import java.util.Random;

/**
 * Created by udingji on 5/16/17.
 */
public class RandomPickIndex {


    public class Solution {

        int[] nums;
        Random random;

        public Solution(int[] nums) {
            this.nums = nums;
            this.random = new Random();
        }

        public int pick(int target) {
            int result = -1;
            int count = 0;
            for (int i = 0; i < nums.length; i++){
                if (nums[i] == target){
                    if (random.nextInt(++count) == 0)
                        result = i;
                }
            }
            return result;
        }
    }
}
