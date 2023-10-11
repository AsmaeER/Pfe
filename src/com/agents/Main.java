package com.agents;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Main {

    public static void main(String[] args) {
        // Create a JADE container
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost"); // Set the host where the platform runs
        profile.setParameter(Profile.MAIN_PORT, "1099"); // Set the platform's port (default is 1099)

        ContainerController containerController = jade.core.Runtime.instance().createMainContainer(profile);

        try {
            // Deploy ManagerAgent
            AgentController managerAgentController = containerController.createNewAgent(
                "ManagerAgent", // Agent name
                "com.agents.ManagerAgent", // Agent class
                null // Agent constructor arguments, if any
            );
            managerAgentController.start();

            // Deploy TaskGeneratorAgent
            AgentController taskGeneratorAgentController = containerController.createNewAgent(
                "TaskGeneratorAgent", // Agent name
                "com.agents.TaskGeneratorAgent", // Agent class
                null // Agent constructor arguments, if any
            );
            taskGeneratorAgentController.start();

            // Deploy RobotAgent
            AgentController robotAgentController = containerController.createNewAgent(
                "RobotAgent", // Agent name
                "com.agents.RobotAgent", // Agent class
                null // Agent constructor arguments, if any
            );
            robotAgentController.start();

            // Deploy HumanAgent
            AgentController humanAgentController = containerController.createNewAgent(
                "HumanAgent", // Agent name
                "com.agents.HumanAgent", // Agent class
                null // Agent constructor arguments, if any
            );
            humanAgentController.start();

            // Optionally, add delays or other logic to control agent interactions

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}

