package tetris2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Config {

	public static String Rotate = "空格", Left = "向左箭头", Right = "向右箭头", Down = "向下箭头", Pause = "P", Harddrop = "向上箭头";
	
	private static ArrayList<Choice> choices;
	
	public static void openConfig(JFrame frame){
		choices = new ArrayList<Choice>();
		final JFrame option = new JFrame("Option");
		option.setSize(350,300);
		option.setResizable(false);
		option.setLocationRelativeTo(frame);
		option.setLayout(null);
		
		//Choice left = addChoice("left", option, 10, 10);
		//left.select(Config.Left);
		//Choice right = addChoice("right", option, 120, 10);
		//right.select(Config.Right);
		//Choice down = addChoice("down", option, 230, 10);
		//down.select(Config.Down);
		//Choice harddrop = addChoice("hard drop", option, 10, 60);
		//harddrop.select(Config.Harddrop);
		//Choice rotate = addChoice("rotate", option, 120, 60);
		//rotate.select(Config.Rotate);
		//Choice pause = addChoice("pause", option, 230, 60);
		//pause.select(Config.Pause);
		addChoice("left", option, 10, 10);
		choices.get(0).select(Config.Left);
		addChoice("right", option, 120, 10);
		choices.get(1).select(Config.Right);
		addChoice("down", option, 230, 10);
		choices.get(2).select(Config.Down);
		addChoice("hard drop", option, 10, 60);
		choices.get(3).select(Config.Harddrop);
		addChoice("rotate", option, 120, 60);
		choices.get(4).select(Config.Rotate);
		addChoice("pause", option, 230, 60);
		choices.get(5).select(Config.Pause);
		
		JButton done = new JButton("SAVE");
		done.setBounds(10, 110, 100, 30);
		done.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Config.saveChanges();
				option.dispose();
			}
		});
		option.add(done);
		option.setVisible(true);
		
	}
	
	public static void saveChanges(){
		Config.Left = choices.get(0).getSelectedItem();
		Config.Right = choices.get(1).getSelectedItem();
		Config.Down = choices.get(2).getSelectedItem();
		Config.Harddrop = choices.get(3).getSelectedItem();
		Config.Rotate = choices.get(4).getSelectedItem();
		Config.Pause = choices.get(5).getSelectedItem();
	}
	
	public static Choice addChoice(String name, JFrame frame, int x, int y){
		JLabel label = new JLabel(name);
		label.setBounds(x, y, 100, 20);
		Choice key = new Choice();
		for(String s:getKeyNames()){
			key.add(s);
		}
		key.setBounds(x,y+20,100,20);
		choices.add(key);
		
		frame.add(key);
		frame.add(label);
		frame.setVisible(true);
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
