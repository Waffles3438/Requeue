package AutoDodge.config;

import AutoDodge.AutoDodge;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Button;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Text;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import cc.polyfrost.oneconfig.renderer.TinyFD;
import cc.polyfrost.oneconfig.utils.Notifications;

import java.io.File;

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class TestConfig extends Config {

    @Text(
            name = "Requeue command",
            placeholder = "/rq",        // optional, text to display when there is nothing written there
            secure = false, multiline = false,
            size = OptionSize.DUAL
    )
    public static String rq = "/rq";

    @Slider(
            name = "Requeue delay",
            min = 0f, max = 10f,        // min and max values for the slider
            // if you like, you can use step to set a step value for the slider,
            // giving it little steps that the slider snaps to.
            step = 1
    )
    public static float delay = 5f; // default value

    @Button(name = "latest.log location",
            text = "Browse",
            size = OptionSize.DUAL)
    public Runnable logButton = this::browse;

    private void notify(String message) {
        Notifications.INSTANCE.send("AutoDodge", message);
    }

    private boolean refreshed = false;
    public static String LogPath = "";
    private void browse() {
        notify("A file dialogue has opened. You may need to tab out to see it.");

        File result = TinyFD.INSTANCE.openFileSelector(
                "Select a log file",
                "",
                new String[]{"*.log"},
                "Latest log file"
        );

        if (result == null) {
            notify("You must select a latest.log file");
            return;
        }

        String LogPath = result.getAbsolutePath();
        if (!LogPath.endsWith(".log")) {
            notify("You must select a .log file");
            return;
        }

        this.LogPath = LogPath;
        refreshed = false;
        notify("You have selected a new latest.log.");
    }

    public TestConfig() {
        super(new Mod(AutoDodge.NAME, ModType.UTIL_QOL), AutoDodge.MODID + ".json");
        initialize();
    }
}

