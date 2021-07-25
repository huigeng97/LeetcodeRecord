package String;

public class ImplementstrStr28 {

    public int strStr2(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    // The second is the KMP algorithm;

    // first is to find do the KMP process to generate the partial table of the needle word;

    // second is to use the partial table to one pass the haystack word;

    public int strStr(String haystack, String needle) {

        // kmpProcess

        return 0;

    }
    public int[] KMPprocess(String needle) {
        int[] table = new int[needle.length()];

        for (int right = 1, len = 0 ; right < needle.length(); ) {
            if (needle.charAt(right) == needle.charAt(len)) {
                table[right++] = ++len;
            } else if (len != 0) {


                // return the last left end character and continue to compare;
                len = table[len - 1];

            } else {
                table[right++] = len;
            }
        }
        return table;
    }
}
