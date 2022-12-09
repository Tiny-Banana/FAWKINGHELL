/**
 * This is the driver class that runs and implements the hashtable.
 *
 * @author Jomar Delos Reyes
 * @version 1.0
 * @since 09/12/2022
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Input: ");
        String dna = input.nextLine();
        System.out.println("K-mer: ");
        int merDistribution = input.nextInt();

        ArrayList<Kmer> kmerDistribution = new ArrayList<>();

        HashTable hashTable1 = new HashTable(dna.length(), "mm3");
        HashTable hashTable2 = new HashTable(dna.length(), "xxhash");
        createHashTable(dna, merDistribution, hashTable1);
        createHashTable(dna, merDistribution, hashTable2);

//        createKmerDistribution(hashTable1, kmerDistribution);
        createKmerDistribution(hashTable2, kmerDistribution);
    }

    /**
     * This method is responsible for the creation of kmerDistribution
     *
     * @param kmerDistribution the arraylist that will then contain all the kmers and their number of occurrences
     * @param hashTable the hashtable where all the kmer-number of occurrences pair will be mapped at
     */
    private static void createKmerDistribution(HashTable hashTable, ArrayList<Kmer> kmerDistribution) {
        for (int i = 0; i < hashTable.getHashTable().length; i++) {
            kmerDistribution.addAll(hashTable.getHashTable()[i]);
        }
        for (Kmer kmer: kmerDistribution) {
            System.out.println(kmer);
        }
    }

    /**
     * This method is responsible for populating the hash table with the kmers and their
     * respective number of occurences in a given dna sequence.
     *
     * @param dna the dna sequence given by the user
     * @param merDistribution the k-mer distribution given by the user
     * @param hashTable the hashtable where all the kmer-number of occurrences pair will be mapped at
     */
    public static void createHashTable(String dna, int merDistribution, HashTable hashTable) {
        for (int i = 0; i < dna.length() - merDistribution + 1 ; i++) {
            String substring = dna.substring(i, i + merDistribution);
            hashTable.insert(substring, 1);
        }

//        hashTable.print();
    }
}

//taccaccaccatag