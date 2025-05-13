import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu
{
    // instance variables - replace the example below with your own
    public Menu()
    {
    }

    /**
     * Menu de arranque que permite acceder de forma agil a los diferentes roles que existen en la aplicacion
     */
    public void inicial()
    {
        System.out.println("==========================================");
        System.out.println("==  GESTION DE LA MOVILIDAD SOSTENIBLE  ==");
        System.out.println("==    Miguel Angel Rodriguez Roldan     ==");
        System.out.println("==            POO 2024-2025             ==");
        System.out.println("========================================== \n");
        System.out.println("==========================================");
        System.out.println("==             MENU INICIAL             ==");
        System.out.println("==========================================\n");
        
        int opcion;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Elige tu rol:\n1 - Usuario\n2 - Mantenimiento\n3 - Administrador\n0 - Salir");
            opcion = s.nextInt();
            switch(opcion){
                case 0:
                    System.out.println("Gracias por usar la aplicacion");
                    break;
                case 1:
                    this.usuario();
                    break;
                case 2:
                    this.mantenimiento();
                    break;
                case 3:
                    this.administrador();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            
        } while (opcion != 0);
    }
    
     /**
     * Opciones de mantenimiento de la aplicacion
     */
    public void mantenimiento()
    {
        int opcion;
        Mantenimiento man = (Mantenimiento) Datos.getEmpleado("MAN-1"); 
        Scanner s = new Scanner(System.in);
         do {
            System.out.println("Elige tu rol:\n1 - Ver vehiculos Asignados \n2 - \n3 - Administrador\n0 - Salir");
            opcion = s.nextInt();
            switch(opcion){
                case 0:
                    
                    break;
                case 1:
                    man.verVehiculosAsignados();
                    break;
                case 2:
                    this.mantenimiento();
                    break;
                case 3:
                    this.administrador();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            
        } while (opcion != 0);
    
    }
    
    /**
     * Opciones de usuario de la aplicacion
     */
    public void usuario()
    {
        Scanner s = new Scanner(System.in);
       

        Usuario usuario = null;
        do {
            System.out.println("\nLogin DNI");
            String id = s.nextLine();
            usuario = Datos.getUsuario(id);
            if (usuario == null) {System.out.println("Cuenta no registrada");}
        } while (usuario == null);
        
        System.out.println("==============================================================================");
        System.out.println("Bienvenido "+ usuario.getNombre() + " " + usuario.getApellidos());
        System.out.println("==============================================================================\n");
        
        int opcion;
        
      
        do {
            System.out.println("---------------------------------------------------\n");
            // menu usuario sin alquiler en este momento
            if (usuario.getAlquiler() == null){
                String text = "¿Que deseas hacer?:\n1 - Consultar saldo \n2 - Cargar saldo\n3 - Ver vehiculos disponibles\n4 - Alquiler vehiculo\n5 - Ver alquileres";
                if (usuario.isPremium()) {
                    text += "\n6 - Reservar vehiculo";
                }
                System.out.println( text+"\n0 - Volver menu inicial");
                opcion = s.nextInt();
                                    
                if (opcion == 0){break;}
                else if(opcion == 1) {
                    DecimalFormat formato = new DecimalFormat("#.##");
                    System.out.println("\nSu saldo es: "+ formato.format(usuario.getSaldo()) + "€");
                } else if(opcion == 2) {
                    System.out.print("\nCantidad:");
                    Double importe = s.nextDouble();
                    usuario.cargarSaldo(importe);
                } else if(opcion == 3) {
                     Datos.imprimirVehiculos(Vehiculo.Estado.LIBRE);
                } else if(opcion == 4) {
                    alquilarVehiculo(usuario);
                } else if(opcion == 5) {
                    usuario.verHistorial();
                } else if(opcion == 6 && usuario.isPremium()) {
                
                } else {
                     System.out.println("Opcion no valida");
                }
        
            } else {
                System.out.println("¿Que deseas hacer?:\n1 - Finalizar alquiler \n2 - Reportar fallo\n0 - Volver menu inicial");
                opcion = s.nextInt();
                switch(opcion){
                    case 0: break;
                    case 1:
                        usuario.desalquilar();
                        break;
                    case 2:
                        System.out.println(usuario.getAlquiler().getVehiculo());
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            }
            
        } while (opcion != 0);
        
    }
    
    public void alquilarVehiculo(Usuario usuario)
    {
        Vehiculo v = null;
        Scanner s = new Scanner(System.in);
        Vehiculo.Tipo tipo;
        System.out.println("¿Que vehiculo desea?:\n1 - Patinete \n2 - Bicicleta \n3 - Moto\n0 - Volver");
        try {
            int opcion = s.nextInt();
            if (opcion == 0) {
                return;
            } else if (opcion == 1) {
                tipo = Vehiculo.Tipo.PATINETE;
            }
            else if (opcion == 2) {
                tipo = Vehiculo.Tipo.BICICLETA;
            }else if (opcion == 3) {
                tipo = Vehiculo.Tipo.MOTO;
            } else {
                System.out.println("opcion no valida");
                return;
            }
            
            if (tipo == Vehiculo.Tipo.PATINETE || tipo == Vehiculo.Tipo.BICICLETA) {
                System.out.println("Elige una estacion de origen de " + tipo +"s:");
                Datos.imprimirBases(tipo);
                int idOrigen = s.nextInt();
                Base origen = Datos.getBase(idOrigen);
                
                if (!origen.isTipoValido(tipo)){System.out.println("Opcion no valida");}
                else {
                    origen.imprimirVehiculos();
                    int plaza = s.nextInt();
                    v = origen.getVehiculo(plaza);
                    if (v == null) {
                        System.out.println("No has seleccionado ningun vehiculo");
                    } else if (v.getEstado() != Vehiculo.Estado.LIBRE) {
                        System.out.println("Vehiculo no disponible");
                    } else {
                        v = origen.retirarVehiculo(plaza);
                        System.out.println("\nHas obtenido " + v);
                        System.out.println("----------------------------------------------------");
                        System.out.println("Elige una estacion de destino de " + tipo +"s:");
                        Datos.imprimirBases(tipo);
                      
                        int idDestino = s.nextInt();
                        Base destino = Datos.getBase(idDestino);
                        if (!destino.isTipoValido(tipo)){
                            origen.agregarVehiculo(plaza,v);
                            System.out.println("Opcion no valida");
                        }
                        else {
                            destino.imprimirVehiculos();
                            plaza = s.nextInt();
                            if (destino.getVehiculo(plaza) != null) {
                                origen.agregarVehiculo(plaza,v);
                                System.out.println("Opcion no valida");
                            } else {
                                destino.agregarVehiculo(plaza,v);
                                Alquiler alquiler = new Alquiler(v, LocalDateTime.now(),origen, destino);
                                usuario.alquilar(alquiler);
                            }
                        }
                    } 
                }
            } else if (tipo == Vehiculo.Tipo.MOTO) {
                
                System.out.println("Elige una " + tipo);
                Datos.imprimirVehiculos(tipo, Vehiculo.Estado.LIBRE);
                int id = s.nextInt();
                System.out.println("Coordenada X:");
                int x= s.nextInt();
                System.out.println("Coordenada Y:");
                int y = s.nextInt();
                Moto moto = (Moto) Datos.getVehiculo(tipo, id);
                moto.setCoordenadas(new Coordenadas(x,y));
                usuario.alquilar(new Alquiler(moto,LocalDateTime.now()));
                
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e);
        }
    }
   
    
    /**
     * Opciones del administrador de la aplicacion
     */
    public void administrador()
    {
        Administrador admin = (Administrador) Datos.getEmpleado("AD-1");
        System.out.println("==========================================");
        System.out.println("==       Bienvenido Administrador       ==");
        System.out.println("==========================================\n");
        
        int opcion;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("¿Que deseas hacer?:\n1 - Gestion de Usuarios \n2 - Gestion de Vehiculos\n0 - Volver menu inicial");
            opcion = s.nextInt();
            switch(opcion){
                case 0:
                    break;
                case 1:
                    gestionUsuario(admin);
                    break;
                case 2:
                    gestionVehiculos();
                    break;
                case 3:
                    System.out.println("Administrador elegido");
                    break;
         
                default:
                    System.out.println("Opcion no valida");
            }
            
        } while (opcion != 0);
    }
    
     /**
     * Opciones del administrador de la aplicacion
     */
    public void gestionUsuario(Administrador admin)
    {
        System.out.println("==========================================");
        System.out.println("==       Gestion Usuarios       ==");
        System.out.println("==========================================\n");
        
        int opcion;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("¿Que deseas hacer?:\n1 - Ver usurarios\n2 - Ver descuentos usuario premium\n3 - Cambiar porcentaje de descuento\n4 - Dar de alta a usuario\n0 - Volver menu inicial");
            opcion = s.nextInt();
            
            switch(opcion){
                case 0:
                    break;
                case 1:
                    verUsuarios();
                    break;
                case 2:
                    System.out.println("Los usuarios premium cuentan con un " + Usuario.getDescuento() + "% de descuento");
                    break;
                case 3:
                    System.out.print("Agrege el nuevo porcentaje de descuento ");
                    int des = s.nextInt();
                    Usuario.setDescuento(des);
                    System.out.println("El nuevo descuento es de " + Usuario.getDescuento() + "% de descuento");
                    break;
                case 4: 
                    crearUsuario();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            
        } while (opcion != 0);
    }
    
    public void verUsuarios()
    {
        Datos.imprimirUsuarios();
    }
    
    public void crearUsuario()
    {
        System.out.println("\n==       Dar de alta a usuario       ==");
        Scanner s = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = s.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = s.nextLine();
        System.out.print("DNI: ");
        String dni = s.nextLine();
        Datos.addUsuario(new Usuario(nombre, apellidos,dni));
    }
    
    // Gestion de vehiculos
    public void gestionVehiculos()
    {
        {
        System.out.println("\n==========================================");
        System.out.println("==       Gestion Vehiculos       ==");
        System.out.println("==========================================\n");
        
        int opcion;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("¿Que deseas hacer?:\n1 - Ver vehiculos\n2 - Dar de alta una base\n3 - Dar de alta vehiculo\n4 - Cambiar tarifas\n0 - inicial");
            opcion = s.nextInt();
            
            switch(opcion){
                case 0:
                    break;
                case 1:
                    verVehiculos();
                    break;
                case 2:
                    crearBase();
                    break;
                case 3:
                    crearVehiculo();
                    break;
                case 4:
                    cambiarTarifa();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            
        } while (opcion != 0);
    }
    }
    
    public void verVehiculos() 
    {
        Datos.imprimirVehiculos();
    }
    
    public void crearBase(){
        Scanner s = new Scanner(System.in);
        try {
            System.out.println("\n\nCrear base:");
            System.out.println("Nombre de base:");
            String nombre = s.nextLine();
            System.out.println("Tipo de base:\n1 - Patinetes.\n2 - Bicicletas");
            Integer opcion = s.nextInt();
            Vehiculo.Tipo tipo = null;
            if (opcion == 1) {
                tipo= Vehiculo.Tipo.PATINETE;
            } else if (opcion == 2) {
                tipo= Vehiculo.Tipo.BICICLETA;
            } else {
                System.out.println("Opcion no valida");
                return;
            }
            System.out.println("Coordenada X:");
            Integer x = s.nextInt();
            System.out.println("Coordenada Y:");
            Integer y = s.nextInt();
            System.out.println("Numero de plazas:");
            Integer plazas = s.nextInt();
            Base base = new Base(nombre, tipo, new Coordenadas(x, y), plazas);
            Datos.addBase(base);
        }catch (Exception e) {
            
        }        
    }
    
    public void crearVehiculo(){
        Scanner s = new Scanner(System.in);
            try {
                System.out.println("\n\nCrear Vehiculo:");
                System.out.println("Tipo de Vehiculo:\n1 - Patinetes.\n2 - Bicicletas \n3 - Moto");
                Integer opcion = s.nextInt();
                Vehiculo vehiculo = null;
                if (opcion == 1) {
                   vehiculo = crearPatinete();
                } else if (opcion == 2) {
                   vehiculo = crearBicicleta();
                }else if (opcion == 3) {
                  //  vehiculo = crearMoto();
                }
                System.out.println("+" + vehiculo);
                if (vehiculo != null) {Datos.addVehiculo(vehiculo);}
               
            }catch (Exception e) {
                
            }        
    }
    
    public Patinete crearPatinete()
    {
        return new Patinete();
    }
    
    public Bicicleta crearBicicleta()
    {
        return new Bicicleta();
    }
    
    public Moto crearMoto()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Coordenada X:");
        Integer x = s.nextInt();
        System.out.println("Coordenada Y:");
        Integer y = s.nextInt();
        return new Moto(new Coordenadas(x,y));
    }
    
     
    public void cambiarTarifa(){
    
        int opcion;
        Scanner s = new Scanner(System.in);
        
        System.out.println("\n\nTarifas:");
        
        for (Vehiculo.Tipo tipo: Vehiculo.Tipo.values()) {
            if (Datos.getTarifa(tipo) >= -1) {
                System.out.println(tipo + " - " + (Datos.getTarifa(tipo) >= -1 ? Datos.getTarifa(tipo) + "€": "Sin asignar"));
            }
        }
        try{
     
        Vehiculo.Tipo tipo = null;
        
        System.out.println("escriba tipo de vehiculo:");
        String nombre =s.nextLine().toLowerCase();
        System.out.println("nuevo precio:");
        switch(nombre)
        {
         case "patinete":
             tipo = Vehiculo.Tipo.PATINETE;
             break;
        case "bicicleta":
             tipo = Vehiculo.Tipo.BICICLETA;
             break;
        case "moto":
             tipo = Vehiculo.Tipo.MOTO;
             break;
        }
        
        Double precio = s.nextDouble();
        if (precio instanceof Double && tipo != null){  Datos.addTarifa(tipo, precio);}
        } catch (Exception e) {
            System.out.println(e);
        }
        
    
    }
    
    
}
