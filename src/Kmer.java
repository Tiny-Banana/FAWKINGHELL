public class Kmer {
    private String name;
    private int numOccurrences;

    public Kmer(String name) {
        this.name = name;
        this.numOccurrences = 0;
    }

    public String getName() {
        return name;
    }

    public void setNumOccurrences(int numOccurrences) {
        this.numOccurrences = numOccurrences;
    }

    public int getNumOccurrences() {
        return numOccurrences;
    }

}
