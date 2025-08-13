package DynamicProgramming.recursion;

public class DistinctSubsequence {
    //t=cat, s=caaat result = {ca**t, c*a*t, c**at}
    public static int disSubsequent(String s, String t){
        return rec_sub(s, t, 0, 0);
    }
    private static int rec_sub(String s, String t, int si, int ti){
        if(ti >= t.length()){
            return 1;           // if ti reached end means match
        }
        if(si >= s.length()){   // if ti not reached end but s reached end means miss
            return 0;
        }
        int total = 0;
        if(s.charAt(si) == t.charAt(ti)){
            total += rec_sub(s, t, si+1, ti+1); // if match check for next match
        }
        total += rec_sub(s, t, si+1, ti); // if match or not match there can have other match
        return total;
    }

    public static void main(String[] args) {
        System.out.println("Distinct subsequece caaat # cat : "+disSubsequent("caaat", "cat"));
        System.out.println("Distinct subsequece xxyxy # xy : "+disSubsequent("xxyxy", "xy"));
    }
}
