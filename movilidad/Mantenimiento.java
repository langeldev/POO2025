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
        this.vehiculosAsignados.add(vehiculo);
    
    }
    
    public void verVehiculosAsignados()
    {
        for (int i = 0;i < this.vehiculosAsignados.size(); i++) {
            System.out.println(i + " - "+ this.getVehiculo(i));
        }
    }
    
    public Vehiculo getVehiculo(int index)
    {
        return vehiculosAsignados.get(index);
    }

}
