package tetris2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import java.util.*;

public class Config implements ActionListener {

	public static String Rotate = "空格";//default
	public static String Left = "向左箭头";
	public static String Right = "向右箭头";
	public static String Down = "向下箭头";
	public static String Pause = "P";
	public static String Harddrop = "向上箭头";
	public static String BGFile = getDefaultDir()+"\\BG\\Tetris.mp3";
	
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
		
		
		
		JLabel BGMusic = new JLabel("BG music");
		BGMusic.setBounds(10, 120, 100, 30);
		option.add(BGMusic);
		
		final JTextField BGMusicFile = new JTextField(BGFile);
		BGMusicFile.setBounds(10, 150, 320, 30);
		//BGMusicFile.setText();
		option.add(BGMusicFile);
		
		JButton Browser = new JButton("Open a File...");
		Browser.setBounds(200, 120, 130, 30);
		Browser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				//chooser.addChoosableFileFilter(new FileFilter);
				int ret = chooser.showOpenDialog(option);
				if (ret == JFileChooser.APPROVE_OPTION){
					BGMusicFile.setText( chooser.getSelectedFile().toString() );
					BGFile = BGMusicFile.getText();
		        }
			}
		});
		option.add(Browser);
		
		JButton done = new JButton("SAVE");
		done.setBounds(10, 200, 100, 30);
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
		//Config.BGFile = BGMusicFile.
		try{
			saveConfig();
		}
		catch(Exception e){
			e.printStackTrace();			
		}
	}
	
	public static void loadConfig()throws Exception {
		String filepath = File.separator+"Config.txt";
		File configfile = new File(getDefaultDir(),filepath);
		if(!configfile.exists()){
			configfile.createNewFile();
			saveConfig();
		}
		//do load
		Scanner s = new Scanner(configfile);
		HashMap<String, String> values = new HashMap<String, String>();
		while(s.hasNextLine()){
			String[] entry = s.nextLine().split("::");
			String key = entry[0];
			String value = entry[1];
			values.put(key, value);
		}
		if(values.size() != 7)
			saveConfig();
		
		Config.Left = values.get("left");
		Config.Right = values.get("right");
		Config.Down = values.get("down");
		Config.Harddrop = values.get("harddrop");
		Config.Rotate = values.get("rotate");
		Config.Pause = values.get("pause");
		Config.BGFile = values.get("BG File");
	}
	
	public static void saveConfig() throws Exception {
		String filepath = File.separator+"Config.txt";
		File configfile = new File(getDefaultDir(),filepath);
		if(!configfile.exists()){
			configfile.createNewFile();
		}
		//save to file
		PrintWriter pw = new PrintWriter(configfile);
		pw.println("left::" + Config.Left);
		pw.println("right::" + Config.Right);
		pw.println("down::" + Config.Down);
		pw.println("harddrop::" + Config.Harddrop);
		pw.println("rotate::" + Config.Rotate);
		pw.println("pause::" + Config.Pause);
		pw.println("BG File::"+Config.BGFile);
		pw.close();
	}
	
	public static String getDefaultDir(){
		String OS = System.getProperty("os.name");
		if(OS.contains("Win")){
			String dir = System.getProperty("user.dir");
			//System.out.println(dir);
			return dir;
		}
		if(OS.contains("Mac")){
			return System.getProperty("user.home")+"Library/Application Support";
		}
		return System.getProperty("user.home");
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
