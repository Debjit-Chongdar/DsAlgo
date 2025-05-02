package design_pattern;

import java.util.ArrayList;
import java.util.List;

interface Iterate<T> {
    boolean hasNext();

    T next();
}

class Students {
    private List<String> students = new ArrayList<>();

    public void add(String name) {
        this.students.add(name);
    }

    public Iterate<String> getIterator() {
        return new StudentIterator();
    }

    class StudentIterator implements Iterate<String> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return students.size() > index;
        }

        @Override
        public String next() {
            return students.get(index++);
        }
    }
}

public class Iterator {


    public static void main(String[] args) {
        Students students = new Students();
        students.add("abc");
        students.add("acb");
        students.add("bac");
        students.add("bca");
        Iterate iterater = students.getIterator();
        while (iterater.hasNext()) {
            System.out.println(iterater.next());
        }
        System.out.println("Add cab & cba");
        students.add("cab");
        students.add("cba");
        Iterate iterater1 = students.getIterator();
        while (iterater1.hasNext()) {
            System.out.println(iterater1.next());
        }
    }
}
