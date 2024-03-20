package org.example.StringMatching.FiniteAutomata;

import java.util.*;

public class FiniteAutomata {

    public static void run(String completeTxt, String patternTxt){
        List<String> pattern = splitString(patternTxt);
        Map<String, Integer>[] table = new Map[pattern.size()+1];
        setInitialData(table, pattern);
        fillTable(table, getLetters(splitString(completeTxt)), pattern);

        printMatchIndices(getResultTable(splitString(completeTxt), table, pattern.size()));
    }

    public static void printMatchIndices(int[] result) {
        for (int i = 0; i < result.length; i++) {
            if (result[i] == result.length - 1) {
                System.out.println("MATH INDEX: " + (i - result.length + 1));
            }
        }
    }

    public static void setInitialData(Map<String, Integer>[] table, List<String> values){
        int index = 0;
        for (String letter : values){
            table[index] = new HashMap<>();
            table[index].put(letter, index + 1);
            index++;
        }
        table[index] = new HashMap<>();
    }

    public static void fillTable(Map<String, Integer>[] table, List<String> values, List<String> pattern){

        List<String> emptyList = new ArrayList<>();
        for (String value : values){
            if (table[0].get(value) == null){
                table[0].put(value, getLongestPrefix(pattern, emptyList, value));
            }
        }

        int index = 1;
        for (int i = 0; i < pattern.size(); i++){
            for (String value : values){
                if (table[index].get(value) == null){
                    table[index].put(value, getLongestPrefix(pattern, pattern.subList(0, i+1), value));
                }
            }
            index++;
        }
    }

    public static int[] getResultTable(List<String> lettersList, Map<String, Integer>[] table, int patternLength){
        int[] result = new int[lettersList.size() + 1];

        int auxValue = 0;
        int index = 1;
        int key = 0;

        for (String letter : lettersList){
            auxValue = table[key].get(letter);
            result[index] = auxValue;
            key = auxValue;
            index++;

            if(auxValue == patternLength){
                System.out.println("MATH INDEX: " + (index - 1 - patternLength));
            }

        }

        return result;
    }

    public static int getLongestPrefix(List<String> pattern, List<String> subPattern, String plus){
        List<String> auxSubPattern = new ArrayList<>(subPattern);
        auxSubPattern.add(plus);
        List<String> auxComparator;

        int index = auxSubPattern.size() <= pattern.size() ?  0 :  1;;

        for (int i = index; i < auxSubPattern.size(); i++) {
            auxComparator = auxSubPattern.subList(i, auxSubPattern.size());
            if (auxComparator.equals(pattern.subList(0, auxComparator.size()))) {
                return auxComparator.size();
            }
        }

        return 0;
    }

    public static List<String> splitString(String string) {
        String[] list = string.split("");
        return Arrays.asList(list);
    }

    public static List<String> getLetters(List<String> string){
        Set<String> letters = new HashSet<>(string);
        return new ArrayList<>(letters);
    }


    public static void main(String[] args) {

        String txt = "THIS IS A TEST TEXT";
        String pattern = "TEST";
        run(txt, pattern);
        System.out.println("2");
        txt = "AABAACAADAABAAABAA";
        pattern = "AABA";
        run(txt, pattern);

    }

}