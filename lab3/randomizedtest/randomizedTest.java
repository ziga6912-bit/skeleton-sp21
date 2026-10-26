package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class randomizedTest {
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>(); // 新加一个 Buggy 列表
        int N = 5000; // 建议后面改成 5000

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal); // 同步操作
            } else if (operationNumber == 1) {
                int sizeL = L.size();
                int sizeB = B.size(); // 同步操作
                assertEquals(sizeL, sizeB); // 比较大小是否一致
            } else if (operationNumber == 2) {
                if (L.size() > 0) {
                    int lastL = L.getLast();
                    int lastB = B.getLast(); // 同步操作
                    assertEquals(lastL, lastB); // 比较返回值
                }
            } else if (operationNumber == 3) {
                if (L.size() > 0) {
                    int removedL = L.removeLast();
                    int removedB = B.removeLast(); // 同步操作
                    assertEquals(removedL, removedB); // 比较返回值
                }
            }
        }
    }
}
