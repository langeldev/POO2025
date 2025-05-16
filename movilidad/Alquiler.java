import java.time.LocalDateTime;
import java.time.Period;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
/**
 * Write a description of class Alquiler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alquiler
{
    // instance variables - replace the example below with your own
    private long timestamp;
    private int tiempo, descuento;
    
    private Vehiculo vehiculo;
    private Base baseOrigen, baseDestino;
    private Coordenadas coordOrigen, coordDestino;

    private double importeTotal, precioActual, bateria;;
    private LocalDateTime fecha;
   
    /**
     * Constructor for objects of class Alquiler
     */
    public Alquiler(Vehiculo vehiculo, LocalDateTime date)
    {
        // initialise instance variables
        this.timestamp = System.currentTimeMillis();
        this.vehiculo = vehiculo;
        this.importeTotal = 0;
        this.precioActual = Datos.getTarifa(vehiculo.getTipo());
        this.fecha = date;
    }
    
   
    public Alquiler(VehiculoBase vehiculo,LocalDateTime date,Base BaseOrigen,Base baseDestino)
    {
        this(vehiculo,date);
        this.vehiculo = vehiculo;
        this.baseOrigen = BaseOrigen;
        this.baseDestino =baseDestino;
    }
    
    public Alquiler(MotoMenor vehiculo, LocalDateTime date, Coordenadas coord)
    {
        this(vehiculo,date);
        this.coordOrigen = vehiculo.getCoordenadas();
        this.coordDestino = coord;
        vehiculo.setCoordenadas(coord);
    }
    
    public Alquiler(MotoMayor vehiculo, LocalDateTime date, Coordenadas coord)
    {
        this(vehiculo,date);
        this.coordOrigen = vehiculo.getCoordenadas();
        this.coordDestino = coord;
        vehiculo.setCoordenadas(coord);
    }
    
    
    public Vehiculo getVehiculo()
    {
        return this.vehiculo;
    }
    
    public void setImporte(double importe)
    {
       this.importeTotal = importe;
    }
    
    public double getImporte()
    {
        return this.importeTotal;
    }
    
    public void tiempo() {
        long tiempo = System.currentTimeMillis();
        tiempo = tiempo - this.timestamp;
        this.tiempo =  (int) (tiempo/ 1000/ 60);
    }

    public int getMinutos()
    {
        return tiempo;
    }
    
    public double getPrecioActual()
    {
        return this.precioActual;
    }
    
    public void hacerPago(){
        this.tiempo();
        this.vehiculo.actualizarBateria(this.getMinutos());
        this.vehiculo.setEstado(Vehiculo.Estado.LIBRE);
        double factura = (this.getMinutos() * this.getPrecioActual());
        this.bateria = this.vehiculo.getBateria();
        //penalizacion si no es premium
        if (this.descuento == 0 && this.bateria < 20){
            factura += 1;
        }
        // descuento premium
        if (this.descuento > 0) {
            factura -= ((factura * this.descuento) /100);
            if (this.vehiculo.getBateria() < 1) {
                factura += 1;
            }
        }
        this.setImporte(factura);    
    }
    
    public int periodoMes( )
    {
        Period d = Period.between(LocalDateTime.now().toLocalDate(),this.fecha.toLocalDate());
        return d.getMonths();
    }
    
    public void addDescuento(int descuento)
    {
        this.descuento = descuento;
    }
    
    public String toString() {
        DecimalFormat currentformat = new DecimalFormat("#.##");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime  fecha = LocalDateTime.parse(this.fecha.toString().substring(0,16), dateFormat);
        dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String texto = "+++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\nVehiculo alquilado: "+ this.vehiculo.getTipo() +
                    "\nMinutos del alquiler: " + getMinutos() + " minutos\nFecha: "+ fecha.format(dateFormat) +
                    "\nPrecio: " + currentformat.format(getPrecioActual()) + "€/min"
                    +"\nBateria al finalizar: " + this.bateria + "%\n\n_______________________________________________";
                    
        if (this.descuento == 0 && this.vehiculo.getBateria() < 20){
            texto += "\nPenalizacion: 1€";
        }
        // descuento premium
        if (this.descuento > 0) {
            texto += "\nDescuento: " + this.descuento +"%";
            if (this.vehiculo.getBateria() < 10) {
                texto += "\nPenalizacion: 1€";
            }   
        }            
        texto += "\nImporte: " + currentformat.format(getImporte()) + "€"; 
        return texto;
                
                
              
                
    }
}
