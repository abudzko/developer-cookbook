package com.budzko.cookbook.algo.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    /**
     * Finds all line segments containing 4 or more points
     */
    public FastCollinearPoints(Point[] points) {
        noNulls(points);
        Arrays.sort(points);
        noDuplicates(points);
        List<LineSegment> segmentList = new ArrayList<>();

        Point[] jCopy = points.clone();
        Arrays.sort(jCopy);
        for (int i = 0; i < jCopy.length - 3; i++) {
            Arrays.sort(jCopy);

            Arrays.sort(jCopy, jCopy[i].slopeOrder());

            for (int p = 0, first = 1, last = 2; last < jCopy.length; last++) {
                // find last collinear to p point
                while (last < jCopy.length
                        && Double.compare(jCopy[p].slopeTo(jCopy[first]), jCopy[p].slopeTo(jCopy[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && jCopy[p].compareTo(jCopy[first]) < 0) {
                    segmentList.add(new LineSegment(jCopy[p], jCopy[last - 1]));
                }
                first = last;
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
