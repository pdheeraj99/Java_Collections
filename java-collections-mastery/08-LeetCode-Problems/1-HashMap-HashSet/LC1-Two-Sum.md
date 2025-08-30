# LeetCode 1: Two Sum

**Problem Statement:**
Given an array of integers `nums` and an integer `target`, return the indices of the two numbers such that they add up to `target`. You may assume that each input would have **exactly one solution**, and you may not use the same element twice.

**Example:**
*   **Input:** `nums = [2, 7, 11, 15]`, `target = 9`
*   **Output:** `[0, 1]`
*   **Explanation:** Because `nums[0] + nums[1] == 9`, we return `[0, 1]`.

---

### Brute-Force Approach

The simplest way to solve this is to just check every possible pair of numbers in the array.

1.  Outer loop `i` from `0` to `n-1`.
2.  Inner loop `j` from `i+1` to `n-1`.
3.  Check if `nums[i] + nums[j] == target`.
4.  If it is, we found our pair. Return `[i, j]`.

**Code:**
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Should not happen based on problem statement
    }
}
```

**Complexity Analysis:**
*   **Time Complexity: O(n²)** - We have nested loops, so for each element, we are iterating through the rest of the array.
*   **Space Complexity: O(1)** - We are not using any extra space.

This works, but it's too slow for large inputs. The interviewer will always ask you to optimize this.

---

### Optimized Approach using `HashMap`

We can solve this in a single pass using a `HashMap`. The core idea is, as we iterate through the array, we check if the **complement** of the current number (i.e., `target - currentNumber`) already exists in our map.

1.  Create a `HashMap<Integer, Integer>` to store `(number, index)`.
2.  Iterate through the `nums` array from `i = 0` to `n-1`.
3.  For each number `nums[i]`, calculate the complement: `complement = target - nums[i]`.
4.  Check if the `map.containsKey(complement)`.
    *   If **yes**, it means we have found our pair! The complement exists in the map, and its index is `map.get(complement)`. The current number's index is `i`. Return `[map.get(complement), i]`.
    *   If **no**, we haven't seen the complement yet. Add the current number and its index to the map: `map.put(nums[i], i)`. This is so we can find it later if we encounter *its* complement.

**Why it works:**
Instead of searching the array again and again (an O(n) operation), we use the `HashMap` to do the search for the complement in O(1) time. This reduces the overall time complexity significantly.

**The Code:**
```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }
        return null; // Should not happen
    }
}
```

**Complexity Analysis:**
*   **Time Complexity: O(n)** - We iterate through the array only once. Each lookup and insertion in the `HashMap` is, on average, O(1).
*   **Space Complexity: O(n)** - In the worst case, we might have to store all `n` elements in the `HashMap`.
