package com.tobin.master2;

import java.awt.*;
import java.awt.event.*;

import java.util.Calendar;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Vector;

import javax.swing.*;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;


public class ChatUserImpl extends TimerTask implements ChatUser{
    private String title = "Logiciel de discussion en ligne";
    private String pseudo = null;

    private JFrame window = new JFrame(this.title);
    private JTextArea txtOutput = new JTextArea();
    private JTextField txtMessage = new JTextField();
    private JButton btnSend = new JButton("Envoyer");

    private XmlRpcClient myServe;
    
   String token;
    
    public ChatUserImpl() {
        this.createIHM();
        this.requestPseudo();
        try {
        	token = Integer.toString(Calendar.getInstance().get(Calendar.MILLISECOND));
       	  myServe = new XmlRpcClient("http://localhost/ChatRoom");
       	  Vector<String> params = new Vector<String>();
       	  
       	  params.addElement(token);
       	  params.addElement(pseudo);
       	
       	new java.util.Timer().schedule( 
       	        new java.util.TimerTask() {
       	            @Override
       	            public void run() {
       	                // your code here
       	            }
       	        }, 
       	        5000 
       	);
       	
       	myServe.execute("ChatRoom.subscribe", params);
       	  
       	
       /*	new java.util.Timer().schedule( 
       	        new java.util.TimerTask() {
       	            @Override
       	            public void run() {
       	                // your code here
       	            	try {
							myServe.execute("ChatRoom.postMessage", params);
							System.err.println("posting");
						} catch (XmlRpcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
       	            }
       	        }, 
       	        5000 
       	);*/
       	
		} catch (Exception e) {
			// TODO: handle exception
			//System.err.println("Unable to Subscribe on the Chat Room : "+e.getMessage());
			//System.err.println(e.getStackTrace());
		}
    }

    public void createIHM() {
        // Assemblage des composants
        JPanel panel = (JPanel)this.window.getContentPane();
	JScrollPane sclPane = new JScrollPane(txtOutput);
	panel.add(sclPane, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(this.txtMessage, BorderLayout.CENTER);
        southPanel.add(this.btnSend, BorderLayout.EAST);
        panel.add(southPanel, BorderLayout.SOUTH);

        // Gestion des évènements
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                window_windowClosing(e);
            }
        });
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSend_actionPerformed(e);
            }
        });
	txtMessage.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent event) {
		if (event.getKeyChar() == '\n')
		    btnSend_actionPerformed(null);
	    }
	});

        // Initialisation des attributs
        this.txtOutput.setBackground(new Color(220,220,220));
	this.txtOutput.setEditable(false);
        this.window.setSize(500,400);
        this.window.setVisible(true);
        this.txtMessage.requestFocus();
    }

    public void requestPseudo() {
        this.pseudo = JOptionPane.showInputDialog(
                this.window, "Entrez votre pseudo : ",
                this.title,  JOptionPane.OK_OPTION
        );
        if (this.pseudo == null) System.exit(0);
    }

    public void window_windowClosing(WindowEvent e) {
	System.exit(-1);
    }

    public void btnSend_actionPerformed(ActionEvent e) {
    	
 	//this.txtOutput.append(this.txtMessage.getText() + "\n");
 	
 	Vector<String> params = new Vector<String>();
	  
 	params.addElement(pseudo);  
 	params.addElement(this.txtMessage.getText() + "\n");
	  
 	
 	try {
 			Object result;
		
			result = myServe.execute("ChatRoom.postMessage", params);
			String messages  = ((String) result);
			System.out.println(messages);
			this.txtOutput.append(messages);
		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	}
 	
	this.txtMessage.setText("");
        this.txtMessage.requestFocus();
        
    }

    public static void main(String[] args) {
    	TimerTask tache = new ChatUserImpl();
        Timer timer = new Timer();
        timer.schedule(tache, 100,1000);
        
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
	public int getParameterCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void displayMessage(String message) throws XmlRpcException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		 Vector<String> params = new Vector<String>();
      	  
      	  params.addElement(token);
      	  //params.addElement(pseudo);				
		
			try {
				
					Object result = myServe.execute("ChatRoom.getMessages", params);
					String messages  = ((String) result);
					System.out.println(messages);
					this.txtOutput.setText("");
					this.txtOutput.append(messages);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	

	
}

