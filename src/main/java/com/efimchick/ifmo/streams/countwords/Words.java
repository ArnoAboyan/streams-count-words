package com.efimchick.ifmo.streams.countwords;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Words {

    static final int MIN_LENGTH = 4;
    static final int MIN_FREQUENCY = 10;

    public String countWords(List<String> lines) {
        String string = lines.stream().flatMap(line -> Arrays.stream(line.split("\\P{javaLetter}+")))
                .map(String::toLowerCase).filter(a -> a.length() >= MIN_LENGTH).collect(Collectors.groupingBy(a -> a, Collectors.counting()))
                .entrySet().stream().filter(collectList -> collectList.getValue() >= MIN_FREQUENCY).sorted(Map.Entry.comparingByKey())
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())).map(a -> String.format("%s - %d\n", a.getKey(), a.getValue())).collect(Collectors.joining());

        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();

    }
}
