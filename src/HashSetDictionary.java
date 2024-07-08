package uk.ac.aber.cs21120.trie.solution;

import uk.ac.aber.cs21120.trie.interfaces.IDictionary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetDictionary implements IDictionary {

    private Set<String> dictionary;

    public HashSetDictionary() {
        dictionary = new HashSet<String>();
    }

    /**
     * Add a string to the dictionary
     *
     * @param s string to add
     */
    @Override
    public void add(String s) {
        dictionary.add(s);
    }

    /**
     * Return true if a string is in the dictionary
     *
     * @param s string to check
     * @return true if in dictionary
     */
    @Override
    public boolean contains(String s) {
        return dictionary.contains(s);
    }

    /**
     * Delete a string from the dictionary
     *
     * @param s string to delete
     */
    @Override
    public void delete(String s) {
        dictionary.remove(s);
    }

    /**
     * Return the number of strings in the dictionary
     *
     * @return count of strings in dictionary
     */
    @Override
    public int count() {
        return dictionary.size();
    }

    /**
     * Return a list of the strings contained in the dictionary;
     * not guaranteed to be sorted but may be sorted in some implementations.
     *
     * @return list of strings in dictionary
     */
    @Override
    public List<String> contents() {
        List<String> list = List.copyOf(dictionary);
        return list;
    }
}
