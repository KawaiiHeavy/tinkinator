package com.example.app.utils;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class Streams<T> {

    /**
     * Main collection
     */
    private static List streamList;

    /**
     * List of filters and functions
     * Adding new filter per calling .filter()
     * Adding new function per calling .transform()
     * All filters and functions using in addition
     * order only after calling .toMap()
     */
    private static List<Object> operationsList;

    public static List getStreamList() {
        return streamList;
    }

    public static Streams of(List list) {
        streamList = new LinkedList(list);
        operationsList = new LinkedList<>();
        return new Streams();
    }

    public static Streams of(Set set) {
        streamList = Arrays.asList(set.toArray());
        operationsList = new LinkedList<>();
        return new Streams();
    }

    public Streams<T> filter(Predicate<? super T> predicate) {

        operationsList.add(predicate);
        return this;
    }

    public Streams<T> transform(Function<? super T, ? super T> function) {

        operationsList.add(function);
        return this;
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyFunction,
                                  Function<? super T, ? extends V> valueFunction) {

        Map<K, V> map = new HashMap();

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

    public Set<T> toSet(){

        Set<T> set = new HashSet<>();

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
            if (addObject) set.add((T) transformedObject);
        }
        return set;
    }

    public List<T> toList(){

        List<T> list = new ArrayList<>();

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
            if (addObject) list.add((T) transformedObject);
        }
        return list;
    }

}