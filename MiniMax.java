/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DuyN
 */
public class MiniMax {
    Scanner sc;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MiniMax();
    }
    public MiniMax(){
        sc = new Scanner(System.in);
        int turn = 0;
        int player = 1;
        State game = new State();
        game.Print();
        while(!Win(game) && !isFull(game)){
            if(turn % 2 + 1 == player){
                System.out.println("Type ......");
                int x = sc.nextInt();
                int y = sc.nextInt();
                State tmp = (new Operator(x, y)).Move(game);
                if(tmp == null){
                    continue;
                }
                game = tmp;
                game.Print();
                if (Win(game)){
                    System.out.println("Player Won!");
                    return;
                }
            } else {
                System.out.println("AI turn ... ");
                State minChild = null;
                int min = Integer.MIN_VALUE;
                int depth = 5;
                for(int i = 0; i < game.width; i++){
                    for(int j = 0; j < game.width; j++){
                        State child = (new Operator(i, j)).Move(game);
                        if (child != null){
                            int tmp = MiniMaxAl(child, depth, false);
//                            System.out.println(tmp);
//                            child.Print();
//                            System.out.println();
                            minChild = min < tmp ? child : minChild;
                            min = Math.max(min, tmp);
//                            System.out.println("min: " + min);
                        }
                    }
                }
                game = minChild;
                game.Print();
                if (Win(game)){
                    System.out.println("AI Won!");
                    return;
                }
            }
            turn++;
        }
        System.out.println("Draw!");
    }
    private int MiniMaxAl(State child, int depth, boolean MP) {
        return AlphaBeta(child, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, MP);
    }

    private int AlphaBeta(State node, int depth, int min, int max, boolean MP) {
        if (isEndNode(node) || depth == 0){
            return Value(node);
        }
        ArrayList<State> childList = new ArrayList<State>();
        for(int i = 0; i < node.width; i++){
            for(int j = 0; j < node.width; j++){
                State tmp = (new Operator(i, j)).Move(node);
                if (tmp != null){
                    childList.add(tmp);
                }
            }
        }
        if (MP){
            for (State child : childList) {
                min = Math.max(min, AlphaBeta(child, depth - 1, min, max, false));
                if (min >= max){
                    break;
                }
            }
            return min;
        } else {
            for (State child : childList) {
                max = Math.min(max, AlphaBeta(child, depth - 1, min, max, true));
                if (min >= max){
                    break;
                }
            }
            return max;
        }
    }

    private boolean isEndNode(State node) {
        return Win(node) || isFull(node);
    }

    private int Value(State node) {
        int count = 0;
        for(int i = 0; i < node.width; i++){
            for(int j = 0; j < node.width; j++){
                if (node.map[i][j] != 0){
                    count++;
                }
            }
        }
        if (Win(node)){
            if (count % 2 == 0){
                return 2;
            } else {
                return -2;
            }
        } else {
            return 0;
        }
    }

    private int seemWin(State node){
        for(int i = 0; i < node.width - 1; i++){
            for(int j = 0; j < node.width - 1; j++){
                if (node.map[i][j] == node.map[i][j + 1] || node.map[i][j] == node.map[i + 1][j] || node.map[i][j] == node.map[i + 1][j + 1]){
                    if (node.map[i][j]==1) return -1; 
                    else  if (node.map[i][j]==2) return 1;
                }
            }
        }
        return 0;
    }
    private boolean Win(State node) {
        for(int i = 0; i < node.width; i++){
            if (node.map[i][0] != 0 && node.map[i][0] == node.map[i][1] && node.map[i][2] == node.map[i][0]){
                return true;
            }
            if (node.map[0][i] != 0 && node.map[0][i] == node.map[1][i] && node.map[2][i] == node.map[0][i]){
                return true;
            }
        }
        if (node.map[0][0] == node.map[1][1] && node.map[0][0] == node.map[2][2] && node.map[0][0] != 0){
            return true;
        }
        if (node.map[2][0] == node.map[1][1] && node.map[2][0] == node.map[0][2] && node.map[2][0] != 0){
            return true;
        }
        return false;
    }
    private boolean isFull(State node){
        for(int i = 0; i < node.width; i++){
            for(int j = 0; j < node.width; j++){
                if (node.map[i][j] != 0){
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}
