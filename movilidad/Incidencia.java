import java.time.LocalDate;

public class Incidencia
{
    // instance variables - replace the example below with your own
    private Vehiculo vehiculo= null;
    private Base base= null;
    private String registroEntrada, registroSalida;
    public enum Estado {PENDIENTE, REPARACION, FINALIZADA}
    private Estado estado;
    private LocalDate date;
    private String reporte;
    private String idMecanico;
    private float importe;

    /**
     * Constructor for objects of class Incidencia
     */
    private Incidencia(String reporte, String registro)
    {
        this.vehiculo = vehiculo;
        this.estado = Estado.PENDIENTE;
        this.date = LocalDate.now();
        this.reporte = reporte;
        this.idMecanico = "";
        this.registroEntrada = registro;
        this.registroSalida = "";
        this.importe = 0;
    }
    
    public Incidencia(Vehiculo vehiculo, String reporte)
    {
        this(reporte,vehiculo.toString());
        this.vehiculo = vehiculo;
    }
    
    public Incidencia(Base base, String reporte)
    {
        this(reporte,"Base " + base.toString());
        this.base = base;
    }
    
    
    public Vehiculo getVehiculo()
    {
        return this.vehiculo;
    }
    
    public void setVehiculo(Vehiculo v)
    {
        this.vehiculo = v;
    }
    
    public Base getBase()
    {
        return this.base;
    }
    
    public void setBase(Base b)
    {
        this.base = b;
    }
    
    public boolean isInsencidenciaBase() 
    {
        return this.base != null;
    }
    
    
    public Estado getEstado()
    {
        return this.estado;
    }
    
    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }
    
    public String getReporte() {
        return this.reporte;
    }
    
    public void addReporte(String reporte)
    {
        this.reporte += "\n\n-----------------\nEl mecancigo agrega al informe: \n" + reporte;
    }
    
    public void setMecanico(String id)
    {
        this.idMecanico = id;
    }
    
    public String getMecanico()
    {
        return this.idMecanico;
    }
    
    public void setImporte(float importe)
    {
        this.importe = importe;
    }
    
    public float getImporte()
    {
        return this.importe;
    }
    
    public boolean isMecanicoAsignado()
    {
        return !("".equals(this.idMecanico));
    }
    
    public void registrarSalida()
    {
        if (this.isInsencidenciaBase()) {
            this.registroSalida = "Base " + base.toString();
            this.setBase(null);
        } else {
            this.registroSalida = vehiculo.toString();
            this.setVehiculo(null);
        }
    }

    
    public String toString()
    {
        String texto = "\n--------------------\nIncidencia\n--------------------\n";
        texto += "Condiciones registro de incidencia\n -> " + this.registroEntrada;
        if (!("".equals(this.registroSalida)))
            texto += "\nCondicion de salida\n -> " + this.registroSalida;
         if (isMecanicoAsignado())
            texto += "\nMecanico asignado: " + this.idMecanico;
        texto += "\nfecha de la incidencia: " + this.date + " Estado: "+ this.estado
        + "\nMotivo:" + this.reporte;
       
        if (this.estado == Estado.FINALIZADA && this.importe != 0)
            texto += "\n\nFactura: " + this.importe  +"€";
        texto += "\n\n###############################################################";
        return texto;
    }
}
