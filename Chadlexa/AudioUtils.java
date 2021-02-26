import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class AudioUtils {

    @FunctionalInterface
    public static interface Void {
        void dispose();
    }

    public static ArrayList<Mixer.Info> getMixerInfo() {  
        return new ArrayList<Mixer.Info>(Arrays.asList(AudioSystem.getMixerInfo()));
    }

    public static <T> List<T> mapMixerInfo(Function<? super Mixer.Info, ? extends T> function) {
        return getMixerInfo().stream().map(function).collect(Collectors.toList());
    }

    public static <T> List<T> mapMixerInfo(ArrayList<Mixer.Info> info, Function<? super Mixer.Info, ? extends T> function) {
        return info.stream().map(function).collect(Collectors.toList());
    }

    public static Line getMixerLine(Mixer mixer) throws LineUnavailableException {
        return mixer.getLine(mixer.getLineInfo());
    }

}
