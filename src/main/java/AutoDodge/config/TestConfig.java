package AutoDodge.config;

import AutoDodge.AutoDodge;
import AutoDodge.hud.TestHud;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class TestConfig extends Config {

    @Text(
            name = "Requeue command",
            placeholder = "/rq",        // optional, text to display when there is nothing written there
            secure = false, multiline = false
    )
    public static String rq = "/rq";

    @Text(
            name = "latest.log file location",
            placeholder = "latest.log",        // optional, text to display when there is nothing written there
            secure = false, multiline = false, size = OptionSize.DUAL
    )
    public static String log = "";

    @Slider(
            name = "Requeue delay",
            min = 0f, max = 10f,        // min and max values for the slider
            // if you like, you can use step to set a step value for the slider,
            // giving it little steps that the slider snaps to.
            step = 1
    )
    public static float delay = 5f; // default value

    public TestConfig() {
        super(new Mod(AutoDodge.NAME, ModType.UTIL_QOL), AutoDodge.MODID + ".json");
        initialize();
    }
}

