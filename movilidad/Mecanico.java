import java.util.ArrayList;

public class Mecanico extends Empleado
{
    private ArrayList<Incidencia> insidenciasAsignadas = new ArrayList<>(); 
    /**
     * Constructor for objects of class Mecacnico
     */
    public Mecanico(String nombre, String apellidos, String id)
    {
       super(nombre, apellidos, "MEC-" + id);
    }
    
    public void addIncidencia(Incidencia incidencia)
    {
        incidencia.setEstado(Incidencia.Estado.REPARACION);
        incidencia.setMecanico(super.getID());
        this.insidenciasAsignadas.add(incidencia);
    }
    
    public void verIncidenciasAsignadas()
    {
        int size = this.insidenciasAsignadas.size();
        if (size == 0) {
            System.out.println("No tienes vehiculos asignados");
        } else {
            for (int i = 0;i < size; i++) {
                System.out.println(i + " - "+ this.getIncidencia(i));
            }
        }
    }
    
    public Incidencia getIncidencia(int index)
    {
        return insidenciasAsignadas.get(index);
    }
    
    public void removeIncidencia(Incidencia i)
    {
         this.insidenciasAsignadas.remove(i);
         System.out.println("La incidencia ya no esta asignada a " + super.getID());
    }
    
    
    
    public String toString()
    {
        return "Perfil: Mecanico |" + super.toString();
    }

   
}
