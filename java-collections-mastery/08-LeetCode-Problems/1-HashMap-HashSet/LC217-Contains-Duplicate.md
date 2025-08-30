# LeetCode 217: Contains Duplicate

**Problem Statement:**
Given an integer array `nums`, return `true` if any value appears **at least twice** in the array, and `false` if every element is distinct.

**Example 1:**
*   **Input:** `nums = [1, 2, 3, 1]`
*   **Output:** `true`

**Example 2:**
*   **Input:** `nums = [1, 2, 3, 4]`
*   **Output:** `false`

---

### Brute-Force Approach

The most straightforward way is to compare every element with every other element.

1.  Outer loop `i` from `0` to `n-1`.
2.  Inner loop `j` from `i+1` to `n-1`.
3.  Check if `nums[i] == nums[j]`.
4.  If they are equal, we've found a duplicate. Return `true`.
5.  If the loops complete without finding any duplicates, return `false`.

**Code:**
```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

**Complexity Analysis:**
*   **Time Complexity: O(n²)** - The nested loops lead to a quadratic time complexity.
*   **Space Complexity: O(1)** - No extra space is used.

This is very inefficient for large arrays.

---

### Optimized Approach using `HashSet`

This problem is a perfect use case for a `HashSet` to track the numbers we have already seen.

1.  Create an empty `HashSet<Integer>` called `seen`.
2.  Iterate through the `nums` array.
3.  For each number `num`:
    *   Check if `seen.contains(num)`.
    *   If **yes**, it means we have seen this number before. It's a duplicate. Return `true`.
    *   If **no**, add the number to the set: `seen.add(num)`.
4.  If the loop finishes, it means we never found a duplicate. Return `false`.

**Why it works:**
The power of `HashSet` is its O(1) average time complexity for both `contains()` and `add()`. Instead of re-scanning the array for each element, we do a single, super-fast check against the `HashSet`.

**The Code:**
```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
```
**Alternative `add()` trick:**
The `Set.add()` method itself returns a boolean: `true` if the element was new and added successfully, and `false` if the element was already in the set. We can use this to make the code even more concise.

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            // If add() returns false, it means the element was already there.
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}
```

**Complexity Analysis:**
*   **Time Complexity: O(n)** - We iterate through the array only once. Each `HashSet` operation is O(1) on average.
*   **Space Complexity: O(n)** - In the worst case (all elements are unique), we have to store all `n` elements in the `HashSet`.
