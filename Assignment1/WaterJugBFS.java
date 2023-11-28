package ArtificialIntelligence.Assignment1;

import java.util.*;

public class WaterJugBFS {
    public static void main(String[] args) {
        int jug1 = 5;
        int jug2 = 7;
        int target = 3;
//        solveWaterJugBFS(jug1, jug2, target);
        solveWaterJugDFS(jug1, jug2, target);
    }


    static private class State{
        int jug1;
        int jug2;

        public State(int jug1, int jug2) {
            this.jug1 = jug1;
            this.jug2 = jug2;
        }
    }

    public static void solveWaterJugDFS(int jug1, int jug2, int target){
        State initialState = new State(0,0);
        Set<State> visited = new HashSet<>();
        HashMap<State, State> map = new HashMap<>();

        if(dfs(initialState, jug1, jug2, target, visited, map)){
            printAnswerDfs(map);
        }else{
            System.out.println("No Solution Exists");
        }
    }

    public static boolean dfs(State currentState, int jug1, int jug2, int target, Set<State> visited, HashMap<State,
            State> parentMap){
        // base case
        if(currentState.jug1 == target || currentState.jug2 == target){
            return true;
        }

        visited.add(currentState);

        for (int i = 1; i <= 6 ; i++) {
            State nextState = operation(currentState, i, jug1, jug2);

            if(!visited.contains(nextState) && dfs(nextState, jug1, jug2, target, visited, parentMap)){
                parentMap.put(nextState, currentState);
                return true;
            }
        }

        return false;
    }

    public static void solveWaterJugBFS(int jug1, int jug2, int target){
        State initialState = new State(0, 0);
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        HashMap<State , State> parentMap = new HashMap<>();

        queue.add(initialState);
        visited.add(initialState);

        // Implementing BFS
        while(!queue.isEmpty()){
            State currentState = queue.poll();

            // base case
            if(currentState.jug1 == target || currentState.jug2 == target){
                // printing the solution
                printAnswer(currentState, parentMap);
                break;
            }

            for (int i = 1; i <=6; i++) {
                State nextState = operation(currentState, i, jug1, jug2);

                if(!visited.contains(nextState)){
                    queue.add(nextState);
                    visited.add(nextState);
                    parentMap.put(nextState, currentState);
                }
            }
        }
    }


    public static State operation(State currentState, int operation, int jug1Capacity, int jug2Capacity){
        int jug1 = currentState.jug1;
        int jug2 = currentState.jug2;

        if(operation == 1){
            jug1 = jug1Capacity;
        }else if(operation == 2){
            jug2 = jug2Capacity;
        }else if(operation == 3){
            jug1 = 0;
        }else if(operation == 4){
            jug2 = 0;
        }else if(operation == 5){
            // from jug1 to jug2
            int pourAmount = Math.min(jug1, jug2Capacity - jug2);
            jug1 -= pourAmount;
            jug2 += pourAmount;
        }else if(operation == 6){
            int pourAmount = Math.min(jug2, jug1Capacity - jug1);
            jug1 += pourAmount;
            jug2 -= pourAmount;
        }

        return new State(jug1, jug2);
    }

    public static void printAnswerDfs(HashMap<State, State> parentMap){
        List<State> ans = new ArrayList<>();
        State init = new State(0,0);

        while (parentMap.containsKey(init)){
            init = parentMap.get(init);
            ans.add(init);
        }

        System.out.println("Solution: ");
        for(State x : ans){
            System.out.println("Jug 1: " + x.jug1 + " | Jug 2: " + x.jug2);
        }

    }

    public static void printAnswer(State currentState, HashMap<State, State> parentMap){
        List<State> ans = new ArrayList<>();
        ans.add(currentState);

        while (parentMap.containsKey(currentState)){
            currentState = parentMap.get(currentState);
            ans.add(currentState);
        }

        Collections.reverse(ans);
        System.out.println("Solution: ");
        for(State x : ans){
            System.out.println("Jug 1: " + x.jug1 + " | Jug 2: " + x.jug2);
        }
    }
}

