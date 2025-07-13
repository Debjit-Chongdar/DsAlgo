package recursion;

public class SumOfNumbers {

    public static int sumOfTwoNumbers(int num1, int num2){
        return sumOfTwoNum(num1, num2, 0, 0, 1);
    }
    private static int sumOfTwoNum(int num1, int num2, int remainder, int result, int mul){
        if(num1 == 0 && num2 == 0){
            if(remainder == 0) {
                return result;
            }else{
                return sumOfTwoNum(num1, num2, 0, (remainder*mul)+result, mul*10);
            }
        }
        if(num1 !=0){
            remainder += num1 % 10;
        }
        if(num2 != 0){
            remainder += num2 % 10;
        }
        int res = remainder % 10;
        int rem = remainder / 10;
        return sumOfTwoNum(num1 / 10, num2 / 10, rem, (res*mul)+result, mul*10);
    }

    public static void main(String[] args) {
        int sum = sumOfTwoNumbers(314, 728);
        System.out.println(sum);
    }
}
