
package exregan;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Erwin14k
 */
public class MainLogin extends JFrame {
    String user="erwin";
    String password="123";
    public static void main(String[] args) throws IOException {
            pLoginFrame();
        }
     public static void pLoginFrame() throws IOException{
        
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,20);
        
        //Instancia de la lámina
        //SheetsLogin sheet1= new SheetsLogin();
        
        //=========================Creación del Frame Login Principal=========================
        
        //Se crea el frame y se le agrega un título
        JFrame loginFrame = new JFrame("ExpAnalyzer----Jose Lopez");
        loginFrame.setLayout(null);
        //Se hace visible el frame
        loginFrame.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        loginFrame.setResizable(false);
        //Se le coloca un color de fondo al frame
        loginFrame.getContentPane().setBackground(Color.GRAY);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame login
        loginFrame.setSize(1080,720);
        //Le agreamos una locación al frame login
        loginFrame.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame login
        Image myIcon= myScreen.getImage("iconoLogin.png");
        loginFrame.setIconImage(myIcon);
        //loginFrame.setLayout(new BorderLayout());
        
        
        
        
        
   
        
        //=========================Creación de Labels =========================
        //label que nos muestra el mensaje "DTT"
        JLabel titleL = new JLabel("ExpAnalyzer ");
        titleL.setLayout(null);
        titleL.setVisible(true);
        titleL.setForeground(Color.BLACK);
        titleL.setBounds(470,100,250,60);
        titleL.setFont(font);
        loginFrame.add(titleL);
        //label que dice ingresar el nombre de usuario/ Código
        JLabel usernameL = new JLabel("Ingresa tu usuario: ");
        usernameL.setLayout(null);
        usernameL.setVisible(true);
        usernameL.setForeground(Color.BLACK);
        usernameL.setBounds(220,300,250,30);
        usernameL.setFont(font2);
        loginFrame.add(usernameL);
        //label que dice ingresar contraseña
        JLabel passwordL = new JLabel("Ingresa tu Contraseña: ");
        passwordL.setLayout(null);
        passwordL.setVisible(true);
        passwordL.setForeground(Color.BLACK);
        passwordL.setBounds(220,400,300,30);
        passwordL.setFont(font2);
        loginFrame.add(passwordL);
        
        
        //=========================Creación de los campos de texto=========================
        //Creamos el campo de texto que recibirá el nombre de usuario
        JTextField usernameTF = new JTextField();
        usernameTF.setLayout(null);
        usernameTF.setVisible(true);
        usernameTF.setBounds(520,300,250,30);
        loginFrame.add(usernameTF);
        
        //Creamos el campo de texto que recibirá la contraseña
        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBounds(520,400,250,30);
        passwordTF.setVisible(true); 
        passwordTF.setLayout(null);
        loginFrame.add(passwordTF);
        //=========================Creación del Botón Inicio de sesión=========================
        //Creamos un botón de inicio de Sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setLayout(null);
        loginButton.setVisible(true);
        loginButton.setBounds(470, 550, 150, 60);
        loginButton.addMouseListener(new MouseAdapter()  
                {  
                    public void mouseClicked(MouseEvent e)  
                    {  
                        try {
                            LoginMethod login_handler = LoginMethod.getInstance();
                            if (login_handler.loginAuthentication(usernameTF.getText(), passwordTF.getText())) {
                                System.out.println("Bievenido");
                                JOptionPane.showMessageDialog(null, "Bienvenido!!");
                                loginFrame.dispose();
                                MasterMindUI masterHandler = new MasterMindUI();
                                masterHandler.pMasterMindFrame();
                                
                            }else{
                                JOptionPane.showMessageDialog(null, "Código/Contraseña Incorrectos, Intente de nuevo por favor"); 
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(MainLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            }); 
        loginFrame.add(loginButton);
        
        //=========================Generación del Repintado=========================
       
        /* Este método provoca una llamada al método de pintura de este componente
        lo antes posible. De lo contrario, este método provoca una llamada al
        método de actualización de este componente lo antes posible.*/
        loginFrame.repaint();
        
        
      
                }
}
