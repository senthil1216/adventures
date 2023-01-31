/*
ID: senthil8
LANG: JAVA
TASK: milk2
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class milk2 {

    static class Time implements Comparable<Time> {
        int time;
        int enter;

        public int compareTo(Time t) {
            if (this.time == t.time) {
                return this.enter - t.enter;
            }
            return this.time - t.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
        int caseCount = Integer.parseInt(br.readLine());
        Time[] times = new Time[caseCount * 2];
        for (int i = 0; i < caseCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Time t = new Time();

            t.time = Integer.parseInt(st.nextToken());
            t.enter = 0;
            times[i * 2] = t;

            t = new Time();
            t.time = Integer.parseInt(st.nextToken());
            t.enter = 1;
            times[i * 2 + 1] = t;
        }
        br.close();

        Arrays.sort(times);
        int currentCount = 0;
        int lastEmptyTime = times[0].time;
        int lastHasFarmerTime = 0;
        int maxEmpty = 0;
        int maxFarmer = 0;

        for (int i = 0; i < times.length; i++) {
            if (times[i].enter == 0) {
                if (currentCount == 0) {
                    maxEmpty = Math.max(times[i].time - lastEmptyTime, maxEmpty);
                    lastHasFarmerTime = times[i].time;
                }
                currentCount++;
            } else {
                currentCount--;
                if (currentCount == 0) {
                    maxFarmer = Math.max(times[i].time - lastHasFarmerTime, maxFarmer);
                    lastEmptyTime = times[i].time;
                }
            }
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        pw.println(maxFarmer + " " + maxEmpty);
        pw.close();
    }
}