package logicaDeNegocios;
/**
@author Ariel Rodriguez, Daniel Salazar
*/
public class SustitucionVigenere extends Sustitucion{
	private static String letras="abcdefghijklmnopqrstuvwxyz";
	private static int posicionLlave=0;	
	
  public SustitucionVigenere(){
  }
  
  /**
	  * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
	  * @param pMensaje recibe el texto que se va a codificar
	  * @param pLlave recibe la llave necesaria para el proceso de codificación
	  * @return encriptado que es el texto ya codificado
	  */
  @Override
  public String encriptar(String pTexto, String pLlave) {
		posicionLlave=0;
		String encriptado="";
		for(int i=0;i<pTexto.length();i++) {
			determinarLlave(pLlave);
			encriptado+=validarEspacio(pTexto, i,pLlave);			
		}
		return encriptado;
	}	
  
  /**
	  * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
	  * @param pMensaje recibe el texto que se va a descodificar
	  * @param pLlave recibe la llave necesaria para el proceso de descodificación
	  * @return encriptado que es el texto ya descodificado
	  */
	@Override
	public String desencriptar(String pMensaje, String pLlave) {
		posicionLlave=0;
		String encriptado="";
		for(int i=0;i<pMensaje.length();i++) {
			determinarLlave(pLlave);
			encriptado+=validarDesecriptado(pMensaje, i,pLlave);			
		}
		return encriptado;
	}	
	
	/**
	 * Método que valida el espacio en blanco que existe en el alfabeto dado
	 * @param pMensaje recibe el texto de entrada del mensaje
	 * @param pPosicionLetra recibe la letra del alfabeto para que darle el valor correspondiente
	 * @param pCifra recibe el valor que ingresa el usuario
	 * @return letraEncriptada devuelve la letra o espacio en blanco que fue encontrado
	 * */
	private String validarEspacio(String pMensaje, int pPosicionLetra,String pCifra) {
		String encriptado="";
		String valorCifra = "";
		valorCifra +=pCifra.charAt(posicionLlave);
		if(pMensaje.charAt(pPosicionLetra)!=' ') {
			encriptado += buscarLetra(pMensaje,pPosicionLetra,valorCifra);
			posicionLlave++;
		}else {
			encriptado+=" ";
			posicionLlave=0;
		}
		return encriptado;
	}
	
	/**
	 * Método que retorna la letra si coinciden los parametros dados
	 * @param pMensaje recibe el mensaje que ingresa el usuario
	 * @param pPosicionLetra recibe la posicion de la letra
	 * @param pValorCifra recibe la cifra que se ingreso
	 * @return letra es el valor de la letra en caso que fuera encontrada 
	 * */
	private char buscarLetra(String pMensaje, int pPosicionLetra,String pValorCifra) {
		int valor = Integer.parseInt(pValorCifra);
		char letra=' ';
		for(int j=0;j<letras.length();j++) {
			if(pMensaje.charAt(pPosicionLetra) ==letras.charAt(j)) {
				letra = determinarLetra(j, valor);
			}
		}
		return letra;
	}
	
	/**
	 * Método que determina la letra 
	 * @param pMensaje recibe el mensaje que ingresa el usuario
	 * @param pPosicionLetra recibe la posicion de la letra
	 * @param pValorCifra recibe la cifra que se ingreso
	 * @return letra devuelve el char de la letra que ess la suma de la 1er letra del mensaje y de la llave % el total de cifras del alfabeto
	 * */
	private char determinarLetra(int pPosicionLetra,int pValorLlave) {
		char letra=' ';
		if(pPosicionLetra+pValorLlave>=letras.length()) {
			letra=letras.charAt((pPosicionLetra+pValorLlave)%26);
		}else {
			letra=letras.charAt(pPosicionLetra+pValorLlave);
		}
		return letra;
	}
	
	/**
	 * Método que determina la primera posición de la llave
	 * @param pLlave recibe la llave que ingresa el usuario
	 * */
	private void determinarLlave(String pCifra) {
		if(posicionLlave>=pCifra.length()) {
			posicionLlave=0;
		}
	}
	
	/**
	 * Método que valida el espacio en blanco que existe en el Desencriptado
	 * @param pMensaje recibe el texto de entrada del mensaje
	 * @param pPosicionLetra recibe la letra del alfabeto para que darle el valor correspondiente
	 * @param pCifra recibe la cifra que ingresa el usuario
	 * @return encriptado devuelve la letra o espacio en blanco que fue encontrado
	 * */
	private String validarDesecriptado(String pMensaje, int pPosicionLetra,String pCifra) {
		String encriptado="";
		String valorCifra = "";
		valorCifra +=pCifra.charAt(posicionLlave);
		if(pMensaje.charAt(pPosicionLetra)!=' ') {
			encriptado += buscarCodigo(pMensaje,pPosicionLetra,valorCifra);
			posicionLlave++;
		}else {
			encriptado+=" ";
			posicionLlave=0;
		}
		return encriptado;
	}
	
	/**
	 * Método que busca que determinar la letra del abecedario a partir del valor en la posición
	 * @param pMensaje recibe el texto de entrada del mensaje
	 * @param pPosicionLetra recibe la letra del alfabeto para que darle el valor correspondiente
	 * @param pValorCifra recibe la cifra que ingresa el usuario
	 * @return letra devuelve la letra o espacio en blanco que fue encontrado
	 * */
	private char buscarCodigo(String pMensaje, int pPosicionLetra,String pValorCifra) {
		int valor = Integer.parseInt(pValorCifra);
		char letra=' ';
		for(int j=0;j<letras.length();j++) {
			if(pMensaje.charAt(pPosicionLetra) ==letras.charAt(j)) {
				letra = determinarCodigo(j, valor);
			}
		}
		return letra;
	}
	
	/**
	 * Método que busca que determinar la letra del abecedario a partir del valor en la posición y de la llave
	 * @param pPosicionLetra recibe la letra del alfabeto para que darle el valor correspondiente
	 * @param pValorCifra recibe la cifra que ingresa el usuario
	 * @return letra devuelve el char de la letra que se definio despues de las operaciones realizadas
	 * */
	private char determinarCodigo(int pPosicionLetra,int pValorLlave) {
		char letra=' ';
		if(pPosicionLetra-pValorLlave<0) {
			letra=letras.charAt(26+(pPosicionLetra-pValorLlave));
		}else {
			letra=letras.charAt(pPosicionLetra-pValorLlave);
		}
		return letra;
	}
	
	 /**
		 * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
		 * @param pTexto recibe el texto que se va a codificar
		 * @return textoEncriptado con el mensaje de advertencia que esta clase no provee uso de este método
		 */
  @Override
  public String cifrar(String pTexto) {
  	String texto = "Esta clase no provee uso de este metodo";
		return texto;
  }
  
	 /**
		 * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
		 * @param pTexto recibe el texto que se va a descodificar
		 * @return textoEncriptado con el mensaje de advertencia que esta clase no provee uso de este método
		 */
  @Override
  public String descifrar(String pTexto) {
  	String texto = "Esta clase no provee uso de este metodo";
		return texto;
	}

}
