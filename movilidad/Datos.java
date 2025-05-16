import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Datos
{
    private static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    private static HashMap<String, Empleado> empleados = new HashMap<String, Empleado>();
    private static ArrayList<Base> bases = new ArrayList<>();
    private static HashMap<Vehiculo.Tipo, Double> tarifa = new HashMap<Vehiculo.Tipo, Double>();
    private static Map<Vehiculo.Tipo, ArrayList<Vehiculo>> vehiculos = new HashMap<>();
    private static ArrayList<Incidencia> incidencias = new ArrayList<>();
    
    public static void iniciar()
    {
        
       Administrador admin= new Administrador("admin","admin", "1"); 
       addEmpleado(admin);
        
       //Creacion de usuarios 
       Usuario usu1 =new Usuario("Juan Carlos", "Smith", "premium", LocalDate.of(1994, 2, 1));
       addUsuario(usu1);
       usu1.setPremium(true);
       usu1.cargarSaldo(8.9);
       
    
       Usuario usu2 = new Usuario("Rosario", "Parrales", "67676792J",LocalDate.of(1985, 12, 19));
       usu2.cargarSaldo(7);
        
       addUsuario(usu2);
       addUsuario(new Usuario("Blanca", "Romero", "882487509T", LocalDate.of(2000, 7, 24)));
       
       Usuario usu4 =new Usuario("Marina", "Ternero", "usuario", LocalDate.of(1998, 11, 25));
       addUsuario(usu4);
       usu4.setPromocionPremium(true);
       usu4.cargarSaldo(36);
       
        
       Mantenimiento man1 = new Mantenimiento("Paco", "Clemente", "1");
       addEmpleado(man1);
       Mantenimiento man2 = new Mantenimiento("Lucia", "Salamanca", "2");
       addEmpleado(man2);
        
       Mecanico mec1 = new Mecanico("Samuel", "Macias", "1");
       addEmpleado(mec1);
       
       // Creacion de bases
       Base baseP1= new Base("Av. Principal", Vehiculo.Tipo.PATINETE, new Coordenadas(0, 2), 3);
       addBase(baseP1);
        
       Base baseP2= new Base("Los scout", Vehiculo.Tipo.PATINETE,  new Coordenadas(80, 77),4);
       addBase(baseP2);
        
       Base baseB1= new Base("Parque los nogales", Vehiculo.Tipo.BICICLETA,  new Coordenadas(1, 2),2);
       addBase(baseB1);
        
       Base baseB2= new Base("Plaza Mayor", Vehiculo.Tipo.BICICLETA, new Coordenadas(22, 64), 4);
       addBase(baseB2);
       
       Base baseP3= new Base("Av del fuego", Vehiculo.Tipo.PATINETE, new Coordenadas(41, 41), 6);
       baseP3.setEstado(Base.Estado.FALLO);
       addBase(baseP3);
       
       Base baseB3= new Base("Puerta grande", Vehiculo.Tipo.BICICLETA, new Coordenadas(80, 29), 3);
       baseB3.setEstado(Base.Estado.FALLO);
       addBase(baseB2);

       
       //Creacion de vehiculos
       MotoMenor m1 = new MotoMenor(new Coordenadas(22,12));
       addVehiculo(m1);
        
       MotoMenor m2 = new MotoMenor( new Coordenadas(62,10));
       addVehiculo(m2);
        
       MotoMenor m3 = new MotoMenor( new Coordenadas(10,90));
       addVehiculo(m3);
        
       MotoMenor m4 = new MotoMenor( new Coordenadas(123,20));
       addVehiculo(m4);
       m4.setBateria(10);
       man1.addVehiculo(m4);
        
       MotoMayor mayor1 = new MotoMayor(new Coordenadas(13,120));
       addVehiculo(mayor1);
       
       MotoMayor mayor2 = new MotoMayor(new Coordenadas(132,20));
       addVehiculo(mayor2);
       
       MotoMayor mayor3 = new MotoMayor(new Coordenadas(10,10));
       addVehiculo(mayor3);
       
       Bicicleta b1 = new Bicicleta();
       addVehiculo(b1);
       b1.setEstado(Vehiculo.Estado.RESERVADO);
       baseB1.agregarVehiculo(1, b1);
         
       Bicicleta b2 = new Bicicleta();
       addVehiculo(b2);
       baseB1.agregarVehiculo(0, b2);
        
       Bicicleta b3 = new Bicicleta();
       addVehiculo(b3);
       b3.setBateria(4);
       baseB2.agregarVehiculo(1, b3);
        
       Bicicleta b4 = new Bicicleta();
       addVehiculo(b4);
       baseB2.agregarVehiculo(2, b4);
        
       Bicicleta b5 = new Bicicleta();
       addVehiculo(b5);
       b5.setBateria(30);
       man1.addVehiculo(b5);
       man1.addVehiculo(b3); 
        
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
        
       Patinete p4 = new Patinete();
       p4.setEstado(Vehiculo.Estado.FALLO);
       addVehiculo(p4);
       Incidencia i1 = new Incidencia((p4), "La rueda esta torcida");
       addIncidencia(i1);
       mec1.addIncidencia(i1);
        
       Patinete p5 = new Patinete();
       addVehiculo(p5);
       baseP2.agregarVehiculo(1,p5);
        
       Patinete p6 = new Patinete();
       addVehiculo(p6);
       baseP2.agregarVehiculo(2,p6);
       
       Patinete p7 = new Patinete();
       p7.setBateria(9);
       addVehiculo(p7);
       man1.addVehiculo(p7);
       
       Bicicleta b6 = new Bicicleta();
       b6.setBateria(12);
       addVehiculo(b6);
       man1.addVehiculo(b6);
       Incidencia i2 = new Incidencia(baseB3, "Base atascada");
       addIncidencia(i2);
       mec1.addIncidencia(i2);
       Incidencia i3 = new Incidencia(baseP3, "No tiene luz");
       addIncidencia(i3);
       mec1.addIncidencia(i3);
       
       addTarifa(Vehiculo.Tipo.PATINETE, 0.10);
       addTarifa(Vehiculo.Tipo.BICICLETA, 0.15);
       addTarifa(Vehiculo.Tipo.MOTO_MENOR, 0.35);
       addTarifa(Vehiculo.Tipo.MOTO_MAYOR, 0.50);
       
       //alquiler 
       VehiculoBase va = baseP1.retirarVehiculo(0);
       System.out.println(va);
       baseP2.agregarVehiculo(0,va);
       
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
    
    public static void imprimirEmpleados()
    {
        for (String id: empleados.keySet()){
            System.out.println(empleados.get(id));
        }
    }
    
    public static void imprimirEmpleados(String rol)
    {
        for (String id: empleados.keySet()){
            if (id.startsWith(rol)) {
                 System.out.println(empleados.get(id));
            }
        }
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
        imprimirVehiculos(Vehiculo.Tipo.MOTO_MENOR, estado);
        imprimirVehiculos(Vehiculo.Tipo.MOTO_MAYOR, estado);   
    }
    
    public static void imprimirBases(Vehiculo.Tipo tipo) {
        for (int i = 0; i < bases.size(); i++) {
            Base base = bases.get(i);
            if(base.isTipoValido(tipo)){
                System.out.println("ID base: " + i+ " Detalles: "+ base);
            }
        }
    }
    
    public static void imprimirBases(Vehiculo.Tipo tipo, Base.Estado estado) {
        for (int i = 0; i < bases.size(); i++) {
            Base base = bases.get(i);
            if(base.isTipoValido(tipo) && base.getEstado() == estado){
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
                if (vehiculo instanceof VehiculoBase) {
                    System.out.println(vehiculo + (((VehiculoBase) vehiculo).getBase() != null ? " -> " +  ((VehiculoBase) vehiculo).getBase(): " ->Sin base asignada"));
                } else {
                    System.out.println(vehiculo.toString());
                }
                  
            }
          }
    }
    
    public static void addIncidencia(Incidencia incidencia)
    {
        incidencias.add(incidencia);
    }
    
    public static Incidencia getIncidencia(int index)
    {
        return incidencias.get(index);
    }
    
    public static void imprimirIncidencias(Incidencia.Estado estado) {
        int j = 0;
        for (int i =0; i < incidencias.size(); i++) {
            Incidencia incidencia = incidencias.get(i);
            if(incidencia.getEstado() == estado){
                j++;
                System.out.println("ID: " + i+ " |_\n" + incidencia );
            }
        }
        if (j == 0) {
            System.out.println("No hay incidencias " + estado);
        }
    }
}
