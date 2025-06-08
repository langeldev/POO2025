import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.InputMismatchException;
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
        
        int opcion = -1;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Elige tu rol:\n1 - Usuario\n2 - Mantenimiento\n3 - Mecanico\n4 - Administrador\n0 - Salir");
            try {
                opcion = s.nextInt();
                switch(opcion){
                    case 0:
                        System.out.println("Gracias por usar la aplicacion");
                        break;
                    case 1:
                        usuario();
                        break;
                    case 2:
                        mantenimiento();
                        break;
                    case 3:
                        mecanico();
                        break;
                    case 4:
                        administrador();
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe introducir numeros del Menu");
                s.nextLine();
            }
        } while (opcion != 0);
    }
    
    /*================================================
     * Inicio Menus de MANTENIMIENTO 
     ===============================================*/
    public void mantenimiento()
    {
        Scanner s = new Scanner(System.in);
        int opcion = -1;
        Empleado emple = null;
        do {
            System.out.print("\nID de empleado: ");
            String id = s.nextLine();
            emple = Datos.getEmpleado(id);
            if (emple == null) {System.out.println("Cuenta no registrada");}
            else if (!(emple instanceof Mantenimiento)) {System.out.println("Usuario no valido");}
        } while (!(emple instanceof Mantenimiento));
        Mantenimiento man = (Mantenimiento) emple;
        System.out.println("==============================================================================");
        System.out.println("Bienvenido "+ man.getNombre() + " " + man.getApellidos());
        System.out.println("==============================================================================\n");
        
         do {
            System.out.println("\n¿Que desea hacer?:\n1 - Ver vehiculos Asignados \n2 - Cargar bateria a vehiculo asignado \n3 - Retirar vehiculo de la circulacion\n4 - Devolver vehiculo a la circulacion\n0 - Salir");
            Vehiculo.Tipo tipo;
            Vehiculo v;
            VehiculoBase vb;
            try {
                opcion = s.nextInt();
                switch(opcion){
                    case 0:
                        break;
                    case 1:
                        man.verVehiculosAsignados();
                        break;
                    case 2:
                        man.verVehiculosAsignados();
                        System.out.print("Seleccione el vehiculo: ");
                        int id= s.nextInt();
                        v = man.getVehiculo(id);
                        System.out.print("Cantidad de carga para el vehiculo: ");
                        int carga = s.nextInt();
                        if (carga > 100 || carga < 0) {throw new Exception("La carga debe ser comprendida entre 0 y 100");}
                        man.cargarBateria(v,carga);
                        break;
                    case 3:
                        // retirar vehiculo 
                        tipo =getTipoVehiculo();
                        if (tipo == Vehiculo.Tipo.PATINETE || tipo == Vehiculo.Tipo.BICICLETA) {
                            int cantidad = man.verVehiculosAsignados(tipo);
                            if(cantidad == 0) {
                               System.out.println("No hay " + tipo + " asociados");
                            } else {
                                System.out.println("Hay " + cantidad + " " + tipo + " asociados");
                                System.out.print("Seleccione el vehiculo: ");
                                id= s.nextInt();
                                vb = man.getVehiculo(id,tipo);
                                if (vb != null)
                                    man.retirarVehiculoBase(vb);
                            }
                           
                        } else {
                            int cantidad = man.verVehiculosAsignados(tipo);
                            if(cantidad == 0) {
                               System.out.println("No hay " + tipo + " asociados");
                            } else {
                                System.out.println("Hay " + cantidad + " " + tipo + " asociados");
                                System.out.print("Seleccionar el vehiculo: ");
                                id= s.nextInt();
                                v = man.getVehiculo(id);
                                if(!v.isTipoValido(tipo)) {throw new Exception("No has seleccionado un vehiculo de la lista");}
                                if (v instanceof MotoMenor) {
                                    ((MotoMenor) v).setCoordenadas(null);
                                } else if (v instanceof MotoMayor) {
                                    ((MotoMayor) v).setCoordenadas(null);
                                }
                                System.out.println(tipo + " retirado con exito");
                            }
                        }
                        break;
                    case 4:
                        //Agregar vehiculo a circulacion
                        tipo =getTipoVehiculo();
                        man.verVehiculosAsignados(tipo);
                        if (tipo == Vehiculo.Tipo.PATINETE || tipo == Vehiculo.Tipo.BICICLETA) {                            
                            System.out.print("Seleccione el vehiculo: ");
                            id= s.nextInt();
                            if(((Mantenimiento) man).getVehiculo(id).getTipo() != tipo) {throw new Exception("Vehiculo no coincide con el tipo seleccionado");}
                            vb = man.getVehiculo(id,tipo);
                            if (vb.getBase() == null) {
                                Base base = getBase(tipo);
                                base.imprimirVehiculos();
                                id= s.nextInt();
                                if (!base.isPlazaLibre(id)) {
                                    base.agregarVehiculo(id, vb);
                                    man.devolverVehiculoCirculacion(vb);
                                } else {
                                    System.out.println("Plaza ocupada");
                                }
                            }
                        } else{
                            id= s.nextInt();
                            v = man.getVehiculo(id);
                            Coordenadas coord = getCoordenadas();
                            if (v instanceof MotoMenor) 
                                ((MotoMenor) v).setCoordenadas(coord);
                            if(v instanceof MotoMayor)
                                ((MotoMayor) v).setCoordenadas(coord);
                            man.devolverVehiculoCirculacion(v);
                        }
                        break;                       
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch (InputMismatchException e) { 
                System.out.println("Debe introducir numeros del Menu");
            } catch (Exception e) { 
                System.out.println(e.getMessage());
            } finally {
                s.nextLine();
            }
        } while (opcion != 0);
    
    }
    /*================================================
     * FIN Menus de MANTENIMIENTO 
     ===============================================*/
    
    /*================================================
    * Inicio Menus del MECANICO 
    ==================================================*/
    public void mecanico()
    {
        Scanner s = new Scanner(System.in);
        int opcion = -1;
        Empleado emple = null;
        do {
            System.out.print("\nID de empleado: ");
            String id = s.nextLine();
            emple = Datos.getEmpleado(id);
            if (emple == null) {System.out.println("Cuenta no registrada");}
            else if (!(emple instanceof Mecanico)) {System.out.println("Usuario no valido");}
        } while (!(emple instanceof Mecanico));
        Mecanico mec = (Mecanico) emple;
        System.out.println("==============================================================================");
        System.out.println("Bienvenido "+ mec.getNombre() + " " + mec.getApellidos());
        System.out.println("==============================================================================\n");
        
         do {
            System.out.println("\n¿Que desea hacer?:\n1 - Ver incidencias Asignadas \n2 - Arreglar incidencia\n0 - Salir");
    
            try {
                opcion = s.nextInt();
                switch(opcion){
                   case 0:
                        break;
                   case 1:
                        mec.verIncidenciasAsignadas();
                        break;
                   case 2:
                       repararIncidencia(mec);
                       break;          
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch (InputMismatchException e) { 
                System.out.println("Debe introducir numeros del Menu");
            } catch (Exception e) { 
                System.out.println(e.getMessage());
            } finally {
                s.nextLine();
            }
        } while (opcion != 0);
    
    }
    
    private void repararIncidencia(Mecanico mec) {
        Scanner s = new Scanner(System.in);
        mec.verIncidenciasAsignadas();
        System.out.print("Elegir la incidencia a reparar: ");
        try {
            int id = s.nextInt();
           
            Incidencia inci = mec.getIncidencia(id);
            System.out.println(inci);
            
            System.out.println("Agregar factura");
            float addImporte = s.nextFloat();
            s.nextLine();
            inci.setImporte(addImporte);
            
            System.out.println("Agregar informe de mecanico");
            String addInforme = s.nextLine();
            inci.addReporte(addInforme);
            
            if (inci.isInsencidenciaBase()) {
                inci.getBase().actualizarEstado();
            } else {
                inci.getVehiculo().setEstado(Vehiculo.Estado.LIBRE);
            }
            
            inci.setEstado(Incidencia.Estado.FINALIZADA);
            inci.registrarSalida();
            mec.removeIncidencia(inci);
            System.out.println("Incidencia finalizada con exito!");
            System.out.println("\n" + inci);
        } catch(Exception e) {
            System.out.println("Ha ocurrido un error");
        }
    }
    /*================================================
    * FIN Menus del MECANICO 
    ==================================================*/
    
    /*================================================
     * Inicio Menus del USUARIO 
     ===============================================*/
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
        
        int option = -1;       
        do {
            try {
                System.out.println("---------------------------------------------------\n");
                // menu usuario sin alquiler en este momento
                if (usuario.getAlquiler() == null){
                    String text = "¿Que deseas hacer?:\n1 - Consultar saldo \n2 - Cargar saldo\n3 - Ver vehiculos disponibles\n4 - Alquiler vehiculo\n5 - Ver alquileres"
                    + "\n6 - Reportar fallo base\n7 - Reportar vehiculo";
                    if (usuario.isPremium()) {
                        text += "\n8 - Reservar vehiculo";
                    }
                    System.out.println( text+"\n0 - Volver menu inicial");
                    option = s.nextInt();
                                      
                    if (option == 0){break;}
                    else if(option == 1) {
                        DecimalFormat formato = new DecimalFormat("#.##");
                        System.out.println("\nSu saldo es: "+ formato.format(usuario.getSaldo()) + "€");
                    } else if(option == 2) {
                        System.out.print("\nCantidad:");
                        Double importe = s.nextDouble();
                        usuario.cargarSaldo(importe);
                    } else if(option == 3) {
                         Datos.imprimirVehiculos(Vehiculo.Estado.LIBRE);
                    } else if(option == 4) {
                        alquilarVehiculo(usuario);
                    } else if(option == 5) {
                        usuario.verHistorial();
                    } else if(option == 6) {
                        Vehiculo.Tipo tipo = getTipoBase();
                        Datos.imprimirBases(tipo, Base.Estado.LIBRE);
                        Datos.imprimirBases(tipo, Base.Estado.LLENO);
                        int id = s.nextInt();
                        Base b = Datos.getBase(id);
                        s.nextLine(); 
                        if (!b.isTipoValido(tipo)) { throw new Exception("No has elegido una de las opciones");}
                        System.out.println("Notificar problema");
                        String str = s.nextLine();
                        b.setEstado(Base.Estado.FALLO);
                        Incidencia i = new Incidencia(b, str);
                        Datos.addIncidencia(i);
                        System.out.println(i);
                    } else if(option == 7) {
                        Vehiculo.Tipo tipo = getTipoVehiculo();
                        Datos.imprimirVehiculos(tipo,Vehiculo.Estado.LIBRE);
                        int id = s.nextInt();
                        Vehiculo v = Datos.getVehiculo(tipo,id);
                        s.nextLine(); 
                        if (!(v.getTipo()==tipo) || !(v.getEstado() ==Vehiculo.Estado.LIBRE)) { throw new Exception("No has elegido una de las opciones");}
                        System.out.println("Notificar problema");
                        String str = s.nextLine();
                        v.setEstado(Vehiculo.Estado.FALLO);
                        Incidencia i = new Incidencia(v, str);
                        Datos.addIncidencia(i);
                        System.out.println(i);
                    }  
                    else if(option == 8 && usuario.isPremium()) {
                    
                    } else {
                         System.out.println("Opcion no valida");
                    }
            
                } else {
                    System.out.println("¿Que deseas hacer?:\n1 - Finalizar alquiler \n2 - Reportar fallo en el vehiculo que estoy usando\n0 - Volver menu inicial");
                    option = s.nextInt();
                    s.nextLine();
                    switch(option){
                        case 0: break;
                        case 1:
                            usuario.desalquilar();
                            break;
                        case 2:
                            System.out.println("Motivo del fallo: ");
                            String texto = s.nextLine();
                            usuario.getAlquiler().getVehiculo().setEstado(Vehiculo.Estado.FALLO);
                            Datos.addIncidencia(new Incidencia(usuario.getAlquiler().getVehiculo(), texto));
                            usuario.desalquilar();
                            System.out.println("Fallo reportado: ");
                            break;
                        default:
                            System.out.println("Opcion no valida");
                    }
                }
            } catch(Exception InputMismatchException) {
                System.out.println("Debe escribir un numero entre las opciones");
                s.nextLine();
            } 
        } while (option != 0);
        
    }
    
    public void alquilarVehiculo(Usuario usuario)
    {
        Scanner s = new Scanner(System.in);
        // obtener tipo
        Vehiculo.Tipo tipo = getTipoVehiculo();
       
        if (tipo == Vehiculo.Tipo.PATINETE || tipo == Vehiculo.Tipo.BICICLETA) {
            // base origen
            Base origen = getBase(tipo);
            origen.imprimirVehiculos();
            int plaza = -1;
            VehiculoBase v;
            try {
                plaza = s.nextInt();
                v = origen.getVehiculo(plaza);
               if (v == null) {
                   throw new Exception("No has seleccionado ningun vehiculo");
               }
               if (v.getEstado() != Vehiculo.Estado.LIBRE) {
                   throw new Exception("Vehiculo no disponible\n");
               }
                
               v = origen.retirarVehiculo(plaza);
               System.out.println("\nHas obtenido " + v);
               System.out.println("--------------------------------------------------");
               try {
                   Base destino = getBase(tipo);
                   while(destino.getEstado() == Base.Estado.LLENO) {
                       System.out.println("El destino no tiene plazas libres");
                       destino = getBase(tipo);
                   }
                   int plazaDestino;
                   do {
                       destino.imprimirVehiculos();
                       plazaDestino = s.nextInt();
                       if (plazaDestino < 0 || plazaDestino > destino.vehiculosSize()){
                           throw new Exception("No has seleccionado ninguna plaza");
                       }
                   } while (destino.isPlazaLibre(plazaDestino));
                   destino.agregarVehiculo(plazaDestino,v);
                   Alquiler alquiler = new Alquiler(v, LocalDateTime.now(),origen, destino);
                   usuario.alquilar(alquiler);
                   
               } catch(InputMismatchException | IndexOutOfBoundsException error) {
                    System.out.println("Debe escribir un numero entre las opciones\n");
                    if (v != null) {origen.agregarVehiculo(plaza,v);}
                    s.nextLine(); 
               } catch(Exception e) {
                    System.out.println(e.getMessage()+"\n");
                    if (v != null) {origen.agregarVehiculo(plaza,v);}
                    System.out.println("Vehiculo devuelto a la base\n");
                    
               }
            }catch(InputMismatchException | IndexOutOfBoundsException error) {
                System.out.println("Debe escribir un numero entre las opciones\n");
                s.nextLine(); 
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (tipo == Vehiculo.Tipo.MOTO_MENOR || tipo == Vehiculo.Tipo.MOTO_MAYOR) {
            
            System.out.println("Elige una " + tipo);
            Alquiler alquiler = null;
            do {
                Datos.imprimirVehiculos(tipo, Vehiculo.Estado.LIBRE);
                try {
                    int id = s.nextInt(); 
                    if (tipo == Vehiculo.Tipo.MOTO_MENOR) {
                        MotoMenor moto = (MotoMenor) Datos.getVehiculo(tipo, id);
                        Coordenadas coord = getCoordenadas();
                        alquiler = new Alquiler(moto,LocalDateTime.now(), coord); 
                    }
                    if (tipo == Vehiculo.Tipo.MOTO_MAYOR) {
                        MotoMayor moto = (MotoMayor) Datos.getVehiculo(tipo, id);
                        Coordenadas coord = getCoordenadas();
                        alquiler = new Alquiler(moto,LocalDateTime.now(), coord); 
                    }
                } catch (Exception e) {
                    System.out.println("Opcion no valida");
                    s.nextLine();
                }
            } while(alquiler == null);            
            usuario.alquilar(alquiler);     
        } 
    }
    /*================================================
     * FIN Menus del USUARIO 
     ===============================================*/
    
     
    /*================================================
     * Inicio Menus del administrador 
     ===============================================*/
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
                    gestionVehiculos(admin);
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            
        } while (opcion != 0);
    }
    
    //Gestion usuarios
    public void gestionUsuario(Administrador admin)
    {
        System.out.println("==========================================");
        System.out.println("==       Gestion Usuarios       ==");
        System.out.println("==========================================\n");
        
        int opcion;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("\n¿Que deseas hacer?:\n1 - Ver usurarios\n2 - Ver descuentos usuario premium\n3 - Cambiar porcentaje de descuento\n"
            +"4 - Dar de alta a usuario\n5 - Promocionar usuario a premium \n6 - Ver empleados\n7 - Dar de alta encargado de mantenimiento\n8 - Dar de alta mecanico\n0 - Volver menu inicial");
            opcion = s.nextInt();
            s.nextLine();
            
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
                    Usuario usuario = crearUsuario();
                    if (usuario != null ) {admin.darAltaUsuario(usuario);}
                    break;
                case 5:
                    verUsuarios();
                    System.out.print("Elegir DNI: ");
                    String dni = s.nextLine();
                    Usuario usu = Datos.getUsuario(dni);
                    if(usu != null && usu.isPromocionPremium()){
                        usu.promocionar();
                    } else {
                        System.out.println("El usuario no puede ser promocionado al no cumplir los requisitos");
                    } 
                    break;
                case 6: 
                    Datos.imprimirEmpleados();
                    break;
                case 7:
                    Mantenimiento man = crearMantenimiento();
                    if (man != null ) {admin.darAltaEmpleado(man);}
                    break;
                case 8:
                    Mecanico mec = crearMecanico();
                    if (mec != null ) {admin.darAltaEmpleado(mec);}
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            
        } while (opcion != 0);
    }
    
    //Gestion vehiculos
    
    public void verUsuarios()
    {
        Datos.imprimirUsuarios();
    }
    
    public Usuario crearUsuario()
    {
        System.out.println("\n==       Dar de alta a usuario       ==");
        Scanner s = new Scanner(System.in);
        Usuario usu = null;
        try {   
            System.out.print("Nombre: ");
            String nombre = s.nextLine();
            System.out.print("Apellidos: ");
            String apellidos = s.nextLine();
            System.out.print("DNI: ");
            String dni = s.nextLine();
            System.out.print("año nacimiento: ");
            int y = s.nextInt();
            if (y < 1910) {throw new Exception("El año debe ser superior a 1910");}
            System.out.print("mes nacimiento: ");
            int m = s.nextInt();
            if (m < 0 && m > 13) {throw new Exception("El mes debe estar comprendido entre 1 y 12");}
            System.out.print("dia nacimiento: ");
            int d = s.nextInt();
            if (d < 0 && d > 32) {throw new Exception("El dia debe estar comprendido entre 1 y 31");}
            usu= new Usuario(nombre, apellidos,dni, LocalDate.of(y,m,d));  
        } catch(Exception e) {
                System.out.println(e.getMessage());
        } finally {
            s.nextLine();
        }
        return usu;
    }
    
    public Mantenimiento crearMantenimiento()
    {
        System.out.println("\n==       Dar de alta Mantenimiento       ==");
        Scanner s = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = s.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = s.nextLine();
        System.out.print("Identificador: ");
        String id = s.nextLine();
        return new Mantenimiento(nombre, apellidos,id);
    }
    
    public Mecanico crearMecanico()
    {
        System.out.println("\n==       Dar de alta Mecanico       ==");
        Scanner s = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = s.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = s.nextLine();
        System.out.print("Identificador: ");
        String id = s.nextLine();
        return new Mecanico(nombre, apellidos,id);
    }
    
    // Gestion de vehiculos
    public void gestionVehiculos(Administrador admin)
    {
        {
        System.out.println("\n==========================================");
        System.out.println("==            Gestion Vehiculos           ==");
        System.out.println("==========================================\n");
        
        int option = -1;
        Scanner s = new Scanner(System.in);
        do {
            String text = "¿Que deseas hacer?:\n1 - Ver vehiculos\n2 - Ver Bases\n3 - Dar de alta una base"
            + "\n4 - Dar de alta vehiculo\n5 - Asignar Vehiculo a mantenimiento"
            + "\n6 - Cambiar tarifas\n7 - Ver incidencias en vehiculos"
            + "\n8 - Asiganar incidencia a mecanico\n0 - inicial";
            System.out.println(text);
            try {
                option = s.nextInt();
                switch(option){
                    case 0:
                        break;
                    case 1:
                        verVehiculos();
                        break;
                    case 2:
                        Datos.imprimirBases(Vehiculo.Tipo.PATINETE);
                        Datos.imprimirBases(Vehiculo.Tipo.BICICLETA);
                        break;
                    case 3:
                        Base b = crearBase();
                        if (b != null) {admin.crearBase(b);}
                        break;
                    case 4:
                        Vehiculo v = crearVehiculo();
                        if(v != null) {admin.crearVehiculo(v);}
                        break;
                    case 5:
                        asignarVehiculoMantenimiento();
                        break;
                    case 6:
                        cambiarTarifa();
                        break;
                    case 7:
                        Incidencia.Estado estado = getEstadoIncidencia();
                        admin.verIncidencias(estado);
                        break;
                    case 8:
                        asignarIncidenciaMecanico();
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe escribir un numero entre las opciones");
                s.nextLine();
            }
        } while (option != 0);
    }
    }
    
    public void verVehiculos() 
    {
        Datos.imprimirVehiculos();
    }
    
    public Base crearBase(){
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
                throw new Exception("Opcion no valida");
            }
            Coordenadas coord = getCoordenadas();
            System.out.println("Numero de plazas:");
            Integer plazas = s.nextInt();
            return new Base(nombre, tipo, coord, plazas);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Menu crear vehiculo
     */
    public Vehiculo crearVehiculo(){
        System.out.println("\n\nCrear Vehiculo:");
        Vehiculo.Tipo  tipo= getTipoVehiculo();
        Vehiculo vehiculo = null;
        switch(tipo) {
            case PATINETE:
                vehiculo = crearPatinete();
                agregarVehiculoBase(vehiculo);
                break;
            case BICICLETA:
                vehiculo = crearBicicleta();
                agregarVehiculoBase(vehiculo);
                break;
            case MOTO_MENOR:
                vehiculo = crearMotoMenor();
                break;
            case MOTO_MAYOR:
                vehiculo = crearMotoMayor();
                break;
        }
        return vehiculo;
    }
           
    public VehiculoBase crearPatinete()
    {
        return new Patinete();
    }
    
    public Bicicleta crearBicicleta()
    {
        return new Bicicleta();
    }
    
    public MotoMenor crearMotoMenor()
    {
        return new MotoMenor(getCoordenadas());
    }
    
    public MotoMayor crearMotoMayor()
    {
        return new MotoMayor(getCoordenadas());
    }
    
     
    public void cambiarTarifa(){
        Scanner s = new Scanner(System.in);
        System.out.println("\n\nTarifas:");
        
        for (Vehiculo.Tipo tipo: Vehiculo.Tipo.values()) {
            if (Datos.getTarifa(tipo) >= -1) {
                System.out.println(tipo + " - " + (Datos.getTarifa(tipo) >= -1 ? Datos.getTarifa(tipo) + "€": "Sin asignar"));
            }
        }
        try{
     
        Vehiculo.Tipo tipo = getTipoVehiculo();
        Double precio = s.nextDouble();
        if (precio instanceof Double && tipo != null){  Datos.addTarifa(tipo, precio);}
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     
    /*================================================
     * FIN Menus del administrador 
     ===============================================*/
    /** Metodos comunes del menu*/
    /**
     * Menu de seleccion de tipos de vehiculos
     * @return Vehiculo.Tipo o null 
    */
    private Vehiculo.Tipo getTipoVehiculo() {
        Scanner s = new Scanner(System.in);
        Vehiculo.Tipo tipo = null;
        do {
            System.out.println("\n#####################################\n¿Que vehiculo desea?:\n1 - Patinete \n2 - Bicicleta \n3 - Moto pequeña\n4 - Moto mayor");
            try {
               int option = s.nextInt();
               if (option == 1) {
                   tipo = Vehiculo.Tipo.PATINETE;
               } else if (option == 2) {
                   tipo = Vehiculo.Tipo.BICICLETA;
               } else if (option == 3) {
                   tipo = Vehiculo.Tipo.MOTO_MENOR;
               } else if (option == 4){
                   tipo = Vehiculo.Tipo.MOTO_MAYOR;
               } else{
                   System.out.println("opcion no valida");
               }
            } catch(InputMismatchException e) {
                System.out.println("Debe escribir un numero entre las opciones");
                s.nextLine();
            }
        } while(tipo == null);
        return tipo;
    }

    /** Metodos comunes del menu*/
    /**
     * Menu de seleccion de tipos de vehiculos
     * @return Vehiculo.Tipo o null 
    */
    private Vehiculo.Estado getEstadoVehiculo() {
        Scanner s = new Scanner(System.in);
        Vehiculo.Estado tipo = null;
        do {
            System.out.println("\n#####################################\n¿Que estado desea?:\n1 - Libre \n2 - Ocupado \n3 - Fallo\n4 - Reservado");
            try {
               int option = s.nextInt();
               if (option == 1) {
                   tipo = Vehiculo.Estado.LIBRE;
               } else if (option == 2) {
                   tipo = Vehiculo.Estado.OCUPADO;
               } else if (option == 3) {
                   tipo = Vehiculo.Estado.FALLO;
               } else if (option == 4){
                   tipo = Vehiculo.Estado.RESERVADO;
               } else{
                   System.out.println("opcion no valida");
               }
            } catch(InputMismatchException e) {
                System.out.println("Debe escribir un numero entre las opciones");
                s.nextLine();
            }
        } while(tipo == null);
        return tipo;
    }
    
   
    private Vehiculo.Tipo getTipoBase() {
        Scanner s = new Scanner(System.in);
        Vehiculo.Tipo tipo = null;
        do {
            System.out.println("\n#####################################\n¿Que estado desea?:\n1 - PATINETE \n2 - BICICLETA");
            try {
               int option = s.nextInt();
               if (option == 1) {
                   tipo = Vehiculo.Tipo.PATINETE;
               } else if (option == 2) {
                   tipo = Vehiculo.Tipo.BICICLETA;
               } else{
                   System.out.println("opcion no valida");
               }
            } catch(InputMismatchException e) {
                System.out.println("Debe escribir un numero entre las opciones");
                s.nextLine();
            }
        } while(tipo == null);
        return tipo;
    }
    
    private Base.Estado getEstadoBase() {
        Scanner s = new Scanner(System.in);
        Base.Estado tipo = null;
        do {
            System.out.println("\n#####################################\n¿Que estado desea?:\n1 - Libre \n2 - Lleno \n3 - Fallo");
            try {
               int option = s.nextInt();
               if (option == 1) {
                   tipo = Base.Estado.LIBRE;
               } else if (option == 2) {
                   tipo = Base.Estado.LLENO;
               } else if (option == 3) {
                   tipo = Base.Estado.FALLO;
               } else{
                   System.out.println("opcion no valida");
               }
            } catch(InputMismatchException e) {
                System.out.println("Debe escribir un numero entre las opciones");
                s.nextLine();
            }
        } while(tipo == null);
        return tipo;
    }
    
    private Incidencia.Estado getEstadoIncidencia() {
        Scanner s = new Scanner(System.in);
        Incidencia.Estado tipo = null;
        do {
            System.out.println("\n#####################################\n¿Que estado desea?:\n1 - PENDIENTE \n2 - REPARACION \n3 - FINALIZADA");
            try {
               int option = s.nextInt();
               if (option == 1) {
                   tipo = Incidencia.Estado.PENDIENTE;
               } else if (option == 2) {
                   tipo = Incidencia.Estado.REPARACION;
               } else if (option == 3) {
                   tipo = Incidencia.Estado.FINALIZADA;
               } else{
                   System.out.println("opcion no valida");
               }
            } catch(InputMismatchException e) {
                System.out.println("Debe escribir un numero entre las opciones");
                s.nextLine();
            }
        } while(tipo == null);
        return tipo;
    }
    
    
       /**
     * Menu para seleccionar base por un tipo
     */
    private void asignarVehiculoMantenimiento() {
        Scanner s = new Scanner(System.in);
        Vehiculo v = null;
        Base b = null;
        Vehiculo.Tipo tipo = getTipoVehiculo();
        Vehiculo.Estado estado = getEstadoVehiculo();
        
         try {
            int idv;      
            if (tipo == Vehiculo.Tipo.PATINETE || tipo == Vehiculo.Tipo.BICICLETA) {
                Datos.imprimirVehiculos(tipo, estado);
                idv = s.nextInt();
                s.nextLine();
                v = Datos.getVehiculo(tipo,idv);
            } else {
        
                Datos.imprimirVehiculos(tipo, estado);
                idv = s.nextInt();
                s.nextLine();
                v = Datos.getVehiculo(tipo,idv);
            }
            System.out.println("Asignar un Empleado");
            Datos.imprimirEmpleados("MAN-");
            String id = s.nextLine();
            
            Empleado e = Datos.getEmpleado(id);
            if (e instanceof Mantenimiento) {
                Mantenimiento m =(Mantenimiento) e;
                m.addVehiculo(v);
            }
                
        } catch (Exception e) {
            System.out.println("Ha ocurrido un problema");
        }
    }
    
    private void asignarIncidenciaMecanico()
    {
        Scanner s = new Scanner(System.in);
        Incidencia.Estado estado = getEstadoIncidencia();
        Datos.imprimirIncidencias(estado);
        System.out.println("Elegir incidencia");
        try {
            int id = s.nextInt();
            s.nextLine();
            Incidencia incidencia = Datos.getIncidencia(id);
            if (incidencia.isMecanicoAsignado() && (incidencia.getEstado() == estado)) {
               Mecanico meca = (Mecanico) Datos.getEmpleado(incidencia.getMecanico());
               meca.removeIncidencia(incidencia);
            } else if(incidencia.getEstado() != estado) {
                throw new Exception("No has elegido una incidencia de la lista");
            }
            System.out.println("Asignar un Mecanico");
            Datos.imprimirEmpleados("MEC-");
            String idEmple = s.nextLine();
            
            Empleado e = Datos.getEmpleado(idEmple);
            if (e instanceof Mecanico) {
                Mecanico m =(Mecanico) e;
                m.addIncidencia(incidencia);
            }
            
        } catch(Exception e) {
            System.out.print(e.getMessage());
        }

    }
    
    /**
     * Menu para seleccionar base por un tipo
     */
    private Base getBase(Vehiculo.Tipo tipo) {
        Base base = null;
        Scanner s = new Scanner(System.in);
        System.out.println("Elige una estacion de " + tipo +"s:");
        while(base == null || !base.isTipoValido(tipo)){
            Datos.imprimirBases(tipo);
            try {
                int id = s.nextInt();
                base = Datos.getBase(id);
                if (!base.isTipoValido(tipo)) {
                    throw new Exception("No has elegido una de las opciones del menu\n");
                }          
            } catch(InputMismatchException | IndexOutOfBoundsException error) {
                System.out.println("Debe escribir un numero entre las opciones\n");
                s.nextLine();
            } catch(Exception e) {
               System.out.println(e.getMessage());
            }
        }
        return base;
    }
    
    
    private Base getBase(Vehiculo.Tipo tipo, Base.Estado estado ) {
        Base base = null;
        Scanner s = new Scanner(System.in);
        System.out.println("Elige una estacion de " + tipo +"s:");
        while(base == null || !base.isTipoValido(tipo)){
           
            try {
               
                Datos.imprimirBases(tipo, estado);
                int id = s.nextInt();
                base = Datos.getBase(id);
                if (!base.isTipoValido(tipo) && base.getEstado() == estado) {
                    throw new Exception("No has elegido una de las opciones del menu\n");
                }          
            } catch(InputMismatchException | IndexOutOfBoundsException error) {
                System.out.println("Debe escribir un numero entre las opciones\n");
                s.nextLine();
            } catch(Exception e) {
               System.out.println(e.getMessage());
            }
        }
        return base;
    }
    
    /**
     * Menu para obtener Coordenadas
     */
    private Coordenadas getCoordenadas()
    {    
        Scanner s = new Scanner(System.in);
        int x= -1,y = -1;
        while (x<=0 && y<=0) {
            try {
                System.out.println("Coordenada X:");
                x = s.nextInt();
                System.out.println("Coordenada Y:");
                y = s.nextInt();
            } catch(Exception error) {
                System.out.println("Debe escribir numeros mayores o iguales a 0\n");
                s.nextLine();
            }
        }
        return new Coordenadas(x,y);
    }
    
    private void  agregarVehiculoBase(Vehiculo vehiculo) {
            Scanner s = new Scanner(System.in);
            int option = 0;
            do { 
                try {
                    System.out.println("\n¿Desea agregar a una base?\n1 - Si\n2 - No");
                    option = s.nextInt();
                    if (option == 1) {
                        Base b = getBase(vehiculo.getTipo());
                        b.imprimirVehiculos();
                        int plaza = s.nextInt();
                        
                        b.agregarVehiculo(plaza,(VehiculoBase) vehiculo);
                    } else if (option == 2) {
                        System.out.println("Ha decidido no agregar el vehiculo a una base");
                    } else {
                        System.out.println("Opcion no valida");
                    }
               
                } catch (Exception e) {
                    s.nextLine();
                }
            } while(option != 1 && option != 2);
    }
}