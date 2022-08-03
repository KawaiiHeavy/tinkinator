package com.example.app.utils;


import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;


public class Streams<T> {

    /**
     * Main collection
     */
    private static Collection<?> streamList;

    /**
     * List of filters and functions
     * Adding new filter per calling .filter()
     * Adding new function per calling .transform()
     * All filters and functions using in addition
     * order only after calling .toMap()
     */
    private static List<Object> operationsList;

    public static Collection<?> getStreamList() {
        return streamList;
    }

    public static Streams of(List list) {
        streamList = new LinkedList(list);
        operationsList = new LinkedList<>();
        return new Streams();
    }

    public static Streams of(Set set) {
        streamList = new HashSet(set);
        operationsList = new LinkedList<>();
        return new Streams();
    }

    private Streams() {
    }

    private Streams(List<Object> operationsList, Collection<?> streamList) {
        Streams.operationsList = operationsList;
        Streams.streamList = streamList;
    }

    public Streams<T> filter(Predicate<? super T> predicate) {

        operationsList.add(predicate);
        return this;
    }

    public <K> Streams<K> transform(Function<? super T, ? super K> function) {

        operationsList.add(function);
        return new Streams<>(operationsList, streamList);
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyFunction,
                                  Function<? super T, ? extends V> valueFunction) {

        Map<K, V> map = new HashMap<>();

        Object transformedObject;
        boolean addObject;

        for (Object obj : streamList) {

            addObject = true;
            transformedObject = obj;

            for (Object operator : operationsList) {

                if (operator instanceof Predicate) {
                    if (!((Predicate) operator).test(transformedObject)) {
                        addObject = false;
                    }
                } else if (operator instanceof Function) {
                    if (!addObject) break;
                    transformedObject = ((Function) operator).apply(transformedObject);
                }
            }
            if (addObject) map.put(keyFunction.apply((T) transformedObject),
                    valueFunction.apply((T) transformedObject));
        }
        return map;
    }

    private Collection<T> toCollection(Collection<T> collection) {

        Object transformedObject;
        boolean addObject;

        for (Object obj : streamList) {

            addObject = true;
            transformedObject = obj;

            for (Object operator : operationsList) {

                if (operator instanceof Predicate) {
                    if (!((Predicate) operator).test(transformedObject)) {
                        addObject = false;
                    }
                } else if (operator instanceof Function) {
                    if (!addObject) break;
                    transformedObject = ((Function) operator).apply(transformedObject);
                }
            }
            if (addObject) collection.add((T) transformedObject);
        }
        streamList = null;
        return collection;
    }

    public Set<T> toSet() {
        return (HashSet<T>) toCollection(new HashSet<>());
    }

    public List<T> toList() {
        return (ArrayList<T>) toCollection(new ArrayList<>());
    }
}