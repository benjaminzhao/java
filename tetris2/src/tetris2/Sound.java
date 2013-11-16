package tetris2;

import java.io.*;
import java.nio.file.*;

import javax.sound.sampled.*;

import javazoom.jl.player.*;


public class Sound {

	AudioInputStream in;
	AudioFormat decodedFormat;
	AudioInputStream din;
	AudioFormat baseFormat;
	SourceDataLine line;
	
	private boolean loop;
	private int times;
	private BufferedInputStream stream;
	private String fileType;
	private String fileName;
	private Player mp3Player;
	
	Sound(String filename, int times){
		this(filename);
		if(times == 0){
			throw new Error("invalide number");
		}
		else{
			this.times = times;
			this.loop = false;
		}
	}
	
	Sound(String filename, Boolean loop){
		this(filename);
		this.loop = loop;
		if (loop == false)
			this.times = 1;
	}
	
	Sound(String filename){
		this.loop = false;
		this.times = 0;
		
		fileType = getAudioFileType(filename);
		System.out.println(fileType);
		
		if( fileType.equalsIgnoreCase("mp3")){//if mp3
			fileName = filename;
		}
		else if(fileType.equalsIgnoreCase("wave")||fileType.equalsIgnoreCase("midi")){//if wav/aiff/au/mid
			try{
				File fi = new File(filename);			
				FileInputStream fis = new FileInputStream(fi);
				stream = new BufferedInputStream(fis);
				
//				System.out.println(filename);
//				InputStream raw = Object.class.getResourceAsStream(filename);
//				if(raw ==null)
//					System.out.println("AAAAAAAAAAAAAA");
//				stream = new BufferedInputStream(raw);
				
				in = AudioSystem.getAudioInputStream(stream);

				
				
				din = null;
				if(in != null){
					baseFormat = in.getFormat();
					decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
													baseFormat.getSampleRate(), 
													16,
													baseFormat.getChannels(), 
													baseFormat.getChannels()*2,
													baseFormat.getSampleRate(), 
													false);
					
					din = AudioSystem.getAudioInputStream(decodedFormat,in);
					line = getLine(decodedFormat);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			throw new Error("unsupported format");
		}
		
	}
	
	private String getAudioFileType(String filename){
		String extension = null;
		
		try{
			File fi = new File(filename);
			extension = filename.substring(filename.lastIndexOf('.')+1);
			
			String fi_type = Files.probeContentType(fi.toPath());
			System.out.println(fi_type);
			
			if(fi_type.equalsIgnoreCase("audio/mid"))
				extension = "midi";
			else if(fi_type.equalsIgnoreCase("audio/mpeg"))
				extension = "mp3";
			else if(fi_type.equalsIgnoreCase("audio/wav")){
				//wav/aiff/au
				AudioFileFormat filetype = AudioSystem.getAudioFileFormat(fi);
				
				AudioFileFormat.Type[] alltype = AudioSystem.getAudioFileTypes();
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
	
	public void reset(){
		try{
			stream.reset();
			in = AudioSystem.getAudioInputStream(stream);
			din = AudioSystem.getAudioInputStream(decodedFormat,in);
			line = getLine(decodedFormat);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void close(){
		try{
			line.close();
			din.close();
			in.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		boolean firstTime =true;
		if(fileType == "mp3"){
			try{
				while(firstTime || loop){
					firstTime = false;
					File fi = new File(fileName);			
					FileInputStream fis = new FileInputStream(fi);
					stream = new BufferedInputStream(fis);
					mp3Player = new Player(stream);
					mp3Player.play();
					System.out.println("player again");
					//stream.reset();
					//mp3Player.close();
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			try{
				
				while(firstTime || loop){
					firstTime = false;
					byte[] data = new byte[4096];
					if(line != null){
						line.start();
						int nBytesRead = 0;
						while(nBytesRead != -1){
							nBytesRead = din.read(data,0,data.length);
							if(nBytesRead != -1)
								line.write(data, 0, nBytesRead);
						}
						line.drain();
						line.stop();
						line.close();
						reset();
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine)AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}
	
}
