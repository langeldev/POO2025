
/**
 * Write a description of class Empleado here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Empleado extends Persona
{
    protected String id;
    public Empleado (String nombre, String apellidos,String id)
    {
        super(nombre, apellidos);
        this.id = id;
    }
    
    public String getID() 
    {
        return this.id;
    }
    
    public String toString()
    {
        return "ID:" + this.id + " | " + super.toString();
    }

 
}
