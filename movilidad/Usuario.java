import java.util.ArrayList;
public class Usuario extends Persona
{
    private boolean premium;
    private double saldo;
    private String dni;
    private Alquiler alquiler = null;
    private ArrayList<Alquiler> historial = new ArrayList<Alquiler>();
    
    private static int descuentoPremium = 20;
  
    public Usuario(String nombre, String apellidos, String dni)
    {
        super(nombre,apellidos);
        this.dni = dni;
        this.premium = true;
    }
    
    public String getDNI()
    {
        return this.dni;
    }

    public boolean isPremium()
    {
        return this.premium;
    }
    
    public void setPremium(boolean premium)
    {
        this.premium = premium;
    }
    
    public void promocionar()
    {
        this.setPremium(true);
    }
    
    
    public Alquiler getAlquiler()
    {
        return this.alquiler;
    }
    
    public double getSaldo()
    {
        return this.saldo;
    }
    
    public void cargarSaldo(double importe)
    {
        this.saldo += importe;
    }
    
  
    public void alquilar(Alquiler alquiler)
    {
        
        if (this.alquiler == null 
            && ((!this.premium && alquiler.getVehiculo().getBateria() > 20)
            || (this.premium && alquiler.getVehiculo().getBateria() > 10))){ 
            if(this.saldo > 0) {;
             this.alquiler = alquiler;
             if(this.premium) {this.alquiler.addDescuento(getDescuento());}
             addAlquiler(alquiler);
             alquiler.getVehiculo().setEstado(Vehiculo.Estado.OCUPADO);
             System.out.println(alquiler.getVehiculo().getTipo() + " alquilado con exito!");
            } else {
                System.out.println("No le queda saldo");
            }
        } else {
            System.out.println("No es posible alquilar");
        }
       
    }
    
    public void desalquilar()
    {
        if (this.alquiler != null) {
           
            this.alquiler.hacerPago();
            this.cargarSaldo(-this.alquiler.getImporte());
            System.out.println(this.alquiler);
            System.out.println(this.alquiler.getVehiculo().getBateria()+"%");
            this.alquiler = null;
         }
    }
    
    public void addAlquiler(Alquiler alquiler)
    {
        this.historial.add(alquiler);
    }
    
    public void verHistorial()
    {
        for (Alquiler a: this.historial) {
            System.out.println(a);
        }
    }
    
    
    public void consultarVehiculos()
    {
        Datos.imprimirVehiculos(Vehiculo.Estado.LIBRE);
    }
      
    public static int getDescuento()
    {
        return descuentoPremium; 
    }
    
    public static void setDescuento(int descuento)
    {
        descuentoPremium = descuento; 
    }
    
    public String toString()
    {
        return this.dni + " - " + super.toString();
    }

}
