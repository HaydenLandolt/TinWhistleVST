import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
/**
 *  Modified implementaion of a class
 *  retrieved from Stack Overflow
 */
public class MidiInputReceiver implements Receiver {
    public String name;
    Reader reader = new Reader();
 
    /**
     * Constructor for the reciever.
     * 
     * @param name - the name of the receiver
     */
    public MidiInputReceiver(String name) {
        this.name = name;
    }
    
    /**
     *  Receives the MIDIMessage, checks that it is valid, and plays it.
     *  
     *  @param msg - the MIDIMessage received from the keyboard
     *  @param timeStamp - note used in implementation
     */
    public void send(MidiMessage msg, long timeStamp) {
        int note = reader.noteFromKeyboard(msg); // checks to make sure the note is valid and transposes if nessecary
        if(note > 73 || note == -1){
        TinWhistleVST.whistle.play(note); // plays the note recieved from the keyboard
    }
    }
    
    /**
     * Closes the reciever
     */
    public void close(){}
    }
