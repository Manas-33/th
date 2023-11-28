package ArtificialIntelligence.All.waterjug;

import java.util.*;

class State1 {
    int jug1;
    int jug2;
    ArrayList<String> steps;

    State1(int jug1, int jug2, ArrayList<String> steps) {
        this.jug1 = jug1;
        this.jug2 = jug2;
        this.steps = new ArrayList<>(steps);
    }
}

public class WaterJugBFS {

    static boolean isGoalState(int jug1, int jug2, int targetAmount) {
        return jug1 == targetAmount || jug2 == targetAmount;
    }

    static void BFSWaterJug(int jug1Capacity, int jug2Capacity, int targetAmount) {
        Queue<State1> bfsQueue = new LinkedList<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();

        State1 initialState = new State1(0, 0, new ArrayList<>());
        bfsQueue.add(initialState);

        while (!bfsQueue.isEmpty()) {
            State1 current = bfsQueue.poll();

            if (isGoalState(current.jug1, current.jug2, targetAmount)) {
                System.out.println("Goal reached in " + current.steps.size() + " steps.");
                for (int i = 0; i < current.steps.size(); ++i) {
                    System.out.println((i + 1) + ". " + current.steps.get(i));
                }
                return;
            }

            visited.add(new Pair<>(current.jug1, current.jug2));

            // Generate successors
            int[][] successors = {
                    {jug1Capacity, current.jug2},           // Fill jug1
                    {current.jug1, jug2Capacity},           // Fill jug2
                    {0, current.jug2},                       // Empty jug1
                    {current.jug1, 0},                       // Empty jug2
                    {Math.min(jug1Capacity, current.jug1 + current.jug2),
                            Math.max(0, current.jug1 + current.jug2 - jug1Capacity)},  // Pour jug2 into jug1
                    {Math.max(0, current.jug1 + current.jug2 - jug2Capacity),
                            Math.min(jug2Capacity, current.jug1 + current.jug2)}       // Pour jug1 into jug2
            };

            String[] actionNames = {"Fill jug1", "Fill jug2", "Empty jug1", "Empty jug2",
                    "Pour jug2 into jug1", "Pour jug1 into jug2"};

            for (int i = 0; i < successors.length; ++i) {
                int newJug1 = successors[i][0];
                int newJug2 = successors[i][1];

                if (!visited.contains(new Pair<>(newJug1, newJug2))) {
                    ArrayList<String> newSteps = new ArrayList<>(current.steps);
                    String transitionStep = actionNames[i] + " ("
                            + current.jug1 + ", " + current.jug2 + ") -> ("
                            + newJug1 + ", " + newJug2 + ")";
                    newSteps.add(transitionStep);
                    State1 newState = new State1(newJug1, newJug2, newSteps);
                    bfsQueue.add(newState);
                }
            }
        }

        System.out.println("Goal not reachable.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter capacities of jug 1 and jug 2: ");
        int jug1Capacity = scanner.nextInt();
        int jug2Capacity = scanner.nextInt();
        System.out.print("Enter target amount: ");
        int targetAmount = scanner.nextInt();

        BFSWaterJug(jug1Capacity, jug2Capacity, targetAmount);
    }

    static class Pair<K, V> {
        K first;
        V second;

        Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
