package org.fireplume;

public class ProfilingUtil {
    private static ProfilingUtil parentProfilingClass;

    private long start;
    private long end;
    private long delta;
    private String mark;
    private ProfilingUtil child;
    private ProfilingUtil parent;

    private ProfilingUtil(String mark) {
        this.mark = mark;
    }

    public static ProfilingUtil start(String mark) {
        final ProfilingUtil profilingUtil = new ProfilingUtil(mark);
        if (parentProfilingClass == null) {
            parentProfilingClass = profilingUtil;
        } else {
            parentProfilingClass.child = profilingUtil;
            profilingUtil.parent = parentProfilingClass;
            parentProfilingClass = profilingUtil;
        }
        parentProfilingClass.start = System.nanoTime();
        return parentProfilingClass;
    }

    public void end() {
        end = System.nanoTime();
        delta = end - start;
        System.out.printf("Work time (%s): %d nanos, %f millis, %f sec%n", mark, delta, delta / 1e6, delta / 1e9);
    }
}
