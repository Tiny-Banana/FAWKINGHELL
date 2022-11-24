import java.util.Scanner;
import org.apache.commons.codec.digest.MurmurHash3;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //try catch
        System.out.println("Input: ");
        String dna = input.nextLine();
        System.out.println("K-mer: ");
        int kmer = input.nextInt();
        String[] arr = new String[dna.length()];
        int key;
        String substring;

        for (int i = 0; dna.length() - i >= 6 ; i++) {
            //hash function
            substring = dna.substring(i, i + kmer);
            key = Math.abs(MurmurHash3.hash32x86(substring.getBytes()) % kmer - 1);
            //arr[key] = substring;
            System.out.println(key);
        }

        for (String s : arr) {
            System.out.println(s);
        }
    }
}