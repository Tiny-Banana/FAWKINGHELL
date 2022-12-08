import org.apache.commons.codec.digest.MurmurHash3;
import java.util.LinkedList;

public class HashTable {
    LinkedList<Kmer>[] hashTable;
    int size;
    int collisionCounter;

    public HashTable(int size) {
        this.hashTable = new LinkedList[size];
        this.size = size;
        this.collisionCounter = 0;
        intializeHashTable();
    }

    private void intializeHashTable() {
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    public void insert(String key, int value) {
        int index = Math.abs(MurmurHash3.hash32x86(key.getBytes()) % size);
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

    public int search(String key) {
        for (int i = 0; i < hashTable.length; i++) {
            for (int j = 0; j < hashTable[i].size(); j++) {
                Kmer kmer = hashTable[i].get(j);
                if (kmer.getSubstring().compareTo(key) == 0) return kmer.getNumOccurrences();
            }
        }
        return 0;
    }

    public void print() {
        for (LinkedList s : hashTable) {
            System.out.println(s);
        }
        System.out.println("Collision count: " + collisionCounter);
    }
}
