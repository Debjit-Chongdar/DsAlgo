package problems.string;
//Debjit Chongdar
//tijbeD radgnohC
//Chongdar Debjit
public class SwapSurnameName {
    public void swapWithoutUsingAdditionalSpace(String fullName){
        //instead char array we should use integer so that by + - we can swap instead third variable
        //populate string into int array
        int[] chars = new int[fullName.length()];
        int index = chars.length-1;
        for(int i=0; i<chars.length; i++){
            if(fullName.charAt(i) == ' '){
                index = i;
            }
            chars[i] = fullName.charAt(i) - 'a';
        }
        // swap all character till index-1
        swapAllChars(chars, 0, index-1);    //tijbeD
        //swap all charaters from index+1 to end
        swapAllChars(chars, index+1, chars.length-1);   //radgnohC
        //swap all character start to end
        swapAllChars(chars, 0, chars.length-1);     // Chongdar Debjit
        printName(chars);
    }
    private void swapAllChars(int[] chars, int start, int end){
        if(start >= end){
            return;
        }
        swap(chars, start, end);
        swapAllChars(chars, start+1, end-1);
    }
    private void swap(int[] chars, int start, int end){
        chars[start] = chars[start] + chars[end];
        chars[end] = chars[start] - chars[end];
        chars[start] = chars[start] - chars[end];
    }
    private void printName(int[] chars){
        for(int i : chars){
            System.out.print(((char)(i+'a')));
        }
    }

    public static void main(String[] args) {
        SwapSurnameName sw = new SwapSurnameName();
        sw.swapWithoutUsingAdditionalSpace("Debjit Chongdar");
    }
}
