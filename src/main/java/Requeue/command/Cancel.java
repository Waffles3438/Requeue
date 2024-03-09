package Requeue.command;

import Requeue.Requeue;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import net.minecraft.client.Minecraft;


/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see Requeue
 */
@Command(value = "cancel", description = "cancel requeue")
public class Cancel {
    @Main
    private void main() {
        Requeue.setReady(false);
        Minecraft.getMinecraft().ingameGUI.displayTitle("Canceled!", "",  1, 1, 1);
    }
}