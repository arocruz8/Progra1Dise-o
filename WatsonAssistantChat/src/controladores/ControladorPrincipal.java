package controladores;

import utilitarias.ChatService;
import logicaDeNegocios.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v1.model.RuntimeEntity;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

public class ControladorPrincipal {
	Context context;
	private String operacion;
	CriptografiaSimpleFactory definir;
	
	public ControladorPrincipal() {
	}
	
	/**
	 * Método que valida si el usuario desea codificar o descodificar un mensaje 
	 * @param pContext recibe los datos del contexto de la conversación de Watson
	 * @return respuesta determina la respuesta que ingreso el usuario ya sea codificar o descodificar
	 */
	private boolean detectarOperacion(Context pContext) {
		operacion = obtenerVarContexto(pContext, "respuestaBienvenida");
		boolean respuesta;
		if(operacion.equalsIgnoreCase("Codificar")){
			respuesta=true;
		}else {
			respuesta=false;
		}
		return respuesta;
	}
	
	/**
	 * Método que extrae un dato del contexto obtenido del chat de watson
	 * @param pContext recibe los datos del contexto de la conversación de Watson
	 * @param pTipoVarible obtiene la variable de contexto que se obtuvo 
	 * @return varContexto la variable encontrada
	 */
	private String obtenerVarContexto(Context pContext, String pTipoVarible) {
		String varContexto= (String) pContext.get(pTipoVarible);
		return varContexto;
	}

	/**
	 * Método que realiza un encriptado o desecncriptado el cual no necesite de una lleva para funcionar
	 * @param pContext recibe los datos del contexto de la conversación de Watson
	 * @return codigo que es el resutado de cifrar la palabra
	*/
	public String realizarEncriptado(Context context) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String mensaje= obtenerVarContexto(context, "textoObtenido");
		String tipo=obtenerVarContexto(context, "tipoCodigo");
		String codigo;
		Criptografia funcion;
		boolean operacion = detectarOperacion(context);
		definir = new CriptografiaSimpleFactory();
		funcion = definir.crearObjeto(tipo);
		if(operacion==true) {
			codigo =funcion.cifrar(mensaje);
		}else {
			codigo=funcion.descifrar(mensaje);
		}
		return codigo;
	}
	
	/**
	 * Método que realiza un encriptado o desecncriptado el cual necesita de una lleva para funcionar
	 * @param pContext recibe los datos del contexto de la conversación de Watson
	 * @return codigo que es el resutado de cifrar la palabra
	*/
	public String realizarCodificado(Context context) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String mensaje= obtenerVarContexto(context, "textoObtenido");
		String clave = obtenerVarContexto(context, "claveObtenida");
		String tipo=obtenerVarContexto(context, "tipoCodigo");
		String codigo;
		Criptografia funcion;
		boolean operacion = detectarOperacion(context);
		definir = new CriptografiaSimpleFactory();
		funcion = definir.crearObjeto(tipo);
		if(operacion==true) {
			codigo =funcion.encriptar(mensaje, clave);
		}else {
			codigo= funcion.desencriptar(mensaje, clave);
		}
		return codigo;
	}

}