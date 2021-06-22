package String;

public class Reorganize_String767 {
    public String reorganizeString(String s) {
        // count the number of char;
        int[] hash = new int[26];
        char letter = 0;
        int max = 0 ;
        for (char ch  : s.toCharArray()) {
            hash[ch-'a'] += 1;
            if (max<hash[ch-'a']) {
                max = hash[ch-'a'];
                letter = ch;
            }
        }

        if (max > (s.length() + 1)/2 ) return "";

        // reorganize the char
        // First put the most occurrence char in the every other one place;
        char[] res = new char[s.length()];
        int i = 0;
        while (hash[letter - 'a']>=0) {
            res[i] = letter;
            hash[letter - 'a'] --;
            i = i+2;
        }
        //  put the other chars in the every other one place;
        for (int j = 0; j < hash.length; i++) {

            while (hash[j] >= 0) {
                if (i >= s.length()) {
                    i = 1;
                }
                res[i] = (char) (i + 'a');
                hash[j]--;
                i = i + 2;
            }
        }
        // we could also use the String.valueOf(res);
        return  new String(res);

    }
}
