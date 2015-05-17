package network.client;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

import event.EventManager;
import event.InputEvent;

public class ClientListener implements MessageListener<Client> {
	@Override
	public void messageReceived(Client source, Message message) {
		if (message instanceof InputEvent) {
			// do something with the message
			InputEvent helloMessage = (InputEvent) message;
			System.out.println("Client #" + source.getId() + " received: '" + helloMessage.getActionCommand() + "'");
			EventManager.post(helloMessage);
		} // else...
	}
}