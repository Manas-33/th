package ArtificialIntelligence.All.waterjug;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WaterJugDFS {
    public static void main(String[] args) {
        int jug1Capacity = 4; // Capacity of the first jug
        int jug2Capacity = 3; // Capacity of the second jug
        int targetAmount = 2; // Target amount of water to be measured

        State1 initialState = new State1(0, 0);
        Set<State1> visited = new HashSet<>();

        if (dfs(initialState, jug1Capacity, jug2Capacity, targetAmount, visited)) {
            printSolution(initialState);
        } else {
            System.out.println("No solution found.");
        }
    }


    static private class State1{
        int jug1;
        int jug2;

        public State1(int jug1, int jug2) {
            this.jug1 = jug1;
            this.jug2 = jug2;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            State1 state = (State1) obj;
            return jug1 == state.jug1 && jug2 == state.jug2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(jug1, jug2);
        }
    }

    public static boolean dfs(State1 currentState, int jug1Capacity, int jug2Capacity, int targetAmount, Set<State1> visited) {
        //base condition
        if (currentState.jug1 == targetAmount || currentState.jug2 == targetAmount) {
            return true;
        }

        visited.add(currentState);

        // Try all possible operations: fill, empty, or pour
        for (int operation = 1; operation <= 6; operation++) {
            State1 nextState = applyOperation(currentState, operation, jug1Capacity, jug2Capacity);

            if (!visited.contains(nextState) && dfs(nextState, jug1Capacity, jug2Capacity, targetAmount, visited)) {
                printSolution(nextState);
                return true;
            }
        }

        return false;
    }

    public static State1 applyOperation(State1 currentState, int operation, int jug1Capacity, int jug2Capacity) {
        int jug1 = currentState.jug1;
        int jug2 = currentState.jug2;

        switch (operation) {
            case 1: // Fill jug1
                jug1 = jug1Capacity;
                break;
            case 2: // Fill jug2
                jug2 = jug2Capacity;
                break;
            case 3: // Empty jug1
                jug1 = 0;
                break;
            case 4: // Empty jug2
                jug2 = 0;
                break;
            case 5: // Pour from jug1 to jug2
                int pourAmount = Math.min(jug1, jug2Capacity - jug2);
                jug1 -= pourAmount;
                jug2 += pourAmount;
                break;
            case 6: // Pour from jug2 to jug1
                pourAmount = Math.min(jug2, jug1Capacity - jug1);
                jug1 += pourAmount;
                jug2 -= pourAmount;
                break;
        }

        return new State1(jug1, jug2);
    }

    public static void printSolution(State1 currentState) {
        while (currentState != null) {
            System.out.println("Jug 1: " + currentState.jug1 + " | Jug 2: " + currentState.jug2);
            currentState = null; // To print only one solution path
        }
    }
}