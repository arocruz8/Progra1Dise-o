package logicaDeNegocios;
/**
@author Ariel Rodriguez, Daniel Salazar
*/
public class SustitucionCesar extends Sustitucion{

	private static String min="abcdefghijklmnopqrstuvwxyz";
	private static String may="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static char letra;

	public SustitucionCesar(){
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
	 * Método que valida el espacio en blanco que existe en el alfabeto dado
	 * @param pMensaje recibe el texto de entrada del mensaje
	 * @param pPosicionLetra recibe la letra del alfabeto para que darle el valor correspondiente
	 * @return encriptado recibe la letra encriptado
	 * */
	private String validarEspacio(String pMensaje, int pPosicionLetra) {
		String encriptado="";
		if(pMensaje.charAt(pPosicionLetra)!=' ') {
			encriptado += buscarLetra(pMensaje,pPosicionLetra);
		}else {
			encriptado+=" ";
		}
		return encriptado;
	}
	
	/**
	 * Método que busca la posicion de la letra en el alfabeto
	 * @param pMensaje recibe el mensaje
	 * @param i recibe la posicion
	 * @return letra devuelve su valor en el alfabeto 
	 * */
	private char buscarLetra(String pMensaje, int i) {
		for(int j=0;j<min.length();j++) {
			if(pMensaje.charAt(i) ==min.charAt(j)) {
				letra = cambiarMinuscula(j);
			}
			else if(pMensaje.charAt(i)==may.charAt(j)) {
				letra = cambiarMayuscuta(j);
			}
		}
		return letra;
	}
	
	/**
	 * Método que transforma el valor recibido en la letra minuscula correspondiente a la posición
	 * @param j es la posición de la letra que se va a buscar
	 * @return letra devuelve la letra encontrada
	 * */
	private char cambiarMinuscula(int j) {
		if(j+3>=min.length()) {
			letra=min.charAt((j+3) % min.length());	
		}
		else {
			letra=min.charAt(j+3);
		}
		return letra;
		
	}
	
	/**
	 * Método que transforma el valor recibido en la letra mayuscula correspondiente a la posición
	 * @param j es la posición de la letra que se va a buscar
	 * @return letra devuelve la letra encontrada
	 * */
	private char cambiarMayuscuta(int j) {
		if(j+3>=may.length()) {
			letra=may.charAt((j+3) % may.length());	
		}
		else {
			letra=may.charAt(j+3);
		}
		return letra;
		
	}
	
	/**
	 * Método que codifica la implementación de sustitucion césar
	 * @param pTexto pTexto recibe el texto que se va a codificar
	 * @return mensajeCodificado almacena el valor del mensaje codificado
	 * */
	public String codificar(String pTexto) {
		String encriptado="";
		for(int i=0;i<pTexto.length();i++) {
			encriptado+=validarEspacio(pTexto, i);
		}
		return encriptado;
	}
	
	//////////////////////////////////////////////////////////
	
	/**
	 * Método que busca la posicion de la letra en el alfabeto
	 * @param pCodigo recibe el mensaje
	 * @param i recibe la posicion
	 * @return letra devuelve su valor en el alfabeto 
	 * */
	private char buscarCodigo(String pCodigo, int i) {
		for(int j=0;j<min.length();j++) {
			if(pCodigo.charAt(i) ==min.charAt(j)) {
				letra = descifrarMinuscula(j);
			}
			else if(pCodigo.charAt(i)==may.charAt(j)) {
				letra = descifrarMayuscuta(j);
			}
		}
		return letra;
	}
	
	/**
	 * Método que transforma el valor recibido en la letra minuscula correspondiente a la posición
	 * @param j es la posición de la letra que se va a buscar para la descodificación 
	 * @return letra devuelve la letra encontrada
	 * */
	private char descifrarMinuscula(int j) {
		if(j-3<0) {
			letra=min.charAt(min.length()+(j-3));
		}
		else {
			letra=min.charAt(j-3);
		}
		return letra;
		
	}
	
	/**
	 * Método que transforma el valor recibido en la letra mayuscula correspondiente a la posición
	 * @param j es la posición de la letra que se va a buscar para la descodificación 
	 * @return letra devuelve la letra encontrada
	 * */
	private char descifrarMayuscuta(int j) {
		if(j-3<0) {
			letra=may.charAt(min.length()+(j-3));	
		}
		else {
			letra=may.charAt(j-3);
		}
		return letra;
		
	}
	
	/**
	 * Método que valida el espacio en blanco que existe en el alfabeto dado
	 * @param pCodigo recibe el texto de entrada del mensaje para la descodificación
	 * @param pPosicionLetra recibe la letra del alfabeto para que darle el valor correspondiente
	 * @return encriptado recibe la letra encriptado
	 * */
	private String validarCodigo(String pCodigo, int pPosicionLetra) {
		String encriptado="";
		if(pCodigo.charAt(pPosicionLetra)!=' ') {
			encriptado += buscarCodigo(pCodigo,pPosicionLetra);
		}else {
			encriptado+=" ";
		}
		return encriptado;
	}

	/**
	 * Método que codifica la implementación de sustitucion césar
	 * @param pTexto pTexto recibe el texto que se va a descodificar
	 * @return mensajeCodificado almacena el valor del mensaje descodificado
	 * */
	public String descodificar(String pTexto) {
		String desencriptado="";
		for(int i=0;i<pTexto.length();i++) {
			desencriptado += validarCodigo(pTexto,i);	
		}
		return desencriptado;
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