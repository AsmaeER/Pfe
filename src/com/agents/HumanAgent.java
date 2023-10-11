package com.agents;

import java.awt.Point;

import jade.core.Agent;

public class HumanAgent extends Agent{
	
	 private String idHuman;
	    private Point positionActuelle;
	    private String etatHuman;
	    private Point objectif;
	    
	    
		public String getIdHuman() {
			return idHuman;
		}
		public void setIdHuman(String idHuman) {
			this.idHuman = idHuman;
		}
		public Point getPositionActuelle() {
			return positionActuelle;
		}
		public void setPositionActuelle(Point positionActuelle) {
			this.positionActuelle = positionActuelle;
		}
		public String getEtatHuman() {
			return etatHuman;
		}
		public void setEtatHuman(String etatHuman) {
			this.etatHuman = etatHuman;
		}
		public Point getObjectif() {
			return objectif;
		}
		public void setObjectif(Point objectif) {
			this.objectif = objectif;
		}
	    

}
