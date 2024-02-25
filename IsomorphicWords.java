import java.util.*;
public class IsomorphicWords {
    public int countPairs(String[] words) {
        int count = 0;

        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j<words.length; j++) {
                if(isIsomorphic(words[i], words[j])) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] mapping1 = new int[256];
        int[] mapping2 = new int[256];
        Arrays.fill(mapping1, -1);
        Arrays.fill(mapping2, -1);


        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (mapping1[c1] == -1 && mapping2[c2] == -1) {
                mapping1[c1] = c2;
                mapping2[c2] = c1;

            } else if (!(mapping1[c1] == c2 && mapping2[c2] == c1)){
                    return false;
                }
            }
        return true;
    }
}