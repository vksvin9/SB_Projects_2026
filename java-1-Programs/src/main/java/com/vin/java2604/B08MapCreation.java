package com.vin.java2604;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class B08MapCreation {

    enum Type { A, B }

    public static void main(String[] args) {

        // 1. HashMap - Basic mutable map (unordered)
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "Apple");
        System.out.println("HashMap: " + map1); // {1=Apple}

        // 2. Double brace initialization - Quick but NOT recommended
        Map<Integer, String> map2 = new HashMap<Integer, String>() {{
            put(1, "Apple");
            put(2, "Banana");
        }};
        System.out.println("Double brace: " + map2); // {1=Apple, 2=Banana}

        // 3. Map.of() - Immutable (Java 9+)
        Map<Integer, String> map3 = Map.of(1, "Apple", 2, "Banana");
        System.out.println("Map.of: " + map3); // immutable map

        // 4. Map.ofEntries() - Immutable, no size limit
        Map<Integer, String> map4 = Map.ofEntries(
                Map.entry(1, "Apple"),
                Map.entry(2, "Banana")
        );
        System.out.println("Map.ofEntries: " + map4); // immutable

        // 5. Collections.singletonMap() - Single immutable entry
        Map<Integer, String> map5 = Collections.singletonMap(1, "Apple");
        System.out.println("SingletonMap: " + map5); // one entry

        // 6. Stream API - Functional creation
        Map<Integer, String> map6 = Stream.of(
                new AbstractMap.SimpleEntry<>(1, "Apple"),
                new AbstractMap.SimpleEntry<>(2, "Banana")
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Stream map: " + map6); // created via stream

        // 7. Copy constructor - Copy existing map
        Map<Integer, String> map7 = new HashMap<>(map1);
        System.out.println("Copy map: " + map7); // copy of map1

        // 8. putAll() - Copy entries manually
        Map<Integer, String> map8 = new HashMap<>();
        map8.putAll(map1);
        System.out.println("putAll map: " + map8); // copied entries

        // 9. LinkedHashMap - Maintains insertion order
        Map<Integer, String> map9 = new LinkedHashMap<>();
        map9.put(1, "Apple");
        map9.put(2, "Banana");
        System.out.println("LinkedHashMap: " + map9); // ordered

        // 10. TreeMap - Sorted by keys
        Map<Integer, String> map10 = new TreeMap<>();
        map10.put(2, "Banana");
        map10.put(1, "Apple");
        System.out.println("TreeMap: " + map10); // sorted

        // 11. ConcurrentHashMap - Thread-safe
        Map<Integer, String> map11 = new ConcurrentHashMap<>();
        map11.put(1, "Apple");
        System.out.println("ConcurrentHashMap: " + map11); // thread-safe

        // 12. Properties - Key-value config (String only)
        Properties props = new Properties();
        props.put("key", "value");
        System.out.println("Properties: " + props); // config style

        // 13. EnumMap - Optimized for enum keys
        Map<Type, String> map13 = new EnumMap<>(Type.class);
        map13.put(Type.A, "Alpha");
        System.out.println("EnumMap: " + map13); // enum keys

        // 14. IdentityHashMap - Uses == instead of equals()
        Map<String, String> map14 = new IdentityHashMap<>();
        map14.put(new String("key"), "value");
        map14.put(new String("key"), "value2");
        System.out.println("IdentityHashMap: " + map14); // may store duplicates
    }
}
