# PlagiarismChecker

## Proyecto Recuperatorio
Dynamic Programming – Project
Samuel Escalera Herrera
1. Improved findMisspelledWords method, no longer uses calculateSimilarity method now uses levenshtein directly, also uncouples
the nested loop by making the words in text1 split before iterating and comparing them with text2.
2. The Levenshtein distance, which forms the basis of the DP algorithm, provides a robust way to measure the similarity 
between two strings by calculating the minimum number of operations (insertion, deletion or substitution) required to 
transform one string into the other.
3. The time complexity of the solution lies mainly in the implementation of the Levenshtein distance calculation within 
the calculateSimilarity method. The Levenshtein distance algorithm has a time complexity of O(m*n), where m and n are the 
lengths of the two input strings. Since the algorithm traverses each character of both strings once, the time complexity is 
directly proportional to the product of their lengths.
4. One solution I can think of is the Rabin-Krab algorithm, using hashing to compare the text substrings of the 2 txt 
files, however hashing could not help us to find misspellings or changes in word order. In conclusion, although there are 
other algorithms that are better at comparing text substrings, this algorithm could not help us to find spelling errors.


## Functionality of the code
This code checks the originality of two texts written in English, taking into account spelling errors.
It was created to help a university professor identify possible cases of plagiarism among his students' essays.

Functionality of the code:

The code performs the following tasks:
1. Reads two text files: The program expects two text files as input.
2. Calculates similarity: It uses Levenshtein's algorithm to calculate the edit distance between the texts.
   The smaller the distance, the higher the similarity.
3. Identify misspellings: Analyze the second text and look for words that are similar to words in the first text, considering that they have at least one similarity to words in the first text.
   from the first text, considering that they have at least 55% of their letters in the same order.
4. Deliver results:
   Percentage of similarity: indicates the percentage of similarity between the two texts 2.
    2. Misspelled words: Shows a list of the words in the second text that were identified as misspellings.

## Technologies used
- Programming language: Java
- Algorithm: Levenshtein

## Execution of the code

1. Save the code in a Java file (e.g., PlagiarismChecker.java).
2. Create two text files with the trials you want to compare 1.txt and 2.txt.
3. Compile and run the code from the command line using the following command:
```
javac PlagiarismChecker.java
java PlagiarismChecker
```
The code will read the specified files 1.txt and 2.txt, and display the percentage of similarity and misspelled words found in the second file.
misspelled words found in the second file.