public class Bracket {
    private char bracketChar;
    private int lineNo;

    public char getBracketChar() {
        return bracketChar;
    }

    public int getLineNo() {
        return lineNo;
    }

    public int getColNo() {
        return colNo;
    }

    private int colNo;

    public Bracket(char bracketChar, int lineNo, int colNo) {
        this.bracketChar = bracketChar;
        this.lineNo = lineNo;
        this.colNo = colNo;
    }
}
