package com.vin.java2503;

import java.util.List;

public class A004_Java8_Collection {

    public static void main(String[] args) {

//      forEach(Consumer)
//      filter(Predicate)
//      collect(Collector)
//      map(Function)
//      distinct()
//      flatMap(Function)
//      sorted(Comparator both ASC and DESC)
//      min() & max()
//      GroupBy
//      findFirst()
//      findAny()
//      anyMatch(Predicate)
//      allMatch(Predicate)
//      noneMatch(Predicate)
//      limit(long maxSize)
//      skip(long n)

        List<A004a_Employee> a004a_Employees = A004c_EmployeeDataBase.getAllEmployees();

//        //forEach
//        employees.forEach(e-> System.out.println(e.getName()+" : "+e.getSalary()));
//        employees.stream().forEach(System.out::println);

//        //filter
//        //.collect
//        Map<Integer, String> developmentEmployees = employees.stream()
//                .filter(e -> e.getDept().equals("Development") && e.getSalary() > 80000)
//                .collect(Collectors.toMap(A004a_Employee::getId, A004a_Employee::getName));
//        System.out.println(developmentEmployees);

//        //map
//        //distinct
//        List<String> depts = employees.stream()
//                .map(A004a_Employee::getDept)
//                .distinct()
//                .collect(Collectors.toList());
//        System.out.println(depts);

//        //JavaObject
//        List<Stream<String>> projectNames = employees.stream()
//                .map(e -> e.getProjects().stream()
//                		.map(p -> p.getName())).collect(Collectors.toList());
//        System.out.println(projectNames);
//        //***flatMap***
//        List<String> projects = employees.stream()
//                .flatMap(e -> e.getProjects().stream())
//                .map(p -> p.getName()).distinct()
//                .collect(Collectors.toList());
//        System.out.println(projects);


//        //sorted
//        //asc
//        List<A004a_Employee> ascSortedEmployees = employees.stream()
//                .sorted(Comparator.comparing(A004a_Employee::getSalary))
//                .collect(Collectors.toList());
//        ascSortedEmployees.forEach(System.out::println);
//        A004a_Employee lowestPayEmployee = ascSortedEmployees.get(0);
//        System.out.println(lowestPayEmployee);

//        //desc
//        List<A004a_Employee> descSortedEmployees = employees.stream()
//                .sorted(Collections.reverseOrder(Comparator.comparing(A004a_Employee::getSalary)))
//                .collect(Collectors.toList());
//        descSortedEmployees.forEach(System.out::println);
//      A004a_Employee highestPayEmployee = descSortedEmployees.get(0);
//      System.out.println(highestPayEmployee);

//        //min & max
//        Optional<A004a_Employee> highestPaidEmployees = employees.stream()
//                .max(Comparator.comparingDouble(A004a_Employee::getSalary));
//        System.out.println("Highest paid employee : "+highestPaidEmployees);
//        Optional<A004a_Employee> lowestPaidEmployees = employees.stream()
//                .min(Comparator.comparingDouble(A004a_Employee::getSalary));
//        System.out.println("Lowest paid employee : "+lowestPaidEmployees);

//        //groupingBy
//        Map<String, List<A004a_Employee>> employeeGroup = employees.stream()
//                .collect(Collectors.groupingBy(A004a_Employee::getGender));
//        System.out.println(employeeGroup);

//        //Gender -> [names]
//        Map<String, List<String>> employeeGroupNames = employees.stream()
//                .collect(Collectors.groupingBy(A004a_Employee::getGender,
//                        Collectors.mapping(A004a_Employee::getName, Collectors.toList())
//                ));
//        System.out.println(employeeGroupNames);

//        //Gender -> [count]
//        Map<String, Long> employeeGroupCountMap = employees.stream()
//                .collect(Collectors.groupingBy(A004a_Employee::getGender, Collectors.counting()));
//        System.out.println(employeeGroupCountMap);

//        //findFirst
//        A004a_Employee findFirstElement = employees.stream()
//                .filter(e -> e.getDept().equals("Development"))
//                .findFirst()
//                .orElseThrow(()->new IllegalArgumentException("A004a_Employee not found "));
//        System.out.println(findFirstElement.get());//NPE
//        if(findFirstElement.isPresent()){
//            System.out.println(findFirstElement.get());
//        }
//        findFirstElement.ifPresent(e-> System.out.println(e.getName()));
//        System.out.println(findFirstElement);

//        //findAny
//        A004a_Employee findAnyElement = employees.stream()
//                .filter(e -> e.getDept().equals("Development"))
//                .findAny()
//                .orElseThrow(()->new IllegalArgumentException("A004a_Employee not found "));
//        System.out.println(findAnyElement);

//        //anyMatch(Predicate)
//        boolean developmentEmpAnyMatch = employees.stream()
//                .anyMatch(e -> e.getDept().equals("Development"));
//        System.out.println("is there any employee match from development dept "+developmentEmpAnyMatch);
//        //allMatch(Predicate)
//        boolean developmentEmpAllMatch = employees.stream()
//                .allMatch(e -> e.getSalary()>50000);//55000
//        System.out.println(developmentEmpAllMatch); //false
//        //noneMatch(Predicate)
//        boolean isNoneMatch = employees.stream()
//                .noneMatch(e -> e.getDept().equals("abc"));
//        System.out.println(isNoneMatch);

//        //limit(long)
//        List<A004a_Employee> topPaidEmployees = employees.stream()
//                .sorted(Comparator.comparing(A004a_Employee::getSalary).reversed())
//                .limit(4)
//                .collect(Collectors.toList());
//        topPaidEmployees.forEach(e-> System.out.println(e.getName()));

//        //skip(long)
//        List<A004a_Employee> skipEmployees = employees.stream().skip(10)
//                .collect(Collectors.toList());
//        System.out.println(skipEmployees);

    }
}
