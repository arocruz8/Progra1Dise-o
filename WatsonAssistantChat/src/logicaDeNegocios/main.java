package logicaDeNegocios;

public class main {


	public static void main(String[] args) {

		CriptografiaSimpleFactory FabricaCriptografia= new CriptografiaSimpleFactory();

		
		
	try {
		String wea;
		Criptografia g0;	
		g0 = FabricaCriptografia.crearObjeto("CodBinario");
		wea=g0.cifrar("puta");
		System.out.println("Codigo binario: "+wea);
		
		Criptografia g1;
		g1 = FabricaCriptografia.crearObjeto("CodTelefonico");
		wea=g1.cifrar("puta");
		System.out.println("Codigo telefonico: "+wea);
		
		Criptografia g2;	
		g2 = FabricaCriptografia.crearObjeto("SustitucionLlave");
		wea=g2.cifrar("puta");
		System.out.println("Sust llave: "+wea);
		
		Criptografia g3;
		g3 = FabricaCriptografia.crearObjeto("SustitucionCesar");
		wea=g3.cifrar("puta");
		System.out.println("Sust Cesar: "+wea);
		
		Criptografia g4;
		g4 = FabricaCriptografia.crearObjeto("SustitucionVigenere");
		wea=g4.encriptar("tarea", "23");
		System.out.println("Sust Vigenere: "+wea);
		wea = g4.desencriptar("vdthc", "23");
		System.out.println("VIGENRE "+wea);
		
		Criptografia g5;	
		g5 = FabricaCriptografia.crearObjeto("TransposicionMensajeInverso");
		wea=g5.cifrar("puta");
		System.out.println("Mensaje Inverso: "+wea);
		
		Criptografia g6;
		g6 = FabricaCriptografia.crearObjeto("TransposicionPalabraInversa");
		wea=g6.cifrar("puta");
		System.out.println("Palabra Inversa: "+wea);
			
			
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	}

}
