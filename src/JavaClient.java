
import java.util.Vector;

import org.apache.xmlrpc.*;
public class JavaClient {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			XmlRpcClient myServe = new XmlRpcClient("http://localhost/RPC2");
			Vector<Integer> params = new Vector<Integer>();
			params.addElement(new Integer(99));
			params.addElement(new Integer(91));
			
			Object result = myServe.execute("sample.sum",params);
			int sum = ((Integer) result).intValue();
			System.out.println("La somme est de : "+sum);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur lors du Démarrage du Client .... OK");
		}
	}

}
