package com.tobin.master2;

import java.util.Hashtable;
import java.util.Vector;

import org.apache.xmlrpc.WebServer;
import org.apache.xmlrpc.XmlRpcClientException;
import org.apache.xmlrpc.XmlRpcException;


public class ChatRoomImpl implements ChatRoom {

	private Hashtable<String,String> sessions = new Hashtable<String,String>();
	private Vector<String> discussion = new Vector<String>();
	
	public ChatRoomImpl() {
		// TODO Auto-generated constructor stub
		
	}

	public String getMessages(String token){
		String messages = "";
		
		if(!(sessions.containsKey(token))){
			messages = discussion.lastElement();
		}else{
			for (String message : discussion) {
				messages = messages + " "+message;
				
			}
		}
		return messages;
	}
	
	
	@Override
	public void subscribe(ChatUser user, String pseudo) throws XmlRpcClientException {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void postMessage(String pseudo, String message) throws XmlRpcClientException {
		// TODO Auto-generated method stub
		discussion.addElement(pseudo +" : "+message);
		//System.err.println("Showing message ");
	}

	@Override
	public Vector getParameters() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getMethodName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object getParameter(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void unsubscribe(String pseudo) throws XmlRpcException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		try {
			WebServer server = new WebServer(80);
			ChatRoomImpl chatRoom = new ChatRoomImpl();
			server.addHandler("ChatRoom", chatRoom);
			server.start();
			System.out.println("Démarrage du Serveur XML-RPC .... OK");
			System.out.println("Acceptation de Requetes .... OK");
			System.out.println("Hint : Arrêtez le programme pour Eteindre le serveur");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur lors du Démarrage du Serveur XML-RPC : "+e);
		}
	}


	@Override
	public void subscribe(String userToken, String pseudo) throws XmlRpcException {
		// TODO Auto-generated method stub
		sessions.put(userToken, pseudo);
		System.err.println(pseudo +" viens d'entrer dans le ChatRoom avec le token "+userToken);
	}

}
