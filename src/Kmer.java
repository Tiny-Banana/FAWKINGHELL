public class Kmer {
    private String substring;
    private int numOccurrences;

    public Kmer(String substring, int numOccurrences) {
        this.substring = substring;
        this.numOccurrences = numOccurrences;
    }

    public String getSubstring() {
        return substring;
    }

    public void setNumOccurrences(int numOccurrences) {
        this.numOccurrences = numOccurrences;
    }

    public int getNumOccurrences() {
        return numOccurrences;
    }
    @Override
    public String toString() {
        return "Kmer: " + substring + ", " + numOccurrences;
    }
}
