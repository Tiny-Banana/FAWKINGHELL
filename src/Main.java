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
        ArrayList<Kmer> kmers = new ArrayList<Kmer>();

        int key;
        String substring;
        Kmer kmer;

        //initialize the linkedlists
        for (int i = 0; i < dna.length(); i++) {
            arr[i] = new LinkedList<String>();
        }

        for (int i = 0; dna.length() - i >= 6 ; i++) {
            //hash function
            substring = dna.substring(i, i + merDistribution);
            key = Math.abs(MurmurHash3.hash32x86(substring.getBytes()) % merDistribution - 1);
            if (!arr[key].contains(substring)) {
                kmer = new Kmer(substring);
                kmer.setNumOccurrences(kmer.getNumOccurrences() + 1);
                kmers.add(kmer);
            } else {
                boolean found = false;
                for (int j = 0; j < kmers.size() && !found; j++) {
                    if (kmers.get(j).getName().equals(substring)) {
                        kmers.get(j).setNumOccurrences(kmers.get(j).getNumOccurrences() + 1);
                        found = true;
                    }
                }
            }
            arr[key].add(substring);
        }

        for (LinkedList s : arr) {
            System.out.println(s);
        }

        for (Kmer k: kmers) {
            System.out.println(k.getNumOccurrences());
        }

    }
}