import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.commons.codec.digest.MurmurHash3;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Input: ");
        String dna = input.nextLine();
        System.out.println("K-mer: ");
        int merDistribution = input.nextInt();

        LinkedList<String>[] hashTable = new LinkedList[dna.length()];
        ArrayList<Kmer> kmerDistribution = new ArrayList<Kmer>();
        int key;

        //initialize the linkedlists
        for (int i = 0; i < dna.length(); i++) {
            hashTable[i] = new LinkedList<String>();
        }

        //create hashtable
        for (int i = 0; i < dna.length() - merDistribution + 1 ; i++) {
            String substring = dna.substring(i, i + merDistribution);
            key = Math.abs(MurmurHash3.hash32x86(substring.getBytes()) % dna.length());
            hashTable[key].add(substring);
        }

        //create kmer distribution
        for (int i = 0; i < hashTable.length; i++) {
            for (int j = 0; j < hashTable[i].size(); j++) {
                String substring = hashTable[i].get(j);
                boolean isFound = false;
                if (kmerDistribution.size() == 0) kmerDistribution.add(new Kmer(substring));
                else {
                    for (int k = 0; k < kmerDistribution.size() && !isFound; k++) {
                        if (substring.compareTo(kmerDistribution.get(k).getName()) == 0) {
                            kmerDistribution.get(k).setNumOccurrences();
                            isFound = true;
                        }
                    }
                    if (!isFound) kmerDistribution.add(new Kmer(substring));
                }
            }
        }



        for (LinkedList s : hashTable) {
            System.out.println(s);
        }

        for (Kmer k: kmerDistribution) {
            System.out.println(k.getName() + " " + k.getNumOccurrences());
        }
    }
}

//taccaccaccatag