import javax.sound.sampled.*;
import javax.sound.sampled.Control;
import java.io.File;
public class AudioPlayer
{
    Mixer mixer;
    Clip clip;
    Port line;
    Thread thread;
    TargetDataLine targetLine;
     
    int gain = 0;
  
    /**
     *  Plays an audio file passed to it by another method.
     *  
     *  @param file - the audio file to be played.
     */
    public void play(File file) throws Exception{
        AudioInputStream ais;
        Clip clip;
                
        ais = AudioSystem.getAudioInputStream(file); 
        
        clip = AudioSystem.getClip();
  
        clip.open(ais);
        
        // Retreives the gain control so the Gain Slider can change the 
        // gain of the input line
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(gain);
        
        
        clip.start();
    }
    
    /**
     *  Begins the recoriding of an audio file.
     *  
     *  @param file - the audio file to be recorded to.
     */
    public void record(File file){
        try{            
            //Format the audio as a 16 bit .wav file
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);  
        
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            if(!AudioSystem.isLineSupported(info)){
                System.out.println("Not Supported");
            }
            
            targetLine = (TargetDataLine)AudioSystem.getLine(info);
            targetLine.open();
            
            targetLine.start();          
            
            //creates a thread to write the audio to the file
            thread = new Thread(){
                @Override 
                public void run(){
                    AudioInputStream audioStream = new AudioInputStream(targetLine);
                   try{
                        AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, file);
                   }
                   catch (Exception e){
                        e.printStackTrace();
                   }
                    
                    }               
            };
        
            //begin recording
        thread.start();   
        
        }
        catch(LineUnavailableException e){
            e.printStackTrace();
        }
    }
    
    /**
     *  Passes the file from the TinWhistleVST class to the record method
     *  for ease of access from the TinWhistleVST class.
     *  
     *  @param file - the audio file to be recorded to.
     */
    public void startRecord(File file){
        record(file);
    }
    
    /**
     * Method to close the input line in the record method thereby ending the
     * recording and saving the audio file.
     */
    public void endRecord(){
        targetLine.stop();
        targetLine.close();
    }
    
    /**
     *  Setter for the gain level of the audio player.
     *  
     *  @param value - the value for the audio player's gain.
     */
    public void setGainLevel(int value){
        gain = value;
    }
    
    /**
     *  Getter for the gain level of the audio player.
     *  
     *  @return the audio player's gain level.
     */
    public int getGainLevel(){
        return gain;
    }
}
