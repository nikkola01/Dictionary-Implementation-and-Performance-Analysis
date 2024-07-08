package uk.ac.aber.cs21120.trie.solution;
import uk.ac.aber.cs21120.trie.tests.WordList;

import java.util.List;

public class TimeTest{

    private static final int timesToRun = 400;
    private static final int MinLenOfword = 4;
    private static final int MaxLenOfword = 17;


    private static long[] testHashSetAdd(int wordLen, int runNtimes){
        WordList wordList = new WordList();
        List<String> list = List.copyOf(wordList.getWordsOfLength(wordLen,1000));

        long bestCase = 999999999;
        long worstCase = 0;
        long average = 0;
        int count = 0;

        for (int j=0; j<runNtimes; j++) {

            HashSetDictionary dic = new HashSetDictionary();
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++)         //add all 1000 words from the list
                dic.add(list.get(i));

            long time = System.nanoTime() - start;

            if(time < bestCase)                 //check for best case
                bestCase=time;

            if(time > worstCase)                //check for worst case
                worstCase=time;

            average += time;
            count++;
            dic = null;
        }
        average = average/count;

        long answer[] = {bestCase, worstCase, average};
        return answer;
    }

    private static long[] testHashSetDelete(int wordLen, int runNtimes){
        WordList wordList = new WordList();
        List<String> list = List.copyOf(wordList.getWordsOfLength(wordLen,1000));

        long bestCase = 999999999;
        long worstCase = 0;
        long average = 0;
        int count = 0;

        for (int j=0; j<runNtimes; j++) {

            HashSetDictionary dic = new HashSetDictionary();
            for (String i:list) {                   //Put the words into the dictionary
                dic.add(i);
            }
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++)         //add all 1000 words from the list
                dic.delete(list.get(i));

            long time = System.nanoTime() - start;

            if(time < bestCase)                 //check for best case
                bestCase=time;

            if(time > worstCase)                //check for worst case
                worstCase=time;

            average += time;
            count++;
            dic = null;
        }
        average = average/count;

        long answer[] = {bestCase, worstCase, average};
        return answer;
    }

    private static long[] testTrieAdd(int wordLen, int runNtimes){
        WordList wordList = new WordList();
        List<String> list = List.copyOf(wordList.getWordsOfLength(wordLen,1000));

        long bestCase = 999999999;
        long worstCase = 0;
        long average = 0;
        int count = 0;

        for (int j=0; j<runNtimes; j++) {

            Trie dic = new Trie();
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++)         //add all 1000 words from the list
                dic.add(list.get(i));

            long time = System.nanoTime() - start;

            if(time < bestCase)                 //check for best case
                bestCase=time;

            if(time > worstCase)                //check for worst case
                worstCase=time;

            average += time;
            count++;
            dic = null;
        }
        average = average/count;

        long answer[] = {bestCase, worstCase, average};
        return answer;
    }




    private static long[] testTrieDelete(int wordLen, int runNtimes){
        WordList wordList = new WordList();
        List<String> list = List.copyOf(wordList.getWordsOfLength(wordLen,1000));

        long bestCase = 999999999;
        long worstCase = 0;
        long average = 0;
        int count = 0;

        for (int j=0; j<runNtimes; j++) {

            Trie dic = new Trie();
            for (String i:list) {                   //Put the words into the dictionary
                dic.add(i);
            }
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++)         //add all 1000 words from the list
                dic.delete(list.get(i));

            long time = System.nanoTime() - start;

            if(time < bestCase)                 //check for best case
                bestCase=time;

            if(time > worstCase)                //check for worst case
                worstCase=time;

            average += time;
            count++;
            dic = null;
        }
        average = average/count;

        long answer[] = {bestCase, worstCase, average};
        return answer;
    }


    public static void main(String[] args) throws Exception {
        for(int i=MinLenOfword; i<=MaxLenOfword; i++) {
           System.out.println("----------------------");
           System.out.println("Word length " + i);

           long hashAddResult[] = testHashSetAdd(i, timesToRun);
           System.out.println("HashSet Add - Average - " + hashAddResult[2] + " - Best case - " + hashAddResult[0] + " - Worst case - " + hashAddResult[1]);
           long hashDelResut[] = testHashSetDelete(i, timesToRun);
           System.out.println("HashSet Delete - Average - " + hashDelResut[2] + " - Best case - " + hashDelResut[0] + " - Worst case - " + hashDelResut[1]);
           long trieAddResult[] = testTrieAdd(i, timesToRun);
           System.out.println("Trie Add - Average - " + trieAddResult[2] + " - Best case - " + trieAddResult[0] + " - Worst case - " + trieAddResult[1]);
           long trieDelResult[] = testTrieDelete(i, timesToRun);
           System.out.println("Trie Delete - Average - " + trieDelResult[2] + " - Best case - " + trieDelResult[0] + " - Worst case - " + trieDelResult[1]);
       }
    }
}

