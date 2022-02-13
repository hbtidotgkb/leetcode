package weekly.two80;

import java.util.Arrays;

public class MagicBeans {
    public long minimumRemoval(int[] beans) {
        long total = 0;
        int len = beans.length;
        for(int i:beans) {
            total += i;
        }
        Arrays.sort(beans);
        long count = Long.MAX_VALUE;
        for(int i=0;i<beans.length;i++) {
            long c = total - len*beans[i];
            count = Math.min(count, c);
            len--;
        }
        return count;
    }

    public long minimumRemovalLC(int[] beans) {
        long sum = 0;
        for (int bean : beans) {
            sum += bean;
        }
        Arrays.sort(beans);
        long result = Long.MAX_VALUE;
        long m = beans.length;
        for (int i = 0; i < beans.length; i++) {
            long c = sum - m * beans[i];
            result = Math.min(result, c);
            m--;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MagicBeans().minimumRemoval(new int[]{63,43,12,94}));
    }
}
