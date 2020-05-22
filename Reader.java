import java.io.File;
import java.util.ArrayList;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Reader {
    public static final int NOTE_ON = 0x90; // int value for a key being pressed
    public static final int NOTE_OFF = 0x80; // in value for a key being released

    /**
     *  Reads through the MIDI sequence and puts the MIDI key into an int[].
     *  
     *  @param sequence - the MIDI sequence being played.
     *  @return the int[] cointaining the MIDI keys.
     */
    public static int[] getNotes(Sequence sequence) throws Exception {

        ArrayList<Integer> notes = new ArrayList<Integer>();
        for (Track track : sequence.getTracks()) {
            for (int i = 0; i < track.size(); i++) { 
                MidiEvent event = track.get(i); // gets the MIDI event
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) { // no settings, just notes
                    ShortMessage sm = (ShortMessage) message;
                    if (sm.getCommand() == NOTE_ON) {  //if this is an instance of the key being pressed                     
                        int key = sm.getData1();
                        int velocity = sm.getData2();
                        if(velocity > 0 && validNote(key)){ // if the note is playable by the whistle
                            notes.add(key);
                        }
                    }

                }
            }
        }

        int[] array = new int[notes.size()];
        for(int i = 0; i < array.length; i++){
            array[i] = notes.get(i);   
        }
        return array;
    }

    /**
     *  Reads through the MIDI sequence and puts the MIDI ticks into an int[].
     *  
     *  @param sequence - the MIDI sequence being played.
     *  @return the long[] cointaining the MIDI ticks.
     */
    public static long[] getTicks(Sequence sequence) throws Exception {

        ArrayList<Long> ticks = new ArrayList<Long>();
        for (Track track : sequence.getTracks()) {
            for (int i = 0; i < track.size(); i++) { 
                MidiEvent event = track.get(i); // gets the MIDI event

                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) { // not settings, only notes
                    ShortMessage sm = (ShortMessage) message;
                    if (sm.getCommand() == NOTE_ON) {  // if the note is being pressed                      
                        int key = sm.getData1();
                        int velocity = sm.getData2();
                        if(velocity > 0 && validNote(key)){
                            ticks.add(event.getTick());
                        }
                    } else if (sm.getCommand() == NOTE_OFF) { // if the note is being released 
                        int key = sm.getData1();
                        int velocity = sm.getData2();
                        if(velocity > 0 && validNote(key)){
                            ticks.add(event.getTick());
                        }
                    }

                }
            }
        }

        long[] array = new long[ticks.size()];
        for(int i = 0; i < array.length; i++){
            array[i] = ticks.get(i);   
        }
        return array;
    }

    /**
     *  Takes the note played by an external MIDI keyboard and makes sure it 
     *  is valid. Will also transpose the MIDI note into the proper key.
     *  
     *  @param message - the MIDIMessage being sent by the external keyboard.
     *  @return the MIDI key.
     */
    public int noteFromKeyboard(MidiMessage message){
        int note = 0;
        if (message instanceof ShortMessage) { // no settings, only notes
            ShortMessage sm = (ShortMessage) message;
            if (sm.getCommand() == NOTE_ON) {   // if a note is being pressed                     
                int key = sm.getData1();
                int velocity = sm.getData2();
                if(velocity > 0){
                    note = key;
                }
            } else if (sm.getCommand() == NOTE_OFF) { // if a note is being released
                int key = sm.getData1();
                int velocity = sm.getData2();
                if(velocity > 0){
                    note =  -1; // returned to reset the visual elements
                }
            }

        }
        if(note == -1) // if it's a call to reset the visuals, don't transpose 
            return note;
        else{
            if(!TinWhistleVST.transposeMidi){
                if(TinWhistleVST.whistleKey == 1) // don't transpose
                    return note;
                else if(TinWhistleVST.whistleKey == 2) // transpose 5 semitones down from G
                    return note - 5;
                else if(TinWhistleVST.whistleKey == 3) // transpose 3 semitones down from F
                    return note - 3;
                else // transpose 2 semitones up from C
                    return note + 2;
            }
                    else
                    return note;
                }
    }

    /**
     * Checks to make sure that the note is playable by the Diatonic Tin Whistle.
     * 
     * @param note - the MIDI note
     * @return returns true if the note is playable
     */
    public static boolean validNote(int note){
        if(note > 73 && note < 96){
            switch(note){
                case 75:
                return false;
                case 77:
                return false;
                case 80:
                return false;
                case 82:
                return false;
                case 87:
                return false;
                case 89:
                return false;
                case 92:
                return false;
                case 94:
                return false;
                default:
                return true;
            }
        }
        else
            return false;
    }
}