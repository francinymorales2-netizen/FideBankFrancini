package fidebank.francinim;

import java.util.Date;
import java.util.List;

public class Transaccion {
    private Date fecha;
    private int numeroTransaccion;
    private static int idAutoIncremental= 0;
    private Cuenta cuenta;
    private double monto;
    private String tipoTransaccion;
    private double saldoFinal ;
    
    
    //CONTRUCTOR

    public Transaccion(Cuenta cuenta, double monto, String tipoTransaccion, double saldoFinal) {
        idAutoIncremental++;
        this.numeroTransaccion = idAutoIncremental;
        this.fecha = new Date();
        this.cuenta = cuenta;
        this.monto= monto;
        this.tipoTransaccion = tipoTransaccion;
        this.saldoFinal = saldoFinal;
    }

    
    //GETTERS Y SETTERS
    
 
    public Date getFecha() {
        return fecha;
    }


    public int getNumeroTransaccion() {
        return numeroTransaccion;
    }


    public static int getIdAutoIncremental() {
        return idAutoIncremental;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
   
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    
    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
    
    
    //MÉTODOS
    public static Cuenta buscarCuenta(List<Cuenta> cuentas, int numeroCuenta) throws Exception {

        for (Cuenta cuenta : cuentas) {

            if (cuenta.getId() == numeroCuenta) {
                return cuenta;
            }

        }

        throw new Exception("La cuenta no existe.");

    }
    
    
    public static void agregarTransaccion(Transaccion transaccion) throws Exception {

        List<Transaccion> transacciones = RegistroBanco.leerTransacciones();

        transacciones.add(transaccion);

        RegistroBanco.guardarTransacciones(transacciones);

    }
    
    
    public static void retirar(int numeroCuenta, double monto) throws Exception {

        List<Cuenta> cuentas = Cuenta.LeerCuentas();

        Cuenta cuenta = buscarCuenta(cuentas, numeroCuenta);

        if (monto <= 0) {
            throw new Exception("El monto debe ser mayor que cero.");
        }

        if (cuenta.getSaldo() < monto) {
            throw new Exception("Saldo insuficiente.");
        }

        cuenta.setSaldo(cuenta.getSaldo() - monto);

        Cuenta.agregarCuenta(cuentas);

        Transaccion transaccion = new Transaccion(cuenta,monto,"Retiro",cuenta.getSaldo());

        agregarTransaccion(transaccion);

    }
    
    
    public static void depositar(int numeroCuenta, double monto) throws Exception {

        List<Cuenta> cuentas = Cuenta.LeerCuentas();

        Cuenta cuenta = buscarCuenta(cuentas, numeroCuenta);

        if (monto <= 0) {
            throw new Exception("El monto debe ser mayor que cero.");
        }

        cuenta.setSaldo(cuenta.getSaldo() + monto);

        Cuenta.agregarCuenta(cuentas);

        Transaccion transaccion = new Transaccion(cuenta,monto,"Deposito",cuenta.getSaldo());

        agregarTransaccion(transaccion);

    }
    
    
    
    public static void transferir(int cuentaOrigen,int cuentaDestino,double monto) throws Exception {

        List<Cuenta> cuentas = Cuenta.LeerCuentas();

        Cuenta origen = buscarCuenta(cuentas, cuentaOrigen);

        Cuenta destino = buscarCuenta(cuentas, cuentaDestino);

        if (monto <= 0) {
            throw new Exception("El monto debe ser mayor que cero.");
        }

        if (cuentaOrigen == cuentaDestino) {
            throw new Exception("No puede transferir a la misma cuenta.");
        }

        if (origen.getSaldo() < monto) {
            throw new Exception("Saldo insuficiente.");
        }

        origen.setSaldo(origen.getSaldo() - monto);

        destino.setSaldo(destino.getSaldo() + monto);

        Cuenta.agregarCuenta(cuentas);

        Transaccion transaccionOrigen = new Transaccion(origen, monto,"Transferencia Enviada",origen.getSaldo());

        agregarTransaccion(transaccionOrigen);

        Transaccion transaccionDestino = new Transaccion(destino,monto,"Transferencia Recibida",destino.getSaldo());

        agregarTransaccion(transaccionDestino);

    }
}
    
    
    
   

    