package logicaDeNegocios;
/**
@author Ariel Rodriguez, Daniel Salazar
*/
public abstract class Sustitucion extends Criptografia {
	
	public abstract String cifrar(String pTexto);
	public abstract String descifrar(String pTexto);
	public abstract String encriptar(String pMensaje, String pLlave);
	public abstract String desencriptar(String pMensaje, String pLlave);
}
