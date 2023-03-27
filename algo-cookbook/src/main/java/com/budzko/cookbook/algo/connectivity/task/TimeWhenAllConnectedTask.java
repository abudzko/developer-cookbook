package com.budzko.cookbook.algo.connectivity.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Social network connectivity. Given a social network containing n members and a log file containing m timestamps
 * at which times pairs of members formed friendships, design an algorithm to determine the earliest time
 * at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend).
 * Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
 * The running time of your algorithm should be mlogn or better and use extra space proportional to n.
 */
public class TimeWhenAllConnectedTask {
    public static void main(String[] args) {
        int n = 5;
        List<Log> logs = logs();
        logs.forEach(System.out::println);
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(n);

        long timestamp = findTimestamp(logs, weightedQuickUnion);
        System.out.println("Result = " + timestamp);
    }

    private static long findTimestamp(
            List<Log> logs,
            WeightedQuickUnion weightedQuickUnion
    ) {
        int i = 0;
        long timestamp = -1;
        while (i < logs.size()) {
            Log log = logs.get(i);
            weightedQuickUnion.union(log.first, log.second);
            if (weightedQuickUnion.count == 1) {
                timestamp = log.timestamp;
                break;
            }
            i++;
        }
        return timestamp;
    }

    private static List<Log> logs() {
        ArrayList<Log> logs = new ArrayList<>();
        long timestamp = 0;
        logs.add(new Log(timestamp++, 1, 2));
        logs.add(new Log(timestamp++, 2, 0));
        logs.add(new Log(timestamp++, 1, 0));
        logs.add(new Log(timestamp++, 3, 0));
        logs.add(new Log(timestamp++, 3, 1));
        logs.add(new Log(timestamp++, 3, 4));
        return logs;
    }

    record Log(long timestamp, int first, int second) {
    }

    static class WeightedQuickUnion {
        int[] ids;
        int[] sizes;
        int count;

        WeightedQuickUnion(int n) {
            count = n;
            ids = new int[n];
            sizes = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
                sizes[i] = 1;
            }
        }

        void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if (rootP == rootQ) return;
            if (sizes[rootP] > sizes[rootQ]) {
                ids[rootQ] = rootP;
                sizes[rootP] += sizes[rootQ];
            } else {
                ids[rootP] = rootQ;
                sizes[rootQ] += sizes[rootP];
            }
            count--;
        }

        private int root(int id) {
            while (id != ids[id]) {
                int parentId = ids[id];
                ids[id] = ids[parentId];//optimization
                id = parentId;
            }
            return id;
        }
    }
}
