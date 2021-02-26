import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.sound.sampled.Mixer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AudioInputSelectorGUI {

    public static AudioInputSelectorGUI singleton;

    private JFrame window;
    private JPanel panel;
    private JLabel label;
    private JComboBox<String> deviceSelection;

    private List<Consumer<AudioInputSelectorGUI>> disposers = new ArrayList<>();

    private void init() {
        if (singleton == null)
        singleton = this;

        singleton.window = new JFrame("Audio input device selector");
        singleton.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        singleton.window.setVisible(true);
        singleton.window.setSize(400, 200);

        singleton.panel = new JPanel();
        singleton.panel.setLayout(new BoxLayout(singleton.panel, BoxLayout.Y_AXIS));
        singleton.window.add(singleton.panel);

        singleton.label = new JLabel("Select your preferred audio input device");
        singleton.label.setAlignmentX(Component.CENTER_ALIGNMENT);
        singleton.panel.add(singleton.label);

        List<String> mixers = AudioUtils.mapMixerInfo(mixer -> mixer.getName());

        String[] mixerNames = new String[mixers.size()];
        for (int i = 0; i < mixers.size(); i++)
            mixerNames[i] = mixers.get(i);

        singleton.deviceSelection = new JComboBox<String>(mixerNames);
        singleton.deviceSelection.setMaximumSize(singleton.deviceSelection.getPreferredSize());
        singleton.deviceSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
        singleton.panel.add(singleton.deviceSelection);

        final JButton save = new JButton("Save");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AudioInputSelectorGUI.dispose();
            }     
        });

        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        singleton.panel.add(save);
    }

    public static AudioInputSelectorGUI spawn() {
        AudioInputSelectorGUI gui = new AudioInputSelectorGUI();
        gui.init();
        return gui;
    }

    public static void dispose() {
        for (Consumer<AudioInputSelectorGUI> disposer : singleton.disposers) 
            disposer.accept(singleton);
        AudioInputSelectorGUI.singleton.window.dispose();
    }

    public static void onSave(Consumer<AudioInputSelectorGUI> consumer) {
        singleton.disposers.add(consumer);
    }

    public static String getSelectedAudioDevice() {
        return (String) singleton.deviceSelection.getSelectedItem();
    }

    public static Mixer.Info getSelectedMixerInfo() {
        return AudioUtils.getMixerInfo().stream().filter(mixer -> mixer.getName().equals(getSelectedAudioDevice())).collect(Collectors.toList()).get(0);
    }

}
