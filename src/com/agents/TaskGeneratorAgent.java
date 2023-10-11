package com.agents;

import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.awt.Point;
import java.io.IOException;
import java.util.Base64;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream; 

import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.awt.Point;

public class TaskGeneratorAgent extends Agent {

	protected void setup() {
	    // Create a list of tasks
	    List<Task> tasks = new ArrayList<>();

	    // Add tasks to the list
	    tasks.add(new Task("3,56", new Point(1, 2), new Point(3, 4), Task.TaskType.PICK_ORDER));
	    tasks.add(new Task("1,6", new Point(3, 4), new Point(5, 6), Task.TaskType.STORE_ARTICLE));
	    tasks.add(new Task("47,1", new Point(5, 6), new Point(7, 8), Task.TaskType.PICK_ORDER));
	    // Add more tasks as needed

	    // Send the list of tasks to the manager agent
	    sendTaskListToManager(tasks);
	}


    private void sendTaskListToManager(List<Task> tasks) {
        // Create an ACL message to send the list of tasks
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
        AID managerAID = new AID("ManagerAgent", AID.ISLOCALNAME); // Replace with the actual name of the manager agent
        message.addReceiver(managerAID);

        // Serialize the list of tasks and send it as the message content
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(tasks);
            oos.close();
            byte[] taskListBytes = baos.toByteArray();
            message.setContent(Base64.getEncoder().encodeToString(taskListBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Send the message to the manager agent
        send(message);
    }
}

