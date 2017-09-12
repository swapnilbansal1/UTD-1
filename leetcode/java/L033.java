import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class L033 {
    @Test
    public void bsearch() throws Exception {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        assertEquals(4, new SolutionRec().bsearch(a, 5, 0, a.length - 1));
        assertEquals(12, new SolutionRec().bsearch(a, 13, 0, a.length - 1));
        assertEquals(-1, new SolutionRec().bsearch(a, 133, 0, a.length - 1));

        int[] b = {1, 3};
        assertEquals(1, new SolutionRec().bsearch(b, 3, 0, b.length - 1));
    }

    @Test
    public void search() throws Exception {
        int[] a = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7};
        assertEquals(14, new SolutionRec().search(a, 5, 0, a.length - 1));
        assertEquals(5, new SolutionRec().search(a, 13, 0, a.length - 1));
        assertEquals(-1, new SolutionRec().search(a, 133, 0, a.length - 1));

        int[] b = {1, 3};
        assertEquals(1, new SolutionRec().search(b, 3, 0, b.length - 1));
    }

    /**
     * Recursive solution with binary search.
     */
    class SolutionRec {
        /**
         * Recursive binary search over sorted array.
         *
         * @param nums   Array to do search on.
         * @param target Search target.
         * @param l      Starting index.
         * @param r      Ending index.
         * @return The index of the target, if not exist, return -1.
         */
        public int bsearch(int[] nums, int target, int l, int r) {
            if (l > r)
                return -1;

            final int mid = (r + l) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                return bsearch(nums, target, l, mid - 1);
            return bsearch(nums, target, mid + 1, r);
        }

        /**
         * Recursive binary search over rotated sorted array.
         *
         * @param nums   Array to do search on.
         * @param target Search target.
         * @param l      Starting index.
         * @param r      Ending index.
         * @return The index of the target, if not exist, return -1.
         */
        public int search(int[] nums, int target, int l, int r) {
            if (l > r)
                return -1;

            final int mid = (r + l) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[l] == target)
                return l;
            if (nums[r] == target)
                return r;

            if (nums[l] < nums[mid]) {
                if (nums[mid] > target && nums[l] < target)
                    return bsearch(nums, target, l + 1, mid - 1);
                return search(nums, target, mid + 1, r - 1);
            } else {
                if (nums[mid] < target && nums[r] > target)
                    return bsearch(nums, target, mid + 1, r - 1);
                return search(nums, target, l + 1, mid - 1);
            }
        }

        public int search(int[] nums, int target) {
            return search(nums, target, 0, nums.length - 1);
        }
    }
}
