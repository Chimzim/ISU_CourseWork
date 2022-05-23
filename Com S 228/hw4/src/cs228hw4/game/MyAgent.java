package cs228hw4.game;

import java.awt.Color;
import java.io.File;


import cs228hw4.game.GalaxyState;
import cs228hw4.game.SystemState;


public class MyAgent implements Agent{
	private String lastName, firstName, ID, username;
	private Color myColor = null, Ocolor = null;
	private AgentAction[] myActions;
	private SystemState options;
	
	public MyAgent() {
		firstName = "Chimzim";
		lastName = "Ogbondah";
		ID = "273362113";
		username = "cogbondah";
	}
	 /**
	    * This method must return your first name.
	    * 
	    * @return firstName returns first name of student
	    */
	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}
	 /**
	    * This method must return your last name. 
	    * 
	    * @return  lastName last name of student
	    */
	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}
	 /**
	    * This method must return your student ID number 
	    * 
	    * @return ID number of student
	    */
	@Override
	public String getStuID() {
		// TODO Auto-generated method stub
		return ID;
	}
	 /**
	    * This method must returns the username of choice. 
	    * 
	    * @return  username given username by student
	    */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	 /**
	    * This method must return your Agent name 
	    * 
	    * @return  username given username by student
	    */
	@Override
	public String getAgentName() {
		// TODO Auto-generated method stub
		return username;
	}
	 /**
	    * Function to retrieve your image file (assuming it is present at the base
	    * level of your project).
	    * 
	    * @return the agent's image file (if you wish to make a 128x128 icon for your
	    *         agent) or null (to use the default image)
	    */
	@Override
	public File getAgentImage() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	    * Whether your agent is a candidate for the tournament. Return true if this
	    * agent is submitted for the tournament, false otherwise.
	    * 
	    * @return true iff you wish to enter this agent in the tournament
	    */
	@Override
	public boolean inTournament() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
    * Function used to generate command turns from your agent.  Use the passed
    * GalaxyState to decide 3 actions for your agent.  This version
    * will only be used if this agent is run during a tournament.
    * 
    * @param g a scan of the current state of the system
    * @param energyLevel energy level corresponding to the amount of energy the agent has
    * @return an array of 3 AgentActions that indicates the agent's next 3
    *         actions if in a tournament situation. If fewer than 3 actions are
    *         specified or there are null actions, these agent moves are lost.
    */
	@Override
	public AgentAction[] getCommandTurnTournament(GalaxyState g, int energyLevel) {
		// TODO Auto-generated method stub
		return getCommandTurnGrading(g,energyLevel);
	}
	/**
    * Function used to generate command turns from your agent.  Use the passed
    * GalaxyState to decide 3 actions for your agent.  This version
    * will only be used if this agent is run during a tournament.
    * 
    * @param g a scan of the current state of the system
    * @param energyLevel energy level corresponding to the amount of energy the agent has
    * @return an array of 3 AgentActions that indicates the agent's next 3
    *         actions if in a tournament situation. If fewer than 3 actions are
    *         specified or there are null actions, these agent moves are lost.
    */
	@Override
	public AgentAction[] getCommandTurnGrading(GalaxyState g, int energyLevel) {
		// TODO Auto-generated method stub
		myActions = new AgentAction[3];
		int currentEnergy = energyLevel;
		options = null;
		String move = "";
		int key = 0;
		SystemState[] neighbors = options.getNeighbors();
		int[] travel = options.getTravelCost();
		int least = travel[0];
		
		move = neighbors[key].getName();
		for(int i = 0; i < 3; i++) {
			if(options.getOwner() != myColor) {
				if(options.getCostToCapture() < currentEnergy) {
					myActions[i] = new Agent.Capture(options.getCostToCapture());
				}
				else if(i <= 1 && options.getCostToCapture() < currentEnergy) {
					myActions[i] = new Agent.Capture(options.getCostToCapture());
					i++;
					myActions[i] = new Agent.ContinueCapture();
				}
			}
			else if(options.getOwner().equals(myColor)) {
				myActions[i] = new Agent.Fortify(currentEnergy/2);	
				}
			else if(currentEnergy < 2) {
				for(int j = 1; j < travel.length-1; j++) {
					if(least > travel[j]) {
						least = travel[j];
						key = j;
					}
				}
				move = neighbors[key].getName();
				myActions[i] = new Agent.Move(move);
			}
		}
		return myActions;
	}
	/**
	    * Allow the simulation to set the color of your agent. It's up to you to
	    * save this information somehow.  This method is called only once, at the
	    * start of each simulation.
	    * 
	    * @param c color your agent will appear as
	    */
	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		myColor = c;
	}
	/**
	    * Allow the simulation to set the color of opposing agent. It's up to you to
	    * save this information somehow.  This method is called only once, at the
	    * start of each simulation.
	    * 
	    * @param c color your agent will appear as
	    */
	@Override
	public void setOpponentColor(Color c) {
		// TODO Auto-generated method stub
		Ocolor = c;
	}

}
