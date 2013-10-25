package tetris2;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Config {

	public static String Rotate = "UP", Left = "LEFT", Right = "RIGHT", Down = "DOWN", Pause = "P";
	private static ArrayList<Choice> choices;
	
	public static void openConfig(JFrame frame){
		choices = new ArrayList<Choice>();
		JFrame option = new JFrame("Option");
		option.setSize(400,300);
		option.setResizable(false);
		option.setLocationRelativeTo(frame);
		option.setLayout(null);
		
		Choice left = addChoice("left", option, 10, 10);
		left.select(Config.Left);
		
		option.setVisible(true);
		
	}
	
	public static Choice addChoice(String name, JFrame option, int x, int y){
		JLabel label = new JLabel(name);
		label.setBounds(x, y, 100, 20);
		Choice key = new Choice();
		for(String s:getKeyNames()){
			key.add(s);
		}
		key.setBounds(x,y+20,100,20);
		choices.add(key);
		
		option.add(key);
		option.add(label);
		option.setVisible(true);
		return key;
	}
	
	public static ArrayList<String> getKeyNames(){
		ArrayList<String> result = new ArrayList<String>();
		for(String s:KeyGetter.keyNames){
			//if(s.equalsIgnoreCase("F24")){
				result.add(s);
			//}
		}
		return result;
	}
	
}
