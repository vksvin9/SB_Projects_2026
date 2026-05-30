package com.vin.java2604;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Person {
	String name;
	int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return name + " (" + age + ")";
	}
}

class AgeComparator implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		return Integer.compare(p1.age, p2.age);
	}
}

public class A06Comparator {

	public static void main(String[] args) {

		List<Person> list = new ArrayList<>();
		list.add(new Person("John", 25));
		list.add(new Person("Alice", 30));
		list.add(new Person("Bob", 20));

		// 1. Java 7 Comparator 

		Collections.sort(list, new AgeComparator());
		System.out.println("Java 7 - Sorted by age: " + list);
		// [Bob (20), John (25), Alice (30)]

		Collections.sort(list, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.name.compareTo(p2.name);
			}
		});
		System.out.println("Java 7 - Sorted by name: " + list);
		// [Alice (30), Bob (20), John (25)]

		// 2. Java 8 Stream API 
		List<Person> sortedByNameStream = list.stream().sorted(Comparator.comparing(p -> p.name))
				.collect(Collectors.toList());
		System.out.println("Java 8 Stream - Sorted by name: " + sortedByNameStream);

		List<Person> sortedByAgeStream = list.stream().sorted(Comparator.comparingInt(p -> p.age))
				.collect(Collectors.toList());
		System.out.println("Java 8 Stream - Sorted by age: " + sortedByAgeStream);

		// 3. Lambda Comparator 
		list.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
		System.out.println("Lambda - Sorted by age: " + list);

		// 4. Method Reference 
		list.sort(Comparator.comparing(Person::getName));
		System.out.println("Sorted by name: " + list);

		list.sort(Comparator.comparing(Person::getAge));
		System.out.println("Sorted by age: " + list);

		// 5. Reverse sorting
		list.sort(Comparator.comparing(Person::getAge).reversed());
		System.out.println("Age Desc: " + list);
		// [Alice (30), John (25), Bob (20)]

		// 6. Manual descending comparator
		list.sort((p1, p2) -> Integer.compare(p2.getAge(), p1.getAge()));
		System.out.println("Age Desc (manual): " + list);
		// [Alice (30), John (25), Bob (20)]

		// 7. Multi-field sorting (name → age)
		list.sort(Comparator.comparing(Person::getName).thenComparing(Person::getAge));
		System.out.println("Name + Age: " + list);
		// sorted by name, then age

		// 8. Comparator chaining + reversed
		list.sort(Comparator.comparing(Person::getAge).thenComparing(Person::getName).reversed());
		System.out.println("Age+Name Desc: " + list);

		// 9. Null handling (important)
		list.add(new Person(null, 40));
		list.sort(Comparator.comparing(Person::getName, Comparator.nullsLast(String::compareTo)));
		System.out.println("Null-safe name sort: " + list);

		// 10. Collections.sort with lambda (hybrid style)
		Collections.sort(list, (p1, p2) -> p1.getName() == null ? 1
				: p2.getName() == null ? -1 : p1.getName().compareTo(p2.getName()));
		System.out.println("Collections.sort lambda: " + list);
	}
}