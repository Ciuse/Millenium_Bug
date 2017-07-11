package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

/**
 * Created by giulia on 11/07/2017.
 */
public class Music {
    private GuiView guiView;

    public Music(GuiView guiView) {
        this.guiView = guiView;
    }

    public void startMusic() throws Exception {
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(new FileInputStream("/Il Trono di Spade 1 - Game of Thrones 1 -- Sigla iniziale - Main theme.wav"));
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Close to exit");
            }
        });
    }

}
