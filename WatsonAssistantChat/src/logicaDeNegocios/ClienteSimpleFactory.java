package logicaDeNegocios;

/**
@author Ariel Rodriguez, Daniel Salazar
*/
public class ClienteSimpleFactory {
	private CriptografiaSimpleFactory factory;
	
	public ClienteSimpleFactory(CriptografiaSimpleFactory pFactory){
		factory=pFactory;
	}
	
	/**
	 * Método heredado de la facotry para crear un objeto de tipo Criptografia
	 * @param pTipo recibe el nombre de la clase que va a instanciar
	 * @return cripto crea un objeto del tipo del parámetro
	 * */
	public Criptografia crearObjeto(String pTipo) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Criptografia cripto;
		cripto = factory.crearObjeto(pTipo);
		return cripto;
	}

}
