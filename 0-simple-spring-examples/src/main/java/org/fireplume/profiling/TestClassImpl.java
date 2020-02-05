package org.fireplume.profiling;

import org.springframework.stereotype.Service;

@Service
public class TestClassImpl {

    @ProfilingClass.CustomProfiling(isWriteToFile = false, filePath = "/home/nikita/", fileName = "test_prof.txt")
    public void testMethod1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    @ProfilingClass.CustomProfiling(isWriteToFile = false, filePath = "/home/nikita/", fileName = "test_prof.txt")
    public void testMethod2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
