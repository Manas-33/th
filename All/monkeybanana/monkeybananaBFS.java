package ArtificialIntelligence.All.monkeybanana;
import java.util.*;

public class monkeybananaBFS {
    private static class State {
        int monkeyX, monkeyY; // Monkey's position
        int chairX, chairY;   // Chair's position
        int bananaX, bananaY; // Banana's position

        public State(int monkeyX, int monkeyY, int chairX, int chairY, int bananaX, int bananaY) {
            this.monkeyX = monkeyX;
            this.monkeyY = monkeyY;
            this.chairX = chairX;
            this.chairY = chairY;
            this.bananaX = bananaX;
            this.bananaY = bananaY;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            State state = (State) obj;
            return monkeyX == state.monkeyX && monkeyY == state.monkeyY &&
                    chairX == state.chairX && chairY == state.chairY &&
                    bananaX == state.bananaX && bananaY == state.bananaY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(monkeyX, monkeyY, chairX, chairY, bananaX, bananaY);
        }
    }
    private static final int SIZE = 5; // Size of the room

    public static void main(String[] args) {
        solveMonkeyBananaProblem();
    }

    private static void solveMonkeyBananaProblem() {
        State initialState = new State(1, 1, 3, 1, 4, 4);
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        HashMap<State, State> parentMap = new HashMap<>();

        queue.add(initialState);
        visited.add(initialState);

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            if (isGoalState(currentState)) {
                System.out.println("Monkey reached the banana!");
                printPath(currentState, parentMap);
                return;
            }

            List<State> nextStates = generateNextStates(currentState);
            for (State nextState : nextStates) {
                if (!visited.contains(nextState)) {
                    queue.add(nextState);
                    visited.add(nextState);
                    parentMap.put(nextState, currentState);
                }
            }
        }

        System.out.println("No solution found.");
    }

    private static boolean isGoalState(State state) {
        return state.monkeyX == state.bananaX && state.monkeyY == state.bananaY;
    }

    private static List<State> generateNextStates(State currentState) {
        List<State> nextStates = new ArrayList<>();

        // Monkey moves
        addState(nextStates, currentState.monkeyX + 1, currentState.monkeyY, currentState);

        // Chair moves
        addState(nextStates, currentState.chairX + 1, currentState.chairY, currentState);

        return nextStates;
    }

    private static void addState(List<State> nextStates, int newX, int newY, State currentState) {
        if (isValidPosition(newX, newY)) {
            nextStates.add(new State(newX, newY, currentState.chairX, currentState.chairY, currentState.bananaX, currentState.bananaY));
        }
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 1 && x <= SIZE && y >= 1 && y <= SIZE;
    }

    private static void printPath(State finalState, HashMap<State, State> parentMap) {
        Stack<State> path = new Stack<>();
        State currentState = finalState;
        path.push(currentState);

        while (currentState != null) {
            currentState = parentMap.get(currentState);
            path.push(currentState);
        }

        while (!path.isEmpty()) {
            printState(path.pop());
        }
    }

    private static void printState(State state) {
        System.out.println("Monkey: (" + state.monkeyX + ", " + state.monkeyY + "), " +
                "Chair: (" + state.chairX + ", " + state.chairY + "), " +
                "Banana: (" + state.bananaX + ", " + state.bananaY + ")");
    }
}
