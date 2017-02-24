package com.tobin.master2;

import org.apache.xmlrpc.*;


public interface ChatRoom extends XmlRpcServerRequest{

	public void subscribe(ChatUser user,String pseudo) throws XmlRpcException;
	public void subscribe(String  userToken,String pseudo) throws XmlRpcException;
	public void postMessage(String pseudo,String message) throws XmlRpcException;
	public void unsubscribe(String pseudo) throws XmlRpcException;
}
