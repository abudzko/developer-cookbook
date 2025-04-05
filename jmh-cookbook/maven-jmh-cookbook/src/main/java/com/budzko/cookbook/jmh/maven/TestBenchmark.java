package com.budzko.cookbook.jmh.maven;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
//@Fork(value = 2)
//@Warmup(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 2)
//@Measurement(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 2)
//public class TestBenchmark {
//
//    @State(Scope.Thread)
//    public static class TestState {
//        @Param({"1000", "10000"})
//        private int i;
//    }
//
//    @Benchmark
//    public void recursion(Blackhole blackhole, TestState testState) {
//        blackhole.consume(testMethod(testState.i));
//    }
//
//    private int testMethod(int i) {
//        if (i == 0) {
//            return i;
//        } else {
//            return testMethod(i - 1) + i;
//        }
//    }
//}
