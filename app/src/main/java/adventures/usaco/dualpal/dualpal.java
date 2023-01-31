package adventures.usaco.dualpal;
/*
ID: senthil8
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.StringTokenizer;

public class dualpal {
    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader reader = new BufferedReader(new FileReader("dualpal.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(reader.readLine());
        // Get line, break into tokens
        int countNumberToFind = Integer.parseInt(st.nextToken()); // first string
        int smallestNum = Integer.parseInt(st.nextToken()); // second string
        for (int i = smallestNum + 1; i <= Integer.MAX_VALUE; ++i) {
            if (isPalindromicUnderTwoBases(i)) {
                out.println(i);
                if (--countNumberToFind <= 0) {
                    break;
                }
            }
        }
        out.close(); // close the output file
        System.exit(0); // don't omit this!
    }

    private static boolean isPalindromicUnderTwoBases(int value) {
        int countPalindromic = 0;
        for (int base = 2; base <= 10; ++base) {
            final String valueUnderBase = convertToBase(value, base);
            if (isPalindromic(valueUnderBase)) {
                ++countPalindromic;
                if (countPalindromic >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String convertToBase(int number, int baseNum) {
        StringBuilder builder = new StringBuilder();
        while (number > 0) {
            builder.append(number % baseNum);
            number /= baseNum;
        }
        return builder.toString();
    }

    private static boolean isPalindromic(String str) {
        for (int i = 0; i < str.length() / 2; ++i) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
