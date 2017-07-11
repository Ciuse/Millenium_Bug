package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by giulia on 28/06/2017.
 * classe che mi permette di creare un JButton in cui si possa caricare un'immagine come sfondo
 */
public class ButtonCard extends JButton {
    private ActionListener listener;
    private String stringImage;
    private BufferedImage backgroundPanel;
    private String nameButton;

    /* Constructor */
    public ButtonCard(String nameButton) {
        super(nameButton);
    }
    /* Constructor */
    public ButtonCard() {
        this.stringImage = stringImage;
    }

    /**
     * Metodo che mi permette di disegnare il JButton con un'immagine che gli passo tramite i due possibili metodi
     * imageToLoad o reprintImage
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = getSize();
        if (backgroundPanel != null) {
            g.drawImage(backgroundPanel, 0, 0, dimension.width, dimension.height, 0, 0, backgroundPanel.getWidth(), backgroundPanel.getHeight(), null);
        }
    }

    /**
     * Metodo che mi permette di caricare un'immagine che verrà poi utilizzata dal paintComponent
     */
    public void imageToLoad(String stringPath){
        BufferedImage resizedImage = null;
        try{
            resizedImage = ImageIO.read(getClass().getResource(stringPath));
        }catch (IOException e){
            System.err.println("Errore");
        } backgroundPanel=resizedImage;
    }


    /**
     * Metodo che mi permette di caricare un'immagine che verrà poi utilizzata dal paintComponent
     */
    public void imageToReprint(String stringImage) {
        this.stringImage = stringImage;
        imageToLoad(stringImage);
        repaint();
        revalidate();
    }

    public String getStringImage() {
        return stringImage;
    }

    public BufferedImage getBackgroundPanel() {
        return backgroundPanel;
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

}
