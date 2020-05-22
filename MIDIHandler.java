import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
/**
 * Modified implementation of a class
 * retrieved from Stack Overflow
 */
public class MIDIHandler
{
    /**
     *  Opens the MIDI keyboard plugged into the computer
     */
     public static void MIDIHandler()
    {
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        
            try {
            device = MidiSystem.getMidiDevice(infos[1]);
            //does the device have any transmitters?
            //if it does, add it to the device list
            System.out.println(infos[1]);

            //get all transmitters
            List<Transmitter> transmitters = device.getTransmitters();
            //and for each transmitter

            for(int j = 0; j<transmitters.size();j++) {
                //create a new receiver
                transmitters.get(j).setReceiver(
                        //using my own MidiInputReceiver
                        new MidiInputReceiver(device.getDeviceInfo().toString())
                );
            }

            Transmitter trans = device.getTransmitter();
            trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));

            //open each device
            device.open();
            //if code gets this far without throwing an exception
            //print a success message
            System.out.println(device.getDeviceInfo()+" Was Opened");


        } catch (MidiUnavailableException e) {}
    }


}

