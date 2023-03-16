/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;

/**
 *
 * @author Jose
 */
public class Statement {
    public String entry;
    public String name;
    
    
    public Statement(String name, String entry){
        this.name = name;
        this.entry = entry;
    }
    
    public static void newStatement(String name, String entry){
        MasterMindUI.statements.add(new Statement(name,entry));
        
    }
    
}
