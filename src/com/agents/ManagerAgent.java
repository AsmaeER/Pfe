package com.agents;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.*;
import jade.domain.*;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.*;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.awt.Point;
import java.net.ServerSocket;


public class ManagerAgent extends Agent {

    private List<AID> RobotAgents;
    private List<AID> HumanAgents;
    private String[][] env;
    private List<Task> taskList; // Store the received list of tasks
    //private RLModel rlModel;

    protected void setup() {
        // Register the manager agent in the Yellow Pages or perform any necessary setup
        // Inside the setup method of ManagerAgent
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID()); // The AID of the ManagerAgent
        ServiceDescription sd = new ServiceDescription();
        sd.setType("ManagerAgent");
        sd.setName("ManagerService");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        System.out.println("ManagerAgent " + getAID().getLocalName() + " is setting up.");

        // Initialize the list of RobotAgents and HumanAgents
        RobotAgents = new ArrayList<>();
        HumanAgents = new ArrayList<>();
        taskList = new ArrayList<>(); // Initialize the task list
        //rlModel = new RLModel(/* Pass any necessary parameters */);
        CyclicBehaviour loop = new CyclicBehaviour(this) {
            private static final long serialVersionUID = 1L;

            @Override
            public void action() {

              // Receive the incoming message
              ACLMessage aclMsg = receive();

              // Interpret the message
              if (aclMsg != null) {
                System.out.println(myAgent.getLocalName()
                    + "> Received message from: " + aclMsg.getSender());
                System.out.println("Message content: " + aclMsg.getContent());
                // TODO Aufgabe 1
              }
              block(); // Stop the behaviour until next message is received
            }
          };
        // Add a behavior to handle task assignment from TaskGeneratorAgent
        addBehaviour(loop);
        // Start a server to listen for Python messages
        startServer();
    }
    private void startServer() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(9876);
                System.out.println("Java Server listening on port 9876");

                while (true) {
                    Socket socket = serverSocket.accept();
                    Scanner scanner = new Scanner(socket.getInputStream());

                    while (scanner.hasNextLine()) {
                        String message = scanner.nextLine();
                        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
                        aclMessage.setContent(message);
                        aclMessage.addReceiver(getAID());
                        send(aclMessage);
                    }

                    scanner.close();
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /*private class TaskAssignmentBehavior extends CyclicBehaviour {
        public void action() {
            // Receive tasks only from TaskGeneratorAgent
            MessageTemplate msgTemplate = MessageTemplate.MatchSender(new AID("TaskGeneratorAgent", AID.ISLOCALNAME));
            ACLMessage msg = receive(msgTemplate);
            if (msg != null) {
                // Deserialize the list of tasks from the message content
                String content = msg.getContent();
                taskList = deserializeTaskList(content);

                System.out.println("ManagerAgent " + getAID().getLocalName() + " received tasks from TaskGeneratorAgent.");
                // Process the received tasks
                processReceivedTasks();
            } else {
                block();
            }
        }
    }*/

    // Deserialize the list of tasks from a Base64-encoded string
    /*private List<Task> deserializeTaskList(String content) {
        List<Task> tasks = new ArrayList<>();
        try {
            byte[] taskListBytes = Base64.getDecoder().decode(content);
            ByteArrayInputStream bais = new ByteArrayInputStream(taskListBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            tasks = (List<Task>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }*/

   private void processReceivedTasks() {
        // Example: Print the received task descriptions
        System.out.println("Received Tasks:");
        for (Task task : taskList) {
            String description = task.getDescription();
            String[] descriptionParts = description.split(",");
            System.out.println("Task: " + description);
            if (descriptionParts.length >= 2) {
                String numberOfPieces = descriptionParts[0];
                String quantityByKg = descriptionParts[1];

                System.out.println("Number of Pieces: " + numberOfPieces);
                System.out.println("Quantity by Kg: " + quantityByKg);

                // Rest of your processing logic goes here

                // Integrate with RL model to make coordination decisions
                //AgentInfo agentInfo = integrateWithRLModel(task);

                // Calculate path and assign task to the selected agent
                /*if (agentInfo != null) {
                    AID agent = agentInfo.getAgentAID();
                    String path = calculatePath(task.getDescription(), agent, task.getStartLocation(), task.getTargetLocation());
                    sendTaskToAgent(agent, path, env);
                }*/
            }
        }
    }
    // Send the assigned task's path and environment dimensions to the agent (robot or human)
//    private void sendTaskToAgent(AID agent, String path, String[][] env) {
//        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
//        message.addReceiver(agent);
//        message.setContent("Path: " + path + ", Environment: " + envToString(env));
//        send(message);
//
//        System.out.println("Sent task to agent " + agent.getLocalName() + " with path: " + path);
//    }
//    // Convert the environment array to a string for inclusion in the message
//    private String envToString(String[][] env) {
//        StringBuilder sb = new StringBuilder();
//        for (String[] row : env) {
//            for (String cell : row) {
//                sb.append(cell).append(" ");
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//
//    private String calculatePath(String taskDescription, AID robotAgent, Point startLocation, Point targetLocation) {
//        // Implement A* pathfinding using the environment grid with startLocation and targetLocation
//        List<Point> path = aStarPathfinding(env, startLocation, targetLocation);
//
//        // Convert the path to a string representation
//        String pathStr = convertPathToString(path);
//
//        System.out.println("Calculated path for task: " + taskDescription + " assigned to robot: " + robotAgent.getLocalName());
//        return "Path for task: " + taskDescription + " assigned to robot: " + robotAgent.getLocalName() + "\n" + pathStr;
//    }
//
//        private List<Point> aStarPathfinding(String[][] env, Point start, Point target) {
//        // Implement the A* algorithm here
//
//        // Initialize data structures for open and closed sets, and add the start node
//        List<Node> openSet = new ArrayList<>();
//        List<Node> closedSet = new ArrayList<>();
//        openSet.add(new Node(start, 0, calculateHeuristic(start, target)));
//
//        while (!openSet.isEmpty()) {
//            // Find the node with the lowest total cost in the open set
//            Node current = findLowestCostNode(openSet);
//
//            // If the current node is the target, reconstruct and return the path
//            if (current.getPosition().equals(target)) {
//                return reconstructPath(current);
//            }
//
//            // Move the current node from open set to closed set
//            openSet.remove(current);
//            closedSet.add(current);
//
//            // Explore neighbors of the current node
//            for (Point neighbor : getNeighbors(current.getPosition(), env)) {
//                if (isInClosedSet(neighbor, closedSet)) {
//                    continue; // Skip nodes in the closed set
//                }
//
//                double tentativeCost = current.getCost() + calculateDistance(current.getPosition(), neighbor);
//                Node neighborNode = new Node(neighbor, tentativeCost, calculateHeuristic(neighbor, target));
//
//                if (!isInOpenSet(neighborNode, openSet) || tentativeCost < neighborNode.getCost()) {
//                    // Add neighbor to open set if not in open set or if the tentative cost is lower
//                    openSet.add(neighborNode);
//                }
//            }
//        }
//
//        // No path found
//        return null;
//    }
//
//    private Node findLowestCostNode(List<Node> openSet) {
//        Node lowestCostNode = openSet.get(0); // Initialize with the first node in the open set
//        double lowestCost = lowestCostNode.getCost() + lowestCostNode.getHeuristic(); // Calculate the total cost for the first node
//
//        for (Node node : openSet) {
//            double totalCost = node.getCost() + node.getHeuristic();
//            if (totalCost < lowestCost) {
//                lowestCostNode = node; // Update to the node with lower total cost
//                lowestCost = totalCost; // Update the lowest cost
//            }
//        }
//
//        return lowestCostNode;
//    }
//
//
//
//
//    private List<Point> reconstructPath(Node node) {
//        List<Point> path = new ArrayList<>();
//        while (node != null) {
//            path.add(node.getPosition());
//            node = node.getParent(); // Move to the parent node
//        }
//        Collections.reverse(path); // Reverse the path to start from the start node
//        return path;
//    }
//
//
//    private boolean isInClosedSet(Point position, List<Node> closedSet) {
//        for (Node node : closedSet) {
//            if (node.getPosition().equals(position)) {
//                return true; // The position is in the closed set
//            }
//        }
//        return false; // The position is not in the closed set
//    }
//
//
//    private boolean isInOpenSet(Node node, List<Node> openSet) {
//        for (Node openNode : openSet) {
//            if (openNode.equals(node)) {
//                return true; // The node is in the open set
//            }
//        }
//        return false; // The node is not in the open set
//    }
//
//    private double calculateHeuristic(Point from, Point to) {
//        // Calculate the Manhattan distance as the heuristic
//        return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
//    }
//
//
//    private double calculateDistance(Point from, Point to) {
//        // Calculate the Euclidean distance between two points
//        double dx = from.x - to.x;
//        double dy = from.y - to.y;
//        return Math.sqrt(dx * dx + dy * dy);
//    }
//
//
//    private List<Point> getNeighbors(Point position, String[][] env) {
//        List<Point> neighbors = new ArrayList<>();
//        
//        // Define possible movement directions (up, down, left, right)
//        int[] dx = { -1, 1, 0, 0 };
//        int[] dy = { 0, 0, -1, 1 };
//
//        for (int i = 0; i < 4; i++) {
//            int newX = position.x + dx[i];
//            int newY = position.y + dy[i];
//
//            // Check if the new position is within the bounds of the environment
//            if (newX >= 0 && newX < env.length && newY >= 0 && newY < env[0].length) {
//                // Check if the new position is passable (you may have specific criteria)
//                if (env[newX][newY].equals("empty")) {
//                    neighbors.add(new Point(newX, newY));
//                }
//            }
//        }
//
//        return neighbors;
//    }
//    
//	 // Convert a list of Points to a string representation
//	    private String convertPathToString(List<Point> path) {
//	        StringBuilder sb = new StringBuilder();
//	        for (Point point : path) {
//	            sb.append("(").append(point.x).append(",").append(point.y).append(") ");
//	        }
//	        return sb.toString();
//	    }
//
//     // Integrate with the RL model to make coordination decisions using Q-learning
//   /* private AgentInfo integrateWithRLModel(Task task) {
//        // Get the current state representation from the environment and task
//        State currentState = getCurrentState(task);
//
//        // Use the RL model to select an agent based on Q-learning
//        AID selectedAgent = rlModel.selectAgent(currentState);
//
//        if (selectedAgent != null) {
//            return new AgentInfo(selectedAgent);
//        }
//
//        return null;
//    }*/
//    
//    private State getCurrentState(Task task) {
//        // Create a state representation based on task and environment information
//        // This can be a combination of task details, agent status, and environment state
//        // You need to define what information is relevant to your RL model.
//        return new State(/* Provide necessary state information */);
//    }
//    private void startServer() {
//        new Thread(() -> {
//            try {
//                ServerSocket serverSocket = new ServerSocket(9876);
//                System.out.println("Java Server listening on port 9876");
//
//                while (true) {
//                    Socket socket = serverSocket.accept();
//                    Scanner scanner = new Scanner(socket.getInputStream());
//
//                    while (scanner.hasNextLine()) {
//                        String message = scanner.nextLine();
//                        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
//                        aclMessage.setContent(message);
//                        aclMessage.addReceiver(getAID());
//                        send(aclMessage);
//                    }
//
//                    scanner.close();
//                    socket.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
} 

