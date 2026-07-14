package fidebank.francinim;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Mostrable,Comparable<Usuario>, Serializable {
    private String nombre;
    private int cedula;
    private int pin;

    //CONTRUCTOR
    public Usuario(String nombre, int cedula, int pin) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.pin = pin;
    }
   
    //GETTERS Y SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    
    //MÉTODOS
    
    public static void crearUsuario(List<Usuario> usuarios) {
        try {
            FileOutputStream archivo = new FileOutputStream("ListaUsuarios.dat");
            ObjectOutputStream output = new ObjectOutputStream(archivo);
            output.writeObject(usuarios);
            
            output.close();
            archivo.close();

        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    
     public static List<Usuario> LeerUsuario() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            FileInputStream archivo = new FileInputStream("ListaUsuarios.dat");
            ObjectInputStream input = new ObjectInputStream(archivo);
            usuarios = (List<Usuario>) input.readObject();
            input.close();
            archivo.close();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        } finally {
            return usuarios;
        }
    }
     
    public static Usuario iniciarSesion(int cedula, int pin) throws Exception {

        List<Usuario> usuarios = LeerUsuario();

        for (Usuario usuario : usuarios) {

            if (usuario.getCedula() == cedula && usuario.getPin() == pin) {
                return usuario;
            }

        }

        throw new Exception("Cédula o pin incorrectos.");
    } 
     
     
     @Override
    public String mostrarInformacion(){
        return "Nombre: "+this.nombre+"\nCédula: "+this.cedula+"\nPin: "+this.pin;   
    }

    @Override
    public int compareTo(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
}
