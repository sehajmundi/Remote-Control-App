/** From Head First Design Patterns, Freeman & Robson et al. */  

public class StereoOnWithCDLoudCommand extends Command {
    private Stereo stereo;

    public StereoOnWithCDLoudCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }
    
    public void undo()
    {
        stereo.off();
    }
}
