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
public class End {
    public int state;
    public List<String> terminalList = new ArrayList();
    
    public End(int state, List<String> terminalList){
        this.state = state;
        this.terminalList = terminalList;
    }
}
