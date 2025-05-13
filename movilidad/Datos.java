import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

public class Datos
{
    private static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    private static HashMap<String, Empleado> empleados = new HashMap<String, Empleado>();
    private static ArrayList<Base> bases = new ArrayList<>();
    private static HashMap<Vehiculo.Tipo, Double> tarifa = new HashMap<Vehiculo.Tipo, Double>();
    private static Map<Vehiculo.Tipo, ArrayList<Vehiculo>> vehiculos = new HashMap<>();

    
    public static void iniciar()
    {
        // 
        Administrador admin= new Administrador("admin","admin", "1");
        addEmpleado(admin);
        
        //Creacion de usuarios 
        Usuario usu1 =new Usuario("Usuario", "Usuario", "usuario");
        addUsuario(usu1);
        usu1.cargarSaldo(8.98582582852855);
        Usuario usu2 = new Usuario("Rosario", "Parrales", "67676792J");
        usu2.cargarSaldo(3);
        
        addUsuario(usu2);
        addUsuario(new Usuario("Blanca", "Romero", "882487509T"));
        
        Mantenimiento man1 = new Mantenimiento("Paco", "Clemente", "1");
        addEmpleado(man1);
        Mantenimiento man2 = new Mantenimiento("Lucia", "Salamanca", "2");
        addEmpleado(man2);
        
        Mecanico mec1 = new Mecanico("Samuel", "Macias", "1");
        addEmpleado(mec1);
    
        
         // Creacion de bases
        Base baseP1= new Base("Av. Principal", Vehiculo.Tipo.PATINETE, new Coordenadas(0, 2), 3);
        addBase(baseP1);
       
        Base baseP2= new Base("Los escout", Vehiculo.Tipo.PATINETE,  new Coordenadas(80, 77),4);
        addBase(baseP2);

        Base baseB1= new Base("Parque los nogales", Vehiculo.Tipo.BICICLETA,  new Coordenadas(1, 2),2);
        addBase(baseB1);

        Base baseB2= new Base("Plaza Mayor", Vehiculo.Tipo.BICICLETA, new Coordenadas(22, 64), 4);
        addBase(baseB2);

        //Creacion de vehiculos
        Moto m1 = new Moto( new Coordenadas(22,12));
        addVehiculo(m1);
  
        Moto m2 = new Moto( new Coordenadas(62,10));
        addVehiculo(m2);
        
        Moto m3 = new Moto( new Coordenadas(10,90));
        addVehiculo(m3);
        
        Moto m4 = new Moto( new Coordenadas(123,20));
        addVehiculo(m4);
        m4.setBateria(10);
        man1.addVehiculo(m4);
        
        
        Bicicleta b1 = new Bicicleta();
        addVehiculo(b1);
        b1.setEstado(Vehiculo.Estado.RESERVADO);
        baseB1.agregarVehiculo(1, b1);
        
        
        Bicicleta b2 = new Bicicleta();
        addVehiculo(b2);
        baseB1.agregarVehiculo(0, b2);
        
        Bicicleta b3 = new Bicicleta();
        addVehiculo(b3);
        baseB2.agregarVehiculo(1, b3);
       
        Bicicleta b4 = new Bicicleta();
        addVehiculo(b4);
        baseB2.agregarVehiculo(2, b4);
        
        Bicicleta b5 = new Bicicleta();
        addVehiculo(b5);
        b5.setBateria(30);
        man1.addVehiculo(b5);
        
     
        Patinete p1 = new Patinete();
        p1.setBateria(9);
        addVehiculo(p1);
        baseP1.agregarVehiculo(0,p1);

        Patinete p2 = new Patinete();
        p2.setBateria(15);
        addVehiculo(p2);
        baseP1.agregarVehiculo(1,p2);
        
        Patinete p3 = new Patinete();
        addVehiculo(p3);
        baseP1.agregarVehiculo(2, p3);
        
        Patinete p4 = new Patinete(Vehiculo.Estado.FALLO);
        addVehiculo(p4);
        man1.addVehiculo(p4);

        Patinete p5 = new Patinete();
        addVehiculo(p5);
        baseP2.agregarVehiculo(1,p5);

        Patinete p6 = new Patinete();
        addVehiculo(p6);
        baseP2.agregarVehiculo(2,p6);
        
       
       addTarifa(Vehiculo.Tipo.PATINETE, 0.70);
       addTarifa(Vehiculo.Tipo.BICICLETA, 0.95);
       addTarifa(Vehiculo.Tipo.MOTO, 1.25);
       
       //alquiler 
       Vehiculo va = baseP1.retirarVehiculo(0);
       System.out.println(va);
       baseP2.agregarVehiculo(0,va);

       
       //usu2.alquilar(new Alquiler(va,new LocalDateTime.of(2025, 4,22),baseP1, baseP2));
       usu1.consultarVehiculos();
    }
    
    
    public static void addTarifa(Vehiculo.Tipo tipo, double precio)
    {
        tarifa.put(tipo, precio);
    }
    
    /**
     * Devuelve -1 si no encuentra el dato
     * @return Double
     */
    public static double getTarifa(Vehiculo.Tipo tipo)
    {       
        return  (tarifa.get(tipo) instanceof Double )? tarifa.get(tipo) : -1;
    }
    
    public static Map<Vehiculo.Tipo, Double> getTarifas()
    {
        return tarifa;
    }
    
    
    public static void addUsuario(Usuario user)
    {
        usuarios.put(user.getDNI(), user);
    }
    
    public static void addEmpleado(Empleado emple)
    {
        empleados.put(emple.getID(), emple);
    }
    
    public static Empleado getEmpleado(String id)
    {
        return empleados.get(id);
    }
   
    
    public static void addBase(Base base)
    {
        bases.add(base);
    }
    
    public static void addVehiculo(Vehiculo vehiculo)
    {
        if (vehiculos.get(vehiculo.getTipo()) != null) {
             vehiculos.get(vehiculo.getTipo()).add(vehiculo);
        } else {
            ArrayList<Vehiculo> list = new ArrayList<>();
            list.add(vehiculo);
            vehiculos.put(vehiculo.getTipo(), list);
        }
       
    }
    
    public static void imprimirUsuarios()
    {
        for (String dni: usuarios.keySet()){
            System.out.println(usuarios.get(dni).toString());
        }
    }
    
    public static Usuario getUsuario(String id)
    {
        return usuarios.get(id);
    }
  
    public static void imprimirVehiculos(Vehiculo.Estado estado)
    {
        System.out.println("Bases:");
        for (Base base: bases) {
            System.out.println("\n" + base);
            base.imprimirVehiculos(estado);
        }
        System.out.println("Motos:");
        imprimirVehiculos(Vehiculo.Tipo.MOTO, estado);
        
    }
    
    public static void imprimirBases(Vehiculo.Tipo tipo) {
        for (int i = 0; i < bases.size(); i++) {
            Base base = bases.get(i);
            if(base.isTipoValido(tipo)){
                System.out.println("ID base: " + i+ " Detalles: "+ base);
            }
        }
    }
    
    public static Base getBase(int index)
    {
        return bases.get(index);
    }
    
    public static void imprimirVehiculos(Vehiculo.Tipo tipo, Vehiculo.Estado estado)
    {
            
        List<Vehiculo> v= vehiculos.get(tipo);
        
        for (int i = 0; i < v.size(); i++ ){
                if (estado == v.get(i).getEstado()){
                  System.out.println("id: " + i +" detalles:" + v.get(i).toString());
                }
            }
    }
    
    public static Vehiculo getVehiculo(Vehiculo.Tipo tipo, int index)
    {
        return vehiculos.get(tipo).get(index);
    }
    
    public static void imprimirVehiculos()
    {    
        for (Vehiculo.Tipo tipo: vehiculos.keySet()) {
            
            for (Vehiculo vehiculo: vehiculos.get(tipo)){
                  System.out.println(vehiculo.toString());
            }
          }
    }
    
}
