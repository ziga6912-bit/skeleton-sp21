package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static double timeElapse(int N) {
        AList<Integer> Ns = new AList<>();
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < N; i++) {
            Ns.addLast(0);
        }
        return sw.elapsedTime();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        Ns.addLast(1000);
        Ns.addLast(2000);
        Ns.addLast(4000);
        Ns.addLast(8000);
        Ns.addLast(16000);
        Ns.addLast(32000);
        Ns.addLast(64000);
        Ns.addLast(128000);
        times.addLast(timeElapse(1000));
        times.addLast(timeElapse(2000));
        times.addLast(timeElapse(4000));
        times.addLast(timeElapse(8000));
        times.addLast(timeElapse(16000));
        times.addLast(timeElapse(32000));
        times.addLast(timeElapse(64000));
        times.addLast(timeElapse(128000));
        printTimingTable(Ns, times, Ns);
    }
}
