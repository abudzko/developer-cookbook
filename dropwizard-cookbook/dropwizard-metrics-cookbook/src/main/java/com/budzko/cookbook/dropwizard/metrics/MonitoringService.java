package com.budzko.cookbook.dropwizard.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MonitoringService {
    private MetricRegistry metricRegistry = new MetricRegistry();
    private final Meter meter = metricRegistry.meter("event_1");
    private Histogram histogram = metricRegistry.histogram("distribution_1");
    private Timer timer = metricRegistry.timer("timer_1");
    private Counter counter = metricRegistry.counter("counter_1");
    private Gauge gauge = metricRegistry.gauge("gauge_1", new MetricRegistry.MetricSupplier<Gauge>() {
        @Override
        public Gauge newMetric() {
            return new Gauge() {
                @Override
                public Object getValue() {
                    return new Random().nextInt(1000);
                }
            };
        }
    });

    public MonitoringService() {
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        consoleReporter.start(1, TimeUnit.SECONDS);

        final Graphite graphite = new Graphite(new InetSocketAddress("localhost", 2003));
        final GraphiteReporter graphiteReporter = GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith("dropwizard-metrics-cookbook")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);
        graphiteReporter.start(1, TimeUnit.SECONDS);
    }

    public void event() {
        meter.mark();
        Random random = new Random();
        histogram.update(random.nextInt(100));
        timer.update(Duration.ofMillis(random.nextInt(20)));
        counter.inc();
    }
}
