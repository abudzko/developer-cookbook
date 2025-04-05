package com.budzko.cookbook.jmh.maven;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static java.lang.System.out;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Warmup(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 5)
@Measurement(iterations = 2, timeUnit = TimeUnit.SECONDS, time = 5)
public class ExceptionBenchmark {

    private static final Pattern p1 = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}$");
    private static final Pattern p2 = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{3}[+-][0-9]{2}:[0-9]{2}$");

    private static final LocalDateTime NOW = LocalDateTime.now();
    private static final String LOCAL_DATA_TIME = "2000-01-02T14:30:55";
    private static final String ZONED_DATE_TIME = "2000-01-02T14:30:55.010+05:00";


    private static TemporalAccessor throwEx(String date) {
        try {
            return LocalDateTime.parse(date);
        } catch (Exception e1) {
            try {
                return ZonedDateTime.parse(date);
            } catch (Exception e2) {
                return NOW;
            }
        }
    }

    public static void main(String[] args) {
        out.println(pattern(LOCAL_DATA_TIME));
        out.println(pattern(ZONED_DATE_TIME));
        out.println(throwEx(LOCAL_DATA_TIME));
        out.println(throwEx(ZONED_DATE_TIME));
    }

    private static TemporalAccessor pattern(String date) {
        if (p1.matcher(date).matches()) {
            return LocalDateTime.parse(date);
        } else if (p2.matcher(date).matches()) {
            return ZonedDateTime.parse(date);
        } else {
            return NOW;
        }
    }

    @Benchmark
    public void ex(Blackhole blackhole, TestState testState) {
        blackhole.consume(throwEx(testState.date));
    }

    @Benchmark
    public void pattern(Blackhole blackhole, TestState testState) {
        blackhole.consume(pattern(testState.date));
    }

    @State(Scope.Thread)
    public static class TestState {
        @Param({LOCAL_DATA_TIME, ZONED_DATE_TIME})
        private String date;
    }
}
