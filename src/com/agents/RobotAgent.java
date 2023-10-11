package com.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.awt.Point;

// Classe Agent Robot
public class RobotAgent extends Agent {
	
    private String idRobot;
    private Point positionActuelle;
    private String etatRobot;
    private Point objectif;
    
    
	public String getIdRobot() {
		return idRobot;
	}
	public void setIdRobot(String idRobot) {
		this.idRobot = idRobot;
	}
	public Point getPositionActuelle() {
		return positionActuelle;
	}
	public void setPositionActuelle(Point positionActuelle) {
		this.positionActuelle = positionActuelle;
	}
	public String getEtatRobot() {
		return etatRobot;
	}
	public void setEtatRobot(String etatRobot) {
		this.etatRobot = etatRobot;
	}
	public Point getObjectif() {
		return objectif;
	}
	public void setObjectif(Point objectif) {
		this.objectif = objectif;
	}

    // Méthodes
	


    
    
    
  
}