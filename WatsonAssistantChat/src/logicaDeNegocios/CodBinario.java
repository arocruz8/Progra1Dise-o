package logicaDeNegocios;

/**
@author Ariel Rodriguez, Daniel Salazar
*/

public class CodBinario extends Criptografia {
	private static String[] codigos= {"00000","00001","00010","00011","00100","00101","00110","00111","01000","01001","01010","01011","01100","01101","01110","01111","10000","10001","10010","10011","10100","10101","10110","10111","11000","11001","*"}; 
	private static String letras="abcdefghijklmnopqrstuvwxyz ";	
	private static String codigo;
	private static char letraDecodificada=' ';
	private String encriptado;
	private String mensaje;
	private String palabra;

	public CodBinario(){
	}
	
  /**
  * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
  * @param pTexto recibe el texto que se va a codificar
  * @return textoEncriptado que es el texto ya codificado
  */
	@Override
	public String cifrar(String pTexto) {
		String textoEncriptado = codificar(pTexto);
		return textoEncriptado;
	}
	
	/**
	* Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
	* @param pTexto recibe el texto que se va a descodificar
	* @return textoDesencriptado que es el texto ya descodificado
	*/
	@Override
	public String descifrar(String pTexto) {
		String textoDesencriptado = descodificar(pTexto);
		return textoDesencriptado;
	}	
	
	/**
	 * Método que codifica el valor de la letra del mensaje
	 * @param pMensaje recibe el texto que ingresa el usuario
	 * @param pContadorLetra recibe la posicion de la letra
	 * @return codigo con el valor del mensaje 
	 * */
	private String codificarLetra(String pMensaje,int pContadorLetra) {
		for(int j=0;j<letras.length();j++) {
			if(pMensaje.charAt(pContadorLetra)==letras.charAt(j)) {
				codigo =codigos[j];
			}
		}
		return codigo;
	}
	
	/**
	 * Método que descodifica el valor de la letra del mensaje
	 * @param pPalabra recibe el texto que ingresa el usuario
	 * @param pPosicionLetra recibe la posicion de la letra
	 * @return letraDecodificada devuelve la letra que se descodifico 
	 * */
	private char decodificarLetra(String pPalabra,int pPosicionLetra) {
		for(int j=0;j<codigos.length;j++) {
			if(pPalabra.equals(codigos[j])) {
				letraDecodificada=letras.charAt(j);
			}
		}
		return letraDecodificada;
	}

	/**
	 * Método que codifica el mensaje que se recibe con el método de codificación binaria
	 * @param pTexto recibe el texto de entrada
	 * @return encriptado devuelve el texto encriptado
	 * */
	private String codificar(String pTexto) {
		encriptado="";		
		pTexto=pTexto.toLowerCase();
		for(int i=0;i<pTexto.length();i++) {
			encriptado += codificarLetra(pTexto, i);
			encriptado +=" ";
		}
		return encriptado;
	}

	/**
	 * Método que descodifica el mensaje que se recibe usando el uso del método de codificación binaria
	 * @param pTexto recibe el texto de entrada
	 * @return mensaje devuelve el texto desencriptado
	 * */
	private String descodificar(String pTexto) {
		mensaje="";
		palabra="";
		for(int i=0;i<pTexto.length();i++) {
			if(pTexto.charAt(i)!=' ' && i!=pTexto.length()) {
				palabra+=pTexto.charAt(i);
			}
			else {
				mensaje+=decodificarLetra(palabra, i);
				palabra="";
			}
		}
		mensaje+=decodificarLetra(palabra, pTexto.length());
		return mensaje;
	}
	
	/**
	 * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
	 * @param pMensaje recibe el texto que se va a codificar
	 * @param pLlave recibe la llave 
	 * @return texto con el mensaje de advertencia que esta clase no provee uso de este método
	 */
	@Override
	public String encriptar(String pMensaje, String pLlave) {
		String texto = "Esta clase no provee uso de este metodo";
		return texto;
	}

	/**
	 * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
	 * @param pMensaje recibe el texto que se va a descodificar
	 * @param pLlave recibe la llave 
	 * @return texto con el mensaje de advertencia que esta clase no provee uso de este método
	 */
	@Override
	public String desencriptar(String pMensaje, String pLlave) {
		String texto = "Esta clase no provee uso de este metodo";
		return texto;
	}


}
