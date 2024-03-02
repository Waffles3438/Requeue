package AutoDodge.command;

import AutoDodge.AutoDodge;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see AutoDodge
 */
@Command(value = AutoDodge.MODID, description = "Access the " + AutoDodge.NAME + " GUI.")
public class ExampleCommand {
    @Main
    private void handle() {
        AutoDodge.INSTANCE.config.openGui();
    }
}