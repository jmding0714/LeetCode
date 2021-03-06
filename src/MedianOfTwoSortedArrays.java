/**
 * Created by jmding on 1/25/17.
 */
public class MedianOfTwoSortedArrays {

    // There are two sorted arrays nums1 and nums2 of size m and n respectively.

    // Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

    // Example 1:
    // nums1 = [1, 3]
    // nums2 = [2]

    // The median is 2.0
    // Example 2:
    // nums1 = [1, 2]
    // nums2 = [3, 4]

    // The median is (2 + 3)/2 = 2.5

    //Idea: If we cut the sorted array into two halves with equal length, then any number in the upper half is always greater than any number in the lower half
    //       left_part          |        right_part
    // A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
    // B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]

    // (1) i + j == m - i + n - j (or: m - i + n - j + 1)
    //      if n >= m, we just need to set: i = 0 ~ m, j = (m + n + 1)/2 - i
    // (2) B[j-1] <= A[i] and A[i-1] <= B[j]

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Switch nums1 and nums2 if the length of num1 is greater than the length of nums2.
        // Otherwise will throw error indexOutOfBound on nums2
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        // i is the cut point of nums1, j is the cut point of nums2
        int i, j;
        // left is the start index of nums1. right is the end index of nums1, half is the half size of the combined array
        int left = 0, right = nums1.length, half = (nums1.length + nums2.length + 1) / 2;
        while (left <= right) {
            // Choose the mid as the cut point of nums1
            i = (left + right) / 2;
            // The cut point of nums2 will be the rest of the half size
            j = half - i;

            if (i < nums1.length && nums2[j - 1] > nums1[i]) {
                // i is too small
                left++;
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                // i is too big
                right--;
            } else {
                int max_left, min_right;
                if (i == 0) {
                    // There is nothing on the left side of cut point in nums1
                    max_left = nums2[j - 1];
                } else if (j == 0) {
                    // There is nothing on the left side of cut point in nums2
                    max_left = nums1[i - 1];
                } else {
                    // Pick the largest value to the left of the cut point
                    max_left = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                // If the length of combined array is odd, just return the max left
                if ((nums1.length + nums2.length) % 2 == 1) {
                    return max_left;
                }

                if (i == nums1.length) {
                    // If there is nothing on the right side of nums1
                    min_right = nums2[j];
                } else if (j == nums2.length) {
                    // If there is nothing on the right side of nums2
                    min_right = nums1[i];
                } else {
                    // Pick the smallest value to the left of the cut point
                    min_right = Math.min(nums1[i], nums2[j]);
                }
                // Return the mean
                return (max_left + min_right) / 2.0;
            }
        }
        return -1.0;
    }

    // [ # 1 # 2 # 3 # 4 # 5 #] N = 5, points = 11
    // [ # 6 # 7 # 8 # 9 #] N = 4, points = 9
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2){
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 < len2){
            return findMedianSortedArrays(nums2,nums1);
        }

        if (len2 == 0)
            return  ((double)nums1[(len1 - 1) / 2] + (double)nums1[len1 / 2]) / 2;

        int lo = 0, hi = len2 * 2;
        while (lo <= hi){
            int mid2 = (lo + hi) / 2;
            int mid1 = len1 + len2 - mid2;

            double l1 = mid1 == 0 ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];
            double l2 = mid2 == 0 ? Integer.MIN_VALUE : nums2[(mid2 - 1 ) / 2];
            double r1 = mid1 == len1 * 2 ? Integer.MAX_VALUE : nums1[mid1 / 2];
            double r2 = mid2 == len2 * 2 ? Integer.MAX_VALUE : nums2[mid2 / 2];

            if (l1 > r2){
                lo = mid2 + 1;
            } else if (l2 > r1){
                hi = mid2 - 1;
            } else {
                return (Math.max(l1,l2) + Math.min(r1,r2)) / 2;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

}
