package weekly.two79;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * You are given an integer num. Rearrange the digits of num such that its value is minimized and it does not contain any leading zeros.
 * <p>
 * Return the rearranged number with minimal value.
 * <p>
 * Note that the sign of the number does not change after rearranging the digits.
 */
public class SmallesValueRearrangedNumber {
    public long smallestNumber(long num) {
        PriorityQueue<Long> pq = null;
        long n = num;
        if (num > 0) {
            pq = new PriorityQueue<>();
        } else {
            pq = new PriorityQueue<>(Collections.reverseOrder());
            n *= -1;
        }
        while (n > 0) {
            pq.add(n % 10);
            n = n / 10;
        }

        long res = 0;
        int zeroCount = 0;
        while (!pq.isEmpty()) {
            if (res == 0 && pq.peek() == 0) {
                while (!pq.isEmpty() && pq.peek() == 0) {
                    pq.poll();
                    zeroCount++;
                }
            }

            res = res * 10 + pq.poll();

            if (zeroCount > 0) {
                while (zeroCount > 0) {
                    res = res * 10;
                    zeroCount--;
                }
            }
        }
        if (num < 0) res *= -1;
        return res;
    }
}
