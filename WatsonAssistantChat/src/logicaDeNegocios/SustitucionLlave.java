package logicaDeNegocios;
/**
@author Ariel Rodriguez, Daniel Salazar
*/
public class SustitucionLlave extends Sustitucion{
  private static String letras="abcdefghijklmnopqrstuvwxyz";
  private static int[] valores= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
  private static int posicionLlave=0;

  public SustitucionLlave(){
  }
 
	 /**
	  * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
	  * @param pMensaje recibe el texto que se va a codificar
	  * @param pLlave recibe la llave necesaria para el proceso de codificación
	  * @return encriptado que es el texto ya codificado
	  */
  @Override
  public String encriptar(String pMensaje, String pLlave) {
    posicionLlave=0;
    String encriptado="";		
    pMensaje=pMensaje.toLowerCase();
    for(int i=0;i<pMensaje.length();i++) {
      encriptado+=validarEspacio(pMensaje, i, pLlave);
    }
		 return encriptado;
  }
	
	/**
	 * Método que valida el espacio en blanco que existe en el alfabeto dado
	 * @param pMensaje recibe el texto de entrada del mensaje
	 * @param pPosicionLetra recibe la letra del alfabeto para que darle el valor correspondiente
	 * @param pLlave recibe la llave que ingresa el usuario
	 * @return letraEncriptada devuelve la letra o espacio en blanco que fue encontrado
	 * */
	private String validarEspacio(String pMensaje, int pPosicionLetra,String pLlave) {
		String letraEncriptada="";
		int valorLetra=0;
		int valorLlave=0;		
		if(pMensaje.charAt(pPosicionLetra)!=' ') {
			determinarLlave(pLlave);				
			valorLetra = valorarLetra(pMensaje, pPosicionLetra);		
			valorLlave = valorarLetra(pLlave, posicionLlave);
			valorLetra = calcularValor(valorLetra, valorLlave);
			letraEncriptada+=encriptarLetra(valorLetra);		
			posicionLlave++;
		}else {
			letraEncriptada+=" ";
		}	
		return letraEncriptada;
	}
	
	/**
	 * Método que determina la primera posición de la llave
	 * @param pLlave recibe la llave que ingresa el usuario
	 * */
	private void determinarLlave(String pLlave) {
		if(posicionLlave>=pLlave.length()) {
			posicionLlave=0;
		}
	}
	
	/**
	 * Método que determina la primera posicion del mensaje
	 * @param pPosicionLetra recibe la posición de la letra que va a buscar
	 * @param pMensaje recibe el mensaje que ingresa el usuario
	 * @return valorLetra devuelve la posición de la letra 
	 * */
	private int valorarLetra(String pMensaje,int pPosicionLetra) {
		int valorLetra=0;
		for(int j=0;j<letras.length();j++) {
			if(pMensaje.charAt(pPosicionLetra)==letras.charAt(j)) {
				valorLetra=valores[j];	
			}
		}
		return valorLetra;
	}
	
	/**
	 * Método que calcula el valor de la posición de la llave con su valor
	 * @param pValorLetra recibe el valor de la letra del mensaje que se va a codificar
	 * @param pValorLlave recibe la primer letra de la llave
	 * @return valorTotal devuelve el valor combinado de los parámetros recibidos
	 * */
	private int calcularValor(int pValorLetra,int pValorLlave) {
		int valorTotal=pValorLetra+pValorLlave;
		if(valorTotal>letras.length()) {
			valorTotal=valorTotal % 26;
		}
		return valorTotal;
	}
	
	/**
	 * Método que encripta el valor de la letra
	 * @param pValor recibe el valor de la letra
	 * @return letra que es el valor de la letra codificada
	 * */
	private char encriptarLetra(int pValor) {
		char letra=' ';
		for(int j=0;j<letras.length();j++) {
			if(pValor==valores[j]) {
				letra=letras.charAt(j);
			}
		}
		return letra;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	 /**
	  * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
	  * @param pMensaje recibe el texto que se va a descodificar
	  * @param pLlave recibe la llave necesaria para el proceso de descodificación
	  * @return encriptado que es el texto ya descodificado
	  */
	@Override
	public String desencriptar(String pMensaje, String pLlave) {
		posicionLlave=0;
		posicionLlave=0;
		String encriptado="";		
		pMensaje=pMensaje.toLowerCase();
		for(int i=0;i<pMensaje.length();i++) {
			encriptado+=validarDesencriptado(pMensaje, i, pLlave);
		}
		return encriptado;
	}
	
	/**
	 * Método que valida el espacio en blanco que existe en el Desencriptado
	 * @param pMensaje recibe el texto de entrada del mensaje
	 * @param pPosicionLetra recibe la letra del alfabeto para que darle el valor correspondiente
	 * @param pLlave recibe la llave que ingresa el usuario
	 * @return letraEncriptada devuelve la letra o espacio en blanco que fue encontrado
	 * */
	private String validarDesencriptado(String pMensaje, int pPosicionLetra,String pLlave) {
		String letraEncriptada="";
		int valorLetra=0;
		int valorLlave=0;		
		if(pMensaje.charAt(pPosicionLetra)!=' ') {
			determinarLlave(pLlave);				
			valorLetra = valorarLetra(pMensaje, pPosicionLetra);		
			valorLlave = valorarLetra(pLlave, posicionLlave);
			valorLetra = calcularDesencriptado(valorLetra, valorLlave);
			letraEncriptada+=encriptarLetra(valorLetra);		
			posicionLlave++;
		}else {
			letraEncriptada+=" ";
		}	
		return letraEncriptada;
	}
	
	/**
	 * Método que calcula el valor de la posición de la llave con su valor
	 * @param pValorLetra recibe el valor de la letra del mensaje que se va a codificar
	 * @param pValorLlave recibe la primer letra de la llave
	 * @return valorTotal devuelve el valor combinado de los parámetros recibidos
	 * */
	private int calcularDesencriptado(int pValorLetra,int pValorLlave) {
		int valorTotal=pValorLetra-pValorLlave;
		if(valorTotal<0) {
			valorTotal=valorTotal + 26;
		}
		return valorTotal;
	}

	 /**
		 * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
		 * @param pTexto recibe el texto que se va a codificar
		 * @return textoEncriptado con el mensaje de advertencia que esta clase no provee uso de este método
		 */
	  @Override
	  public String cifrar(String pTexto) {
	  	String textoEncriptado="";
			return textoEncriptado;
	  }
	  
		 /**
			 * Método que se sobrescribe proveniente de la interfaz y hace su propia implementación
			 * @param pTexto recibe el texto que se va a descodificar
			 * @return textoEncriptado con el mensaje de advertencia que esta clase no provee uso de este método
			 */
	  @Override
	  public String descifrar(String pTexto) {
	  	String textoEncriptado="";
			return textoEncriptado;
	  }


}
