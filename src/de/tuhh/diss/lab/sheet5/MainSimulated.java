package de.tuhh.diss.lab.sheet5;

import MazebotSim.MazebotSimulation;
import MazebotSim.Visualization.GuiMazeVisualization;
import lejos.utility.Delay;

public class MainSimulated {

	public static void main(String[] args) {
		
		MazebotSimulation sim = new MazebotSimulation("Mazes/3x4_1.png", 1.4,  1.05);
		GuiMazeVisualization gui = new GuiMazeVisualization(1.5, sim.getStateAccessor());

		sim.scaleSpeed(1);
//		sim.setRobotPosition(0.35, 0.35, 90);
		sim.setRobotPosition(0.75, 0.75, 90);

		sim.startSimulation();
		gui.startVisualization();

		// Here goes your Code!
		MazeSolverTest.main(new String[0]);
		
		Delay.msDelay(100);
		sim.stopSimulation();
	}

}
