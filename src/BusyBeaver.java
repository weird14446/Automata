import java.io.*;
import java.nio.charset.StandardCharsets;

public class BusyBeaver extends TuringMachine {
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    File file;
    int num;
    boolean isWrite; // true: write, false: don't write

    public BusyBeaver(int n) {
        super();
        num = n;
        file = new File(n + "-busyBeaver.txt");
        isWrite = true;
        try {
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public BusyBeaver(int n, boolean b) {
        super();
        num = n;
        file = new File(n + "-busyBeaver.txt");
        isWrite = b;
        try {
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(String tape) {
        this.tape = tape;
        int i = 0;
        String state = initialState;
        String head = "";
        if (isWrite) {
            try {
                fileOutputStream.write((tape+"\n").getBytes(StandardCharsets.UTF_8)); //System.out.println(this.tape);
                fileOutputStream.write(("|"+state+"\n").getBytes(StandardCharsets.UTF_8)); //System.out.println("|" + state);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
                        if (finalStates.contains(state)) break;
                        try {
                            fileOutputStream.write((tape+"\n").getBytes(StandardCharsets.UTF_8)); //System.out.println(this.tape);
                            head = "";
                            for (int _i = 0; _i < i; _i++) head+=" ";
                            fileOutputStream.write((head+"|"+state+"\n").getBytes(StandardCharsets.UTF_8)); //System.out.println(head + "|" + state);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                }
            }
            try {
                fileOutputStream.write(("\nBB(" + num + ")=" + String.valueOf(countChar('1'))+"\n").getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
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
                        if (finalStates.contains(state)) break;
                        break;
                    }
                }
            }
            try {
                fileOutputStream.write(("BB("+num+")="+countChar('1')).getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void close() {
        try {
            fileOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
