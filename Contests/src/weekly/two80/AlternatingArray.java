package weekly.two80;

import java.util.*;

/**
 * 2170. Minimum Operations to Make the Array Alternating
 * <p>
 * You are given a 0-indexed array nums consisting of n positive integers.
 * The array nums is called alternating if:
 * nums[i - 2] == nums[i], where 2 <= i <= n - 1.
 * nums[i - 1] != nums[i], where 1 <= i <= n - 1.
 * In one operation, you can choose an index i and change nums[i] into any positive integer.
 * <p>
 * Return the minimum number of operations required to make the array alternating.
 */
public class AlternatingArray {
    public int minimumOperations(int[] nums) {
        if (isAllSame(nums)) {
            return nums.length / 2;
        }

        int count = 0;
        Map<Integer, Node> oddMap = new HashMap<>();
        Map<Integer, Node> evenMap = new HashMap<>();

        PriorityQueue<Node> pqOdd = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n2.freq - n1.freq;
            }
        });

        PriorityQueue<Node> pqEven = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n2.freq - n1.freq;
            }
        });

        for (int i = 0; i < nums.length; i++) {
            if (i % 2 != 0) {
                Node node = oddMap.getOrDefault(nums[i], new Node(nums[i], 0));
                node.freq++;
                oddMap.put(nums[i], node);
            } else {
                Node node = evenMap.getOrDefault(nums[i], new Node(nums[i], 0));
                node.freq++;
                evenMap.put(nums[i], node);
            }
        }

        for (Map.Entry<Integer, Node> e : oddMap.entrySet()) {
            pqOdd.add(e.getValue());
        }

        for (Map.Entry<Integer, Node> e : evenMap.entrySet()) {
            pqEven.add(e.getValue());
        }

        while (!pqEven.isEmpty() && !pqOdd.isEmpty() && pqEven.peek().val == pqOdd.peek().val) {
            if (pqEven.peek().freq > pqOdd.peek().freq) {
                pqOdd.poll();
            } else {
                pqEven.poll();
            }
        }

        int maxEven = pqEven.peek().val;
        int maxOdd = pqOdd.peek().val;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0 && nums[i] != maxEven) {
                count++;
            } else if (i % 2 != 0 && nums[i] != maxOdd) {
                count++;
            }
        }
        return count;
    }

    private boolean isAllSame(int[] nums) {
        Set<Integer> set = new HashSet<>();
        set.add(nums[0]);
        for (int i : nums) {
            if (!set.contains(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new AlternatingArray().minimumOperations(new int[]{1, 2, 2, 2, 2}));
    }
}

class Node {
    int freq;
    int val;

    public Node(int val, int freq) {
        this.freq = freq;
        this.val = val;
    }
}
