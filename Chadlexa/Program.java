import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

public class Program {

    public static void main(String[] args) {
        AudioInputSelectorGUI.spawn();
        AudioInputSelectorGUI.onSave(gui -> {
            Mixer.Info info = AudioInputSelectorGUI.getSelectedMixerInfo();
            Mixer mixer = AudioSystem.getMixer(info);
            TargetDataLine line;
            try {
                line = (TargetDataLine) AudioUtils.getMixerLine(mixer);
                line.open();
            } catch (Exception e) { 
                e.printStackTrace();
            }
            
        });
    }

}
