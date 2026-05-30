package com.vin.java2508;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class D009_HMJava78 {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Olivia");
        map.put(2, "Liam");
        map.put(3, "Sophia");
        map.put(4, "Noah");
        map.put(5, "Isabella");
        map.put(6, "Ethan");
        // 1. Iterate over map
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Java7 Iterate: " + entry.getKey() + " -> " + entry.getValue());
            // Output example: Java7 Iterate: 1 -> Olivia (order may vary)
        }
        map.forEach((k, v) -> System.out.println("Java8 Iterate: " + k + " -> " + v));
        // Output example: Java8 Iterate: 1 -> Olivia (order may vary)
        // 2. Check if key exists
        if (map.containsKey(1)) System.out.println("Java7 ContainsKey 1: true"); // true
        boolean ck8 = map.containsKey(1);
        System.out.println("Java8 ContainsKey 1: " + ck8); // true
        // 3. Check if value exists
        if (map.containsValue("Sophia")) System.out.println("Java7 ContainsValue Sophia: true"); // true
        boolean cv8 = map.containsValue("Sophia");
        System.out.println("Java8 ContainsValue Sophia: " + cv8); // true
        // 4. Get with default
        String v7 = map.containsKey(10) ? map.get(10) : "Default";
        System.out.println("Java7 GetOrDefault: " + v7); // Default
        String v8 = map.getOrDefault(10, "Default");
        System.out.println("Java8 GetOrDefault: " + v8); // Default
        // 5. Replace value
        if (map.containsKey(2)) map.put(2, "UpdatedLiam");
        System.out.println("Java7 Replace: " + map); // 2=UpdatedLiam
        map.replace(2, "ReplacedLiam");
        System.out.println("Java8 Replace: " + map); // 2=ReplacedLiam
        // 6. ReplaceAll values
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            e.setValue(e.getValue().toUpperCase());
        }
        System.out.println("Java7 ReplaceAll: " + map); // all values uppercase
        map.replaceAll((k, v) -> v.toLowerCase());
        System.out.println("Java8 ReplaceAll: " + map); // all values lowercase
        // 7. ComputeIfAbsent
        if (!map.containsKey(7)) map.put(7, "Generated");
        System.out.println("Java7 computeIfAbsent: " + map); // adds 7
        map.computeIfAbsent(8, k -> "Gen" + k);
        System.out.println("Java8 computeIfAbsent: " + map); // adds 8
        // 8. ComputeIfPresent
        if (map.containsKey(1)) map.put(1, map.get(1) + "_NEW");
        System.out.println("Java7 computeIfPresent: " + map); // 1=olivia_new
        map.computeIfPresent(1, (k, v) -> v + "_X");
        System.out.println("Java8 computeIfPresent: " + map); // 1=olivia_new_X
        // 9. Compute
        map.put(9, "Test");
        map.put(9, map.get(9) + "_Comp");
        System.out.println("Java7 compute: " + map); // 9=Test_Comp
        map.compute(9, (k, v) -> v + "_C");
        System.out.println("Java8 compute: " + map); // 9=Test_Comp_C
        // 10. Merge values
        if (map.containsKey(1)) map.put(1, map.get(1) + "MERGE");
        System.out.println("Java7 Merge: " + map); // 1=olivia_new_XMERGE
        map.merge(1, "M", (oldVal, newVal) -> oldVal + newVal);
        System.out.println("Java8 Merge: " + map); // 1=olivia_new_XMERGEM
        // 11. Remove by key
        map.remove(6);
        System.out.println("Java7 Remove: " + map); // removes 6
        map.put(6, "Ethan");
        map.remove(6, "Ethan");
        System.out.println("Java8 Remove(key,value): " + map); // removes 6
        // 12. Size
        System.out.println("Java7 Size: " + map.size()); // current size
        long size8 = map.entrySet().stream().count();
        System.out.println("Java8 Size: " + size8); // current size
        // 13. Empty check
        System.out.println("Java7 Empty: " + map.isEmpty()); // false
        boolean empty8 = map.entrySet().stream().count() == 0;
        System.out.println("Java8 Empty: " + empty8); // false
        // 14. Keys to list
        List<Integer> keys7 = new ArrayList<>(map.keySet());
        System.out.println("Java7 Keys: " + keys7); // list of keys
        List<Integer> keys8 = map.keySet().stream().collect(Collectors.toList());
        System.out.println("Java8 Keys: " + keys8); // list of keys
        // 15. Values to list
        List<String> vals7 = new ArrayList<>(map.values());
        System.out.println("Java7 Values: " + vals7); // list of values
        List<String> vals8 = map.values().stream().collect(Collectors.toList());
        System.out.println("Java8 Values: " + vals8); // list of values
        // 16. Filter by value
        List<String> filter7 = new ArrayList<>();
        for (String s : map.values()) if (s.startsWith("o")) filter7.add(s);
        System.out.println("Java7 Filter: " + filter7); // values starting with 'o'
        List<String> filter8 = map.values().stream().filter(s -> s.startsWith("o")).collect(Collectors.toList());
        System.out.println("Java8 Filter: " + filter8); // values starting with 'o'
        // 17. Sorting keys
        List<Integer> sort7 = new ArrayList<>(map.keySet());
        Collections.sort(sort7);
        System.out.println("Java7 Sort keys: " + sort7); // sorted keys
        List<Integer> sort8 = map.keySet().stream().sorted().collect(Collectors.toList());
        System.out.println("Java8 Sort keys: " + sort8); // sorted keys
        // 18. Sorting values
        List<String> sortVal7 = new ArrayList<>(map.values());
        Collections.sort(sortVal7);
        System.out.println("Java7 Sort values: " + sortVal7); // sorted values
        List<String> sortVal8 = map.values().stream().sorted().collect(Collectors.toList());
        System.out.println("Java8 Sort values: " + sortVal8); // sorted values
        // 19. Convert map to string list
        List<String> entry7 = new ArrayList<>();
        for (Map.Entry<Integer, String> e : map.entrySet()) entry7.add(e.getKey() + "=" + e.getValue());
        System.out.println("Java7 Entries: " + entry7); // key=value strings
        List<String> entry8 = map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.toList());
        System.out.println("Java8 Entries: " + entry8); // key=value strings
        // 20. Count values > length 5
        int count7 = 0;
        for (String s : map.values()) if (s.length() > 5) count7++;
        System.out.println("Java7 Count: " + count7); // number of long values
        long count8 = map.values().stream().filter(s -> s.length() > 5).count();
        System.out.println("Java8 Count: " + count8); // number of long values
        // 21. Find first value
        String first7 = null;
        for (String s : map.values()) { first7 = s; break; }
        System.out.println("Java7 First value: " + first7); // first value
        String first8 = map.values().stream().findFirst().orElse("NA");
        System.out.println("Java8 First value: " + first8); // first value
        // 22. Max by key
        int maxKey7 = Collections.max(map.keySet());
        System.out.println("Java7 MaxKey: " + maxKey7); // max key
        int maxKey8 = map.keySet().stream().max(Integer::compare).get();
        System.out.println("Java8 MaxKey: " + maxKey8); // max key
        // 23. Min by value
        String minVal7 = Collections.min(map.values());
        System.out.println("Java7 MinVal: " + minVal7); // alphabetically min
        String minVal8 = map.values().stream().min(String::compareTo).get();
        System.out.println("Java8 MinVal: " + minVal8); // alphabetically min
        // 24. Collect map to another map with transformed values
        Map<Integer, String> map7 = new HashMap<>();
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            map7.put(e.getKey(), e.getValue().toUpperCase());
        }
        System.out.println("Java7 Transform: " + map7); // uppercase values
        Map<Integer, String> map8 = map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toUpperCase()));
        System.out.println("Java8 Transform: " + map8); // uppercase values
        // 25. Convert map keys to comma string
        StringBuilder sb = new StringBuilder();
        for (Integer k : map.keySet()) sb.append(k).append(",");
        String keyStr7 = sb.substring(0, sb.length()-1);
        System.out.println("Java7 Keys CSV: " + keyStr7); // "1,2,3,..."
        String keyStr8 = map.keySet().stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println("Java8 Keys CSV: " + keyStr8); // "1,2,3,..."
        // 26. Convert map values to comma string
        StringBuilder sb2 = new StringBuilder();
        for (String v : map.values()) sb2.append(v).append(";");
        String valStr7 = sb2.substring(0, sb2.length()-1);
        System.out.println("Java7 Values CSV: " + valStr7); // "Olivia;Liam;..."
        String valStr8 = map.values().stream().collect(Collectors.joining(";"));
        System.out.println("Java8 Values CSV: " + valStr8); // "Olivia;Liam;..."
        // 27. Partition by key > 5
        Map<Boolean, List<Map.Entry<Integer, String>>> part8 =
                map.entrySet().stream().collect(Collectors.partitioningBy(e -> e.getKey() > 5));
        System.out.println("Java8 Partition: " + part8); // two groups
        // 28. Group by value length
        Map<Integer, List<String>> group8 = map.values().stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Java8 GroupBy: " + group8); // lengths as keys
        // 29. Find any
        String any8 = map.values().stream().findAny().orElse("NA");
        System.out.println("Java8 FindAny: " + any8); // any value
        // 30. Parallel forEach
        map.entrySet().parallelStream().forEach(e -> System.out.println("Java8 ParallelForEach: " + e));
        // Output: entries printed in parallel
        // 31. Copy map
        Map<Integer, String> copy7 = new HashMap<>();
        for (Map.Entry<Integer, String> e : map.entrySet()) copy7.put(e.getKey(), e.getValue());
        System.out.println("Java7 Copy: " + copy7); // copy of map
        Map<Integer, String> copy8 = map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Java8 Copy: " + copy8); // copy of map
        // 32. Convert map keys to set
        Set<Integer> set7 = new HashSet<>(map.keySet());
        System.out.println("Java7 KeySet: " + set7); // set of keys
        Set<Integer> set8 = map.keySet().stream().collect(Collectors.toSet());
        System.out.println("Java8 KeySet: " + set8); // set of keys
        // 33. Convert map values to set
        Set<String> valSet7 = new HashSet<>(map.values());
        System.out.println("Java7 ValueSet: " + valSet7); // set of values
        Set<String> valSet8 = map.values().stream().collect(Collectors.toSet());
        System.out.println("Java8 ValueSet: " + valSet8); // set of values
        // 34. Find max length value
        String maxLen7 = "";
        for (String s : map.values()) if (s.length() > maxLen7.length()) maxLen7 = s;
        System.out.println("Java7 MaxLenVal: " + maxLen7); // value with max length
        String maxLen8 = map.values().stream().max(Comparator.comparingInt(String::length)).get();
        System.out.println("Java8 MaxLenVal: " + maxLen8); // value with max length
        // 35. Find min key > 2
        int minKey7 = Integer.MAX_VALUE;
        for (Integer k : map.keySet()) if (k > 2 && k < minKey7) minKey7 = k;
        System.out.println("Java7 MinKey>2: " + minKey7); // smallest key > 2
        int minKey8 = map.keySet().stream().filter(k -> k > 2).min(Integer::compare).get();
        System.out.println("Java8 MinKey>2: " + minKey8); // smallest key > 2
        // 36. Concatenate all values
        StringBuilder sb7 = new StringBuilder();
        for (String s : map.values()) sb7.append(s);
        System.out.println("Java7 Concat: " + sb7.toString()); // concatenated values
        String concat8 = map.values().stream().collect(Collectors.joining());
        System.out.println("Java8 Concat: " + concat8); // concatenated values
        // 37. Count distinct value lengths
        Set<Integer> lens7 = new HashSet<>();
        for (String s : map.values()) lens7.add(s.length());
        System.out.println("Java7 DistinctLen: " + lens7.size()); // distinct lengths
        long lens8 = map.values().stream().map(String::length).distinct().count();
        System.out.println("Java8 DistinctLen: " + lens8); // distinct lengths
        // 38. Map to TreeMap
        Map<Integer, String> tree7 = new TreeMap<>(map);
        System.out.println("Java7 TreeMap: " + tree7); // sorted by key
        Map<Integer, String> tree8 = map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, TreeMap::new));
        System.out.println("Java8 TreeMap: " + tree8); // sorted by key
        // 39. Find key of specific value
        int found7 = -1;
        for (Map.Entry<Integer, String> e : map.entrySet()) if (e.getValue().equals("Sophia")) found7 = e.getKey();
        System.out.println("Java7 KeyOfSophia: " + found7); // key for Sophia
        int found8 = map.entrySet().stream()
                .filter(e -> e.getValue().equals("Sophia")).map(Map.Entry::getKey).findFirst().orElse(-1);
        System.out.println("Java8 KeyOfSophia: " + found8); // key for Sophia
        // 40. Increment key values by 10
        Map<Integer, String> inc7 = new HashMap<>();
        for (Map.Entry<Integer, String> e : map.entrySet()) inc7.put(e.getKey() + 10, e.getValue());
        System.out.println("Java7 IncKey: " + inc7); // keys + 10
        Map<Integer, String> inc8 = map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey() + 10, Map.Entry::getValue));
        System.out.println("Java8 IncKey: " + inc8); // keys + 10
        // 41. Remove null values
        Map<Integer, String> nullMap7 = new HashMap<>(map);
        nullMap7.put(9, null);
        nullMap7.values().removeIf(Objects::isNull);
        System.out.println("Java7 RemoveNull: " + nullMap7); // null removed
        Map<Integer, String> nullMap8 = new HashMap<>(map);
        nullMap8.put(9, null);
        nullMap8.entrySet().removeIf(e -> e.getValue() == null);
        System.out.println("Java8 RemoveNull: " + nullMap8); // null removed
        // 42. Map keys squared
        Map<Integer, Integer> square7 = new HashMap<>();
        for (Integer k : map.keySet()) square7.put(k, k * k);
        System.out.println("Java7 KeySquare: " + square7); // key squared
        Map<Integer, Integer> square8 = map.keySet().stream().collect(Collectors.toMap(k -> k, k -> k * k));
        System.out.println("Java8 KeySquare: " + square8); // key squared
        // 43. Sort map by values
        List<Map.Entry<Integer, String>> sortVal7a = new ArrayList<>(map.entrySet());
        Collections.sort(sortVal7a, Comparator.comparing(Map.Entry::getValue));
        System.out.println("Java7 SortByValue: " + sortVal7a); // sorted by value
        List<Map.Entry<Integer, String>> sortVal8a = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        System.out.println("Java8 SortByValue: " + sortVal8a); // sorted by value
        // 44. Reverse sort by key
        List<Map.Entry<Integer, String>> rev7 = new ArrayList<>(map.entrySet());
        Collections.sort(rev7, (a, b) -> b.getKey() - a.getKey());
        System.out.println("Java7 ReverseSortKey: " + rev7); // reverse sorted keys
        List<Map.Entry<Integer, String>> rev8 = map.entrySet().stream()
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed()).collect(Collectors.toList());
        System.out.println("Java8 ReverseSortKey: " + rev8); // reverse sorted keys
        // 45. Average of key set
        double avg7 = 0;
        for (Integer k : map.keySet()) avg7 += k;
        avg7 /= map.size();
        System.out.println("Java7 AvgKey: " + avg7); // average of keys
        double avg8 = map.keySet().stream().mapToInt(Integer::intValue).average().getAsDouble();
        System.out.println("Java8 AvgKey: " + avg8); // average of keys
        // 46. Longest key,value string
        String long7 = "";
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            String temp = e.getKey() + "=" + e.getValue();
            if (temp.length() > long7.length()) long7 = temp;
        }
        System.out.println("Java7 LongestEntry: " + long7); // longest key=value string
        String long8 = map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue())
                .max(Comparator.comparingInt(String::length)).get();
        System.out.println("Java8 LongestEntry: " + long8); // longest key=value string
        // 47. Shortest value
        String short7 = null;
        for (String v : map.values()) if (short7 == null || v.length() < short7.length()) short7 = v;
        System.out.println("Java7 ShortestVal: " + short7); // shortest value
        String short8 = map.values().stream().min(Comparator.comparingInt(String::length)).get();
        System.out.println("Java8 ShortestVal: " + short8); // shortest value
        // 48. Sum of key squares
        int sum7 = 0;
        for (Integer k : map.keySet()) sum7 += k * k;
        System.out.println("Java7 SumSquares: " + sum7); // sum of squares
        int sum8 = map.keySet().stream().mapToInt(k -> k * k).sum();
        System.out.println("Java8 SumSquares: " + sum8); // sum of squares
        // 49. All values uppercase joined with comma
        StringBuilder sb9 = new StringBuilder();
        for (String v : map.values()) sb9.append(v.toUpperCase()).append(",");
        System.out.println("Java7 UpperCSV: " + sb9.substring(0, sb9.length()-1)); // uppercase joined
        String join8 = map.values().stream().map(String::toUpperCase).collect(Collectors.joining(","));
        System.out.println("Java8 UpperCSV: " + join8); // uppercase joined
        // 50. Check if all values start with capital
        boolean all7 = true;
        for (String v : map.values()) if (!Character.isUpperCase(v.charAt(0))) all7 = false;
        System.out.println("Java7 AllStartCap: " + all7); // true/false
        boolean all8 = map.values().stream().allMatch(v -> Character.isUpperCase(v.charAt(0)));
        System.out.println("Java8 AllStartCap: " + all8); // true/false
    }
}