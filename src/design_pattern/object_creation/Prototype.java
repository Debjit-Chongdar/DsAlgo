package design_pattern.object_creation;

import java.util.ArrayList;
import java.util.List;

public class Prototype {
    interface Proto<T>{
        T clone();
        //Object clone(); if we don't want to define class type then this is the option
    }
    static class Emp implements Proto<Emp>{
        int id;
        String name;
        public Emp(int id, String name){
            this.id = id;
            this.name = name;
        }
        @Override
        public Emp clone() {
            return new Emp(this.id, this.name);
        }
    }
    static class Dept implements Proto<Dept>{
        int dept;
        List<Emp> emps;
        public Dept(int dept, List<Emp> emps){
            this.dept = dept;
            this.emps = emps;
        }
        @Override
        public Dept clone() {
            List<Emp> empList = new ArrayList<>();
            this.emps.forEach(emp -> empList.add(emp.clone()));
            return new Dept(this.dept, empList);
        }
    }

    public static void main(String[] args) {
        Emp emp = new Emp(5, "abc");
        Emp eClone = emp.clone();
        System.out.println(emp.name + " "+ emp.hashCode());
        System.out.println(eClone.name + " "+ eClone.hashCode());
        System.out.println(emp.name + " "+ emp.hashCode());
        List list = new ArrayList<>();
        list.add(emp);
        list.add(eClone);
        Dept dept = new Dept(3, list);
        System.out.println(dept.dept+ " "+dept.emps.size()+" "+dept.hashCode());
        Dept dClone = dept.clone();
        System.out.println(dClone.dept+ " "+dClone.emps.size()+" "+dClone.hashCode());
    }
}
