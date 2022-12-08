import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Input: ");
        String dna = input.nextLine();
        System.out.println("K-mer: ");
        int merDistribution = input.nextInt();

        HashTable hashTable = new HashTable(dna.length());
        createHashTable(dna, merDistribution, hashTable);
        //create kmerdistribution arraylist if needed
    }
    public static void createHashTable(String dna, int merDistribution, HashTable hashTable) {
        //create hashtable
        for (int i = 0; i < dna.length() - merDistribution + 1 ; i++) {
            String substring = dna.substring(i, i + merDistribution);
            hashTable.insert(substring, 1);
        }

        hashTable.print();
    }
}

//taccaccaccatag