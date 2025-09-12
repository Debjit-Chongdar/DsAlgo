package problems.string;

public class ZigZagString {

    //PAYPALISHIRING -> PAHNAPLSIIGYIR

    // P   A   H   N
    // A P L S I I G
    // Y   I   R
    public static String zigzag_conversion(String str, int rows){
        //if rows is one then no conversion required
        StringBuilder[] sb = new StringBuilder[rows];
        // initialize all String Builder instance
        for(int i=0; i<sb.length; i++){
            sb[i] = new StringBuilder();
        }
        boolean isMoveDownward = true;
        int sb_index = 0;
        // populate value to sb array
        for(char ch : str.toCharArray()){
            if(isMoveDownward){
                sb[sb_index].append(ch);
                if(sb_index == rows-1){ // if it reached to the last row
                    sb_index--;
                    isMoveDownward = false;
                }else{
                    sb_index++;
                }
            }else{
                sb[sb_index].append(ch);
                if(sb_index == 0){  // if it reached to the first row
                    sb_index++;
                    isMoveDownward = true;
                }else{
                    sb_index--;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for(StringBuilder strB : sb){
            result.append(strB);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(zigzag_conversion("PAYPALISHIRING", 3));
        System.out.println(zigzag_conversion("PAYPALISHIRING", 4));
    }
}
