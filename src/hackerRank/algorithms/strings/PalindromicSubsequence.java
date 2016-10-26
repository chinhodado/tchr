package hackerRank.algorithms.strings;

import java.util.Arrays;

/**
 * The problem is very simple. For every string given as input, you need to tell us the number of subsequences of it that
 * are palindromes (need not necessarily be distinct). Note that the empty string is not a palindrome.
 *
 * For example, the palindromic subsequences of "aab" are: "a", "a", "b", "aa", and the method returns 4.
 *
 * Input
 * First line contains the number of test cases T (atmost 20). Each of the next T lines contains a single string
 * whose number of palindromic subsequences are to be printed. The maximum length of any input string is 50.
 *
 * Output
 * For each test case, print in a single line, the number of palindromic subsequences of the input string.
 *
 *
 * Example
 *
 * Input:
 * 3
 * aab
 * dddd
 * thisisapalindromeemordnilapasisiht
 *
 * Output:
 * 4
 * 15
 * 814157
 */
public class PalindromicSubsequence {
    public static void main(String[] args) {
        FastIo.FastScanner scanner = FastIo.in;
        java.io.PrintWriter out = FastIo.out;

        int numInputs = scanner.nextInt();
        for (int ix = 0; ix < numInputs; ++ix) {
            out.println(DynamicProgrammingApproach.getNumPalindromicSubsequences(scanner.next()));
        }

        out.flush();
    }

    //
    // The approach here is to use DP to build the solution using smaller subproblems
    //
    // g(i, j) is the Number of palindromic subsequences in the substring that starts at i and ends at j
    //
    // g(i, j) needs to account for all PS in these categories
    //      g(i, j - 1)
    //      g(i + 1, j)
    //      g(i + 1, j - 1)
    //
    // These are the 4 disjoint sets we need to sum up to get the result:
    //      1. PS starts after i and ends before j
    //      2. PS starts at i and ends before j
    //      3. PS starts after i and ends at j
    //      4. PS starts at i and ends at j
    //
    // g(i, j - 1) includes 1 and 2
    // g(i + 1, j) includes 1 and 3
    // g(i + 1, j - 1) includes just 1
    //
    // Let f(i, j) represent the number of PS that start exactly at i and end exactly at j
    // f(i, j) = g(i, j) - g(i, j - 1) - g(i + 1, j) + g(i + 1, j - 1)
    //
    // f(i, j) includes just 4
    //
    // g(i, i) = 1
    // g(i, i + 1) = 2 if str[i] != str[i + 1]
    // g(i, i + 1) = 3 if str[i] == str[i + 1]
    // g(i, j) = g(i, j - 1) + g(i + 1, j) - g(i + 1, j - 1) if str[i] != str[j]
    // g(i, j) = g(i, j - 1) + g(i + 1, j) - g(i + 1, j - 1) + f(i + 1, j - 1) if str[i] == str[j]
    //
    private static class DynamicProgrammingApproach {
        private static class DpTable {
            private final int[][] mTable;

            public DpTable(int len) {
                mTable = new int[len][len];
                for (int ix = 0; ix < len; ++ix) {
                    Arrays.fill(mTable[ix], 0);
                }
            }

            public void setVal(int start, int end, int val) {
                mTable[start][end] = val;
            }

            // g(i, j)
            public int getVal(int start, int end) {
                return mTable[start][end];
            }
        }

        public static int getNumPalindromicSubsequences(String str) {
            if (str == null) {
                throw new IllegalArgumentException("Invalid arg");
            }

            int result = 0;
            int len = str.length();
            if (len > 0) {
                DpTable dpTable = new DpTable(len);

                // Base cases
                // Length 1
                for (int ix = 0; ix < len; ++ix) {
                    dpTable.setVal(ix, ix, 1);
                }
                // Length 2
                for (int ix = 0; ix < len - 1; ++ix) {
                    int currVal = (str.charAt(ix) == str.charAt(ix + 1)) ? 3 : 2;
                    dpTable.setVal(ix, ix + 1, currVal);
                }
                // All Higher lengths
                for (int currLen = 3; currLen <= len; ++currLen) {
                    for (int start = 0, end = start + currLen - 1; end < len; ++start, ++end) {
                        int currVal = (str.charAt(start) == str.charAt(end)) ? dpTable.getVal(start + 1, end - 1) + 1 : 0;
                        currVal += dpTable.getVal(start, end - 1);
                        currVal += dpTable.getVal(start + 1, end);
                        currVal -= dpTable.getVal(start + 1, end - 1);
                        dpTable.setVal(start, end, currVal);
                    }
                }

                result = dpTable.getVal(0, len - 1);
            }

            return result;
        }
    }

    // A Helper Class for Fast I/O in Java
    private static final class FastIo {
        public static final String NEW_LINE = System.getProperty("line.separator");
        public static final java.io.PrintWriter out = new java.io.PrintWriter(new java.io.BufferedOutputStream(System.out));
        public static final FastScanner in = new FastScanner();

        private FastIo() {
            // Private Empty Constructor to prevent subclassing
            throw new UnsupportedOperationException("Cannot instantiate FastIo");
        }

        // A Fast Scanner implementation without pedantic input checking
        public static class FastScanner {
            private static final int BUFFER_SIZE = 16 * 1024;

            // The Input Stream to read from
            private final java.io.InputStream mInputStream;
            // The Buffer to read the Stream into
            private final byte[] mBuffer = new byte[BUFFER_SIZE];
            // The offset of the next byte in the buffer that should be parsed
            private int mBufferOffset = 0;
            // Number of Bytes in the buffer
            private int mSize = 0;

            public FastScanner() {
                this(System.in);
            }

            public FastScanner(java.io.InputStream inputStream) {
                mInputStream = inputStream;
            }

            // Returns the next token as a String
            public String next() {
                byte currByte = nextNonWhitespaceChar();
                StringBuilder result = new StringBuilder();
                while (!isWhitespace(currByte)) {
                    result.appendCodePoint(currByte);
                    currByte = nextByteFromStream();
                }
                return result.toString();
            }

            // Returns the next complete line
            public String nextLine() {
                byte currByte = nextNonNewLineChar();
                StringBuilder result = new StringBuilder();
                while (!isNewLine(currByte)) {
                    result.appendCodePoint(currByte);
                    currByte = nextByteFromStream();
                }
                return result.toString();
            }

            // Returns the next token parsed as a byte
            public byte nextByte() {
                byte currByte = nextNonWhitespaceChar();
                byte sign = 1;
                if (currByte == '-') {
                    sign = -1;
                    currByte = nextByteFromStream();
                } else if (currByte == '+') {
                    currByte = nextByteFromStream();
                }
                byte result = 0;
                while (!isWhitespace(currByte)) {
                    if (!isNumber(currByte)) {
                        throw new java.util.InputMismatchException("Not a number '" + currByte + "'");
                    }
                    result *= 10;
                    result += currByte - '0';
                    currByte = nextByteFromStream();
                }
                return (byte)(result * sign);
            }

            // Returns the next token parsed as a short
            public short nextShort() {
                byte currByte = nextNonWhitespaceChar();
                short sign = 1;
                if (currByte == '-') {
                    sign = -1;
                    currByte = nextByteFromStream();
                } else if (currByte == '+') {
                    currByte = nextByteFromStream();
                }
                short result = 0;
                while (!isWhitespace(currByte)) {
                    if (!isNumber(currByte)) {
                        throw new java.util.InputMismatchException("Not a number '" + currByte + "'");
                    }
                    result *= 10;
                    result += currByte - '0';
                    currByte = nextByteFromStream();
                }
                return (short)(result * sign);
            }

            // Returns the next token parsed as an int
            public int nextInt() {
                byte currByte = nextNonWhitespaceChar();
                int sign = 1;
                if (currByte == '-') {
                    sign = -1;
                    currByte = nextByteFromStream();
                } else if (currByte == '+') {
                    currByte = nextByteFromStream();
                }
                int result = 0;
                while (!isWhitespace(currByte)) {
                    if (!isNumber(currByte)) {
                        throw new java.util.InputMismatchException("Not a number '" + currByte + "'");
                    }
                    result *= 10;
                    result += currByte - '0';
                    currByte = nextByteFromStream();
                }
                return result * sign;
            }

            // Returns the next token parsed as a long
            public long nextLong() {
                byte currByte = nextNonWhitespaceChar();
                int sign = 1;
                if (currByte == '-') {
                    sign = -1;
                    currByte = nextByteFromStream();
                } else if (currByte == '+') {
                    currByte = nextByteFromStream();
                }
                long result = 0;
                while (!isWhitespace(currByte)) {
                    if (!isNumber(currByte)) {
                        throw new java.util.InputMismatchException("Not a number '" + currByte + "'");
                    }
                    result *= 10;
                    result += currByte - '0';
                    currByte = nextByteFromStream();
                }
                return result * sign;
            }

            // Returns the next token parsed as a float
            public float nextFloat() {
                String result = next();
                try {
                    return Float.parseFloat(result);
                } catch (NumberFormatException  exception) {
                    throw new java.util.InputMismatchException("Failed to parse '" + result + "' caused by\n" + stackTraceToString(exception));
                }
            }

            // Returns the next token parsed as a double
            public double nextDouble() {
                String result = next();
                try {
                    return Double.parseDouble(result);
                } catch (NumberFormatException  exception) {
                    throw new java.util.InputMismatchException("Failed to parse '" + result + "' caused by\n" + stackTraceToString(exception));
                }
            }

            // Returns true if there is a valid token to be read
            // This does not move the pointer in any way
            // FIXME: There is still a bug where hasNext() swallows a lot of empty spaces
            // such that invoking nextLine() does not have those spaces. This happens when
            // the rest of the buffer has only spaces
            public boolean hasNext() {
                if (mSize == -1) {
                    return false;
                }
                while (readIntoBuffer()) {
                    // Scan through the buffer to see if there is at least one non-whitespace character
                    int offset = mBufferOffset;
                    while (offset < mSize) {
                        if (!isWhitespace(mBuffer[offset])) {
                            return true;
                        }
                        ++offset;
                    }
                    // Since the buffer has only whitespace characters, update the offset so that
                    // readIntoBuffer() will attempt to read into Buffer again
                    mBufferOffset = offset;
                }
                return false;
            }

            // Returns the next non-Whitespace character from the buffer, and reads from the Stream if required
            private byte nextNonWhitespaceChar() {
                byte currByte = nextByteFromStream();
                while (isWhitespace(currByte)) {
                    currByte = nextByteFromStream();
                }
                return currByte;
            }

            private byte nextNonNewLineChar() {
                byte currByte = nextByteFromStream();
                while (isNewLine(currByte)) {
                    currByte = nextByteFromStream();
                }
                return currByte;
            }

            // Returns true if the next character in the buffer is good to be read
            private boolean readIntoBuffer() {
                if (mBufferOffset >= mSize) {
                    mBufferOffset = 0;
                    try {
                        mSize = mInputStream.read(mBuffer);
                    } catch (java.io.IOException exception) {
                        throw new java.util.InputMismatchException("IOException while reading from stream caused by:\n" + stackTraceToString(exception));
                    }
                    if (mSize <= 0) {
                        return false;
                    }
                }
                return true;
            }

            // Returns the next byte from the stream
            // If the buffer already has the next byte, just returns it without reading from the stream
            public byte nextByteFromStream() {
                if (mSize == -1) {
                    throw new java.util.InputMismatchException("No more bytes to read from stream");
                }
                if (!readIntoBuffer()) {
                    return -1;
                }
                return mBuffer[mBufferOffset++];
            }

            // Returns true if the byte is a valid number
            private boolean isNumber(byte currByte) {
                return (currByte >= '0' && currByte <= '9');
            }

            // Returns true if the byte is a white space character
            private static boolean isWhitespace(byte currByte) {
                switch (currByte) {
                    case ' ':
                    case '\n':
                    case '\r':
                    case '\t':
                    case -1:
                        return true;
                    default:
                        return false;
                }
            }

            // Returns true if the byte is is an EOL character
            private static boolean isNewLine(byte currByte) {
                switch (currByte) {
                    case '\n':
                    case '\r':
                    case -1:
                        return true;
                    default:
                        return false;
                }
            }

            // Returns a Stringified Human Readable Exception Stack Trace
            private static String stackTraceToString(Exception exception) {
                java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
                java.io.PrintStream printStream = new java.io.PrintStream(outputStream);
                exception.printStackTrace(printStream);
                printStream.close();
                return outputStream.toString();
            }
        }
    }
}