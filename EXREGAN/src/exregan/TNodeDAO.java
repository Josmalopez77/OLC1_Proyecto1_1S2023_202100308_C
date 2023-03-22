/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;
import static exregan.Node.countAfd;
import static exregan.Node.dotAfd;
import static exregan.Node.terminalList;
import static exregan.Node.theFollowing;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class TNodeDAO {
    //Instancia de nuestra clase TNodeDAO
    private static TNodeDAO instance;
    public static TNodeDAO getInstance() {
        if(instance == null) {
            instance = new TNodeDAO();            
        }
        return instance;
    }
    public Node[] regularExpressions; 
    private TNodeDAO() {
        regularExpressions = new Node[500];
    }
    //Método para crear una nueva expresión regular, y meterla al arreglo.
    public void newRegularExpression(Node leftTag,Node rightTag, String value, int id, String voidability,String previous,String following, int counter) throws IOException {
        for (int i = 0; i < regularExpressions.length; i++) {
            if (regularExpressions[i] == null) {
                regularExpressions[i] = new Node(leftTag, rightTag,value,id,voidability,previous,following,counter);
                break;
            }
        }
    }
    
    
}
