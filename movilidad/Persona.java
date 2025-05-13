
/**
 * Write a description of class Persona here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Persona
{
    // instance variables - replace the example below with your own
    private String nombre;
    private String apellidos;

    /**
     * Constructor for objects of class Persona
     */
    public Persona(String nombre, String apellidos)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
     public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }
    
    public String getApellidos()
    {
        return this.apellidos;
    }
    
    public String toString()
    {
        return this.nombre + " " + this.apellidos;
    }

}
