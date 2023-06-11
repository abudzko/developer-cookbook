package com.budzko.cookbook.algo.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    /**
     * finds all line segments containing 4 points
     */
    public BruteCollinearPoints(Point[] points) {
        noNulls(points);

        Arrays.sort(points);
        noDuplicates(points);
        int n = points.length;
        List<LineSegment> segmentList = new ArrayList<>();
        for (int i = 0; i < n - 3; i++) {
            Point p1 = points[i];
            for (int j = i + 1; j < n - 2; j++) {
                Point p2 = points[j];
                double slope1 = p1.slopeTo(p2);
                for (int k = j + 1; k < n - 1; k++) {
                    Point p3 = points[k];
                    double slope2 = p2.slopeTo(p3);
                    if (slope1 != slope2) {
                        continue;
                    }
                    for (int l = k + 1; l < n; l++) {
                        Point p4 = points[l];
                        double slope3 = p3.slopeTo(p4);
                        if (slope2 == slope3) {
                            segmentList.add(new LineSegment(p1, p4));
                        }
                    }
                }
            }
        }
        segments = segmentList.toArray(new LineSegment[0]);
    }

    private void noDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void noNulls(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * The number of line segments
     */
    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
}
