package design_pattern.object_creation;

import java.util.ArrayList;
import java.util.List;

interface ClonableType<T>{
    T clone();
    //Object clone(); if we don't want to define class type then this is the option
}
class Employee implements ClonableType<Employee> {
    int id;
    String name;
    public Employee(int id, String name){
        this.id = id;
        this.name = name;
    }
    @Override
    public Employee clone() {
        return new Employee(this.id, this.name);
    }
}
class Dept implements ClonableType<Dept> {
    int dept;
    List<Employee> emps;
    public Dept(int dept, List<Employee> emps){
        this.dept = dept;
        this.emps = emps;
    }
    @Override
    public Dept clone() {
        List<Employee> empList = new ArrayList<>();
        this.emps.forEach(emp -> empList.add(emp.clone()));
        return new Dept(this.dept, empList);
    }
}

public class Prototype {

    public static void main(String[] args) {
        Employee emp = new Employee(5, "abc");
        Employee eClone = emp.clone();
        System.out.println(emp.name + " "+ emp.hashCode());
        System.out.println(eClone.name + " "+ eClone.hashCode());
        List list = new ArrayList<>();
        list.add(emp);
        list.add(eClone);
        Dept dept = new Dept(3, list);
        System.out.println(dept.dept+ " "+dept.emps.size()+" "+dept.hashCode());
        Dept dClone = dept.clone();
        System.out.println(dClone.dept+ " "+dClone.emps.size()+" "+dClone.hashCode());
    }
}
