package recursion;

public class PatternMatch {
    public static boolean isMatch(String pattern, String text){
        if(pattern == null || pattern.isEmpty()){
            return text==null || text.isEmpty();
        }
        boolean primaryMatch = (!text.isEmpty() && (pattern.charAt(0)=='.' || text.charAt(0) == pattern.charAt(0)));
        if(pattern.length()>1 && pattern.charAt(1) == '*'){
            // a* can be ignored, as * can be 0 or any occurance
            return (isMatch(pattern.substring(2), text)
                    // if we consider occurance, it can be any occurance
                    || (primaryMatch && isMatch(pattern, text.substring(1))));
        }else{
            return (primaryMatch && isMatch(pattern.substring(1), text.substring(1)));
        }
    }

    public static void main(String[] args) {
        System.out.println("Expected True : "+ isMatch(".*", "ab"));
        System.out.println("Expected true : "+ isMatch("ab*a*c*a", "aaa"));
        System.out.println("Expected true : "+ isMatch("**", ""));
        System.out.println("Expected false : "+isMatch("*", "a"));
        System.out.println("Expected true : "+ isMatch(".*", "ab"));
        System.out.println("Expected false : "+ isMatch("a", "aa"));
        System.out.println("Expected true : "+ isMatch("a*", "aa"));
    }
}
