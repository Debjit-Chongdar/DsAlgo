package sliding_window.fixed_length;
import java.util.Arrays;

public class PermutationString {
    //t = "abc", s2 = "lecabee" return true (abc/cab)
    static boolean checkInclusion(String t, String s){
        if(t.length() > s.length()){return false;}
        int[] in = new int[26];
        int[] out = new int[26];
        for(int i=0; i<t.length(); i++){
            in[t.charAt(i)-'a'] =  in[t.charAt(i)-'a']+1;
            out[s.charAt(i)-'a'] =  out[s.charAt(i)-'a']+1;
        }
        if(Arrays.equals(in, out)){
            return true;
        }
        int start=0;
        for(int i=t.length(); i<s.length(); i++){
            out[s.charAt(start)-'a'] = out[s.charAt(start)-'a']-1;
            start++;
            out[s.charAt(i)-'a'] =  out[s.charAt(i)-'a']+1;
            if(Arrays.equals(in, out)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("abc", "lecabee"));
    }
}
