/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exregan;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author Jose
 */
public class Error_DAO {
    private static Error_DAO instance;
    
    
    public static Error_DAO getInstance() {
        if(instance == null) {
            instance = new Error_DAO();            
        }
        return instance;
    }

        
    private Error[] errors;
    
    private Error_DAO() {
        errors = new Error[150];
    }
     
    
    public void newError(String type, String description, int row, int column ){
        for (int i = 0; i < errors.length; i++) {
            if (errors[i] == null) {
                errors[i] = new Error(type, description, row, column);
                break;
            }
        }

    }
    
    
    public  void errorsReport() {
        FileWriter powerful = null;
        PrintWriter writter = null;
         Date date = new Date();
         String datee = String.valueOf(date);
        try {
            powerful = new FileWriter("./Reportes/ERRORES/Errores.html");
            
            writter = new PrintWriter(powerful);
            writter.println(datee);
            writter.println("<!DOCTYPE html><!--Declarar el tipo de cumento -->\n"
                    + "<html>\n"
                    + "\n"
                    + "<!--Encabezado-->\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\"><!--codififcaion de caracteres ñ y á-->\n"
                    + "\n"
                    + "\n"
                    + "<meta name=\"name\" content=\"Reporte\">\n"
                    + "<meta name=\"description\" content=\"name\">\n"
                    + "<meta name=\"keywods\" content=\"uno,dos,tres\">\n"
                    + "<meta name=\"robots\" content=\"Index, Follow\">\n"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "\n"
                    + "\n"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"Style.css\"/><!--css /estilo/tipo/ruta relativa -->\n"
                    + "\n"
                    + "<title>Reporte De Errores</title>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body bgcolor=”#6CFC07”>\n"
                    + "\n"
                    + "<center>");
            writter.println("<h1>" + "Reportes De Errores Compiladores 1 " + "</h1>");
            writter.println("<table width=\"500\" border=\"2\" cellpadding=\"5\">");
            writter.println("<tr>");
            writter.println("<th>TIPO</th>");
            writter.println("<th>DESCRIPCIÓN</th>");
            writter.println("<th>FILA</th>");
            writter.println("<th>COLUMNA</th>");
            writter.println(" </tr>");
            if(errors[0]!=null){
                System.out.println("EnseriO???");
                System.out.println(errors[0]);
                for (int i = 0; i < errors.length; i++) {
                    if(errors[i]!=null){
                        writter.println("<tr>");
                        writter.println("<td>"+errors[i].getType()+"</td>");
                        writter.println("<td>"+errors[i].getDescription()+"</td>");
                        writter.println("<td>"+errors[i].getRow()+"</td>");
                        writter.println("<td>"+errors[i].getColumn()+"</td>");
                        writter.println(" </tr>");
                        }else{
                        break;
                    }
                }
            }
                    
                  
           
            
            writter.println("</center>\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");
              } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (null != powerful) {
                    powerful.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
