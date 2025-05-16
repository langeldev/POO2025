
/**
 * Write a description of class Administrador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Administrador extends Empleado
{

    /**
     * Constructor for objects of class Administrador
     */
    public Administrador(String nombre, String apellidos, String id)
    {
        super(nombre, apellidos,"AD-" + id);
    }
    
    public void darAltaUsuario(Usuario usuario)
    {
        Datos.addUsuario(usuario);
    }
    
    public void darAltaEmpleado(Empleado emple)
    {
        Datos.addEmpleado(emple);
    }
    
    public void promocionarPremium(Usuario usuario)
    {
        usuario.promocionar();
    }
    
    
    public void crearBase(Base base)
    {
        Datos.addBase(base);
        System.out.println("\n########################################\n"+ base + " creada con exito!\n########################################");
    }
    
    public void crearVehiculo(Vehiculo vehiculo){
        Datos.addVehiculo(vehiculo);
        System.out.println("\n########################################\n"+ vehiculo + " creado con exito!\n########################################");
    }
    
    public String toString()
    {
        return "Perfil: Administrador |" + super.toString();
    }
    
    public void verIncidencias(Incidencia.Estado estado)
    {
        Datos.imprimirIncidencias(estado); 
    }
    
    
}
