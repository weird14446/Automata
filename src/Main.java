public class Main {
    public static void main(String[] args) {
        TuringMachine tm = new TuringMachine();
//        tm.addTransition("a", '1', "a", '1', true);
//        tm.addTransition("a", '0', "qf", '1', false);
//        tm.setInitialState("a");
//        tm.setBlankSymbol('0');
//        tm.addFinalState("qf");
//        tm.run("1111");
//        System.out.println(tm.countChar('1'));

        tm.addTransition("g0", '1', "g0", '1', true);
        tm.addTransition("g0", '2', "g0", '2', true);
        tm.addTransition("g0", '0', "g1", '0', false);
        tm.addTransition("g1", '2', "g1", '2', false);
        tm.addTransition("g1", '1', "g2", '2', true);
        tm.addTransition("g2", '2', "g2", '2', true);
        tm.addTransition("g2", '0', "g1", '2', false);
        tm.addTransition("g1", '0', "g3", '0', true);
        tm.addTransition("g3", '2', "g3", '1', true);
        tm.addTransition("g3", '0', "gf", '0', false);
        tm.setInitialState("g0");
        tm.setBlankSymbol('0');
        tm.addFinalState("gf");
        tm.run("111");
        System.out.println(tm.countChar('1'));
    }
}