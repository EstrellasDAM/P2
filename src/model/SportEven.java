/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * SportEven is a class to work with sports that require an even number 
 * of players to be able to play.
 * 
 * @see Sport
 * 
 * @author mfontana
 */
public class SportEven extends Sport {
    
    /**
     * Construct a new SportEven whith name and minPlayers. The list of  
     * registered students is initialized with an empty list.
     * 
     * @param name - String
     * @param minPlayers - int
     */
    public SportEven(String name, int minPlayers) {
        super(name, minPlayers);
    }

    /**
     * Returns true if the sport can be practiced, that is, if the number 
     * of students enrolled is greater than or equal to the minimum number 
     * of players needed (minPlayers) and the number of students enrolled 
     * is even.
     * 
     * @return boolean
     */
    @Override
    public boolean isPossible() {
        return (super.isPossible() && getStudents().size() % 2 == 0);
    }

    
    
    
}
