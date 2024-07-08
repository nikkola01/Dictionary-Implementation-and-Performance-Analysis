package uk.ac.aber.cs21120.trie.solution;

import uk.ac.aber.cs21120.trie.interfaces.IDictionary;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Trie implements IDictionary {

    Node root;
    int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    /**
     * Add a string to the dictionary
     * @param s string to add
     */
    @Override
    public void add(String s) {
        Node cur = root;
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        for(byte i : bytes){
            if(cur.children[i]==null)
                cur.children[i] = new Node();

           cur = cur.children[i];
        }

        if(!cur.isLeaf){
            cur.isLeaf = true;
            size++;
        }
    }

    /**
     * Return true if a string is in the dictionary
     * @param s string to check
     * @return true if in dictionary
     */
    @Override
    public boolean contains(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        Node cur = root;
        for(byte i : bytes){
            if(cur.children[i]==null)
                return false;

            cur = cur.children[i];
        }
        return cur.isLeaf;
    }

    /**
     * Delete a string from the dictionary
     * @param s string to delete
     */
    @Override
    public void delete(String s) {
        if(!contains(s))
            return;

        remove(root, s, 0);
        size--;
    }

    /**
     * Returns true if the Node have no children
     * @param n Node to check
     */
    private boolean isEmpty(Node n){

        for(int i=0; i<=255; i++){
            if(n.children[i]!=null)
                return false;
        }
        return true;
    }

    /**
     * Recursive method to remove the string from the trie and the not needed chars
     * @param root the root of the Node
     * @param strToRemove the string that we need to remove
     * @param depht the depht in the trie that we are
     */
    private void remove(Node root, String strToRemove, int depht) {

        if (root == null)
            return;

        if (depht == strToRemove.length()) {
            if (root.isLeaf)
                root.isLeaf = false;

            if (isEmpty(root))
                root = null;

            return;
        }

        byte[] bytes = strToRemove.getBytes(StandardCharsets.UTF_8);

        remove(root.children[bytes[depht]], strToRemove, depht + 1);

        if (isEmpty(root) && !root.isLeaf)
            root = null;

    }

    /**
     * Return the number of strings in the dictionary
     * @return count of strings in dictionary
     */
    @Override
    public int count() {
        return size;
    }

    /**
     * Return a list of the strings contained in the dictionary;
     * not guaranteed to be sorted but may be sorted in some implementations.
     * @return list of strings in dictionary
     */
    @Override
    public List<String> contents() {
        List<String> list = new ArrayList<>();
        addContents(list, root, "");

        return list;
    }

    /**
     * Recursive method to remove a list full of strings
     * @param list list of the found words
     * @param n the starting Node
     * @param wordSoFar the string collected
     */
    private void addContents(List<String> list, Node n, String wordSoFar){
        for(int i=0; i<=255 ;i++){
            if(n.children[i]==null)
                continue;

            if(n.children[i].isLeaf)
                list.add(wordSoFar + Character.toString((char) i));

            addContents(list, n.children[i], wordSoFar + Character.toString((char) i));
        }
    }
}
