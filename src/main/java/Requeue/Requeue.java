package Requeue;

import Requeue.config.TestConfig;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.utils.Notifications;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = Requeue.MODID, name = Requeue.NAME, version = Requeue.VERSION)
public class Requeue {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)
    public static Requeue INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static TestConfig config;

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new TestConfig();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private boolean ready = false;
    private int counter = 0;
    private int b = TestConfig.intTest;
    private int origin = b;
    private int a = TestConfig.delay;
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        if(!TestConfig.LogPath.isEmpty()){
            String line = getLastLineOfFile(TestConfig.LogPath);
            if(line != null){
                line = line.substring(11);
                if(line.equals("[Client thread/INFO]: [CHAT] Lilith > Dodged")){
                    ready = true;
                }
            }
            if(ready){
                Minecraft.getMinecraft().ingameGUI.displayTitle(String.valueOf(b), "",  1, 1, 1);
                counter++;
                if(counter == 40) {
                    b--;
                    counter = 0;
                }
                if(b == 0){
                    UChat.say(TestConfig.rq);
                    b = origin;
                    counter = 0;
                    ready = false;
                }
            }
        } else if (TestConfig.LogPath.isEmpty()) {
            notify("You must select a latest.log!");
        }
    }

    public static String getLastLineOfFile(String filePath) {
        String lastLine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }

    public static String removeLastWords(String originalString, int maxLength) {
        if (originalString.length() <= maxLength) {
            return originalString;
        } else {
            int lastSpaceIndex = originalString.lastIndexOf(' ', maxLength);
            if (lastSpaceIndex != -1) {
                return originalString.substring(0, lastSpaceIndex);
            } else {
                return ""; // Handle case where no space found
            }
        }
    }

    private void notify(String message) {
        Notifications.INSTANCE.send("Requeue", message);
    }
}
