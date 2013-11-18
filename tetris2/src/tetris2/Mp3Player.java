package tetris2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import javazoom.jl.player.*;

public class Mp3Player {

	private boolean loop;
	private int times;
	
	private String fileType;
	private String fileName;
	private Player mp3Player;
	
	Mp3Player(String filename, int times){
		
	}
	
	Mp3Player(String filename, Boolean loop){
		
	}
	
	Mp3Player(String filename){
		fileName = filename;
	}
	
	private void play(){
		boolean firsttime = true;
		while(firsttime || loop || times>0){
			firsttime = false;
			if(fileType.equalsIgnoreCase("mp3")){
				try{
					File f = new File(fileName);
					FileInputStream f_is = new FileInputStream(f);
					BufferedInputStream s = new BufferedInputStream(f_is);
					mp3Player = new Player(s);
					mp3Player.play();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else {
				throw new Error("nonsupport file");
			}
		}
	}
	
	private void close(){
		mp3Player.close();
	}
	
	private void reset(){
		this.close();
		
	}
	
	private String getAudioFileType(String filename){
		String extension = null;
		try{
			//init file extension
			File fi = new File(filename);
			extension = filename.substring(filename.lastIndexOf('.')+1);
			
			//detect file type
			String fi_type = Files.probeContentType(fi.toPath());
			System.out.println(fi_type);
			
			//get system supported audio file type..//wav/aiff/au
			AudioFileFormat.Type[] alltype = AudioSystem.getAudioFileTypes();
			
			//adjust file extension
			if(fi_type.equalsIgnoreCase("audio/mid"))
				extension = "mid";
			else if(fi_type.equalsIgnoreCase("audio/mpeg"))
				extension = "mp3";
			else if(fi_type.equalsIgnoreCase("audio/wav")||fi_type.equalsIgnoreCase("audio/au")||fi_type.equalsIgnoreCase("audio/aiff")){//wav/aiff/au
				AudioFileFormat filetype = AudioSystem.getAudioFileFormat(fi);
				for(AudioFileFormat.Type i:alltype){
					if(filetype.getType() == i){
						extension = filetype.getType().toString();
					}
					//System.out.println(i.toString());
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return extension;
	}
}
