import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class AudioUtils {

    @FunctionalInterface
    public static interface Void {
        void dispose();
    }

    public static List<Mixer.Info> getMixerInfo() {  
        return new ArrayList<Mixer.Info>(Arrays.asList(AudioSystem.getMixerInfo()));
    }

    public static List<Mixer> getMixers() {
        return getMixerInfo().stream().map(mixer -> getMixer(mixer)).collect(Collectors.toList());
    }

    public static <T> List<T> mapMixerInfo(Function<? super Mixer.Info, ? extends T> function) {
        return getMixerInfo().stream().map(function).collect(Collectors.toList());
    }

    public static <T> List<T> mapMixerInfo(ArrayList<Mixer.Info> info, Function<? super Mixer.Info, ? extends T> function) {
        return info.stream().map(function).collect(Collectors.toList());
    }

    public static Mixer getMixer(Mixer.Info info) {
        return AudioSystem.getMixer(info);
    }

    public static Line getMixerLine(Mixer mixer) throws LineUnavailableException {
        return mixer.getLine(mixer.getLineInfo());
    }

    public static Line.Info getMixerLineInfo(Mixer mixer) {
        return mixer.getLineInfo();
    }

    public static ActionListener createActionListener(Consumer<ActionEvent> perform) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perform.accept(e);
            }     
        };
    }   
    
    public static ActionListener createActionListener(Void perform) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perform.dispose();
            }     
        };
    }

}