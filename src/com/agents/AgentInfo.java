package com.agents;

import jade.core.AID;

public class AgentInfo {
    private AID agentAID;

    public AgentInfo(AID agentAID) {
        this.agentAID = agentAID;
    }

    public AID getAgentAID() {
        return agentAID;
    }
}
