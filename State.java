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
public class State {
    int width = 3;
    int map[][] = new int[width][width];
    int value;
    public void Print(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                switch(map[i][j]){
                    case 0: 
                        System.out.print(" _ ");
                        break;
                    case 1:
                        System.out.print(" o ");
                        break;
                    case 2: 
                        System.out.print(" x ");
                }
            }
            System.out.println();
        }
    }
    
    public State clone(){
        State tmp = new State();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                tmp.map[i][j] = this.map[i][j];
            }
        }
        return tmp;
    }
}
