package randomizedtest;

import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class testThreeAddThreeRemove {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> N1 = new AListNoResizing<>();
        BuggyAList<Integer> N2 = new BuggyAList<>();
        N1.addLast(4);
        N1.addLast(5);
        N1.addLast(6);
        N2.addLast(4);
        N2.addLast(5);
        N2.addLast(6);
        int n1 = N1.removeLast();
        int n2 = N2.removeLast();
        assertEquals(n1, n2);
        int n3 = N1.removeLast();
        int n4 = N2.removeLast();
        assertEquals(n3, n4);
        int n5 = N1.removeLast();
        int n6 = N2.removeLast();
        assertEquals(n5, n6);
    }
}
