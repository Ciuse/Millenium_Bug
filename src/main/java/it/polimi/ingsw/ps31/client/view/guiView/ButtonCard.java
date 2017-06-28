package it.polimi.ingsw.ps31.client.view.guiView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by giulia on 28/06/2017.
 */
public class ButtonCard extends JButton {
    private String string;
    private BufferedImage backgroundPanel;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        imageToLoad(string);
        Dimension dimension = getSize();
        g.drawImage(backgroundPanel,0,0,dimension.width,dimension.height,0,0,backgroundPanel.getWidth(),backgroundPanel.getHeight(),null);
    }

    public void imageToLoad(String stringPath){
        BufferedImage resizedImage = null;
        try{
            resizedImage = ImageIO.read(getClass().getResource(stringPath));
        }catch (IOException e){
            System.err.println("Errore");
        } backgroundPanel=resizedImage;
    }

    public ButtonCard(String string) {
        this.string = string;
        imageToLoad(string);
    }


    public String getString() {
        return string;
    }
}
