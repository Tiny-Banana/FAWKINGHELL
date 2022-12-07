public class Kmer {
    private String name;
    private int numOccurrences;

    public Kmer(String name) {
        this.name = name;
        this.numOccurrences = 1;
    }

    public String getName() {
        return name;
    }

    public void setNumOccurrences() {
        this.numOccurrences++;
    }

    public int getNumOccurrences() {
        return numOccurrences;
    }

}
