package design_pattern;

import java.util.ArrayList;
import java.util.List;

public class Iterator {
    interface Iterate<T>{
        boolean hasNext();
        T next();
    }
    static class Students implements Iterate{
        private List<String> students;
        int index;
        public Students(){
            students = new ArrayList<>();
            index = 0;
        }
        public void add(String name){
            this.students.add(name);
        }
        @Override
        public boolean hasNext() {
            return students.size() > index;
        }
        @Override
        public Object next() {
            return students.get(index++);
        }
    }

    public static void main(String[] args) {
        Students students = new Students();
        students.add("abc");
        students.add("acb");
        students.add("bac");
        students.add("bca");
        System.out.println(students.hasNext());
        System.out.println(students.next());
        System.out.println(students.hasNext());
        System.out.println(students.next());
        System.out.println(students.hasNext());
        System.out.println(students.next());
        students.add("cab");
        students.add("cba");
        System.out.println(students.hasNext());
        System.out.println(students.next());
        System.out.println(students.hasNext());
        System.out.println(students.next());
        System.out.println(students.hasNext());
        System.out.println(students.next());
        System.out.println(students.hasNext());
        System.out.println(students.next());
    }
}
