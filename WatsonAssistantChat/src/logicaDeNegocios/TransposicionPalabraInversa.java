package logicaDeNegocios;
/**
@author Ariel Rodriguez, Daniel Salazar
*/
public class TransposicionPalabraInversa extends Transposicion{
	private String[] cadena;
	private String textoCodificado;
	private String textoDecifrado;

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
	 * Método que codifica la implementación de transposicion de palabra inversa
	 * @param pTexto pTexto recibe el texto que se va a codificar
	 * @return mensajeCodificado almacena el valor del mensaje codificado
	 * */
	private String codificar(String pTexto) {
		cadena = pTexto.split(" ");
		textoCodificado="";
		
		for(int i=0; i<cadena.length; i++){
			String palabra = cadena[i];
			String palabraInvertida = "";
			
			for(int j=palabra.length()-1; j>=0; j--) { 
				palabraInvertida = palabraInvertida + palabra.charAt(j);
			}
			textoCodificado = textoCodificado + palabraInvertida + " ";
		}	
		return textoCodificado;
	}
	
	/**
	 * Método que descodifica la implementación de transposicion de palabra inversa
	 * @param pTexto pTexto recibe el texto que se va a codificar
	 * @return mensajeCodificado almacena el valor del mensaje codificado
	 * */
	private String descodificar(String pTexto) {
		cadena = pTexto.split(" ");
		textoDecifrado="";
		
		for(int i=0; i<cadena.length; i++){
			String palabra = cadena[i];
			String palabraInvertida = "";
			
			for(int j=palabra.length()-1; j>=0; j--) { 
				palabraInvertida = palabraInvertida + palabra.charAt(j);
			}
			textoDecifrado = textoDecifrado + palabraInvertida + " ";
		}	
		return textoDecifrado;
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
