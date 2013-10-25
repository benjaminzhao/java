package tetris2;

import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;


public class KeyGetter {

	public static HashMap<String, Integer> keys;
	public static ArrayList<String> keyNames;
	
	public static void loadKeys(){
		keys = new HashMap<String, Integer>();
		keyNames = new ArrayList<String>();
		
		Field[] fields = KeyEvent.class.getFields();
		for(Field f:fields)	{
			if(Modifier.isStatic(f.getModifiers())){
				if(f.getName().startsWith("VK")){
					try{
						int num = f.getInt(null);
						String name = KeyEvent.getKeyText(num);
						keys.put(name, num);
						keyNames.add(name);
						System.out.println(name);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
}
