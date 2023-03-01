/** From Head First Design Patterns, Freeman & Robson et al. */  

public class StereoOffCommand extends Command {
    private Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.off();
    }
    
    public void undo()
    {
        stereo.on();
    }
}
