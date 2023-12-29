import java.util.HashSet;

public class TuringMachine {
    protected HashSet<String> states;
    protected HashSet<Character> symbols;
    protected HashSet<Character> inputSymbols;
    protected String initialState;
    protected HashSet<String> finalStates;
    protected char blankSymbol;
    protected HashSet<Transition> transitionFunction;
    protected String tape;

    static class Transition {
        String state1;
        char symbol1;
        String state2;
        char symbol2;
        boolean direction; // true: right, false: left

        public Transition(String state1, char symbol1, String state2, char symbol2, boolean direction) {
            this.state1 = state1;
            this.state2 = state2;
            this.symbol1 = symbol1;
            this.symbol2 = symbol2;
            this.direction = direction;
        }
    }

    public TuringMachine() {
        inputSymbols = new HashSet<>();
        states = new HashSet<>();
        initialState = null;
        finalStates = new HashSet<>();
        symbols = new HashSet<>();
        transitionFunction = new HashSet<>();
    }

    public void setInitialState(String state) {
        initialState = state;
    }

    public void setBlankSymbol(char s) {
        blankSymbol = s;
    }

    public void addFinalState(String state) {
        finalStates.add(state);
    }

    public void addState(String state) {
        states.add(state);
    }

    public void addSymbol(char s) {
        symbols.add(s);
    }

    public void addInputSymbol(char s) {
        inputSymbols.add(s);
    }

    public void addTransition(String readState, char readChar, String setState, char writeChar, boolean direction) {
        transitionFunction.add(new Transition(readState, readChar, setState, writeChar, direction));
    }

    public long countChar(char c) {
        return tape.chars().filter(a -> a == c).count();
    }

    public void run(String tape) {
        this.tape = tape;
        int i = 0;
        String state = initialState;
        String head;
        System.out.println(this.tape);
        System.out.println("|" + state);
        while (!finalStates.contains(state)) {
            for (Transition t : transitionFunction) {
                if (t.state1.equals(state) && t.symbol1 == this.tape.charAt(i)) {
                    state = t.state2;
                    StringBuilder sb = new StringBuilder(this.tape);
                    sb.setCharAt(i, t.symbol2);
                    this.tape = sb.toString();
                    if (t.direction) {
                        if (i == tape.length() - 1){
                            i++;
                            this.tape += blankSymbol;
                        }
                        else i++;
                    }
                    else {
                        if (i == 0) {
                            this.tape = blankSymbol + this.tape;
                        }
                        else i--;
                    }
                    System.out.println(this.tape);
                    head = "";
                    for (int _i = 0; _i < i; _i++) head+=" ";
                    System.out.println(head + "|" + state);
                    break;
                }
            }
        }
        System.out.println("[accept]");
    }
}
