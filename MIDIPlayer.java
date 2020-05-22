import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
public class MIDIPlayer
{
    Reader reader = new Reader();
    /** Takes a MIDI file and then gets the notes and their timestamps
     * from the file. The method then plays the sequence.
     * 
     * @param file - the MIDI file to be played
     */
    public void playMIDI(File file) throws Exception{ 
        Sequence sequence = MidiSystem.getSequence(file);
        
        int[] notes = reader.getNotes(sequence); // gets the notes
        long[] ticks = reader.getTicks(sequence); // gets the timestamps

        playSequence(notes, ticks);
    }
    
    /** Implementation of the MIDI playback system. The method takes the
     * timestamp (ticks) of the next note. When the time counter reaches that
     * tick, the next note is triggered. Because ticks are counted milliseconds
     * and a quarter note is 480 ticks, the system will always play at 125 bpm.
     * 
     * @param notes - an int[] containing the mapping value of the MIDI note
     * @param ticks - an array containing the timestamp (ticks) at which to play the note
     */
    public void playSequence(int[] notes, long[] ticks) throws Exception{
         long nextTrigger;
         long time;
     
         nextTrigger = ticks[0];
         time = 0;
         
         int noteNumber = 0;
        while(noteNumber < notes.length){
            if(time == nextTrigger){
               TinWhistleVST.whistle.midiPlay(notes[noteNumber]);
               noteNumber++;
               nextTrigger = ticks[noteNumber];
            }
            TimeUnit.MILLISECONDS.sleep(1);
            time++;
        }

    }


}
