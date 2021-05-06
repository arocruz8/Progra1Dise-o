package logicaDeNegocios;

/**
@author Ariel Rodriguez, Daniel Salazar
*/
public class CriptografiaSimpleFactory {
	
	/**
	 * Método heredado de la facotry para crear un objeto de tipo Criptografia
	 * @param pTipo recibe el nombre de la clase que va a instanciar
	 * @return nuevoCripto crea un objeto del tipo del parámetro
	 * */
	public Criptografia crearObjeto (String pTipo) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Criptografia nuevoCripto = null;
		nuevoCripto = (Criptografia) Class.forName("logicaDeNegocios."+pTipo).newInstance();
		return nuevoCripto;
	}
	
}



