
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
    
    public void darAltaUsuario(String nombre, String apellidos,String dni)
    {
        Usuario usuario = new Usuario(nombre, apellidos, dni);
        Datos.addUsuario(usuario);
    }
    
    public void promocionarPremium(Usuario usuario)
    {
        usuario.promocionar();
    }
    
    public void crearBase(String nombre, Vehiculo.Tipo tipo,int capacidad, Coordenadas coordenadas)
    {
        Base base = new Base(nombre,tipo,coordenadas, capacidad);
        Datos.addBase(base);
    }
    
    public void crearVehiculo(Vehiculo vehiculo){
        Datos.addVehiculo(vehiculo);
    }
    
    
    
}
