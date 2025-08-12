package DynamicProgramming.recursion;

// insert / replace / delete
// word1 = "monkeys", word2 = "money" , result = 2 (2 delete required)
public class MinDistance {

    public static int minOPerationRequired(String s, String t){
        return minOps(s, 0, t, 0);
    }
    private static int minOps(String s, int si, String t, int ti){
        if(ti >= t.length()){
            return s.length() -si;
        }
        if(si >= s.length()){
            return t.length() -ti;
        }
        if(s.charAt(si) == t.charAt(ti)){
            return minOps(s, si+1, t, ti+1);
        }
        int min = 0;
        min += minOps(s, si+1, t, ti+1);             // replace operation
        min = Math.min(min, minOps(s, si, t, ti+1));    // insert / delete
        min = Math.min(min, minOps(s, si+1, t, ti));    // insert / delete
        return 1 + min;
    }

    public static void main(String[] args) {
        System.out.println("min operation required monkeys # money : "+minOPerationRequired("monkeys", "money"));
    }
}
