package ArtificialIntelligence.Assignment1;

import java.util.*;

class State {
    int jugA;
    int jugB;
    List<String> actions;

    State(int a, int b, List<String> act) {
        jugA = a;
        jugB = b;
        actions = new ArrayList<>(act);
    }
}

class StateWithHeuristic implements Comparable<StateWithHeuristic> {
    State state;
    int heuristic;

    StateWithHeuristic(State s, int tgt) {
        state = s;
        heuristic = Math.abs(s.jugA - tgt) + Math.abs(s.jugB - tgt);
    }

    public int compareTo(StateWithHeuristic other) {
        return Integer.compare(heuristic, other.heuristic);
    }
}

public class Assignment3BF {
    public static List<String> waterJugBestFirst(int jugACapacity, int jugBCapacity, int target) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<StateWithHeuristic> pq = new PriorityQueue<>();

        pq.add(new StateWithHeuristic(new State(0, 0, new ArrayList<>()), target));

        while (!pq.isEmpty()) {
            StateWithHeuristic current = pq.poll();

            if (current.state.jugA == target || current.state.jugB == target) {
                return current.state.actions;
            }

            visited.add(current.state.jugA + "," + current.state.jugB);

            List<Map.Entry<String, int[]>> possibleActions = List.of(
                    Map.entry("Fill A", new int[]{jugACapacity, current.state.jugB}),
                    Map.entry("Fill B", new int[]{current.state.jugA, jugBCapacity}),
                    Map.entry("Empty A", new int[]{0, current.state.jugB}),
                    Map.entry("Empty B", new int[]{current.state.jugA, 0}),
                    Map.entry("Pour A to B", new int[]{
                            Math.max(0, current.state.jugA - (jugBCapacity - current.state.jugB)),
                            Math.min(jugBCapacity, current.state.jugB + current.state.jugA)}),
                    Map.entry("Pour B to A", new int[]{
                            Math.min(jugACapacity, current.state.jugA + current.state.jugB),
                            Math.max(0, current.state.jugB - (jugACapacity - current.state.jugA))}));

            for (var actionData : possibleActions) {
                int newA = actionData.getValue()[0];
                int newB = actionData.getValue()[1];
                String stateStr = newA + "," + newB;

                if (!visited.contains(stateStr)) {
                    List<String> newActions = new ArrayList<>(current.state.actions);
                    newActions.add(actionData.getKey());
                    State newState = new State(newA, newB, newActions);
                    pq.add(new StateWithHeuristic(newState, target));
                    visited.add(stateStr);
                }
            }
        }

        return Collections.emptyList(); // Solution not found
    }

    public static void main(String[] args) {
        int jugACapacity = 5;
        int jugBCapacity = 3;
        int target = 2;
        List<String> solution = waterJugBestFirst(jugACapacity, jugBCapacity, target);

        if (!solution.isEmpty()) {
            System.out.println("Solution found:");
            for (int step = 0; step < solution.size(); ++step) {
                System.out.println("Step " + (step + 1) + ": " + solution.get(step));
            }
        } else {
            System.out.println("Solution not possible.");
        }
    }
}
