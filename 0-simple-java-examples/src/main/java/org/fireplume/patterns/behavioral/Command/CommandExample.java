package org.fireplume.patterns.behavioral.Command;

/**
 * Created by cloudjumper on 8/16/16.
 */
public class CommandExample {
    public static void main(String... args) {
        Light l = new Light();
        Command switchUp = new TurnOnLightCommand(l);
        Command switchDown = new TurnOffLightCommand(l);

        Switch s = new Switch(switchUp, switchDown);

        s.flipUp();
        s.flipDown();
    }
}

interface Command {
    void execute();
}

class Switch {
    private Command flipUpCommand;
    private Command flipDownCommand;

    public Switch(Command flipUpCommand, Command flipDownCommand) {
        this.flipUpCommand = flipUpCommand;
        this.flipDownCommand = flipDownCommand;
    }

    public void flipUp() {
        this.flipUpCommand.execute();
    }

    public void flipDown() {
        this.flipDownCommand.execute();
    }
}

class Light {
    public Light() {

    }

    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}

class TurnOnLightCommand implements Command {
    private Light theLight;

    public TurnOnLightCommand(Light light) {
        this.theLight = light;
    }

    @Override
    public void execute() {
        this.theLight.turnOn();
    }
}

class TurnOffLightCommand implements Command {
    private Light theLight;

    public TurnOffLightCommand(Light light) {
        this.theLight = light;
    }

    @Override
    public void execute() {
        theLight.turnOff();
    }
}
