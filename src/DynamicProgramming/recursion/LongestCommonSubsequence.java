package DynamicProgramming.recursion;

public class LongestCommonSubsequence {
    public static String longestCommonSubsequence(String s, String t){
        return lcs(s, t, "", 0,0);
    }
    public static String lcs(String s, String t, String target, int si, int ti){
        if(si >= s.length() || ti >= t.length()){
            return target;
        }
        if(s.charAt(si) == t.charAt(ti)){
            return lcs(s, t, target+s.charAt(si), si+1, ti+1);
        }
        String st1 = lcs(s, t, target, si+1, ti);
        String st2 = lcs(s, t, target, si, ti+1);
        if(st1.length()>st2.length()){
            return st1;
        }else{
            return st2;
        }
    }

    public static void main(String[] args) {
        System.out.println("LCS of cat # crabt : "+ longestCommonSubsequence("cat", "crabt"));
        System.out.println("LCS of abcd # abcd : "+ longestCommonSubsequence("abcd", "abcd"));
        System.out.println("LCS of abcd # efgh : "+ longestCommonSubsequence("abcd", "efgh"));
        System.out.println("LCS of abbcbd # xaabccdd : "+ longestCommonSubsequence("abbcbd", "xaabccdd"));
    }
}
