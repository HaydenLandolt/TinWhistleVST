import java.io.File;
import java.util.HashMap;
import javafx.scene.paint.Color;
public class TinWhistle
{
    HashMap<Integer, File> midiMap;
    AudioPlayer audio = new AudioPlayer();

    /**
     *  Constructor for the WinWhistle which automatically maps the 
     *  whistle to D.
     */
    public TinWhistle(){
        mapWhistle(1);
    }
    
    /**
     *  Changes the key of the whistle 
     *  Keys:
     *  1 D Major
     *  2 G Major
     *  3 F Major
     *  4 C Major
     *  
     *  @param key - the key that the will will be mapped to.
     *  
     */
    public void mapWhistle(int key){
        switch(key){
            case 1:
            mapDWhistle();
            break;
            case 2:
            mapGWhistle();
            break;
            case 3:
            mapFWhistle();
            break;
            case 4:
            mapCWhistle();
            break;
        }       
    }
    
    /**
     *  Maps the Tin Whistle to D Major.
     */
    public void mapDWhistle(){
        midiMap = new HashMap<Integer, File>();
        midiMap.put(74, new File("./Samples/D5 Tin Whistle.wav"));
        midiMap.put(76, new File("./Samples/E5 Tin Whistle.wav"));
        midiMap.put(78, new File("./Samples/F#5 Tin Whistle.wav"));
        midiMap.put(79, new File("./Samples/G5 Tin Whistle.wav"));
        midiMap.put(81, new File("./Samples/A5 Tin Whistle.wav"));
        midiMap.put(83, new File("./Samples/B5 Tin Whistle.wav"));
        midiMap.put(84, new File("./Samples/C6 Tin Whistle.wav"));
        midiMap.put(85, new File("./Samples/C#6 Tin Whistle.wav"));
        midiMap.put(86, new File("./Samples/D6 Tin Whistle.wav"));
        midiMap.put(88, new File("./Samples/E6 Tin Whistle.wav"));
        midiMap.put(90, new File("./Samples/F#6 Tin Whistle.wav"));
        midiMap.put(91, new File("./Samples/G6 Tin Whistle.wav"));
        midiMap.put(93, new File("./Samples/A6 Tin Whistle.wav"));
        midiMap.put(95, new File("./Samples/B6 Tin Whistle.wav"));
    }
    
    /**
     *  Maps the Tin Whistle to G Major.
     */
    public void mapGWhistle(){
        midiMap = new HashMap<Integer, File>();
        midiMap.put(74, new File("./Samples/G5 Tin Whistle.wav"));
        midiMap.put(76, new File("./Samples/A5 Tin Whistle.wav"));
        midiMap.put(78, new File("./Samples/B5 Tin Whistle.wav"));
        midiMap.put(79, new File("./Samples/C6 Tin Whistle.wav"));
        midiMap.put(81, new File("./Samples/D6 Tin Whistle.wav"));
        midiMap.put(83, new File("./Samples/E6 Tin Whistle.wav"));
        midiMap.put(84, new File("./Samples/F6 Tin Whistle.wav"));
        midiMap.put(85, new File("./Samples/F#6 Tin Whistle.wav"));
        midiMap.put(86, new File("./Samples/G6 Tin Whistle.wav"));
        midiMap.put(88, new File("./Samples/A6 Tin Whistle.wav"));
        midiMap.put(90, new File("./Samples/B6 Tin Whistle.wav"));
        midiMap.put(91, new File("./Samples/C7 Tin Whistle.wav"));
        midiMap.put(93, new File("./Samples/D7 Tin Whistle.wav"));
        midiMap.put(95, new File("./Samples/E7 Tin Whistle.wav"));
    }
    
    /**
     *  Maps the Tin Whistle to F Major.
     */
    public void mapFWhistle(){
        midiMap = new HashMap<Integer, File>();
        midiMap.put(74, new File("./Samples/F5 Tin Whistle.wav"));
        midiMap.put(76, new File("./Samples/G5 Tin Whistle.wav"));
        midiMap.put(78, new File("./Samples/A5 Tin Whistle.wav"));
        midiMap.put(79, new File("./Samples/A#5 Tin Whistle.wav"));
        midiMap.put(81, new File("./Samples/C6 Tin Whistle.wav"));
        midiMap.put(83, new File("./Samples/D6 Tin Whistle.wav"));
        midiMap.put(84, new File("./Samples/D#6 Tin Whistle.wav"));
        midiMap.put(85, new File("./Samples/E6 Tin Whistle.wav"));
        midiMap.put(86, new File("./Samples/F6 Tin Whistle.wav"));
        midiMap.put(88, new File("./Samples/G6 Tin Whistle.wav"));
        midiMap.put(90, new File("./Samples/A6 Tin Whistle.wav"));
        midiMap.put(91, new File("./Samples/A#6 Tin Whistle.wav"));
        midiMap.put(93, new File("./Samples/C7 Tin Whistle.wav"));
        midiMap.put(95, new File("./Samples/D7 Tin Whistle.wav"));
    }
    
    /**
     *  Maps the Tin Whistle to C Major.
     */
    public void mapCWhistle(){
        midiMap = new HashMap<Integer, File>();
        midiMap.put(74, new File("./Samples/C5 Tin Whistle.wav"));
        midiMap.put(76, new File("./Samples/D5 Tin Whistle.wav"));
        midiMap.put(78, new File("./Samples/E5 Tin Whistle.wav"));
        midiMap.put(79, new File("./Samples/F5 Tin Whistle.wav"));
        midiMap.put(81, new File("./Samples/G5 Tin Whistle.wav"));
        midiMap.put(83, new File("./Samples/A5 Tin Whistle.wav"));
        midiMap.put(84, new File("./Samples/A#5 Tin Whistle.wav"));
        midiMap.put(85, new File("./Samples/B5 Tin Whistle.wav"));
        midiMap.put(86, new File("./Samples/C6 Tin Whistle.wav"));
        midiMap.put(88, new File("./Samples/D6 Tin Whistle.wav"));
        midiMap.put(90, new File("./Samples/E6 Tin Whistle.wav"));
        midiMap.put(91, new File("./Samples/F6 Tin Whistle.wav"));
        midiMap.put(93, new File("./Samples/G6 Tin Whistle.wav"));
        midiMap.put(95, new File("./Samples/A6 Tin Whistle.wav"));
        
    }

    /**
     *  Plays the MIDI note including the visual elements.
     *  
     *  @param note - the MIDI note to be played
     */
    public void play(int note){   
        if( note < 0) // -1 will be recieved as a call to reset visuals
            resetVisuals();
        else{
            try{
                audio.play(midiMap.get(note));
            }
            catch(Exception e){
                e.printStackTrace();   
            }
            showNote(note);
        }
    }
    
    /**
     *  Plays the MIDI note not including the visual elements which causes prevent
     *  an error that occurs when playing MIDI files causes every visual element
     *  to activate.
     *  
     *  @param note - the MIDI note to be played
     */
    public void midiPlay(int note){
        try{
                audio.play(midiMap.get(note));
            }
            catch(Exception e){
                e.printStackTrace();   
            }
    }

    /**
     *  Displays the appropriate visual elements on the screen
     *  and resets them according to the key of the whistle
     *  
     *  @param note - the MIDI note to be played
     */
    private void showNote(int note){
        switch(note){
            case 74:
            TinWhistleVST.b.setVisible(true);
            TinWhistleVST.a.setVisible(true);
            TinWhistleVST.g.setVisible(true);
            TinWhistleVST.fSharp.setVisible(true);
            TinWhistleVST.e.setVisible(true);
            TinWhistleVST.d.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[1].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[0].setFill(Color.RED); 
            break;
            case 76:
            TinWhistleVST.b.setVisible(true);
            TinWhistleVST.a.setVisible(true);
            TinWhistleVST.g.setVisible(true);
            TinWhistleVST.fSharp.setVisible(true);
            TinWhistleVST.e.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[2].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[1].setFill(Color.RED); 
            break;
            case 78:
            TinWhistleVST.b.setVisible(true);
            TinWhistleVST.a.setVisible(true);
            TinWhistleVST.g.setVisible(true);
            TinWhistleVST.fSharp.setVisible(true);
            if(TinWhistleVST.whistleKey == 1)
            TinWhistleVST.blackKeyArray[3].setFill(Color.RED);
            else if(TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[3].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[2].setFill(Color.RED); 
            break;
            case 79:
            TinWhistleVST.b.setVisible(true);
            TinWhistleVST.a.setVisible(true);
            TinWhistleVST.g.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[4].setFill(Color.RED);
            else if(TinWhistleVST.whistleKey == 3)
            TinWhistleVST.blackKeyArray[2].setFill(Color.RED);
            else            
            TinWhistleVST.whiteKeyArray[3].setFill(Color.RED);
            break;
            case 81:
            TinWhistleVST.b.setVisible(true);
            TinWhistleVST.a.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[5].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[4].setFill(Color.RED); 
            break;
            case 83:
            TinWhistleVST.b.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[6].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[5].setFill(Color.RED); 
            break;
            case 84:
            TinWhistleVST.a.setVisible(true);
            TinWhistleVST.g.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[7].setFill(Color.RED);            
            else
            TinWhistleVST.blackKeyArray[5].setFill(Color.RED);
            break;
            case 85:
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.blackKeyArray[7].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[6].setFill(Color.RED);
            break;
            case 86:
            TinWhistleVST.a.setVisible(true);
            TinWhistleVST.g.setVisible(true);
            TinWhistleVST.fSharp.setVisible(true);
            TinWhistleVST.e.setVisible(true);
            TinWhistleVST.d.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[8].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[7].setFill(Color.RED); 
            break;
            case 88:
            TinWhistleVST.b.setVisible(true);
            TinWhistleVST.a.setVisible(true);
            TinWhistleVST.g.setVisible(true);
            TinWhistleVST.fSharp.setVisible(true);
            TinWhistleVST.e.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[9].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[8].setFill(Color.RED);  
            break;
            case 90:
            TinWhistleVST.b.setVisible(true);
            TinWhistleVST.a.setVisible(true);
            TinWhistleVST.g.setVisible(true);
            TinWhistleVST.fSharp.setVisible(true);
            if(TinWhistleVST.whistleKey == 1)
            TinWhistleVST.blackKeyArray[10].setFill(Color.RED);
            else if(TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[10].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[9].setFill(Color.RED); 
            break;
            case 91:
            TinWhistleVST.b.setVisible(true);
            TinWhistleVST.a.setVisible(true);
            TinWhistleVST.g.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[11].setFill(Color.RED);
            else if(TinWhistleVST.whistleKey == 3)
            TinWhistleVST.blackKeyArray[9].setFill(Color.RED);
            else            
            TinWhistleVST.whiteKeyArray[10].setFill(Color.RED);
            break;
            case 93:
            TinWhistleVST.b.setVisible(true);
            TinWhistleVST.a.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[12].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[11].setFill(Color.RED);  
            break;
            case 95:
            TinWhistleVST.b.setVisible(true);
            if(TinWhistleVST.whistleKey == 1 || TinWhistleVST.whistleKey == 2)
            TinWhistleVST.whiteKeyArray[13].setFill(Color.RED);
            else
            TinWhistleVST.whiteKeyArray[12].setFill(Color.RED);  
            break;
        }
    }

    /**
     * Resets all visual elements in accordance with the whistle key
     */
    private void resetVisuals(){
        TinWhistleVST.b.setVisible(false);
        TinWhistleVST.a.setVisible(false);
        TinWhistleVST.g.setVisible(false);
        TinWhistleVST.fSharp.setVisible(false);
        TinWhistleVST.e.setVisible(false);
        TinWhistleVST.d.setVisible(false);
        switch(TinWhistleVST.whistleKey){
            case 1:
        for(int i = 0; i < TinWhistleVST.whiteKeyArray.length; i++){
			if( i == 0 || i == 3 || i == 10){
				TinWhistleVST.whiteKeyArray[i].setFill(Color.GREY);
			}
			else{
				TinWhistleVST.whiteKeyArray[i].setFill(Color.WHITE);				
			}
		}
		for(int i = 0; i < TinWhistleVST.blackKeyArray.length; i++){
			if(i == 3 || i == 7 || i == 10){
				TinWhistleVST.blackKeyArray[i].setFill(Color.BLACK);
			}
			else{
				TinWhistleVST.blackKeyArray[i].setFill(Color.DIMGREY);
			}
		}
		break;
	 case 2:
	 for(int i = 0; i < TinWhistleVST.whiteKeyArray.length; i++){
			if( i == 0){
				TinWhistleVST.whiteKeyArray[i].setFill(Color.GREY);
			}
			else{
				TinWhistleVST.whiteKeyArray[i].setFill(Color.WHITE);				
			}
		}
		for(int i = 0; i < TinWhistleVST.blackKeyArray.length; i++){
			if(i == 7){
				TinWhistleVST.blackKeyArray[i].setFill(Color.BLACK);
			}
			else{
				TinWhistleVST.blackKeyArray[i].setFill(Color.DIMGREY);
			}
		}
	 break;
	 case 3:
	 for(int i = 0; i < TinWhistleVST.whiteKeyArray.length; i++){
			if(i == 3 || i == 10 || i == 13){
				TinWhistleVST.whiteKeyArray[i].setFill(Color.GREY);
			}
			else{
				TinWhistleVST.whiteKeyArray[i].setFill(Color.WHITE);				
			}
		}
		for(int i = 0; i < TinWhistleVST.blackKeyArray.length; i++){
			if(i == 2 || i == 5 || i == 9){
				TinWhistleVST.blackKeyArray[i].setFill(Color.BLACK);
			}
			else{
				TinWhistleVST.blackKeyArray[i].setFill(Color.DIMGREY);
			}
		}
		break;
	 case 4:
	 for(int i = 0; i < TinWhistleVST.whiteKeyArray.length; i++){
			if( i == 13){
				TinWhistleVST.whiteKeyArray[i].setFill(Color.GREY);
			}
			else{
				TinWhistleVST.whiteKeyArray[i].setFill(Color.WHITE);				
			}
		}
		for(int i = 0; i < TinWhistleVST.blackKeyArray.length; i++){
			if(i == 5){
				TinWhistleVST.blackKeyArray[i].setFill(Color.BLACK);
			}
			else{
				TinWhistleVST.blackKeyArray[i].setFill(Color.DIMGREY);
			}
		}
	 break;
}
    }
}
