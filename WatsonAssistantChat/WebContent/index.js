var misCabeceras = new Headers();

var miInit = { method: 'GET',
               headers: misCabeceras,
               mode: 'cors',
               cache: 'default' };

class App extends React.Component {
	//constructor de la conversacion vacia
    constructor() {
        super()
        this.state = {
            messages: [],//lista de mensajes que se van a crear en la conversacion
        	ctx: {usr: 'ChatBotDiseño'}
        }
        this.sendMessage = this.sendMessage.bind(this)
    } 
    
    /*El método componentDidMount () es el lugar perfecto, 
     * donde podemos llamar al método setState () para cambiar el 
     * estado de nuestra aplicación y renderizar () los datos 
     * actualizados cargados JSX
    */
    componentDidMount() {
    	this.sendMessage("");
    }

    //metodo que crea los mensajes
    sendMessage(text) {
    	if(text != "") //conficion que verifica que el mensaje no este vacio
	    	this.setState({
	            messages: [...this.state.messages, {"usr": "usuario", "text": text}]
	        })
    	
	    //variable que almacena el archivo JSON con el contenido de la conversación
        var ctxStr = JSON.stringify(this.state.ctx);
    	//fetch(`http://localhost:9080/1Diseno/chatbot/chatservice/?conversationMsg=${encodeURIComponent(text)}&conversationCtx=${encodeURIComponent(ctxStr)}`)
        fetch(`https://PD1DanielAriel.mybluemix.net/chatbot/chatservice/?conversationMsg=${encodeURIComponent(text)}&conversationCtx=${encodeURIComponent(ctxStr)}`)
    	.then((response) => { //funcion que devuelve la respuesta y lo escribe en el json
          return response.json()
        })
        .then((myJsonResponse) => {
        	//el state es el estado actual que contiene el valor de los mensajes
        	//actualiza el valor cada que se ingrese, reciba un mensaje
        	this.setState({
	            messages: [...this.state.messages, {"usr": myJsonResponse.context.usr, "text": myJsonResponse.response}],
        		ctx: myJsonResponse.context
	        })
        })
    }
    
    /*carga la vista de la pagina principal
     * */
    render() {
        return (
            <div className="app">
              <Title />
              <h2 className="title">TI5501-Diseño de Software</h2>
              <h4 className="title">Proyecto 1</h4>
              <h5 className="title">Daniel Salazar - Ariel Rodríguez</h5>
              <hr></hr>
              <Informacion />
              <hr></hr>
			  <MessageList
                  messages={this.state.messages} />
              <SendMessageForm
                  sendMessage={this.sendMessage} />
            </div>
        );
    }
}

/*clase informacion
 * muestra la informacion del app en la pag html 
 * */
class Informacion extends React.Component{
	render(){
		return (
			<form>
				<div className="card">
					<div className="card">
						<h2>Información del app</h2>
						 	<p>La criptografía es la técnica, ciencia o arte de la escritura secreta. El principio básico de la
						 		criptografía es mantener la privacidad de la comunicación entre dos personas alterando el
								mensaje original de modo que sea incomprensible a toda persona distinta del destinatario.
							</p>
						 	<p>
						 		Se puede decir que la criptografía es tan antigua como la civilización, cuestiones
						 		militares, religiosas o comerciales impulsaron desde tiempos remotos el uso de
						 		escrituras secretas; los antiguos egipcios usaron métodos criptográficos, mientras
						 		el pueblo utilizaba la lengua demótica, los sacerdotes usaban la escritura hierática
						 		(jeroglífica) incomprensible para el resto. Los antiguos babilonios también utilizaron
						 		métodos criptográficos en su escritura cuneiforme.
							</p>
					</div>
				</div>
			</form>
		
		)
	}
}


/*clase que tiene la lista de mensajes
 * crea el cuerpo de los mensajes en el html
 * Conceptualmente, los componentes son como las funciones de JavaScript. 
 * Aceptan entradas arbitrarias “props” (que proviene de propiedades)
 *  con datos y devuelve un elemento de React.
 *  {message.text} imprime el contexto de la conversacion en la pantalla
 * */
class MessageList extends React.Component {
    render() {
        return (
            <ul className="message-list">
                {this.props.messages.map((message, index) => {
                    return (
                      <li  key={index} className="message">
                      	<div>{message.usr}</div>  
                      	<div>{message.text}</div>
                      </li>
                    )
                })}
            </ul>
        )
    }
}

//clase para enviar los mensajes
class SendMessageForm extends React.Component {
    constructor() {
        super()
        this.state = {
            message: ''
        }
        //.bind actualiza el mensaje
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }
    
    //obtiene el valor del mensaje
    handleChange(e) {
        this.setState({ //actualiza el estado del mensaje
            message: e.target.value
        })
    }
    
    //obtiene los datos del mensaje
    handleSubmit(e) {
        e.preventDefault()
        this.props.sendMessage(this.state.message)//guarda el valor
        this.setState({
            message: ''
        })
    }
    
    //crea el mensaje del usuario 
    render() {
        return (
            <form id="datosEntrada"
                onSubmit={this.handleSubmit}
                className="send-message-form">
                <input
                	ref={this.entradaRef}
                    onChange={this.handleChange}
                    value={this.state.message}
                    placeholder="Por favor ingrese su mensaje"
                    type="text" />
            </form>
        )
    }  
}

function Title() {
  return <h1 className="title">Consulta de servicios de criptografía</h1>
}

ReactDOM.render(<App />, document.getElementById('root'));