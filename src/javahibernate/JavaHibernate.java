/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahibernate;


import Mapping.Expedientes;
import Mapping.Usuarios;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.query.spi.HQLQueryPlan;

public class JavaHibernate {

    private static BufferedReader br;
    private static HQL hql;
        
    public static Expedientes exp = new Expedientes();
   public static Usuarios u = new Usuarios();

    public static void main(String[] args) {
        try {
            
            hql = new HQL();
            br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("[1] Registro");
            System.out.println("[2] Login");
            
            int opcion;

            opcion = pedirEntero("Elige una opción");
            
           
            switch (opcion) {
                
                case 1:
                    registro();
                    break;
                    
                case 2:
                    login();
                    break;
                    
                default: System.out.println("Opción incorrecta");
            }
            

        } catch (Exception e) {
        }

    }

    public static void registro() {
        try {
            
              System.out.println("-----Registro-----");
        System.out.println("");
        String nombre = pedirCadena("Nombre:");
        String apellidos = pedirCadena("Apellidos:");
        String dni = pedirCadena("Dni: ");
        String matricula = pedirCadena("Matricula: ");
        String password = pedirCadena("Password: ");    
        int tipoÙsuario = pedirEntero("Tipo de usuario: ");

        u.setNombre(nombre);
        u.setApellidos(apellidos);
        u.setDni(dni);
        u.setMatricula(matricula);
        u.setPass(password);
        u.setTipoUsuario(tipoÙsuario);
        
           hql.checkUser(u);
           
      
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      
        
       

    }

    public static void login() {

        System.out.println("-----Login-----");
        System.out.println("");
        String matricula = pedirCadena("Matricula:");
        String password = pedirCadena("Password:");
        
        hql.loginUser(matricula, password);
        
      
        
           switch(hql.token.getTipoUsuario()){
             
             case 1:
                 auxiliarMenu();
                 break;
                 
             case 2: 
                 vetMenu();
                 break;
             case 3: 
                 adminMenu();
         }
 
        
    }

    public static void auxiliarMenu() {
        int opcionAux;
        System.out.println("[1] Consultar Expedientes");
        System.out.println("[9] Cambiar de Usuario");
        opcionAux = pedirEntero("Elige una opción");
        switch(opcionAux){
            
            case 1:
                 hql.checkExpedientes();
                break;
        }

    }
    
    public static void bajaExpediente(){
        String id = pedirCadena("Escribe el Id del expediente");
        
        hql.deleteExpediente(id);
    }
    
    public static void altaExpediente(){
        String nombre = pedirCadena("Nombre: ");
        String apellidos = pedirCadena("Apellidos: ");
        String  dni = pedirCadena("Dni: ");
        String cp = pedirCadena("Código Postal: ");
        
        String tlf = pedirCadena("Teléfono: ");
        int nPets = pedirEntero("Número de mascotas");
        
        exp.setNombre(nombre);
        exp.setApellidos(apellidos);
        exp.setDni(dni);
        exp.setCp(cp);
        exp.setTelefono(tlf);
        exp.setNMascotas(nPets);
        
        
        hql.newExpediente(exp);

    }
    
      public void editExpediente(){
        hql.checkExpedientes();
        int id = pedirEntero("Elige el expediente que quieres cambiar");
        
    }
    public static void editUser(){
        hql.checkAllUsers();
        int id = pedirEntero("Elige el usuario que quieres cambiar");
        
        
        if(hql.checkUserById(id)){
           String nombre = pedirCadena("Nombre:");
        String apellidos = pedirCadena("Apellidos:");
        String dni = pedirCadena("Dni: ");
        String matricula = pedirCadena("Matricula: ");
        String password = pedirCadena("Password: ");    
        int tipoUsuario = pedirEntero("Tipo de usuario: ");

        u.setNombre(nombre);
        u.setApellidos(apellidos);
        u.setDni(dni);
        u.setMatricula(matricula);
        u.setPass(password);
        u.setTipoUsuario(tipoUsuario);
        
        hql.editUser(u);
        
        }else{
            System.out.println("La id no existe");
        }
        
    }
    
    public static void vetMenu() {
        int opcionVet;
        System.out.println("[1] Consultar Expedientes");
        System.out.println("[2] Alta expedientes");
        System.out.println("[3] Baja expedientes");
        System.out.println("[4] Editar expedientes");
        System.out.println("[9] Cambiar de Usuario");
        opcionVet = pedirEntero("Elige una opción");
        
        switch(opcionVet){
            case 1:
                hql.checkExpedientes();
                break;
            case 2: 
                altaExpediente();
                break;
                
            case 3: 
                bajaExpediente();
                break;
             
            case 4: 
                
                break;
                
             
            
            
        }
    }
    
  

    public static void adminMenu() {
        int opcionAdmin;
        System.out.println("[1] Consultar Expedientes");
        System.out.println("[2] Alta expedientes");
        System.out.println("[3] Baja expedientes");
        System.out.println("[4] Editar expedientes");
        System.out.println("[5] Alta Usuarios");
        System.out.println("[6] Baja Usuarios");
        System.out.println("[7] Editar Usuarios");
        System.out.println("[8] Consultar Usuarios");
        System.out.println("[9] Cambiar de Usuario");
        opcionAdmin = pedirEntero("Elige una opción");
        
        switch(opcionAdmin){
            case 1: 
                hql.checkExpedientes();
                break;
                
            case 2:
                altaExpediente();
                break;
                
             case 3:
                bajaExpediente();
                break;
                
                 case 4:
                     
                break;
                 case 5:
                registro();
                break;
                 case 6:
                bajaUsuario();
                break;
                 case 7:
                     editUser();
                break;
                     
                 case 8:
                     
                     hql.checkAllUsers();
                
        }
    }

    public static String pedirCadena(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cadena = "";
        do {
            try {
                System.out.println(texto);
                cadena = br.readLine();
                if (cadena.equals("")) {
                    System.out.println("No se puede dejar el campo en blanco.");
                }
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
            }
        } while (cadena.equals(""));
        return cadena;
    }
    
    public static void bajaUsuario(){
        String matricula = pedirCadena("Introduce la matrícula");
        
        hql.deleteUser(matricula);
    }

    public static int pedirEntero(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error;
        do {
            try {
                System.out.println(texto);
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Debes introducir un n�mero entero.");
                error = true;
            }
        } while (error);
        return num;
    }

}
