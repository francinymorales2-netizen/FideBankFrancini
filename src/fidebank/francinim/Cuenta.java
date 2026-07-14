package fidebank.francinim;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Cuenta implements Mostrable{
    private int Id;
    private String nombreCuenta;
    private double saldo;
    private Usuario usuario;
    
    private int generarId(){
        return (int)(Math.random()*10000);
    }
    //CONTRUCTOR

    public Cuenta( String nombreCuenta, double saldo, Usuario usuario) {
        this.Id = generarId();
        this.nombreCuenta = nombreCuenta;
        this.saldo = saldo;
        this.usuario = usuario;
    }

    //GETTERS Y SETTERS
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    //MÈTODOS
    
    public static void agregarCuenta(List<Cuenta> cuentas) {
        try {
            FileOutputStream archivo = new FileOutputStream("ListaCuentas.dat");
            ObjectOutputStream output = new ObjectOutputStream(archivo);
            output.writeObject(cuentas);
            
            output.close();
            archivo.close();

        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    
     public static List<Cuenta> LeerCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();

        try {
            FileInputStream archivo = new FileInputStream("ListaCuentas.dat");
            ObjectInputStream input = new ObjectInputStream(archivo);
            cuentas = (List<Cuenta>) input.readObject();
            input.close();
            archivo.close();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        } finally {
            return cuentas;
        }
    }
     
     
    public static void eliminarCuenta(int numeroCuenta) throws Exception {

        List<Cuenta> cuentas = LeerCuentas();

        Cuenta cuentaEliminar = null;

        for (Cuenta cuenta : cuentas) {

            if (cuenta.getId() == numeroCuenta) {

                cuentaEliminar = cuenta;
                break;

            }

        }

        if (cuentaEliminar == null) {
            throw new Exception("La cuenta no existe.");
        }

        cuentas.remove(cuentaEliminar);

        agregarCuenta(cuentas);

    }
     
     
    
    
    
     @Override
    public String mostrarInformacion(){
        return "Usuario: "+this.usuario.getNombre()+"\nID: "+this.Id+
                "\nNombre de la cuenta: "+this.nombreCuenta+"\nSaldo: "+this.saldo;   
    }
      
    
}
