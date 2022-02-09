package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {
        Department software = new Department("Software", 1);

        Employee sample = new Employee();
        Employee ashish = new Employee(sample);
        ashish.setName("Ashish");
        ashish.setId(007);

        Employee mark = new Employee(sample);
        mark.setName("Mark");
        mark.setId(8);


        System.out.println("");

    }
}
