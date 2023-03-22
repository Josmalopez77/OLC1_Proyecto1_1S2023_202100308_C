/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;

import java.io.IOException;

/**
 *
 * @author Jose
 */
public class RegularExpression_DAO {
    
    //Instancia de nuestra clase RegularExpressionDAO
    private static RegularExpression_DAO instance;
    public static RegularExpression_DAO getInstance() {
        if(instance == null) {
            instance = new RegularExpression_DAO();            
        }
        return instance;
    }
    
    private RegularExpression[] regularExpressions; 
    private RegularExpression_DAO() {
        regularExpressions = new RegularExpression[500];
    }
    //Método para crear una nueva expresión regular, y meterla al arreglo.
    public void newRegularExpression(String codigo) throws IOException {
        for (int i = 0; i < regularExpressions.length; i++) {
            if (regularExpressions[i] == null) {
                regularExpressions[i] = new RegularExpression();
                break;
            }
        }
    }
}
