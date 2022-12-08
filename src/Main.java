import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Input: ");
        String dna = input.nextLine();
        System.out.println("K-mer: ");
        int merDistribution = input.nextInt();

        HashTable hashTable1 = new HashTable(dna.length(), "mm3");
        HashTable hashTable2 = new HashTable(dna.length(), "xxhash");
        createHashTable(dna, merDistribution, hashTable1);
        createHashTable(dna, merDistribution, hashTable2);
        //create kmerdistribution arraylist if needed
    }
    public static void createHashTable(String dna, int merDistribution, HashTable hashTable) {
        //create hashtables
        for (int i = 0; i < dna.length() - merDistribution + 1 ; i++) {
            String substring = dna.substring(i, i + merDistribution);
            hashTable.insert(substring, 1);
        }

        hashTable.print();
    }
}

//taccaccaccatag