package com.vin.java2503;

public class A001_Java8Features {
	public static void main(String[] args) {
//		List<String> names = Arrays.asList("Vikas","Thanulal","Vinayak");
//		ArrayList<String> names = new ArrayList<String>();
//		names.add("Vikas");
//		names.add("Thanulal");
//		names.add("Vinayak");
//		System.out.println("nameList : "+names);
//		//traditional way
//		System.out.println("Names : ");
//		for(String name : names) {
//			System.out.println(name + ", ");
//		}
//		//foreach+lambda
//		names.forEach(n -> System.out.println());
//		//foreach+methodReference
//		names.forEach(System.out::println);
		
//		//CustomFunctionalInterface
//		Operate op = (a,b) -> a+b;
//		int sum = op.sum(10, 20);
//		System.out.println(sum);
//		
//		//FunctionalInterface
//		//Predicate(()->ifCondition),Function(operation),Consumer(noReturn),Supplier(NoInput)
//		Predicate<Integer> pre = a -> a%2==0;
//		boolean test = pre.test(7);
//		System.out.println(test);
//		
//		Function<String, Integer> fun = s -> s.length();
//		Integer apply = fun.apply("Vikas");
//		System.out.println(apply);
//		
//		Function<Integer, Integer> fun2 = i -> i+2;
//		Integer apply2 = fun2.apply(8);
//		System.out.println(apply2);
//		
//		Consumer<String> con = s -> System.out.println(s.hashCode());
//		con.accept("Vikas");
//		
//		Supplier<Double> sup = () -> Math.random();
//		Double double1 = sup.get();
//		System.out.println(double1);
		
//		//default method inside Interface		
//		DefaultMethod d = () -> System.out.println("Functional method");;
//		d.print();
//		d.printHello();

//		//Date/Time Api
//		LocalDate now = LocalDate.now();
//		System.out.println(now);
//		LocalDateTime now2 = LocalDateTime.now();
//		System.out.println(now2);
		
//		//Stream API
//		List<String> cata = Arrays.asList("MenConstructorExample","Women","Lesb","Guy","Bisx","Trans","MenConstructorExample");
//		cata.forEach(System.out::println);
//		long count = cata.stream().count();
//		System.out.println("count->"+count);
//		List<String> filter = cata.stream().filter(o->o.contains("e")).collect(Collectors.toList());
//		System.out.println("filter->"+filter);
//		List<String> map = cata.stream().map(o->o+"").collect(Collectors.toList());
//		System.out.println("map->"+map);
//		List<String> filterMap = cata.stream().filter(str->str.contains("e")).map(str->str+"_conte").collect(Collectors.toList());
//		System.out.println("filterMap->"+filterMap);
//		List<String> sorted = cata.stream().sorted().collect(Collectors.toList());
//		System.out.println("sorted->"+sorted);
//		List<String> distinct = cata.stream().distinct().collect(Collectors.toList());
//		System.out.println("distinct->"+distinct);
//		List<String> limit = cata.stream().limit(2).collect(Collectors.toList());
//		System.out.println("limit"+limit);
//		//reduce:-commonly used to aggregate or combine elements into a single result,
//		Optional<String> reduce = cata.stream().reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2);
//		System.out.println("reduce"+reduce);
//		List<Integer> nums = Arrays.asList(1,4,5,3,2);
//		Integer reduce2 = nums.stream().reduce(0,Integer::sum);
//		System.out.println("reduce2->"+reduce2);
////		//Sequential Streams: Process elements in a sequential manner, one element at a time
////		//Parallel Streams: Process elements in parallel, utilizing multiple CPU cores.
//		nums.parallelStream().forEach(System.out::println);
	}
}

interface Operate{
	int sum(int a, int b);
}

interface DefaultMethod {
	void print();

	default void printHello() {
		System.out.println("Hello World");
	};
}