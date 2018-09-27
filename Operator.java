/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax;

/**
 *
 * @author DuyN
 */
public class Operator {
    int x;
    int y;
    public Operator(int x, int y){
        this.x = x;
        this.y = y;
    }
    public State Move(State state){
        if (x < 0 || x >= state.width){
            return null;
        }
        if (y < 0 || y >= state.width){
            return null;
        }
        if (state.map[x][y] != 0){
            return null;
        }
        State tmp = state.clone();
        int count = 0;
        for (int i = 0; i < state.width; i++){
            for(int j = 0; j < state.width; j++){
                if (state.map[i][j] != 0){
                    count++;
                }
            }
        }
        if(count % 2 == 0){
            tmp.map[x][y] = 1;
        } else {
            tmp.map[x][y] = 2;
        }
        return tmp;
    }
}
