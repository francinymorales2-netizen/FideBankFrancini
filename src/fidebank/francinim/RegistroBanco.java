package fidebank.francinim;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
public  class RegistroBanco {
    public static void guardarTransacciones(List<Transaccion> transacciones) {

        try {

            FileOutputStream archivo = new FileOutputStream("ListaTransacciones.dat");
            ObjectOutputStream output = new ObjectOutputStream(archivo);

            output.writeObject(transacciones);

            output.close();
            archivo.close();

        } catch (IOException ex) {

            System.out.println("Exception: " + ex.getMessage());

        }

    }

  
    public static List<Transaccion> leerTransacciones() {

        List<Transaccion> transacciones = new ArrayList<>();

        try {

            FileInputStream archivo = new FileInputStream("ListaTransacciones.dat");
            ObjectInputStream input = new ObjectInputStream(archivo);

            transacciones = (List<Transaccion>) input.readObject();

            input.close();
            archivo.close();

        } catch (IOException | ClassNotFoundException ex) {

            System.out.println("Exception: " + ex.getMessage());

        }

        return transacciones;

    }

}
    


    
   
         
