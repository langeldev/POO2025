
public class Base
{
    // instance variables - replace the example below with your own
    private String nombre;
    private Coordenadas coordenadas;
    protected int plazasOcupadas;
    private VehiculoBase[] vehiculos;
    private Vehiculo.Tipo tipoVehiculo;
    
    
    public enum Estado {LLENO,LIBRE, FALLO}
    private Estado estado;
      
    
    /**
     * Constructor for objects of class Base
     * @param nombre
     * @param coordenadas 
     * @param capacidad
     * @param plazas
     */
    public Base(String nombre, Vehiculo.Tipo tipoVehiculo,Coordenadas coordenadas, int plazas)
    {
        this.nombre = nombre;
        this.coordenadas = coordenadas;
        this.tipoVehiculo = tipoVehiculo;
        this.plazasOcupadas = 0;
        this.vehiculos = new VehiculoBase[plazas];
        this.estado = Estado.LIBRE;
        
    }
    
    public void agregarVehiculo(int index, VehiculoBase vehiculo)
    {
        if ((index < this.vehiculos.length && index > -1) && this.vehiculos[index] == null) {
            if (isTipoValido(vehiculo.getTipo())) {
                vehiculos[index] = vehiculo;
                this.plazasOcupadas++;
                vehiculo.setBase(this);
                actualizarEstado();
            } else {
                 System.out.println("Vehiculo no compatible con la base");
            }    
        } else {
            System.out.println("No es posible acceder a esa plaza");
        }
    }
    
    public VehiculoBase retirarVehiculo(int index)
    {
        VehiculoBase vehiculo = null;
        if ((index < this.vehiculos.length && index > -1) && this.vehiculos[index] != null) {
            vehiculo = vehiculos[index];
            vehiculos[index] = null;
            vehiculo.setBase(null);           
            this.plazasOcupadas--;
            actualizarEstado();
        }
        return vehiculo;
    }
    
    public void imprimirVehiculos()
    {
        for (int i = 0; i < vehiculos.length; i++){
            System.out.print("plaza: "+ i + " - " + (this.isPlazaLibre(i) ? "Ocupada ": "Libre ")); 
            System.out.println(" vehiculo: " + (this.getVehiculo(i) == null ? "":  this.getVehiculo(i) ));
        }
    }
    
    public void imprimirVehiculos(Vehiculo.Estado estado)
    {
        for (int i = 0; i < vehiculos.length; i++){
            VehiculoBase v = this.getVehiculo(i);
            if(v != null && v.getEstado() == estado) {
                System.out.print("\tplaza: "+ i + " - " + (this.isPlazaLibre(i) ? "Ocupada ": "Libre "));
                System.out.println("|-> vehiculo: " + (v == null ? "":  v ));
            }
        }
    }
    
    public VehiculoBase getVehiculo(int index) 
    {
        return vehiculos[index];
    }
    
    public void actualizarEstado()
    {
        if(this.vehiculos.length > this.plazasOcupadas) {
            this.estado = Estado.LIBRE;
        } else {
            this.estado = Estado.LLENO;
        }
    }
    
    public Estado getEstado()
    {
        return this.estado;
    }
    
    public void setEstado(Base.Estado estado)
    {
        this.estado = estado;
    }
   
    public boolean isTipoValido(Vehiculo.Tipo tipo){
        return this.tipoVehiculo == tipo;
    }
    
    public int vehiculosSize() {
        return this.vehiculos.length;
    }
    
    public boolean isPlazaLibre(int i)
    {
        return vehiculos[i] != null;
    }
    
    public Coordenadas getCoordenadas()
    {
        return this.coordenadas;
    }
    
    public void setCoordenadas(Coordenadas coordenadas)
    {
        this.coordenadas = coordenadas;
    }
   
    public String toString()
    {
        return "Nombre: " + nombre + " | Tipo ["+ this.tipoVehiculo +"] | " + this.coordenadas +  "|  Estado: " + this.estado + " - " + this.plazasOcupadas + "/" + this.vehiculosSize(); 
    }
}
