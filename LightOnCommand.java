/** From Head First Design Patterns, Freeman & Robson et al. */  

public class LightOnCommand extends Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }
    
    public void undo()
    {
        light.off();
    }
}
