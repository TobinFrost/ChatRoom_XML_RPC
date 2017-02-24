import org.apache.xmlrpc.*;
public class JavaServeur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Tentative de Démarrage du Serveur XML-RPC ....");
			WebServer server = new WebServer(80);
			JavaServeur target = new JavaServeur();
			server.addHandler("sample", target);
			server.start();
			System.out.println("Démarrage du Serveur XML-RPC .... OK");
			System.out.println("Acceptation de Requetes .... OK");
			System.out.println("Hint : Arrêtez le programme pour Eteindre le serveur");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur lors du Démarrage du Serveur XML-RPC : "+e);
		}
	}
	
	public Integer sum(int x,int y){
		return new Integer(x+y);
	}
	

}
