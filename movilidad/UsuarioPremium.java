import java.time.LocalDateTime;
import java.time.LocalDate;

public class UsuarioPremium extends Usuario
{
    // instance variables - replace the example below with your own
    private boolean premium;
    private LocalDateTime reserva;

    /**
     * Constructor for objects of class UsuarioPremium
     */
    
    public UsuarioPremium(String nombre, String apellidos, String dni, LocalDate date)
    {
        super(nombre,apellidos,dni, date);
        this.premium = true;
        this.reserva = null;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public LocalDateTime getReserva()
    {
        return this.reserva;
    }
    
    public void setReserva(LocalDateTime datetime)
    {
        this.reserva = datetime;
    }
    
    public void reservar(Vehiculo v)
    {
        if (v.getUsuarioReservado() == null) {
            v.setUsuarioReservado(this);
            setReserva(LocalDateTime.now().plusMinutes(20));
        } else {
            System.out.println("Ya reservado");
        }
    }
}
