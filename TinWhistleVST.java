import java.awt.Desktop;
import java.io.File;
import java.util.Optional;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.event.ChangeListener;

public class TinWhistleVST extends Application
{
    //static variables used by various methods
    static TinWhistle whistle = new TinWhistle();
    MIDIPlayer midiPlayer = new MIDIPlayer();
    MIDIHandler midiHandler = new MIDIHandler();
    KeyboardGenerator keyGenerator = new KeyboardGenerator();

    // global togglle booleans
    boolean recording = false;
    boolean upperRegister = false;
    public static boolean transposeMidi = false;

    File selectedFile; // Selected MIDI file

    public static int whistleKey = 1;
    // 1 = D
    // 2 = G
    // 3 = F
    // 4 = C

    // Keyboard element arrays to be manipulated by various
    // methods and classes
    public static Rectangle[] whiteKeyArray;
    public static Rectangle[] blackKeyArray;
    Text[] whiteNamesArray;
    Text[] blackNamesArray;

    Group group;
    VBox pianoSection;

    //Circles indicating whistle fingerings
    public static Circle b;
    public static Circle a;
    public static Circle g;
    public static Circle fSharp;
    public static Circle e;
    public static Circle d;

    /**
     *  Creates all of the visual elements in the TinWhistleVST as well as
     *  setting up the event handlers and event filters to organize and
     *  facilitate the entire GUI.
     *  
     *  @param stage - the main stage.
     */
    public void start(Stage stage){
        //Open any external MIDI keyboard
        midiHandler.MIDIHandler();
        
        //Sets the in-GUI title
        Text title = new Text("TIN WHISTLE");
        title.setFill(Color.WHITE);
        Glow glow = new Glow();
        glow.setLevel(4);
        title.setEffect(glow);
        title.setFont(new Font(40));

        //Redundant box to fulfill the animation/transition requirement
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.CRIMSON);      

        Box redundantBox = new Box(50, 50, 50);
        redundantBox.setMaterial(material);
        redundantBox.setTranslateX(70);
        redundantBox.setTranslateY(70);

        //Rotates the box
        RotateTransition rotationTransition = new RotateTransition(Duration.millis(7000), redundantBox);
        rotationTransition.setAxis(Rotate.Y_AXIS);
        rotationTransition.setByAngle(360);
        rotationTransition.setCycleCount(50);
        rotationTransition.play();

        //Bottome options bar
        Rectangle bottomBar =  new Rectangle();
        bottomBar.setWidth(810);
        bottomBar.setHeight(110);
        bottomBar.setFill(Color.GREY);

        //Button to play MIDI
        Button selectFile = new Button("Play\nMIDI");
        selectFile.setTextAlignment(TextAlignment.CENTER);
        selectFile.setFocusTraversable(false);
        selectFile.setScaleX(2);
        selectFile.setScaleY(1.8);

        selectFile.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    try{
                        if(selectedFile != null)
                        {
                            midiPlayer.playMIDI(selectedFile);            
                        }
                        else{
                            System.out.println("No MIDI File Selected");
                        }
                    }
                    catch(Exception e){
                        System.out.println("File Error");
                    }
                }
            });

        //Display to show the currently loaded MIDI file
        Label loadedFileLabel = new Label("Loaded File:");
        Text loadedFileText = new Text("None");
        VBox loadedFileBox = new VBox(loadedFileLabel, loadedFileText);
        loadedFileBox.setSpacing(2);

        //Loads a MIDI file
        Button loadFile = new Button("Load MIDI File");
        loadFile.setFocusTraversable(false);
        loadFile.setTranslateX(20);
        loadFile.setScaleX(1.5);
        loadFile.setScaleY(1.25);

        VBox loadFileDisplay = new VBox(loadFile, loadedFileBox);
        loadFileDisplay.setTranslateY(-10);
        loadFileDisplay.setSpacing(10);

        HBox midiFileSection = new HBox(selectFile, loadFileDisplay);
        midiFileSection.setLayoutX(40);
        midiFileSection.setSpacing(35);

        loadFile.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    //Creates a JavaFX file chooser to select a MIDI file
                    Stage chooserStage = new Stage();
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("Open MIDI File");
                    chooser.getExtensionFilters().add(new ExtensionFilter("MIDI Files", "*.mid"));   
                    selectedFile = chooser.showOpenDialog(chooserStage);
                    if(selectedFile != null)
                    loadedFileText.setText(selectedFile.getName());                    

                }
            });        

        //Gain slider
        Text gainValue = new Text();
        gainValue.setFont(new Font(18)); 
        gainValue.setTranslateX(15);

        Slider gainSlider = new Slider(-40, 6, 0);
        whistle.audio.setGainLevel((int)Math.floor(gainSlider.getValue()));
        gainValue.setText("Gain: " + whistle.audio.getGainLevel() + " db");
        gainSlider.setShowTickLabels(true);
        gainSlider.setShowTickMarks(true);
        gainSlider.setMajorTickUnit(20);
        gainSlider.setMinorTickCount(5);
        gainSlider.setBlockIncrement(10);

        gainSlider.valueProperty().addListener(new InvalidationListener(){
                @Override
                public void invalidated(Observable observable){
                    whistle.audio.setGainLevel((int)Math.floor(gainSlider.getValue()));
                    gainValue.setText("Gain: " + whistle.audio.getGainLevel() + " db");
                }

            });

        VBox volumeSection = new VBox(gainValue, gainSlider);
        volumeSection.setSpacing(5);

        //Text field to name the recording file
        Label recordFileLabel = new Label("Recording Name:");
        TextField fileNameField = new TextField("Recording");
        fileNameField.setFocusTraversable(false);
        Label extensionLabel = new Label(".wav");
        HBox fileNameBox = new HBox(fileNameField, extensionLabel);
        fileNameBox.setSpacing(2);
        VBox recordFileBox = new VBox(recordFileLabel, fileNameBox);
        recordFileBox.setSpacing(5);

        fileNameField.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fileNameField.requestFocus(); // when the text field is clicked, focus on the text field
                }
            }); 

        //Record button
        Button record = new Button("Start\nRecording");
        record.setTextAlignment(TextAlignment.CENTER);
        record.setFocusTraversable(false);
        record.setScaleX(2);
        record.setScaleY(1.8);

        record.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    if(!recording){ //we are not recording : start recording
                        //Will store the file in the Recordings folder
                        File file = new File("./Recordings/" + fileNameField.getText() + ".wav");
                        if(!file.exists()){
                            //file doesn't exist : create new file
                            whistle.audio.startRecord(file);
                            recording = true;
                            record.setText("End\nRecording");
                        }
                        else{
                            //file does exists : confirm overwrite?
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirm Overwrite");
                            alert.setHeaderText("Confirm Overwrite");
                            alert.setContentText("A file by this name already exists. Do you wish to overwrite it?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                //Overwrite the file
                                whistle.audio.startRecord(file);
                                recording = true;
                                record.setText("End\nRecording");
                            }
                        }
                    }
                    else{ // we already are recording : stop recording
                        recording = false;
                        record.setText("Start\nRecording");
                        whistle.audio.endRecord();
                    }
                }
            });

        HBox recordingSection = new HBox(record, recordFileBox);
        recordingSection.setSpacing(45);

        //Instruction Manual located in the TinWhistleVST folder
        File instructions = new File("./Tin Whistle VST Instruction Manual.pdf");

        //Help button
        Button help = new Button("HELP");        
        help.setFocusTraversable(false);
        help.setScaleX(2);
        help.setScaleY(3);
        help.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    //Opens the instruction manual pdf through your Desktop's
                    //default program for opening a pdf
                        if (Desktop.isDesktopSupported()) {
        try {
            Desktop.getDesktop().open(new File("./Tin Whistle VST Instruction Manual.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

                }
            }); 

        //Tin Whistle Image
        ImageView tinWhistle = new ImageView("TinWhistle.png");
        tinWhistle.setScaleX(0.75);
        tinWhistle.setScaleY(0.75);
        tinWhistle.setRotate(-25);

        //Fingering Circles
        b = new Circle();
        b.setVisible(false);
        b.setFill(Color.RED);
        b.setRadius(10);
        b.setTranslateX(-415);
        b.setTranslateY(215);

        a = new Circle();
        a.setVisible(false);
        a.setFill(Color.RED);
        a.setRadius(10);
        a.setTranslateX(-390);
        a.setTranslateY(220);

        g = new Circle();
        g.setVisible(false);
        g.setFill(Color.RED);
        g.setRadius(10);
        g.setTranslateX(-355);
        g.setTranslateY(222);

        fSharp = new Circle();
        fSharp.setVisible(false);
        fSharp.setFill(Color.RED);
        fSharp.setRadius(10);
        fSharp.setTranslateX(-320);
        fSharp.setTranslateY(227);

        e = new Circle();
        e.setVisible(false);
        e.setFill(Color.RED);
        e.setRadius(10);
        e.setTranslateX(-300);
        e.setTranslateY(230);

        d = new Circle();
        d.setVisible(false);
        d.setFill(Color.RED);
        d.setRadius(10);
        d.setTranslateX(-265);
        d.setTranslateY(233);

        HBox tinWhistleSection = new HBox(tinWhistle, b, a, g, fSharp, e, d);

        //Exit button
        Button exit = new Button("EXIT");
        exit.setTextAlignment(TextAlignment.CENTER);
        exit.setFocusTraversable(false);
        exit.setLayoutY(40);
        exit.setScaleX(2);
        exit.setScaleY(3);

        exit.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    System.exit(0);
                }
            }); 

        //Section to transpose MIDI keyboard input
        Label midiTransposeLabel = new Label("MIDI");
        midiTransposeLabel.setFont(new Font(20));
        midiTransposeLabel.setTranslateX(10);
        midiTransposeLabel.setTranslateY(20);
        ToggleGroup midiTranspose = new ToggleGroup();

        //Don't transpose (concert) button
        ToggleButton concert = new ToggleButton("Concert");
        concert.setTextAlignment(TextAlignment.CENTER);
        concert.setFocusTraversable(false);
        concert.setSelected(true);
        concert.setToggleGroup(midiTranspose);
        concert.setScaleX(1.8);
        concert.setScaleY(2);
        concert.setTranslateX(5);
        concert.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    toggleMIDITranspose(false);
                }
            }); 

        //Transpose button
        ToggleButton transpose = new ToggleButton("Transpose");
        transpose.setTextAlignment(TextAlignment.CENTER);
        transpose.setFocusTraversable(false);
        transpose.setToggleGroup(midiTranspose);
        transpose.setScaleX(1.5);
        transpose.setScaleY(2);
        transpose.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    toggleMIDITranspose(true);
                }
            }); 

        VBox midiTransposeSection = new VBox(midiTransposeLabel, concert , transpose);
        midiTransposeSection.setSpacing(30);
        midiTransposeSection.setTranslateX(40);

        //Generate the arrays for creating the keyboard display
        whiteKeyArray = keyGenerator.generateWhiteKeys(whistleKey);  
        blackKeyArray = keyGenerator.generateBlackKeys(whistleKey);        
        whiteNamesArray = keyGenerator.generateWhiteNames(whistleKey);
        blackNamesArray = keyGenerator.generateBlackNames(whistleKey);

        //Create the keyboard display
        HBox whiteKeys = new HBox();
        for(int i = 0; i < whiteKeyArray.length; i++){
            whiteKeys.getChildren().add(whiteKeyArray[i]);
        }
        whiteKeys.setSpacing(1);

        HBox whiteNames = new HBox();
        for(int i = 0; i < whiteNamesArray.length; i++){
            whiteNames.getChildren().add(whiteNamesArray[i]);
        }
        whiteNames.setSpacing(26.5);
        whiteNames.setTranslateX(15);
        whiteNames.setTranslateY(-40);

        HBox blackKeys = new HBox();
        for (int i = 0; i < blackKeyArray.length; i++){
            blackKeys.getChildren().add(blackKeyArray[i]);
        }
        blackKeys.setSpacing(1);
        blackKeys.setTranslateX(20);

        HBox blackNames = new HBox();
        for(int i = 0; i < blackNamesArray.length; i++){
            blackNames.getChildren().add(blackNamesArray[i]);
        }
        blackNames.setSpacing(23.49);
        blackNames.setTranslateX(30);
        blackNames.setTranslateY(-105);

        pianoSection = new VBox(blackKeys, whiteKeys, blackNames, whiteNames);
        pianoSection.setSpacing(1);

        //Button bank to change whistle key
        ToggleGroup switchWhistle = new ToggleGroup();

        ToggleButton dWhistle = new ToggleButton("D");
        dWhistle.setToggleGroup(switchWhistle);
        dWhistle.setSelected(true);
        dWhistle.setFocusTraversable(false);
        dWhistle.setScaleX(2);
        dWhistle.setScaleY(2);
        dWhistle.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    setKey(1); // changes the key
                    whistle.mapWhistle(whistleKey); // remaps the whistle
                    switchKeyboard(); //changes the keyboard
                    //positions the keyboard on the screen
                    pianoSection.setLayoutX((stage.getWidth()-570) / 2);
                    pianoSection.setLayoutY((stage.getHeight()/1.88087774295) -40);
                }
            });  

        ToggleButton gWhistle = new ToggleButton("G");
        gWhistle.setToggleGroup(switchWhistle);
        gWhistle.setFocusTraversable(false);
        gWhistle.setScaleX(2);
        gWhistle.setScaleY(2);
        gWhistle.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    setKey(2); // changes the key
                    whistle.mapWhistle(whistleKey); // remaps the whistle
                    switchKeyboard(); // changes the keyboard
                    // positions the keyboard on the screen
                    pianoSection.setLayoutX((stage.getWidth()-570) / 2);
                    pianoSection.setLayoutY((stage.getHeight()/1.88087774295) -40);
                }
            });  

        ToggleButton fWhistle = new ToggleButton("F");
        fWhistle.setToggleGroup(switchWhistle);
        fWhistle.setFocusTraversable(false);
        fWhistle.setScaleX(2);
        fWhistle.setScaleY(2);
        fWhistle.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    setKey(3); // changes the key
                    whistle.mapWhistle(whistleKey); // rempas the whistle
                    switchKeyboard(); // changes the keyboard
                    // positions the keyboard on the screen
                    pianoSection.setLayoutX((stage.getWidth()-570) / 2);
                    pianoSection.setLayoutY((stage.getHeight()/1.88087774295) -40);
                }
            });  

        ToggleButton cWhistle = new ToggleButton("C");  
        cWhistle.setToggleGroup(switchWhistle);
        cWhistle.setFocusTraversable(false);
        cWhistle.setScaleX(2);
        cWhistle.setScaleY(2);
        cWhistle.setOnMouseClicked( new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event){
                    setKey(4); // changes the key
                    whistle.mapWhistle(whistleKey); // remaps the whistle
                    switchKeyboard(); // changes the keyboard
                    //positions the keyboard on the screen
                    pianoSection.setLayoutX((stage.getWidth()-570) / 2);
                    pianoSection.setLayoutY((stage.getHeight()/1.88087774295) -40);
                }
            }); 

        HBox whistleSwitchSection = new HBox(dWhistle, gWhistle, fWhistle, cWhistle);
        whistleSwitchSection.setSpacing(30);

        //root for the scene
        group = new Group(title, bottomBar, tinWhistleSection, exit,
            pianoSection, volumeSection, midiFileSection,
            recordingSection, redundantBox, whistleSwitchSection,
            midiTransposeSection, help);

         //Create the scene
        Scene scene = new Scene(group, 800, 600);

        //Repositions the elements on screen when the screen resizes
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {                
                title.setX((stage.getWidth() - 230) / 2);
                bottomBar.setWidth(stage.getWidth());
                tinWhistleSection.setLayoutX(((stage.getWidth() - 800)/2) - 50);
                exit.setLayoutX(stage.getWidth() - 100);
                pianoSection.setLayoutX((stage.getWidth()-570) / 2);
                volumeSection.setLayoutX((stage.getWidth() - 175) /2);
                recordingSection.setLayoutX(stage.getWidth() - 310);
                whistleSwitchSection.setLayoutX((stage.getWidth() - 220) / 2);
                help.setLayoutX(stage.getWidth() - 100);
            });

            //Repositions the elements on screen when the screen resizes
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
                title.setY((stage.getHeight()/6));
                bottomBar.setY(stage.getHeight() - bottomBar.getHeight() - 38);               
                tinWhistleSection.setLayoutY(((stage.getHeight() - 600)/2) - 50);
                pianoSection.setLayoutY((stage.getHeight()/1.88087774295) -40);
                volumeSection.setLayoutY(stage.getHeight() - 120);
                midiFileSection.setLayoutY(stage.getHeight() - 120);
                recordingSection.setLayoutY(stage.getHeight() - 120);
                whistleSwitchSection.setLayoutY((stage.getHeight() - 130) / 2);
                midiTransposeSection.setLayoutY((stage.getHeight() - 210) / 2);
                help.setLayoutY((stage.getHeight() - 100) / 2);
            });

              //Maps the computer keyboard to the tin whistle notes   
            scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {

                if(key.getCode() == KeyCode.K) {
                    if(!upperRegister){
                        whistle.play(74); //D5
                    }
                    else{
                        whistle.play(86); //D6
                    }
                }
            });            
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.J) {
                    if(!upperRegister){
                        whistle.play(76); //E5
                    }
                    else{
                        whistle.play(88); //E6
                    }
                }
            });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.H) {
                    if(!upperRegister){
                        whistle.play(78); //F#5
                    }
                    else{
                        whistle.play(90); //F#6
                    }
                }
            });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.S) {
                    if(!upperRegister){
                        whistle.play(79); //G5
                    }
                    else{
                        whistle.play(91); //G6
                    }
                }
            });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.D) {
                    if(!upperRegister){
                        whistle.play(81); //A5
                    }
                    else{
                        whistle.play(93); //A6
                    }
                }
            });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.F) {
                    if(!upperRegister){
                        whistle.play(83); //B5
                    }
                    else{
                        whistle.play(95); //B6
                    }
                }
            });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.E) {
                    whistle.play(84);  //C6
                }
            });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.A) {
                    whistle.play(85); //C#6
                }
            });
            
            // enables/disables the upper register of the whistle
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.SPACE) {
                    upperRegister = true;
                }
            });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
                if(key.getCode() == KeyCode.SPACE) {
                    upperRegister = false;
                }
            });
            
            //resets the visuals
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
                if(key.getCode() == KeyCode.K || key.getCode() == KeyCode.J ||
                key.getCode() == KeyCode.H || key.getCode() == KeyCode.S ||
                key.getCode() == KeyCode.D || key.getCode() == KeyCode.F ||
                key.getCode() == KeyCode.E || key.getCode() == KeyCode.A ) {
                    whistle.play(-1);//tells the TinWhistle class to reset the visuals
                }
            });

            //when the mouse is released
        EventHandler<MouseEvent> releaseKeyboard = new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    //Tells the TinWhistle class to reset the visuals as this
                    //event would be triggered when the user releases the piano
                    //keyboard display
                    whistle.play(-1);
                    
                    //Sets the focus on the piano keyboard display to prevent
                    //unwanted typing in the Recording File Name text field
                    //when the rest of the VST is in use
                    pianoSection.requestFocus();
                }
            };
            
            //sets the event fiter for the mouse release
        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, releaseKeyboard);

        //Sets and show the stage
        scene.setFill(Color.GREEN);
        stage.setScene(scene);
        stage.setMinWidth(820);
        stage.setMinHeight(645);
        stage.setTitle("Tin Whistle VST");
        stage.show();
    }

    /**
     *  Main method
     *  
     *  @param args - arguments
     */
    public static void main(String[] args){
        launch(args);
    } 

    /**
     *  Setter for the whistle key.
     *  
     *  @param key - the new key of the whistle
     */
    private void setKey(int key){
        whistleKey = key;
    }    

    /**
     *  Setter for the MIDI transpose boolean
     *  
     *  @param transpose - whether or not to transpose
     */
    private void toggleMIDITranspose(boolean transpose){
        transposeMidi = transpose;
    }

    /**
     *  Sets the keyboard to a new key when the whistle key is switched.
     */
    private void switchKeyboard(){
        group.getChildren().remove(pianoSection); // remove the existing piano section

        // generate a new piano section
        whiteKeyArray = keyGenerator.generateWhiteKeys(whistleKey);  
        blackKeyArray = keyGenerator.generateBlackKeys(whistleKey);        
        whiteNamesArray = keyGenerator.generateWhiteNames(whistleKey);
        blackNamesArray = keyGenerator.generateBlackNames(whistleKey);

        HBox whiteKeys = new HBox();
        for(int i = 0; i < whiteKeyArray.length; i++){
            whiteKeys.getChildren().add(whiteKeyArray[i]);
        }
        whiteKeys.setSpacing(1);

        HBox whiteNames = new HBox();
        for(int i = 0; i < whiteNamesArray.length; i++){
            whiteNames.getChildren().add(whiteNamesArray[i]);
        }
        whiteNames.setSpacing(26.5);
        whiteNames.setTranslateX(15);
        whiteNames.setTranslateY(-40);

        HBox blackKeys = new HBox();
        for (int i = 0; i < blackKeyArray.length; i++){
            blackKeys.getChildren().add(blackKeyArray[i]);
        }
        blackKeys.setSpacing(1);
        blackKeys.setTranslateX(20);

        HBox blackNames = new HBox();
        for(int i = 0; i < blackNamesArray.length; i++){
            blackNames.getChildren().add(blackNamesArray[i]);
        }
        blackNames.setSpacing(23.49);
        blackNames.setTranslateX(30);
        blackNames.setTranslateY(-105);

        pianoSection = new VBox(blackKeys, whiteKeys, blackNames, whiteNames);
        pianoSection.setSpacing(1);

        //add the piano section back into the group
        group.getChildren().add(pianoSection);
    }
}
