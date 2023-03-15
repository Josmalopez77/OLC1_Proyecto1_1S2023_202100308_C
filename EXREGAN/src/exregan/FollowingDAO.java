/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;

/**
 *
 * @author Jose
 */
public class FollowingDAO {
    private static FollowingDAO instance;
    public static FollowingDAO getInstance() {
        if(instance == null) {
            instance = new FollowingDAO();            
        }
        return instance;
    }
    
    
}
