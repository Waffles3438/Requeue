package AutoDodge;

import AutoDodge.config.TestConfig;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import cc.polyfrost.oneconfig.libs.universal.UChat;
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
@Mod(modid = AutoDodge.MODID, name = AutoDodge.NAME, version = AutoDodge.VERSION)
public class AutoDodge {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)
    public static AutoDodge INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static TestConfig config;

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new TestConfig();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        String line = getLastLineOfFile(TestConfig.LogPath);
        line = line.substring(11);
        line = removeLastWords(line, 44);
        if(line.equals("[Client thread/INFO]: [CHAT] Lilith > Dodged")){
            UChat.say(TestConfig.rq);
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
}
