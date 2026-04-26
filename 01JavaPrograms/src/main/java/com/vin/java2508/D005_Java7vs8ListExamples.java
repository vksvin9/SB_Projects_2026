package com.vin.java2508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class D005_Java7vs8ListExamples {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Olivia", "Liam", "Sophia", "Noah", "Isabella", "Ethan");
        // 1. Print all names
        for (String n : names) System.out.print(n + " ");
        System.out.println(); // Output: Olivia Liam Sophia Noah Isabella Ethan
        names.forEach(System.out::print);
        System.out.println(); // Output: OliviaLiamSophiaNoahIsabellaEthan
        // 2. Filter names starting with 'S'
        List<String> s7 = new ArrayList<>();
        for (String n : names) if (n.startsWith("S")) s7.add(n);
        System.out.println("Java7 S: " + s7); // Output: Java7 S: [Sophia]
        List<String> s8 = names.stream().filter(n -> n.startsWith("S")).collect(Collectors.toList());
        System.out.println("Java8 S: " + s8); // Output: Java8 S: [Sophia]
        // 3. Convert names to uppercase
        List<String> up7 = new ArrayList<>();
        for (String n : names) up7.add(n.toUpperCase());
        System.out.println("Java7 Upper: " + up7); // Output: Java7 Upper: [OLIVIA, LIAM, SOPHIA, NOAH, ISABELLA, ETHAN]
        List<String> up8 = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Java8 Upper: " + up8); // Output: Java8 Upper: [OLIVIA, LIAM, SOPHIA, NOAH, ISABELLA, ETHAN]
        // 4. Sort names
        List<String> sort7 = new ArrayList<>(names);
        Collections.sort(sort7);
        System.out.println("Java7 Sorted: " + sort7); // Output: Java7 Sorted: [Ethan, Isabella, Liam, Noah, Olivia, Sophia]
        List<String> sort8 = names.stream().sorted().collect(Collectors.toList());
        System.out.println("Java8 Sorted: " + sort8); // Output: Java8 Sorted: [Ethan, Isabella, Liam, Noah, Olivia, Sophia]
        // 5. Reverse sort
        List<String> rev7 = new ArrayList<>(names);
        Collections.sort(rev7, Collections.reverseOrder());
        System.out.println("Java7 Reverse: " + rev7); // Output: Java7 Reverse: [Sophia, Olivia, Noah, Liam, Isabella, Ethan]
        List<String> rev8 = names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("Java8 Reverse: " + rev8); // Output: Java8 Reverse: [Sophia, Olivia, Noah, Liam, Isabella, Ethan]
        // 6. Find longest name
        String long7 = "";
        for (String n : names) if (n.length() > long7.length()) long7 = n;
        System.out.println("Java7 Longest: " + long7); // Output: Java7 Longest: Isabella
        String long8 = names.stream().max(Comparator.comparingInt(String::length)).get();
        System.out.println("Java8 Longest: " + long8); // Output: Java8 Longest: Isabella
        // 7. Find shortest name
        String short7 = names.get(0);
        for (String n : names) if (n.length() < short7.length()) short7 = n;
        System.out.println("Java7 Shortest: " + short7); // Output: Java7 Shortest: Liam
        String short8 = names.stream().min(Comparator.comparingInt(String::length)).get();
        System.out.println("Java8 Shortest: " + short8); // Output: Java8 Shortest: Liam
        // 8. Count names
        System.out.println("Java7 Count: " + names.size()); // Output: Java7 Count: 6
        long count8 = names.stream().count();
        System.out.println("Java8 Count: " + count8); // Output: Java8 Count: 6
        // 9. Distinct characters in all names
        Set<Character> set7 = new HashSet<>();
        for (String n : names) for (char c : n.toCharArray()) set7.add(c);
        System.out.println("Java7 DistinctChars: " + set7.size()); // Output: Java7 DistinctChars: 17
        long set8 = names.stream().flatMapToInt(String::chars).mapToObj(c -> (char) c).distinct().count();
        System.out.println("Java8 DistinctChars: " + set8); // Output: Java8 DistinctChars: 17
        // 10. Join names with comma
        StringBuilder sb7 = new StringBuilder();
        for (String n : names) sb7.append(n).append(",");
        System.out.println("Java7 Join: " + sb7.substring(0, sb7.length() - 1)); // Output: Java7 Join: Olivia,Liam,Sophia,Noah,Isabella,Ethan
        String join8 = String.join(",", names);
        System.out.println("Java8 Join: " + join8); // Output: Java8 Join: Olivia,Liam,Sophia,Noah,Isabella,Ethan
        // 11. Any name starts with "I"
        boolean any7 = false;
        for (String n : names) if (n.startsWith("I")) any7 = true;
        System.out.println("Java7 Any I: " + any7); // Output: Java7 Any I: true
        boolean any8 = names.stream().anyMatch(n -> n.startsWith("I"));
        System.out.println("Java8 Any I: " + any8); // Output: Java8 Any I: true
        // 12. All names start with capital letter
        boolean all7 = true;
        for (String n : names) if (!Character.isUpperCase(n.charAt(0))) all7 = false;
        System.out.println("Java7 AllCap: " + all7); // Output: Java7 AllCap: true
        boolean all8 = names.stream().allMatch(n -> Character.isUpperCase(n.charAt(0)));
        System.out.println("Java8 AllCap: " + all8); // Output: Java8 AllCap: true
        // 13. None name starts with Z
        boolean none7 = true;
        for (String n : names) if (n.startsWith("Z")) none7 = false;
        System.out.println("Java7 None Z: " + none7); // Output: Java7 None Z: true
        boolean none8 = names.stream().noneMatch(n -> n.startsWith("Z"));
        System.out.println("Java8 None Z: " + none8); // Output: Java8 None Z: true
        // 14. First element
        System.out.println("Java7 First: " + names.get(0)); // Output: Java7 First: Olivia
        System.out.println("Java8 First: " + names.stream().findFirst().get()); // Output: Java8 First: Olivia
        // 15. Last element
        System.out.println("Java7 Last: " + names.get(names.size() - 1)); // Output: Java7 Last: Ethan
        System.out.println("Java8 Last: " + names.stream().skip(names.size() - 1).findFirst().get()); // Output: Java8 Last: Ethan
        // 16. Convert names to lengths
        List<Integer> len7 = new ArrayList<>();
        for (String n : names) len7.add(n.length());
        System.out.println("Java7 Lengths: " + len7); // Output: Java7 Lengths: [6, 4, 6, 4, 8, 5]
        List<Integer> len8 = names.stream().map(String::length).collect(Collectors.toList());
        System.out.println("Java8 Lengths: " + len8); // Output: Java8 Lengths: [6, 4, 6, 4, 8, 5]
        // 17. Square of lengths
        List<Integer> sq7 = new ArrayList<>();
        for (String n : names) sq7.add(n.length() * n.length());
        System.out.println("Java7 LenSquare: " + sq7); // Output: Java7 LenSquare: [36, 16, 36, 16, 64, 25]
        List<Integer> sq8 = names.stream().map(n -> n.length() * n.length()).collect(Collectors.toList());
        System.out.println("Java8 LenSquare: " + sq8); // Output: Java8 LenSquare: [36, 16, 36, 16, 64, 25]
        // 18. Partition names length > 5
        Map<Boolean, List<String>> part7 = new HashMap<>();
        part7.put(true, new ArrayList<>());
        part7.put(false, new ArrayList<>());
        for (String n : names) part7.get(n.length() > 5).add(n);
        System.out.println("Java7 Partition: " + part7); // Output: Java7 Partition: {false=[Liam, Noah, Ethan], true=[Olivia, Sophia, Isabella]}
        Map<Boolean, List<String>> part8 = names.stream().collect(Collectors.partitioningBy(n -> n.length() > 5));
        System.out.println("Java8 Partition: " + part8); // Output: Java8 Partition: {false=[Liam, Noah, Ethan], true=[Olivia, Sophia, Isabella]}
        // 19. Group by length
        Map<Integer, List<String>> group7 = new HashMap<>();
        for (String n : names) group7.computeIfAbsent(n.length(), k -> new ArrayList<>()).add(n);
        System.out.println("Java7 Group: " + group7); // Output: Java7 Group: {4=[Liam, Noah], 5=[Ethan], 6=[Olivia, Sophia], 8=[Isabella]}
        Map<Integer, List<String>> group8 = names.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("Java8 Group: " + group8); // Output: Java8 Group: {6=[Olivia, Sophia], 4=[Liam, Noah], 8=[Isabella], 5=[Ethan]}
        // 20. Name lengths summary
        IntSummaryStatistics stat7 = new IntSummaryStatistics();
        for (String n : names) stat7.accept(n.length());
        System.out.println("Java7 Summary: " + stat7); // Output: Java7 Summary: IntSummaryStatistics{count=6, sum=33, min=4, average=5.500000, max=8}
        IntSummaryStatistics stat8 = names.stream().mapToInt(String::length).summaryStatistics();
        System.out.println("Java8 Summary: " + stat8); // Output: Java8 Summary: IntSummaryStatistics{count=6, sum=33, min=4, average=5.500000, max=8}
        // 21. Shuffle names
        List<String> shuf7 = new ArrayList<>(names);
        Collections.shuffle(shuf7);
        System.out.println("Java7 Shuffle: " + shuf7); // Output: Random order e.g. [Sophia, Ethan, Olivia, Noah, Liam, Isabella]
        List<String> shuf8 = new ArrayList<>(names);
        Collections.shuffle(shuf8);
        System.out.println("Java8 Shuffle: " + shuf8); // Output: Random order e.g. [Noah, Liam, Ethan, Isabella, Olivia, Sophia]
        // 22. Remove names < 5 chars
        List<String> rem7 = new ArrayList<>(names);
        java.util.Iterator<String> it = rem7.iterator();
        while (it.hasNext()) if (it.next().length() < 5) it.remove();
        System.out.println("Java7 Remove: " + rem7); // Output: Java7 Remove: [Olivia, Sophia, Isabella, Ethan]
        List<String> rem8 = new ArrayList<>(names);
        rem8.removeIf(n -> n.length() < 5);
        System.out.println("Java8 Remove: " + rem8); // Output: Java8 Remove: [Olivia, Sophia, Isabella, Ethan]
        // 23. Find index of element
        System.out.println("Java7 IndexOf Noah: " + names.indexOf("Noah")); // Output: Java7 IndexOf Noah: 3
        int idx8 = IntStream.range(0, names.size()).filter(i -> names.get(i).equals("Noah")).findFirst().orElse(-1);
        System.out.println("Java8 IndexOf Noah: " + idx8); // Output: Java8 IndexOf Noah: 3
        // 24. Reverse list
        List<String> revList7 = new ArrayList<>(names);
        Collections.reverse(revList7);
        System.out.println("Java7 ReverseList: " + revList7); // Output: Java7 ReverseList: [Ethan, Isabella, Noah, Sophia, Liam, Olivia]
        List<String> revList8 = IntStream.rangeClosed(1, names.size()).mapToObj(i -> names.get(names.size() - i)).collect(Collectors.toList());
        System.out.println("Java8 ReverseList: " + revList8); // Output: Java8 ReverseList: [Ethan, Isabella, Noah, Sophia, Liam, Olivia]
        // 25. Find duplicates
        Set<String> seen = new HashSet<>();
        Set<String> dup7 = new HashSet<>();
        for (String n : names) if (!seen.add(n)) dup7.add(n);
        System.out.println("Java7 Duplicates: " + dup7); // Output: Java7 Duplicates: []
        Set<String> dup8 = names.stream().filter(i -> Collections.frequency(names, i) > 1).collect(Collectors.toSet());
        System.out.println("Java8 Duplicates: " + dup8); // Output: Java8 Duplicates: []
        // 26. Convert to Set
        Set<String> set71 = new HashSet<>(names);
        System.out.println("Java7 ToSet: " + set71); // Output: Java7 ToSet: [Sophia, Olivia, Noah, Liam, Isabella, Ethan]
        Set<String> set81 = names.stream().collect(Collectors.toSet());
        System.out.println("Java8 ToSet: " + set81); // Output: Java8 ToSet: [Sophia, Olivia, Noah, Liam, Isabella, Ethan]
        // 27. Convert to Map (index->name)
        Map<Integer, String> map7 = new HashMap<>();
        for (int i = 0; i < names.size(); i++) map7.put(i, names.get(i));
        System.out.println("Java7 ToMap: " + map7); // Output: Java7 ToMap: {0=Olivia, 1=Liam, 2=Sophia, 3=Noah, 4=Isabella, 5=Ethan}
        Map<Integer, String> map8 = IntStream.range(0, names.size()).boxed().collect(Collectors.toMap(i -> i, names::get));
        System.out.println("Java8 ToMap: " + map8); // Output: Java8 ToMap: {0=Olivia, 1=Liam, 2=Sophia, 3=Noah, 4=Isabella, 5=Ethan}
        // 28. Parallel processing
        System.out.print("Java7 Parallel: ");
        for (String n : names) System.out.print(n.toUpperCase() + " ");
        System.out.println(); // Output: Java7 Parallel: OLIVIA LIAM SOPHIA NOAH ISABELLA ETHAN 
        System.out.print("Java8 Parallel: ");
        names.parallelStream().forEach(n -> System.out.print(n.toUpperCase() + " "));
        System.out.println(); // Output: Java8 Parallel: (order may vary) e.g. OLIVIA SOPHIA LIAM ETHAN NOAH ISABELLA
        // 29. Find second element
        System.out.println("Java7 Second: " + names.get(1)); // Output: Java7 Second: Liam
        String second8 = names.stream().skip(1).findFirst().get();
        System.out.println("Java8 Second: " + second8); // Output: Java8 Second: Liam
        // 30. Limit first 3 names
        List<String> limit7 = names.subList(0, 3);
        System.out.println("Java7 Limit: " + limit7); // Output: Java7 Limit: [Olivia, Liam, Sophia]
        List<String> limit8 = names.stream().limit(3).collect(Collectors.toList());
        System.out.println("Java8 Limit: " + limit8); // Output: Java8 Limit: [Olivia, Liam, Sophia]
        // 31. Skip first 2 names
        List<String> skip7 = names.subList(2, names.size());
        System.out.println("Java7 Skip: " + skip7); // Output: Java7 Skip: [Sophia, Noah, Isabella, Ethan]
        List<String> skip8 = names.stream().skip(2).collect(Collectors.toList());
        System.out.println("Java8 Skip: " + skip8); // Output: Java8 Skip: [Sophia, Noah, Isabella, Ethan]
        // 32. Find name containing 'a'
        String found7 = null;
        for (String n : names) if (n.contains("a")) { found7 = n; break; }
        System.out.println("Java7 Found a: " + found7); // Output: Java7 Found a: Olivia
        String found8 = names.stream().filter(n -> n.contains("a")).findFirst().orElse(null);
        System.out.println("Java8 Found a: " + found8); // Output: Java8 Found a: Olivia
        // 33. Names in uppercase & joined by space
        String upJoin7 = "";
        for (String n : names) upJoin7 += n.toUpperCase() + " ";
        System.out.println("Java7 UpJoin: " + upJoin7.trim()); // Output: Java7 UpJoin: OLIVIA LIAM SOPHIA NOAH ISABELLA ETHAN
        String upJoin8 = names.stream().map(String::toUpperCase).collect(Collectors.joining(" "));
        System.out.println("Java8 UpJoin: " + upJoin8); // Output: Java8 UpJoin: OLIVIA LIAM SOPHIA NOAH ISABELLA ETHAN
        // 34. Find average length
        int totalLen7 = 0;
        for (String n : names) totalLen7 += n.length();
        double avg7 = totalLen7 / (double) names.size();
        System.out.println("Java7 AvgLen: " + avg7); // Output: Java7 AvgLen: 5.5
        double avg8 = names.stream().collect(Collectors.averagingInt(String::length));
        System.out.println("Java8 AvgLen: " + avg8); // Output: Java8 AvgLen: 5.5
        // 35. Replace all names with lower case
        List<String> low7 = new ArrayList<>();
        for (String n : names) low7.add(n.toLowerCase());
        System.out.println("Java7 Lower: " + low7); // Output: Java7 Lower: [olivia, liam, sophia, noah, isabella, ethan]
        List<String> low8 = names.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println("Java8 Lower: " + low8); // Output: Java8 Lower: [olivia, liam, sophia, noah, isabella, ethan]
        // 36. Check if "Emma" exists
        boolean ex7 = false;
        for (String n : names) if (n.equals("Emma")) ex7 = true;
        System.out.println("Java7 Exists Emma: " + ex7); // Output: Java7 Exists Emma: false
        boolean ex8 = names.contains("Emma");
        System.out.println("Java8 Exists Emma: " + ex8); // Output: Java8 Exists Emma: false
        // 37. Sort by length
        List<String> sortLen7 = new ArrayList<>(names);
        Collections.sort(sortLen7, Comparator.comparingInt(String::length));
        System.out.println("Java7 SortLen: " + sortLen7); // Output: Java7 SortLen: [Liam, Noah, Ethan, Olivia, Sophia, Isabella]
        List<String> sortLen8 = names.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        System.out.println("Java8 SortLen: " + sortLen8); // Output: Java8 SortLen: [Liam, Noah, Ethan, Olivia, Sophia, Isabella]
        // 38. Names with length between 4 and 6
        List<String> mid7 = new ArrayList<>();
        for (String n : names) if (n.length() >= 4 && n.length() <= 6) mid7.add(n);
        System.out.println("Java7 Len4-6: " + mid7); // Output: Java7 Len4-6: [Olivia, Liam, Sophia, Noah, Ethan]
        List<String> mid8 = names.stream().filter(n -> n.length() >= 4 && n.length() <= 6).collect(Collectors.toList());
        System.out.println("Java8 Len4-6: " + mid8); // Output: Java8 Len4-6: [Olivia, Liam, Sophia, Noah, Ethan]
        // 39. Convert list to array
        String[] arr7 = names.toArray(new String[0]);
        System.out.println("Java7 Array: " + Arrays.toString(arr7)); // Output: Java7 Array: [Olivia, Liam, Sophia, Noah, Isabella, Ethan]
        String[] arr8 = names.stream().toArray(String[]::new);
        System.out.println("Java8 Array: " + Arrays.toString(arr8)); // Output: Java8 Array: [Olivia, Liam, Sophia, Noah, Isabella, Ethan]
        // 40. Random pick one name
        Random rand = new Random();
        String rand7 = names.get(rand.nextInt(names.size()));
        System.out.println("Java7 Random: " + rand7); // Output: Java7 Random: (any name from the list)
        String rand8 = names.stream().skip(rand.nextInt(names.size())).findFirst().get();
        System.out.println("Java8 Random: " + rand8); // Output: Java8 Random: (any name from the list)
        // 41. Print each name with index
        for (int i = 0; i < names.size(); i++) System.out.println("Java7 Index " + i + ": " + names.get(i));
        // Output: Java7 Index 0: Olivia ... Java7 Index 5: Ethan
        IntStream.range(0, names.size()).forEach(i -> System.out.println("Java8 Index " + i + ": " + names.get(i)));
        // Output: Java8 Index 0: Olivia ... Java8 Index 5: Ethan
        // 42. Remove duplicates from list
        List<String> dupeList7 = new ArrayList<>(names);
        Set<String> uniq7 = new LinkedHashSet<>(dupeList7);
        System.out.println("Java7 Unique: " + uniq7); // Output: Java7 Unique: [Olivia, Liam, Sophia, Noah, Isabella, Ethan]
        List<String> uniq8 = names.stream().distinct().collect(Collectors.toList());
        System.out.println("Java8 Unique: " + uniq8); // Output: Java8 Unique: [Olivia, Liam, Sophia, Noah, Isabella, Ethan]
        // 43. Repeat each name twice
        List<String> twice7 = new ArrayList<>();
        for (String n : names) { twice7.add(n); twice7.add(n); }
        System.out.println("Java7 Twice: " + twice7); // Output: Java7 Twice: [Olivia, Olivia, Liam, Liam, Sophia, Sophia, Noah, Noah, Isabella, Isabella, Ethan, Ethan]
        List<String> twice8 = names.stream().flatMap(n -> Stream.of(n, n)).collect(Collectors.toList());
        System.out.println("Java8 Twice: " + twice8); // Output: Java8 Twice: [Olivia, Olivia, Liam, Liam, Sophia, Sophia, Noah, Noah, Isabella, Isabella, Ethan, Ethan]
        // 44. Take last 3 names
        List<String> last37 = names.subList(names.size() - 3, names.size());
        System.out.println("Java7 Last3: " + last37); // Output: Java7 Last3: [Noah, Isabella, Ethan]
        List<String> last38 = IntStream.range(names.size() - 3, names.size()).mapToObj(names::get).collect(Collectors.toList());
        System.out.println("Java8 Last3: " + last38); // Output: Java8 Last3: [Noah, Isabella, Ethan]
        // 45. Find all names ending with 'a'
        List<String> end7 = new ArrayList<>();
        for (String n : names) if (n.endsWith("a")) end7.add(n);
        System.out.println("Java7 End a: " + end7); // Output: Java7 End a: [Olivia, Sophia, Isabella]
        List<String> end8 = names.stream().filter(n -> n.endsWith("a")).collect(Collectors.toList());
        System.out.println("Java8 End a: " + end8); // Output: Java8 End a: [Olivia, Sophia, Isabella]
        // 46. Convert to LinkedList
        List<String> link7 = new LinkedList<>(names);
        System.out.println("Java7 LinkedList: " + link7); // Output: Java7 LinkedList: [Olivia, Liam, Sophia, Noah, Isabella, Ethan]
        List<String> link8 = names.stream().collect(Collectors.toCollection(LinkedList::new));
        System.out.println("Java8 LinkedList: " + link8); // Output: Java8 LinkedList: [Olivia, Liam, Sophia, Noah, Isabella, Ethan]
        // 47. Parallel uppercase join
        StringBuilder sb47 = new StringBuilder();
        names.parallelStream().forEach(n -> sb47.append(n.toUpperCase()).append(" "));
        System.out.println("Java7 Parallel style: (not available)"); // Output: Java7 Parallel style: (not available)
        System.out.println("Java8 ParallelJoin: " + sb47.toString().trim()); // Output: Java8 ParallelJoin: OLIVIA LIAM SOPHIA NOAH ISABELLA ETHAN (order may vary)
        // 48. Find longest name length only
        int maxLen7 = 0;
        for (String n : names) maxLen7 = Math.max(maxLen7, n.length());
        System.out.println("Java7 MaxLen: " + maxLen7); // Output: Java7 MaxLen: 8
        int maxLen8 = names.stream().mapToInt(String::length).max().getAsInt();
        System.out.println("Java8 MaxLen: " + maxLen8); // Output: Java8 MaxLen: 8
        // 49. Find name with second longest length
        List<String> sortLenDesc7 = new ArrayList<>(names);
        sortLenDesc7.sort((a, b) -> b.length() - a.length());
        String secLong7 = sortLenDesc7.get(1);
        System.out.println("Java7 2nd Longest: " + secLong7); // Output: Java7 2nd Longest: Olivia
        String secLong8 = names.stream().sorted(Comparator.comparingInt(String::length).reversed()).skip(1).findFirst().get();
        System.out.println("Java8 2nd Longest: " + secLong8); // Output: Java8 2nd Longest: Olivia
        // 50. Flatten nested list
        List<List<String>> nested = Arrays.asList(Arrays.asList("A", "B"), Arrays.asList("C", "D"));
        List<String> flat7 = new ArrayList<>();
        for (List<String> sub : nested) flat7.addAll(sub);
        System.out.println("Java7 Flatten: " + flat7); // Output: Java7 Flatten: [A, B, C, D]
        List<String> flat8 = nested.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println("Java8 Flatten: " + flat8); // Output: Java8 Flatten: [A, B, C, D]
    }
}