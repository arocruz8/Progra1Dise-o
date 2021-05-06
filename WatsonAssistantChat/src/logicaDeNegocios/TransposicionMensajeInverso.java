package logicaDeNegocios;

/**
@author Ariel Rodriguez, Daniel Salazar
*/

public class TransposicionMensajeInverso extends Transposicion{
	
	private char [] letras;
	private String mensajeCodificado;
	private String mensajeDecodificado;
	
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
	 * Método que codifica la implementación de transposicion de mensaje inverso
	 * @param pTexto pTexto recibe el texto que se va a codificar
	 * @return mensajeCodificado almacena el valor del mensaje codificado
	 * */
	private String codificar(String pTexto) {
		letras = new char[pTexto.length()];
		int posicionLetras = 0; //contador
		//agarra las posiciones del string - 1 para cambiarlas de posicion posteriormente
		for(int i=pTexto.length() - 1; i>=0 ; i--){
			letras[posicionLetras] = pTexto.charAt(i);//acomoda la posicion de la cadena de caracteres
			posicionLetras++;//avanza el indice en la cadena
		}
		
		mensajeCodificado = ""; //se crea la nueva cadena para guardar el resultado despues del proceso anterior
		for(int b=0; b<pTexto.length(); b++) { 
			mensajeCodificado = mensajeCodificado + letras[b]; //acomoda el orden de los caracteres en la palabra nueva
		}
		
		return mensajeCodificado;
	}

	/**
	 * Método que descodifica la implementación de transposicion de mensaje inverso
	 * @param pTexto pTexto recibe el texto que se va a descodificar
	 * @return mensajeDecodificado almacena el valor del mensaje codificado
	 * */
	private String descodificar(String pTexto) {
		letras = new char[pTexto.length()];
		int posicionLetras = 0; //contador
		//agarra las posiciones del string - 1 para cambiarlas de posicion posteriormente
		for(int i=pTexto.length() - 1; i>=0 ; i--){
			letras[posicionLetras] = pTexto.charAt(i);//acomoda la posicion de la cadena de caracteres
			posicionLetras++;//avanza el indice en la cadena
		}
		
		mensajeDecodificado = ""; //se crea la nueva cadena para guardar el resultado despues del proceso anterior
		for(int b=0; b<pTexto.length(); b++) { 
			mensajeDecodificado = mensajeDecodificado + letras[b]; //acomoda el orden de los caracteres en la palabra nueva
		}
		
		return mensajeDecodificado;
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
