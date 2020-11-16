package de.tuhh.diss.lab.sheet2;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD; 
import lejos.utility.Delay;

public class Task2 {
	
	public static void menu(int volume, int frequency, int duration) {
		// Display the menu
		
		LCD.drawString("Menu",  2, 0);
		LCD.drawString("Volume",  2, 1);
		LCD.drawString("Frequency",  2, 2);
		LCD.drawString("Duration",  2, 3);
		LCD.drawString("Play",  2, 4);
		LCD.drawString(" " + volume, LCD.DISPLAY_CHAR_WIDTH - 3, 1);
		LCD.drawString(" " + frequency, LCD.DISPLAY_CHAR_WIDTH - 6, 2);
		LCD.drawString(" " + duration,  LCD.DISPLAY_CHAR_WIDTH - 6, 3);	
		
	}

	public static void main(String[] args) {
		
		int volume = 0;
		int frequency = 0; 
		int duration = 0; 
		int y = 1;
		
		LCD.drawString(">", 0, y);
		menu(volume, frequency, duration);
		
		while (Button.ESCAPE.isUp()){
			
			int ButtonID = Button.waitForAnyPress();
			
			if (ButtonID == 1) {  // Button.UP.isDown()
				y--;
				LCD.clear();
				LCD.drawString(">", 0, y);
				menu(volume, frequency, duration);
				
				if(y <= 1) {
					y = 5;
				}	
			}
			else if (ButtonID == 4) { // Button.DOWN.isDown()
				y++;
				LCD.clear();
				LCD.drawString(">", 0, y);
				menu(volume, frequency, duration);
				
				if(y >= 4) {
					y = 0;
				}
			}
			
			if(Button.ENTER.isDown()) {
				
				if(ButtonID == 8) { // Button.RIGHT.isDown()
					
					if(y==1) {
						volume += 10;
						LCD.clear();
						menu(volume, frequency, duration);
					}
					else if(y==2) {
						frequency += 100;
						LCD.clear();
						menu(volume, frequency, duration);
					}
					else if (y==3) {
						duration += 500;
						LCD.clear();
						menu(volume, frequency, duration);
					} 
					else if (ButtonID == 16) { // Button.LEFT.isDown()
					
						if(y==1) {
							volume -= 10;
							LCD.clear();
							menu(volume, frequency, duration);
						}
						else if(y==2) {
							frequency -= 100;
							LCD.clear();
							menu(volume, frequency, duration);
						}
						else if (y==3) {
							duration -= 500;
							LCD.clear();
							menu(volume, frequency, duration);
						}
					}
				}
			}
		}
	}
}
