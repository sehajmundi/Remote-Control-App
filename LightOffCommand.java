/** From Head First Design Patterns, Freeman & Robson et al. */  

public class LightOffCommand extends Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }
    
    public void undo()
    {
        light.on();
    }
}
