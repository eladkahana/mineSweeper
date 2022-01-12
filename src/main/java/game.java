/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author כהנא אלעד
 */
public class game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            mineSweeper game = new mineSweeper(5,5,25);
        } catch (Exception e) {
            mineSweeper.exceptionCreateBoard();
        }
        
        
        
    }
    
}
