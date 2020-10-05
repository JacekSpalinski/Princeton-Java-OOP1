
public class Huntingtons {

    // Returns the maximum number of consecutive repeats of CAG in the DNA string.
    public static int maxRepeats(String dna) {
        int count = 0;
        int maxcount = 0;
        int n = dna.length();
        String codonm1 = "";
        String codonm2 = "";

        for (int i = 0; i < n - 2; i++) {
            String codon = dna.substring(i, i + 3);
            if (i >= 1) codonm1 = dna.substring(i - 1, i + 2);
            if (i >= 2) codonm2 = dna.substring(i - 2, i + 1);

            if (codon.equals("CAG")) {
                count++;
                if (count > maxcount) maxcount = count;
            }
            else if (!codonm1.equals("CAG") && !codonm2.equals("CAG")) count = 0;

        }

        return maxcount;
    }

    // Returns a copy of s, with all whitespace (spaces, tabs, and newlines) removed.
    public static String removeWhitespace(String s) {
        s = s.replace(" ", "");
        s = s.replace("\n", "");
        s = s.replace("\t", "");
        return s;
    }


    // Returns one of these diagnoses corresponding to the maximum number of repeats:
    // "not human", "normal", "high risk", or "Huntington's".
    public static String diagnose(int maxRepeats) {
        if (maxRepeats <= 9) return "not human";
        else if (maxRepeats <= 35) return "normal";
        else if (maxRepeats <= 39) return "high risk";
        else if (maxRepeats <= 180) return "Huntington's";
        else return "not human";
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        String s = in.readAll();
        String dna = removeWhitespace(s);
        int maxcount = maxRepeats(dna);
        StdOut.println("max repeats = " + maxcount);
        StdOut.println(diagnose(maxcount));
    }
}
