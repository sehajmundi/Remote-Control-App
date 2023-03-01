/**
 * ACS-3913 Assignment 4
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
public class RemoteControlApp extends Application{
    private Label[] labels;                             
    RemoteControl remoteControl;

    /** Loads and sets up the remote control */
    public void loadRemote(){
        Light LivingRoomLight = new Light("Living Room");
        Stereo stereo = new Stereo("Living Room");
        Hottub hottub = new Hottub();
        CeilingFan ceilingFan = new CeilingFan("Bed Room");

        LightOnCommand LivingRoomLightOn = 
            new LightOnCommand(LivingRoomLight);
        LightOffCommand LivingRoomLightOff = 
            new LightOffCommand(LivingRoomLight);

        StereoOnWithCDCommand stereoOnWithCD =
            new StereoOnWithCDCommand(stereo);
        StereoOffCommand  stereoOff =
            new StereoOffCommand(stereo);
            
        HottubOnCommand hottubOn = new HottubOnCommand(hottub);
        HottubOffCommand hottubOff = new HottubOffCommand(hottub);    
        
        CeilingFanHighCommand high = new CeilingFanHighCommand(ceilingFan);
        CeilingFanMediumCommand medium = new CeilingFanMediumCommand(ceilingFan);
        CeilingFanLowCommand low = new CeilingFanLowCommand(ceilingFan);
        CeilingFanOffCommand off = new CeilingFanOffCommand(ceilingFan);
        
        StereoOnWithRadioCommand stereoOnWithRadio =
            new StereoOnWithRadioCommand(stereo);
        StereoOffCommand  stereoOffWithRadio =
            new StereoOffCommand(stereo);
            
        StereoOnWithCDCommand stereoOnWithCDLoud =
            new StereoOnWithCDCommand(stereo);
        StereoOffCommand  stereoOffLoud =
            new StereoOffCommand(stereo);    
        
        Command[] partyOn = {LivingRoomLightOn, stereoOnWithCD, low};
        Command[] partyOff = {LivingRoomLightOff, stereoOff, low};
        
        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);
        
        setRemoteCommand(0, LivingRoomLightOn, LivingRoomLightOff, "Living Room Light");
        setRemoteCommand(1, stereoOnWithRadio, stereoOffWithRadio, "Stereo With Radio");
        setRemoteCommand(2, stereoOnWithCD, stereoOff, "Stereo With CD");
        setRemoteCommand(3, medium, off, "Ceiling Fan Medium");
        setRemoteCommand(4, low, off, "Ceiling Fan Low");
        setRemoteCommand(5, partyOnMacro, partyOffMacro, "Work Mode");
        
        System.out.println("\n------ Remote Control App Sim -------\n");
    }

    /** Sets the command and label text */
    private void setRemoteCommand(int slot, Command onCommand, Command offCommand, 
    String description){
        remoteControl.setCommand(slot, onCommand, offCommand);
        labels[slot].setText(description);                 
    }

    /** The main entry point for the application **/
    public void start(Stage stage) throws FileNotFoundException,IOException {     
        Configuration config = new Configuration();
        final int NUM_COMMANDS = config.getNumberOfOptions();
        final int REMOTE_HEIGHT = config.getHeight();
        final int REMOTE_WIDTH = 250;

        remoteControl = new RemoteControl();

        // Create the buttons and labels
        Label onLabel = new Label("on");
        Label offLabel = new Label("off");
        labels = new Label[NUM_COMMANDS];
        Button[] onButtons = new Button[NUM_COMMANDS];  
        Button[] offButtons = new Button[NUM_COMMANDS];
        Button undoButton = new Button("UNDO");
        Button redoButton = new Button("REDO");     

        for (int i=0; i<NUM_COMMANDS; i++){
            onButtons[i] = new Button(" ");
            offButtons[i] = new Button(" ");  
            labels[i] = new Label("no command");
        }

        // Create a new grid pane 
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(230, 400);
        pane.setVgap(20);
        pane.setHgap(10);

        // Set actions on the buttons using lambda expressions
        for (int i=0; i<NUM_COMMANDS; i++){
            final int I = i;
            
            onButtons[i].setOnAction((ActionEvent event) -> {
                    remoteControl.onButtonWasPushed(I);
                });
            offButtons[i].setOnAction((ActionEvent event) -> {
                    remoteControl.offButtonWasPushed(I);
                });}
                
        

        // Set actions on the buttons using method reference
        undoButton.setOnAction(this::undoButtonClick);
        redoButton.setOnAction(this::redoButtonClick);

        // Add the buttons and labels into the pane
        pane.add(onLabel, 1, 0);
        pane.setHalignment(onLabel, HPos.CENTER);
        pane.add(offLabel, 2, 0);
        pane.setHalignment(offLabel, HPos.CENTER);
        for (int i=0; i<NUM_COMMANDS; i++){
            pane.add(labels[i], 0, i+1);
            labels[i].setMinWidth(100);
            pane.add(onButtons[i], 1, i+1);
            pane.add(offButtons[i], 2, i+1);
            pane.setHalignment(onButtons[i], HPos.CENTER);
            pane.setHalignment(offButtons[i], HPos.CENTER);
        }
        pane.add(undoButton, 1, NUM_COMMANDS + 1);
        pane.add(redoButton, 2, NUM_COMMANDS + 1); 

        // Set the pane to a scene and stage, show stage
        Scene scene = new Scene(pane, 250,400);
        stage.setTitle("Command Remote");
        stage.setScene(scene);
        stage.show();

        // Load the remote
        loadRemote();
    
    }
    /** Executes when the undoButton is clicked */
    private void undoButtonClick(ActionEvent event){
        // enter code here...
        
        remoteControl.undoButtonWasPushed();
        
    }

    /** Executes when the redoButton is clicked */
    private void redoButtonClick(ActionEvent event){
        // enter code here...
        
        remoteControl.redoButtonWasPushed();
    
    
    }

    /** Executes when the application stops */
    public void stop(){
        // enter code here...
    }
}
