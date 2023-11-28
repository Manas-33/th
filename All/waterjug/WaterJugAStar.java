package ArtificialIntelligence.All.waterjug;
import java.util.*;

public class WaterJugAStar {
    private static class State {
        int jug1, jug2;
        int cost;

        public State(int jug1, int jug2, int cost) {
            this.jug1 = jug1;
            this.jug2 = jug2;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            State state = (State) obj;
            return jug1 == state.jug1 && jug2 == state.jug2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(jug1, jug2);
        }
    }
    public static void main(String[] args) {
        int jug1Capacity = 4; // Capacity of the first jug
        int jug2Capacity = 3; // Capacity of the second jug
        int targetAmount = 2; // Target amount of water to be measured

        State initialState = new State(0, 0, 0);
        PriorityQueue<State> openList = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        Set<State> closedList = new HashSet<>();
        Map<State, State> parentMap = new HashMap<>();

        openList.add(initialState);

        while (!openList.isEmpty()) {
            State currentState = openList.poll();

            if (currentState.jug1 == targetAmount || currentState.jug2 == targetAmount) {
                // We found a solution
                printSolution(currentState, parentMap);
                return;
            }

            closedList.add(currentState);

            // Try all possible operations: fill, empty, or pour
            for (int operation = 1; operation <= 6; operation++) {
                State nextState = applyOperation(currentState, operation, jug1Capacity, jug2Capacity);

                if (!closedList.contains(nextState)) {
                    nextState.cost = calculateHeuristic(nextState, targetAmount);
                    openList.add(nextState);
                    parentMap.put(nextState, currentState);
                }
            }
        }

        System.out.println("No solution found.");
    }

    public static State applyOperation(State currentState, int operation, int jug1Capacity, int jug2Capacity) {
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

        return new State(jug1, jug2, 0);
    }

    public static int calculateHeuristic(State state, int targetAmount) {
        int distanceToTarget = Math.abs(state.jug1 - targetAmount) + Math.abs(state.jug2 - targetAmount);
        return state.cost + distanceToTarget;
    }

    public static void printSolution(State currentState, Map<State, State> parentMap) {
        List<State> solutionPath = new ArrayList<>();
        solutionPath.add(currentState);

        while (parentMap.containsKey(currentState)) {
            currentState = parentMap.get(currentState);
            solutionPath.add(currentState);
        }

        Collections.reverse(solutionPath);

        System.out.println("Solution Path:");
        for (State state : solutionPath) {
            System.out.println("Jug 1: " + state.jug1 + " | Jug 2: " + state.jug2);
        }
    }
}