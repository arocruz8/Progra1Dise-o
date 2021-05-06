package utilitarias;

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

import controladores.ControladorPrincipal;

@Path("/chatservice")
public class ChatService {
	
	private String apiKey = "iPnlLEjeFOO-r7cbuCW8Mx12Q3DnpxyDwi8raKLBPzo2";
	private String assistantURL = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/c1202d7d-557d-47b5-b8cf-388ab879f57e";
	private static String workspaceId = "9439f8b8-8c0d-4dca-91ba-d9fa51d3b6fd";
	
	public String mensaje;
	public ControladorPrincipal control = new ControladorPrincipal();
	
	public ChatService(){
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces("application/json")
	public Response getResponse(@QueryParam("conversationMsg") String conversationMsg, @QueryParam("conversationCtx") String conversationCtx) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		//autentificacion del usuario en los servicios 
		IamOptions iAmOptions = new IamOptions.Builder().apiKey(apiKey).build();

		//se obtine la skill del servicio de watson assistant
		Assistant service = new Assistant("2018-09-20", iAmOptions);
		service.setEndPoint(assistantURL);
		
		//inicia la conversación con un valor vacio
		JSONObject ctxJsonObj = new JSONObject(conversationCtx);
		
		// se inicia la variable del contexto de la conversion 
		Context context = new Context();
		
		//se agrega toda la informacion de la conversacion al archivo json
		context.putAll(ctxJsonObj.toMap());		
   
		// extrae el mensaje a condificar
		String verificar = obtenerVarContexto(context, "textoObtenido");
		
		
		String tipoCriptografia = obtenerVarContexto(context, "tipoCodigo");
		
		boolean validar= validarNull(tipoCriptografia);
		String llave = obtenerVarContexto(context, "claveObtenida");
		
		//Valida si el tipo de cifrado es sustitucion o transposicion
		if(tipoCriptografia!=null) {
			verificar=verificarTipo(tipoCriptografia,verificar);
		}
		
		//Valida si el tipo de criptografia necesita una llave para realizar el cifrado
		if(verificar!=null && validar==false) {
			String mensaje = control.realizarEncriptado(context);
			context.put("textoObtenido", mensaje);
		}
		else if(llave!=null && validar==true) {
			String mensaje = control.realizarCodificado(context);
			context.put("textoObtenido", mensaje);
			context.put("claveObtenida", null);
		}
   		
		
		//agrega la informacion que digita el usuario
		InputData input = new InputData.Builder(conversationMsg).build();
		
		//crea los mensajes 
		MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
		
		//obtiene la respuesta de la conversacion
		MessageResponse assistantResponse = service.message(options).execute();
		System.out.println(assistantResponse); 
		
		//DespuEs del assistant Response manipulamos el contexto
		//Metemos informaciOn
		//context.put("pruebaVariable", "Soy un valor del cOdigo");	
		
		// Print the output from dialog, if any.
		List<String> assistantResponseList = assistantResponse.getOutput().getText();
		
		//se crea el objeto tipo json
		JSONObject object = new JSONObject();
		
		//variable guarda las respuestas de chatbot
		String assistantResponseText = "";
		
		//recorre la lista de respuestas para añadirlas al json
		for (String tmpMsg : assistantResponseList)
			assistantResponseText = assistantResponseText + System.lineSeparator() + tmpMsg;
		
		//le ingresa a el objeto json las respuestas de la conversacion 
		object.put("response", assistantResponseText);
		object.put("context", assistantResponse.getContext());
		//object.put("textoTelefonicoRecibido", assistantResponse.getContext());
		
		//devuelve el archivo json
		return Response.status(Status.OK).entity(object.toString()).build();
	}
	

	/**
	 * Método que permite obtener varibles de contexto al final de cada nodo
	 * @param pContext recibe el contexto de la conversacion
	 * @param pTipoVarible recibe el nombre que ingresa el usuario para reconocer la variable
	 * @return varContexto la variable encontrada
	 */
	private String obtenerVarContexto(Context pContext, String pTipoVarible) {
		String varContexto= (String) pContext.get(pTipoVarible);
		return varContexto;
	}
	
	
	/**
	 * Método que verifica si el tipo de sustitucion es transposicion o de sustitucion
	 * @param tipo recibe el tipo de de jerarquia que va a explicar
	 * @param texto recibe el texto que ingresa el usuario
	 * @return texto
	 */
	private String verificarTipo(String tipo, String texto) {
		if(tipo.equalsIgnoreCase("transposición")||tipo.equalsIgnoreCase("sustitución")) {
			texto=null;
		}
		return texto;
	}
	
	/**
	 * Método que valida el ingreso de valores vacios al encriptado
	 * @param tipo valida el ingreso de valores
	 * @return validar devuelve si es true o false en caso de que este vacio 
	 */
	private boolean validarNull(String tipo) {
		boolean validar;
		if(tipo!=null) {
			validar=validarSustitucion(tipo);
		}else {
			validar=false;
		}
		return validar;
	}
	
	/**
	 * Método que valida el tipo de sustitucion que el usuario desea realizar
	 * @param tipo recibe el tipo de sutitución si es vigenere o llave
	 * @return validar el calor del tipo que ingreso
	 */
	private boolean validarSustitucion(String tipo) {
		boolean validar;
		if(tipo.equalsIgnoreCase("SustitucionLlave")||tipo.equalsIgnoreCase("SustitucionVigenere")) {
			validar=true;
		}else {
			validar=false;
		}
		return validar;
	}
}