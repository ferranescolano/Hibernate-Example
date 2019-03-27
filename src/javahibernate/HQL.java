package javahibernate;

//TODO LIST
import Mapping.Expedientes;
import Mapping.Usuarios;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javahibernate.HibernateUtil;
import static javahibernate.JavaHibernate.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HQL {

    Date dateTime = new Date();
    public static Usuarios a;
    ArrayList<Usuarios> arrayUsuarios = new ArrayList<Usuarios>();
    static Usuarios token = new Usuarios();
    public static Expedientes e;

    public void checkUser(Usuarios u) {

        Session sesion = HibernateUtil.createSessionFactory().openSession();
        String sentencia = "FROM Usuarios where matricula = '" + u.getMatricula() + "'";
        Query q = sesion.createQuery(sentencia);

        List lista = q.list();
        if (lista.size() == 0) {

            System.out.println("No hay Usuarios");
            System.out.println("Creando usuario");
            arrayUsuarios.add(u);

            insertUser(u);

        } else {
            System.out.println("El usuario ya existe en la base de datos");
        }

    }

    public static void loginUser(String matricula, String password) {

        Session sesion = HibernateUtil.createSessionFactory().openSession();
        String sentencia = "FROM Usuarios where matricula = '" + matricula + "' and pass = '" + password + "'";
        Query q = sesion.createQuery(sentencia);

        List lista2 = q.list();

        if (lista2.size() == 1) {

            token = (Usuarios) q.uniqueResult();
            System.out.println("Bienvenido, " + token.getNombre());

        } else {
            System.out.println("Matricula o contraseña incorrectos");
        }
    }
    
    public static boolean checkUserById(int id){
        
                  Session sesion = HibernateUtil.createSessionFactory().openSession();

                  String sentencia = "FROM Usuarios where id ='" + id + "'";
                  
                  Query q = sesion.createQuery(sentencia);
                  
                  List lista2 = q.list();
                  if(lista2.size() == 1){
                     return true; 
                  }else{
                      return false;
                  }
    }
    
    
    public void deleteExpediente(String id){
        Session sesion  = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        String sentencia = "FROM Expedientes where id = '" + id + "'";
        Query q = sesion.createQuery(sentencia);
        
        e = (Expedientes) q.uniqueResult();
        sesion.delete(e);
        tx.commit();
        sesion.close();
        System.out.println("Expediente eliminado");
        
    }
      public void deleteUser(String matricula){
        
                Session sesion = HibernateUtil.createSessionFactory().openSession();
                Transaction tx = sesion.beginTransaction();
                String sentencia = "FROM Usuarios where matricula = '" + matricula + "'";
                Query q = sesion.createQuery(sentencia);
                

                a = (Usuarios) q.uniqueResult();
                sesion.delete(a);
                tx.commit();
                sesion.close();
                
                System.out.println("Usuario eliminado");
                
                
    }
      
      public void checkAllUsers(){
          Session sesion = HibernateUtil.createSessionFactory().openSession();
          String sentencia = "FROM Usuarios";
          Query q = sesion.createQuery(sentencia);
          List<Usuarios> arrayUsuarios = q.list();
          int contador = 0;
          
          for(Usuarios a: arrayUsuarios){
              contador++;
              System.out.println(" [" + contador + "] Nombre: " + a.getNombre() + ", Apellido: " + a.getApellidos()
                      + ", DNI: " + a.getDni() + ", Matricula: " + a.getMatricula() + ", Pass: " + a.getPass()
                      + ", Tipo Usuario" + a.getTipoUsuario() + ", Ult. Acceso: " + a.getUltimoAcceso());
          }
      }

      public void editUser(Usuarios u){
         Session sesion = HibernateUtil.createSessionFactory().openSession();
         Transaction tx = sesion.beginTransaction();
          String nombreUsu = u.getNombre();
        String apellidosUsu = u.getApellidos();
        String dniUsu = u.getDni();
        String matriculaUsu = u.getMatricula();
        String passwordUsu = u.getPass();
        int tipoUsu = u.getTipoUsuario();
        Date ultAccesoUsu = u.getUltimoAcceso();
        
  Usuarios a = new Usuarios(nombreUsu, apellidosUsu, dniUsu, matriculaUsu, passwordUsu, tipoUsu, ultAccesoUsu);          
  
     sesion.saveOrUpdate(a);
      tx.commit();
        sesion.close();
                System.out.println("Usuario editado");

     
      }
      
      
      public void editExpedientes(){
          
          
      }
    
      
      public void checkExpedientesById(int id){
          
          
      }
    public void checkExpedientes() {
        Session sesion = HibernateUtil.createSessionFactory().openSession();
        String sentencia = "FROM Expedientes";
        Query q = sesion.createQuery(sentencia);

        List<Expedientes> arrayExpedientes = q.list();
        int contador = 0;

        for (Expedientes e : arrayExpedientes) {
            contador ++;
            System.out.println(" [" + contador + "] Nombre: " + e.getNombre() + ", Apellidos: " + e.getApellidos()
                    + ", DNI: " + e.getDni() + ", CP: " + e.getCp() + ", Fecha-Alta: " + e.getFechaAlta()
                    + "Telefono: " + e.getTelefono() + ", Num. Mascotas: " + e.getNMascotas() + ", Usuario: " + e.getUsuarios().getNombre());
        }

    }

    public void newExpediente(Expedientes exp) {

        Session sesion = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        String nombreExp = exp.getNombre();
        String apellidoExp = exp.getApellidos();
        String dniExp = exp.getDni();
        String cpExp = exp.getCp();
        Date fechaAlta = dateTime;
        String tlfExp = exp.getTelefono();
        int nPetsExp = exp.getNMascotas();

        Expedientes e = new Expedientes(token, nombreExp, apellidoExp, dniExp, cpExp, fechaAlta, tlfExp, nPetsExp);

        sesion.save(e);
        tx.commit();
        sesion.close();

        System.out.println("Expediente insertado");

    }
    
  
    public void insertUser(Usuarios u) {

        Session sesion = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        String nombreUsu = u.getNombre();
        String apellidosUsu = u.getApellidos();
        String dniUsu = u.getDni();
        String matriculaUsu = u.getMatricula();
        String passwordUsu = u.getPass();
        int tipoUsu = u.getTipoUsuario();
        Date ultAccesoUsu = u.getUltimoAcceso();

        Usuarios a = new Usuarios(nombreUsu, apellidosUsu, dniUsu, matriculaUsu, passwordUsu, tipoUsu, ultAccesoUsu);
        sesion.save(a);
        tx.commit();
        sesion.close();

        System.out.println("Usuario insertado");
    }

}
