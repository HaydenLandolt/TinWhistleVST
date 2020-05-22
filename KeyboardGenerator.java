import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
public class KeyboardGenerator
{
    /**
     *  Generates the white keys of the piano keyboard display.
     *  
     *  @param key - the key of the whistle.
     *  @return a Rectangle[] of the white keys for the piano display.
     */
    public Rectangle[] generateWhiteKeys(int key){
        switch(key){
            case 1:
            return dWhiteKeys();
            case 2:
            return gWhiteKeys();
            case 3:
            return fWhiteKeys();
            case 4:
            return cWhiteKeys();
        }
        return null; // something went wrong by here
    }
    
    /**
     *  Generates the text labels for the white keys in the piano
     *  keyboard display.
     *  
     *  @param key - the key of the whislte.
     *  @return - a Text[] with the labels for the white keys.
     */
    public Text[] generateWhiteNames(int key){
        switch(key){
            case 1:
            return dWhiteNames();
            case 2:
            return gWhiteNames();
            case 3:
            return fWhiteNames();
            case 4:
            return cWhiteNames();
        }
        return null; // something went wrong by here
    }
    
    /**
     *  Generates the black keys of the piano keyboard display.
     *  
     *  @param key - the key of the whistle.
     *  @return a Rectangle[] of the black keys for the piano display.
     */
    public Rectangle[] generateBlackKeys(int key){
        switch(key){
            case 1:
            return dBlackKeys();
            case 2:
            return gBlackKeys();
            case 3:
            return fBlackKeys();
            case 4:
            return cBlackKeys();
        }
        return null; // something went wrong by here
    }
    
    /**
     *  Generates the text labels for the black keys in the piano
     *  keyboard display.
     *  
     *  @param key - the key of the whislte.
     *  @return - a Text[] with the labels for the black keys.
     */
    public Text[] generateBlackNames(int key){
        switch(key){
            case 1:
            return dBlackNames();
            case 2:
            return gBlackNames();
            case 3:
            return fBlackNames();
            case 4:
            return cBlackNames();
        }
        return null; // something went wrong by here
    }
    
    /**
     * Generates the white keys for the keyboard. It colours them appropriately
     * and instantiates an event handler on the appropriate keys to play the
     * corresponding sound.
     * 
     * @return the Rectangle[] of the white keys.
     */
   public Rectangle[] dWhiteKeys(){
       Rectangle[] whiteKeyArray = new Rectangle[14];
       for(int i = 0; i < 14; i++){
            Rectangle rect = new Rectangle(40, 80);
            if( i == 0 || i == 3 || i == 10){
                rect.setFill(Color.GREY);
            }
            else{
                rect.setFill(Color.WHITE);
                switch(i){
                    case 1:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(74);                    
                }
            });
                    break;
                    case 2:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(76);                    
                }
            });
                    break;
                    case 4:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(79);
                }
            });
                    break;
                    case 5:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(81);
                }
            });
            break;
            case 6:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(83);     
                }
            });
            break;
            case 7:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(84);
                }
            });
            break;
            case 8:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(86);                    
                }
            });
            break;
            case 9:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(88);              
                }
            }); 
            break;
            case 11:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(91);         
                }
            }); 
            break;
            case 12:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(93);                              
                }
            }); 
            break;
            case 13:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(95);                            
                }
            }); 
            break;
                }
            }
            whiteKeyArray[i] = rect;
        }
        return whiteKeyArray;
    }
    
    /**
     * Generates the black keys for the keyboard. It colours them appropriately
     * and instantiates an event handler on the appropriate keys to play the
     * corresponding sound.
     * 
     * @return the Rectangle[] of the black keys.
     */
    public Rectangle[] dBlackKeys(){
        Rectangle[] blackKeyArray = new Rectangle[13];
       for(int i = 0; i < 13; i++){
            Rectangle rect = new Rectangle(40, 80);
            if(i == 3 || i == 7 || i == 10){
                rect.setFill(Color.BLACK);
                if(i == 3){
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(78);
                }
            });
                }
                else if(i == 7){
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(85);                 
                }
            });
                }
                else{
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(90);           
                }
            }); 
                }
            }
            else if (i == 2 || i == 6|| i == 9){
                rect.setVisible(false);
            }
            else{
                rect.setFill(Color.DIMGREY);
            }
            blackKeyArray[i] = rect;
        }
        return blackKeyArray;
    }
    
    /**
     * Generates the white keys for the keyboard. It colours them appropriately
     * and instantiates an event handler on the appropriate keys to play the
     * corresponding sound.
     * 
     * @return the Rectangle[] of the white keys.
     */
    public Rectangle[] gWhiteKeys(){
       Rectangle[] whiteKeyArray = new Rectangle[14];
       for(int i = 0; i < 14; i++){
            Rectangle rect = new Rectangle(40, 80);
            if( i == 0){
                rect.setFill(Color.GREY);
            }
            else{
                rect.setFill(Color.WHITE);
                switch(i){
                    case 1:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(74);                    
                }
            });
                    break;
                    case 2:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(76);                    
                }
            });
                    break;
                    case 3:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(78);
                }
            });
                    break;
                    case 4:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(79);
                }
            });
                    break;
                    case 5:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(81);
                }
            });
            break;
            case 6:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(83);     
                }
            });
            break;
            case 7:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(84);
                }
            });
            break;
            case 8:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(86);                    
                }
            });
            break;
            case 9:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(88);              
                }
            }); 
            break;
            case 10:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(90);         
                }
            }); 
            break;
            case 11:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(91);         
                }
            }); 
            break;
            case 12:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(93);                              
                }
            }); 
            break;
            case 13:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(95);                            
                }
            }); 
            break;
                }
            }
            whiteKeyArray[i] = rect;
        }
        return whiteKeyArray;
    }
    
    /**
     * Generates the black keys for the keyboard. It colours them appropriately
     * and instantiates an event handler on the appropriate keys to play the
     * corresponding sound.
     * 
     * @return the Rectangle[] of the black keys.
     */
    public Rectangle[] gBlackKeys(){
       Rectangle[] blackKeyArray = new Rectangle[13];
       for(int i = 0; i < 13; i++){
            Rectangle rect = new Rectangle(40, 80);
            if(i == 7){
                rect.setFill(Color.BLACK);

                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(85);
                }
            });
        
                
            }
            else if (i == 3 || i == 6|| i == 10){
                rect.setVisible(false);
            }
            else{
                rect.setFill(Color.DIMGREY);
            }
            blackKeyArray[i] = rect;
        }
        return blackKeyArray;
    }
    
    /**
     * Generates the white keys for the keyboard. It colours them appropriately
     * and instantiates an event handler on the appropriate keys to play the
     * corresponding sound.
     * 
     * @return the Rectangle[] of the white keys.
     */
    public Rectangle[] fWhiteKeys(){
       Rectangle[] whiteKeyArray = new Rectangle[14];
       for(int i = 0; i < 14; i++){
            Rectangle rect = new Rectangle(40, 80);
            if( i == 3 || i == 10 || i == 13){
                rect.setFill(Color.GREY);
            }
            else{
                rect.setFill(Color.WHITE);
                switch(i){                    
                    case 0:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(74);                    
                }
            });
                    break;
                    case 1:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(76);
                }
            });
                    break;
                    case 2:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(78);
                }
            });
                    break;
                    case 4:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(81);
                }
            });
            break;
            case 5:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(83);     
                }
            });
            break;
            case 6:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(85);
                }
            });
            break;
            case 7:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(86);                    
                }
            });
            break;
            case 8:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(88);              
                }
            }); 
            break;
            case 9:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(90);         
                }
            }); 
            break;
            case 11:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(93);                              
                }
            }); 
            break;
            case 12:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(95);                            
                }
            }); 
            break;
                }
            }
            whiteKeyArray[i] = rect;
        }
        return whiteKeyArray;
    }
    
    /**
     * Generates the black keys for the keyboard. It colours them appropriately
     * and instantiates an event handler on the appropriate keys to play the
     * corresponding sound.
     * 
     * @return the Rectangle[] of the black keys.
     */
    public Rectangle[] fBlackKeys(){
       Rectangle[] blackKeyArray = new Rectangle[13];
       for(int i = 0; i < 13; i++){
            Rectangle rect = new Rectangle(40, 80);
            if (i == 2 || i == 5 || i == 9){
                rect.setFill(Color.BLACK);
                if(i == 2){
                        rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event){
                        TinWhistleVST.whistle.play(79);
                    }
                });
            
               } 
               else if(i == 5){
                        rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event){
                        TinWhistleVST.whistle.play(84);
                    }
                });
            
               }
               else {
                        rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event){
                        TinWhistleVST.whistle.play(91);
                    }
                });
            
               }
            }
            else if (i == 3 || i == 6|| i == 10){
                rect.setVisible(false);
            }
            else{
                rect.setFill(Color.DIMGREY);
            }
            blackKeyArray[i] = rect;
        }
        return blackKeyArray;
    }
    
    /**
     * Generates the white keys for the keyboard. It colours them appropriately
     * and instantiates an event handler on the appropriate keys to play the
     * corresponding sound.
     * 
     * @return the Rectangle[] of the white keys.
     */
    public Rectangle[] cWhiteKeys(){
       Rectangle[] whiteKeyArray = new Rectangle[14];
       for(int i = 0; i < 14; i++){
            Rectangle rect = new Rectangle(40, 80);
            if( i == 13){
                rect.setFill(Color.GREY);
            }
            else{
                rect.setFill(Color.WHITE);
                switch(i){
                    case 0:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(74);                    
                }
            });
                    break;
                    case 1:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(76);                    
                }
            });
                    break;
                    case 2:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(78);                    
                }
            });
                    break;
                    case 3:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(79);
                }
            });
                    break;
                    case 4:
                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(81);
                }
            });
            break;
            case 5:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(83);     
                }
            });
            break;
            case 6:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(85);
                }
            });
            break;
            case 7:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(86);                    
                }
            });
            break;
            case 8:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(88);              
                }
            }); 
            break;
            case 9:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(90);         
                }
            }); 
            break;
            case 10:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(91);         
                }
            }); 
            break;
            case 11:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(93);                              
                }
            }); 
            break;
            case 12:
            rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(95);                            
                }
            }); 
            break;
                }
            }
            whiteKeyArray[i] = rect;
        }
        return whiteKeyArray;
    }
    
    /**
     * Generates the black keys for the keyboard. It colours them appropriately
     * and instantiates an event handler on the appropriate keys to play the
     * corresponding sound.
     * 
     * @return the Rectangle[] of the black keys.
     */
    public Rectangle[] cBlackKeys(){
       Rectangle[] blackKeyArray = new Rectangle[13];
       for(int i = 0; i < 13; i++){
            Rectangle rect = new Rectangle(40, 80);
            if(i == 5){
                rect.setFill(Color.BLACK);

                    rect.setOnMousePressed( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    TinWhistleVST.whistle.play(84);
                }
            });
                
 
            }
            else if (i == 2 || i == 6|| i == 9){
                rect.setVisible(false);
            }
            else{
                rect.setFill(Color.DIMGREY);
            }
            blackKeyArray[i] = rect;
        }
        return blackKeyArray;
    }
    
    /**
     * Generates the white key name labels for the keyboard and colours 
     * them appropriately.
     * 
     * @return the Test[] of the white key name labels.
     */
    public Text[] dWhiteNames(){
        Text[] textArray = new Text[14];
        textArray[0] = new Text("C5");
        textArray[1] = new Text("D5");
        textArray[2] = new Text("E5");
        textArray[3] = new Text("F5");
        textArray[4] = new Text("G5");
        textArray[5] = new Text("A5");
        textArray[6] = new Text("B5");
        textArray[7] = new Text("C6");
        textArray[8] = new Text("D6");
        textArray[9] = new Text("E6");
        textArray[10] = new Text("F6");
        textArray[11] = new Text("G6");
        textArray[12] = new Text("A6");
        textArray[13] = new Text("B6");
        return textArray;
    }
    
    /**
     * Generates the black key name labels for the keyboard and colours 
     * them appropriately.
     * 
     * @return the Test[] of the black key name labels.
     */
    public Text[] dBlackNames(){
        Text[] textArray = new Text[13];
        textArray[0] = new Text("C#5");
        textArray[1] = new Text("D#5");
        textArray[2] = new Text("  ");
        textArray[3] = new Text("F#5");
        textArray[3].setFill(Color.WHITE);
        textArray[4] = new Text("G#5");
        textArray[5] = new Text("A#5");
        textArray[6] = new Text("  ");
        textArray[7] = new Text("C#6");
        textArray[7].setFill(Color.WHITE);
        textArray[8] = new Text("D#6");
        textArray[9] = new Text("   ");
        textArray[10] = new Text("F#6");
        textArray[10].setFill(Color.WHITE);
        textArray[11] = new Text("G#6");
        textArray[12] = new Text("A#6");
        return textArray;
    }
    
    /**
     * Generates the white key name labels for the keyboard and colours 
     * them appropriately.
     * 
     * @return the Test[] of the white key name labels.
     */
    public Text[] gWhiteNames(){
        Text[] textArray = new Text[14];
        textArray[0] = new Text("F5");
        textArray[1] = new Text("G5");
        textArray[2] = new Text("A5");
        textArray[3] = new Text("B5");
        textArray[4] = new Text("C6");
        textArray[5] = new Text("D6");
        textArray[6] = new Text("E6");
        textArray[7] = new Text("F6");
        textArray[8] = new Text("G6");
        textArray[9] = new Text("A6");
        textArray[10] = new Text("B6");
        textArray[11] = new Text("C7");
        textArray[12] = new Text("D7");
        textArray[13] = new Text("E7");
        return textArray;
    }
    
    /**
     * Generates the black key name labels for the keyboard and colours 
     * them appropriately.
     * 
     * @return the Test[] of the black key name labels.
     */
    public Text[] gBlackNames(){
        Text[] textArray = new Text[13];
        textArray[0] = new Text("F#5");
        textArray[1] = new Text("G#5");
        textArray[2] = new Text("A#5");
        textArray[3] = new Text("  ");
        textArray[4] = new Text("C#6");
        textArray[5] = new Text("D#6");
        textArray[6] = new Text("  ");
        textArray[7] = new Text("F#6");
        textArray[7].setFill(Color.WHITE);
        textArray[8] = new Text("G#6");
        textArray[9] = new Text("A#6");
        textArray[10] = new Text("   ");
        textArray[11] = new Text("C#7");
        textArray[12] = new Text("D#7");
        return textArray;
    }
    
    /**
     * Generates the white key name labels for the keyboard and colours 
     * them appropriately.
     * 
     * @return the Test[] of the white key name labels.
     */
    public Text[] fWhiteNames(){
        Text[] textArray = new Text[14];
        textArray[0] = new Text("F5");
        textArray[1] = new Text("G5");
        textArray[2] = new Text("A5");
        textArray[3] = new Text("B5");
        textArray[4] = new Text("C6");
        textArray[5] = new Text("D6");
        textArray[6] = new Text("E6");
        textArray[7] = new Text("F6");
        textArray[8] = new Text("G6");
        textArray[9] = new Text("A6");
        textArray[10] = new Text("B6");
        textArray[11] = new Text("C7");
        textArray[12] = new Text("D7");
        textArray[13] = new Text("E7");
        return textArray;
    }
    
    /**
     * Generates the black key name labels for the keyboard and colours 
     * them appropriately.
     * 
     * @return the Test[] of the black key name labels.
     */
    public Text[] fBlackNames(){
        Text[] textArray = new Text[13];
        textArray[0] = new Text("Gb5");
        textArray[1] = new Text("Ab5");
        textArray[2] = new Text("Bb5");
        textArray[2].setFill(Color.WHITE);
        textArray[3] = new Text("  ");
        textArray[4] = new Text("Db6");
        textArray[5] = new Text("Eb6");
        textArray[5].setFill(Color.WHITE);
        textArray[6] = new Text("  ");
        textArray[7] = new Text("Gb6");        
        textArray[8] = new Text("Ab6");
        textArray[9] = new Text("Bb6");
        textArray[9].setFill(Color.WHITE);
        textArray[10] = new Text("   ");
        textArray[11] = new Text("Db7");
        textArray[12] = new Text("Eb7");
        return textArray;
    }
    
    /**
     * Generates the white key name labels for the keyboard and colours 
     * them appropriately.
     * 
     * @return the Test[] of the white key name labels.
     */
    public Text[] cWhiteNames(){
        Text[] textArray = new Text[14];
        textArray[0] = new Text("C5");
        textArray[1] = new Text("D5");
        textArray[2] = new Text("E5");
        textArray[3] = new Text("F5");
        textArray[4] = new Text("G5");
        textArray[5] = new Text("A5");
        textArray[6] = new Text("B5");
        textArray[7] = new Text("C6");
        textArray[8] = new Text("D6");
        textArray[9] = new Text("E6");
        textArray[10] = new Text("F6");
        textArray[11] = new Text("G6");
        textArray[12] = new Text("A6");
        textArray[13] = new Text("B6");
        return textArray;
    }
    
    /**
     * Generates the black key name labels for the keyboard and colours 
     * them appropriately.
     * 
     * @return the Test[] of the black key name labels.
     */
    public Text[] cBlackNames(){
        Text[] textArray = new Text[13];
        textArray[0] = new Text("C#5");
        textArray[1] = new Text("D#5");
        textArray[2] = new Text("  ");
        textArray[3] = new Text("F#5");       
        textArray[4] = new Text("G#5");
        textArray[5] = new Text("A#5");
        textArray[5].setFill(Color.WHITE);
        textArray[6] = new Text("  ");
        textArray[7] = new Text("C#6");
        textArray[8] = new Text("D#6");
        textArray[9] = new Text("   ");
        textArray[10] = new Text("F#6");
        textArray[11] = new Text("G#6");
        textArray[12] = new Text("A#6");
        return textArray;
    }
}
