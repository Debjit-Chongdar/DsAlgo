package Jdk_24;

public class SwitchCase_PatternMatching {

    public static void patternMatchingSwitchCase(Object obj){
        switch(obj){
            case String s when s.length() > 5 ->
                System.out.println("Long String = " +s);
            case String s ->
                System.out.println("Sort String = "+ s);
            case Integer i ->
                System.out.println("Integer value = "+i);
            case Double d ->
                System.out.println("Double value = "+d);
            default ->
                System.out.println("Type not found");
        }
    }

    public static void weekday(String day){
        switch (day){
            case "sunday", "saturday" -> System.out.println("WeekEnd");
            case "monday", "tuesday", "wednesday", "thursday", "friday" -> System.out.println("WeekDay");
            default -> System.out.println("wrong day");
        }
    }
    public static void main(String[] args) {
        patternMatchingSwitchCase("Test");
        patternMatchingSwitchCase(32.43);
        patternMatchingSwitchCase('c');

        weekday("wednesday");
        weekday("saturday");
    }
}
