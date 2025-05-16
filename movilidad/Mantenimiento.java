import java.util.ArrayList;

public class Mantenimiento extends Empleado
{
    private ArrayList<Vehiculo> vehiculosAsignados = new ArrayList<>();; 

    /**
     * Constructor for objects of class Mantenimiento
     */
    public Mantenimiento(String nombre, String apellidos, String id)
    {
        super(nombre, apellidos, "MAN-" + id);
    }
    
    public void addVehiculo(Vehiculo vehiculo)
    {
        vehiculo.setEstado(Vehiculo.Estado.MANTENIMIENTO);
        this.vehiculosAsignados.add(vehiculo);
    }
    
    public void verVehiculosAsignados()
    {
        int size = this.vehiculosAsignados.size();
      
        if (size == 0) {
            System.out.println("No tienes vehiculos asignados");
        } else {
            for (int i = 0;i < size; i++) {
                Vehiculo v = this.getVehiculo(i);
                if(v instanceof VehiculoBase){
                     System.out.println(i + " - "+ v  + (((VehiculoBase) v).getBase() != null ? "\n\t-> Base: " +  ((VehiculoBase) v).getBase(): " ->Sin base asignada"));
                } else{
                     System.out.println(i + " - "+ v  );
                } 
            }
        }
        
       
    }
    
    /**
     *  Imprime los vehiculos 
     */
    public int verVehiculosAsignados(Vehiculo.Tipo tipo)
    {
        int size = this.vehiculosAsignados.size();
        int cont = 0;
        if (size == 0) {
            System.out.println("No tienes vehiculos asignados");
        } else {
            for (int i = 0;i < size; i++) {
                Vehiculo v = this.getVehiculo(i);
                if(v.isTipoValido(tipo) && v instanceof VehiculoBase){
                    System.out.println(i + " - "+ v  + (((VehiculoBase) v).getBase() != null ? "\n\t-> Base: " +  ((VehiculoBase) v).getBase(): " ->Sin base asignada"));
                    cont++;
                } else if(v.isTipoValido(tipo)) {
                    System.out.println(i + " - "+ v);
                    cont++;
                }
            }
        }
        return cont;
    }
    
    public Vehiculo getVehiculo(int index)
    {
        return vehiculosAsignados.get(index);
    }
    
      
     public VehiculoBase getVehiculo(int index, Vehiculo.Tipo tipo)
    {
        VehiculoBase v= null;
        try {
            v = (VehiculoBase) getVehiculo(index);
            if(!v.isTipoValido(tipo)) {
                  throw new Exception("No has seleccionado una de las opciones");
            }
        } catch (Exception e) {
            System.out.print("No has seleccionado una de las opciones");
        }
        return v;
    }
    
    public String toString()
    {
        return "Perfil: Mantenimiento |" + super.toString();
    }
    
    public void cargarBateria(Vehiculo vehiculo, float carga) {
        vehiculo.setBateria(carga);
        System.out.println("\n###########################################\n"+vehiculo.getTipo()+ " cargado a " +vehiculo.getBateria() +"%\n###########################################\n");
    }
    
    public void retirarVehiculoBase(VehiculoBase vehiculo) {
        if (vehiculo.getBase() == null) {
            System.out.println("El vehiculo no tiene ninguna esta en ninguna base");
        } else {
            Base b = vehiculo.getBase();
            for (int i = 0; i < b.vehiculosSize(); i++) {
                if (vehiculo.equals(b.getVehiculo(i))) {
                    b.retirarVehiculo(i);
                    System.out.println("Vehiculo retirado de la base: " + b);
                } 
            }
        }
        
        
    }
    
    public void devolverVehiculoCirculacion(Vehiculo v)
    {
        v.setEstado(Vehiculo.Estado.LIBRE);
        vehiculosAsignados.remove(v);
        System.out.println("\n###########################################\n"+v.getTipo()+ " devuelto con exito!" +"\n###########################################\n");
    }

}
