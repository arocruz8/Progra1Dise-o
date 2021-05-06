package logicaDeNegocios;

/**
@author Ariel Rodriguez, Daniel Salazar
*/
public class CodTelefonico extends Criptografia{
	private static String letras="abcdefghijklmnopqrstuvwxyz ";	
	private static String[] codigoTelefonico= {"21","22","23","31","32","33","41","42","43","51","52","53","61","62","63","71","72","73","74","81","82","83","91","92","93","94","*"};
	                //(1,2,3) (4,5,6) (7,8,9) (10,11,12) (13,14,15) (16,17,18,19) (20,21,22) (23,24,25,26)
							   //  a b c  d e f   g h i   j  k  l    m  n  o     p  q  r  s   t  u  v     w  x  y  z	
	
	public CodTelefonico (){
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
		String codigo="";
		for(int j=0;j<letras.length();j++) {
			if(pMensaje.charAt(pContadorLetra)==letras.charAt(j)) {
				codigo =codigoTelefonico[j];
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
		char letraDecodificada=' ';
		for(int j=0;j<codigoTelefonico.length;j++) {
			if(pPalabra.equals(codigoTelefonico[j])) {
				letraDecodificada=letras.charAt(j);
			}
		}
		return letraDecodificada;
	}
	
	/**
	 * Método que codifica el mensaje que se recibe, usando el código teléfonico
	 * @param pTexto recibe el texto de entrada
	 * @return encriptado devuelve el texto encriptado
	 * */
	private String codificar(String pTexto) {
		String encriptado="";		
		pTexto=pTexto.toLowerCase();
		for(int i=0;i<pTexto.length();i++) {
			encriptado += codificarLetra(pTexto, i);
			encriptado +=" ";
		}
		return encriptado;
	}
	
	/**
	 * Método que descodifica el mensaje que se recibe, usando el código teléfonico
	 * @param pTexto recibe el texto de entrada
	 * @return mensaje devuelve el texto desencriptado
	 * */
	private String descodificar(String pTexto) {
		String mensaje="";
		String palabra="";
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
