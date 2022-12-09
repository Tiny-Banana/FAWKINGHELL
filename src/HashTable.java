/**
 * This class handles the actual implementation of the hash table.
 *
 * @author Jomar Delos Reyes
 * @version 1.0
 * @since 09/12/2022
 */
import org.apache.commons.codec.digest.MurmurHash3;
import org.apache.commons.codec.digest.XXHash32;

import java.util.LinkedList;

public class HashTable {
    LinkedList<Kmer>[] hashTable;
    String type;
    int size;
    int collisionCounter;

    public HashTable(int size, String type) {
        this.type = type;
        this.hashTable = new LinkedList[size];
        this.size = size;
        this.collisionCounter = 0;
        intializeHashTable();
    }


    /**
     * This method initializes all the linked lists inside the array of size n.
     */
    private void intializeHashTable() {
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    /**
     * This method handles the insertion of the key-value pair into the hash table by using MurmurHash3 and xxHash32 hashing functions.
     *
     * @param key the kmer that will be hashed by the hashing function and will then be mapped into the hash table
     * @param value the number of occurrences of the kmer in the given dna sequence
     */
    public void insert(String key, int value) {
        int index;
        if (type.equals("mm3")) {
            index = Math.abs(MurmurHash3.hash32x86(key.getBytes()) % size);
        } else {
            XXHash32 hash = new XXHash32();
            hash.update(key.getBytes());
            index = (int) (hash.getValue() % size);
        }
        boolean isFound = false;
        if (hashTable[index].size() != 0) collisionCounter++;
        for (int i = 0; i < hashTable[index].size() && !isFound; i++) {
            Kmer kmer = hashTable[index].get(i);
            if (kmer.getSubstring().compareTo(key) == 0) {
                kmer.setNumOccurrences(kmer.getNumOccurrences()+ 1);
                isFound = true;
            }
        }
        if (!isFound) hashTable[index].add(new Kmer(key, value));
    }
    /**
     * This method handles the searching functionality of a hash table given a key, kmer.
     *
     * @param key the kmer that will be hashed by the hashing function
     * @return an int, the number of occurrences of the given key, kmer
     */
    public int search(String key) {
        for (int i = 0; i < hashTable.length; i++) {
            for (int j = 0; j < hashTable[i].size(); j++) {
                Kmer kmer = hashTable[i].get(j);
                if (kmer.getSubstring().compareTo(key) == 0) return kmer.getNumOccurrences();
            }
        }
        return 0;
    }

    public LinkedList<Kmer>[] getHashTable() {
        return hashTable;
    }

//    public void print() {
//        System.out.println("\nType: " + type);
//        for (LinkedList s : hashTable) {
//            System.out.println(s);
//        }
//        System.out.println("Collision count: " + collisionCounter);
//    }
}
