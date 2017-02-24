package com.tobin.master2;

import org.apache.xmlrpc.XmlRpcClientRequest;
import org.apache.xmlrpc.XmlRpcException;

public interface ChatUser extends XmlRpcClientRequest {

	public void displayMessage(String message) throws XmlRpcException;
}
