/** From Head First Design Patterns, Freeman & Robson et al. */  

public class StereoOnWithCDCommand extends Command {
    private Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(6);
    }
    
    public void undo()
    {
        stereo.off();
    }
}
