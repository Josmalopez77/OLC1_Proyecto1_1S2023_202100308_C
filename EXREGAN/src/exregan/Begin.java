/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Begin {
    public String name;
    public End transition;
    
    public static List<Begin> estates = new ArrayList();
    
    public Begin(String name, End transition){
        this.name = name;
        this.transition = transition;
    }
    
}
