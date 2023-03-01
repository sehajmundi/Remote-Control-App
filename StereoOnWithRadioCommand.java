/** From Head First Design Patterns, Freeman & Robson et al. */  

public class StereoOnWithRadioCommand extends Command {
    private Stereo stereo;

    public StereoOnWithRadioCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.on();
        stereo.setRadio();
        stereo.setVolume(6);
    }
    
    public void undo()
    {
        stereo.off();
    }
}
