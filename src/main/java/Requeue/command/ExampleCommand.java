package Requeue.command;

import Requeue.Requeue;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see Requeue
 */
@Command(value = Requeue.MODID, description = "Access the " + Requeue.NAME + " GUI.")
public class ExampleCommand {
    @Main
    private void handle() {
        Requeue.INSTANCE.config.openGui();
    }
}