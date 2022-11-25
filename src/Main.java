import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.commons.codec.digest.MurmurHash3;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //try catch so that the input will only take the valid characters
        System.out.println("Input: ");
        String dna = input.nextLine();
        System.out.println("K-mer: ");
        int merDistribution = input.nextInt();

        LinkedList<String>[] arr = new LinkedList[dna.length()];
        ArrayList<Kmer> kmerDistribution = new ArrayList<Kmer>();
        int key;
        String substring;

        //initialize the linkedlists
        for (int i = 0; i < dna.length(); i++) {
            arr[i] = new LinkedList<String>();
        }

        for (int i = 0; i < dna.length() - merDistribution + 1 ; i++) {
            //hash function
            substring = dna.substring(i, i + merDistribution);
            key = Math.abs(MurmurHash3.hash32x86(substring.getBytes()) % dna.length() - 1);
            if (!arr[key].contains(substring)) kmerDistribution.add(new Kmer(substring));
            arr[key].add(substring);
        }

        for (Kmer k: kmerDistribution) {
            boolean isFound = false;
            for (int i = 0; i < arr.length && !isFound; i++) {
                if (arr[i].contains(k.getName())) {
                    for (int j = 0; j < arr[i].size(); j++) {
                        if (arr[i].get(j).equals(k.getName())) k.setNumOccurrences(k.getNumOccurrences() + 1);
                    }
                    isFound = true;
                }
            }
        }

        for (LinkedList s : arr) {
            System.out.println(s);
        }

        for (Kmer k: kmerDistribution) {
            System.out.println(k.getName() + " " + k.getNumOccurrences());
        }

    }
}