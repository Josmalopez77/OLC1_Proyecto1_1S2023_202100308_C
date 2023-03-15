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
public class Transition {
    public int state;
    public String terminal;
    public List<String>accepted = new ArrayList();
    
    public Transition(int state, String terminal){
        this.state = state;
        this.terminal = terminal;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public List<String> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<String> accepted) {
        this.accepted = accepted;
    }
    
}
