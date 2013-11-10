package tetris2;

import java.io.*;

import javax.sound.sampled.*;

import sun.audio.*;


public class Sound {

	AudioInputStream in;
	AudioFormat decodedFormat;
	AudioInputStream din;
	AudioFormat baseFormat;
	SourceDataLine line;
	
	private boolean loop;
	private BufferedInputStream stream;
	
	public Sound(String filename, Boolean loop){
		this(filename);
		this.loop = loop;
	}
	
	Sound(String filename){
		this.loop = false;
		try{
			InputStream raw = Object.class.getResourceAsStream(filename);
			stream = new BufferedInputStream(raw);
			in = AudioSystem.getAudioInputStream(stream);
			din = null;
			if(in != null){
				baseFormat = in.getFormat();
				decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
												baseFormat.getSampleRate(), 16,
												baseFormat.getChannels(), baseFormat.getChannels()*2,
												baseFormat.getSampleRate(), false);
				
				din = AudioSystem.getAudioInputStream(decodedFormat,in);
				line = getLine(decodedFormat);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void reset(){
		try{
			stream.reset();
			in = AudioSystem.getAudioInputStream(stream);
			din = AudioSystem.getAudioInputStream(in);
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
		try{
			boolean firstTime =true;
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
	
	private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine)AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}
	
//	public void playMainTheme(){
//		try {
//			InputStream in = new FileInputStream(Config.getDefaultDir()+"/BG/Tetris.mp3");
//			InputStream as = new AudioStream(in);
//			AudioPlayer.player.start(as);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return;
//		}
//	}
//	
//	public void playEraserTheme(){
//		InputStream in = null;
//		try {
//			in = new FileInputStream(Config.getDefaultDir()+"/BG/Tetris.mid");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return;
//		}
//		AudioStream as = null;
//		try {
//			as = new AudioStream(in);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		AudioPlayer.player.start(as);
//	}
}
