package DynamicProgramming.recursion;

public class InterleavingString {

    // s1 = "abc", s2 = "xyz", s3 = "abxzcy"  => false
    // s1 = "abc", s2 = "xyz", s3 = "abxycz"  => true
    public static boolean isValidInterleavingString (String s1, String s2, String t){
        return check(s1, 0, s2, 0, t, 0);
    }
    private static boolean check(String s1, int is1, String s2, int is2, String t, int it){
        if(is1 >= s1.length() && is2 >= s2.length() && it >= t.length()){
            return true;
        }
        if(is1 < s1.length() && t.charAt(it) == s1.charAt(is1)){
            return check(s1, is1+1, s2, is2, t, it+1);
        }
        if(is2 < s2.length() && t.charAt(it) == s2.charAt(is2)){
            return check(s1, is1, s2, is2+1, t, it+1);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("abc xyz => abxzcy : "+isValidInterleavingString("abc", "xyz", "abxzcy"));
        System.out.println("abc xyz => abxycz : "+isValidInterleavingString("abc", "xyz", "abxycz"));
    }
}
