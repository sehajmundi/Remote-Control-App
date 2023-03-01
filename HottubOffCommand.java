/** From Head First Design Patterns, Freeman & Robson et al. */ 

public class HottubOffCommand extends Command {
    Hottub hottub;

    public HottubOffCommand(Hottub hottub) {
        this.hottub = hottub;
    }

    public void execute() {
        hottub.setTemperature(98);
        hottub.off();
    }

    public void undo() {
        hottub.on();
    }
}
