package de.tuhh.diss.lab.sheet2;

import MazebotSim.MazebotSimulation;
import MazebotSim.Visualization.GuiMazeVisualization;

public class SimulatedMain {

	public static void main(String[] args) {
		
		MazebotSimulation sim = new MazebotSimulation("Mazes/maze_1_3by4.png", 1.5,  1.13);
		GuiMazeVisualization gui = new GuiMazeVisualization(1.5, sim.getStateAccessor());
		sim.scaleSpeed(1);
		sim.setRobotPosition(0.525, 0.175, 90);
		
		sim.startSimulation();
		gui.startVisualization();
		
		Task2.main(new String[0]);
				
		sim.stopSimulation();


	}

}
