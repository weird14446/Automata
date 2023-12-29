import java.util.Scanner;

public class Automaton {
    private String[] alphabets;
    private String[] states;
    private String[] finalStates;
    private Scanner sc = new Scanner(System.in);
    private String[][] stateTransition;

    public Automaton() {}

    public void setStateTransition() {
        System.out.print("Enter the number of domain: ");
        int num = sc.nextInt();
        stateTransition = new String[num][3];
        stateTransition[0][0] = "s0"; // state
        System.out.print("Enter the state transition function:\n(e.g. state1 alphabet state2)\ns0 ");
        stateTransition[0][1] = sc.next(); // input
        stateTransition[0][2] = sc.next(); // output
        for (int i = 1; i < num; i++) {
            stateTransition[i][0] = sc.next(); // state
            stateTransition[i][1] = sc.next(); // input
            stateTransition[i][2] = sc.next(); // output
        }

        alphabets = new String[num];
        states = new String[num];
        for (int i = 0; i < num; i++) {
            alphabets[i] = stateTransition[i][1];
            states[i] = stateTransition[i][0];
        }
    }

    public void setFinalStates() {
        System.out.print("Enter the final states:");
        finalStates = sc.nextLine().split(" ");
    }

    public String[] getAlphabets() {
        return alphabets;
    }

    public String[] getStates() {
        return states;
    }

    public String[][] getStateTransition() {
        return stateTransition;
    }

    public String[] getFinalStates() {
        return finalStates;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("final states:\n");
        for (int i = 0; i < finalStates.length; i++) {
            result.append(finalStates[i]).append(" ");
        }
        result.append("\n");

        result.append("state transition function:\n");
        for (int i = 0; i < stateTransition.length; i++) {
            result.append("f(").append(stateTransition[i][0]).append(", ");
            result.append(stateTransition[i][1]).append(")=");
            result.append(stateTransition[i][2]);
            if (i != stateTransition.length - 1) result.append("\n");
        }

        return result.toString();
    }
}
