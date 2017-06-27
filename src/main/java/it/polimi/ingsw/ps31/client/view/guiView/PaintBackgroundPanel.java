package it.polimi.ingsw.ps31.client.view.guiView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by giulia on 27/06/2017.
 */
public class PaintBackgroundPanel extends JPanel {
    BufferedImage backgroundPanel;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Dimension dimension = getSize();
        g.drawImage(backgroundPanel,0,0,dimension.width,dimension.height,0,0,backgroundPanel.getWidth(),backgroundPanel.getHeight(),null);
    }

    public void ImageToLoad(String stringPath){
        URL path = getClass().getResource(stringPath);
        BufferedImage resizedImage = null;
        try{
            resizedImage = ImageIO.read(path);
        }catch (IOException e){
            System.err.println("Errore");
        } backgroundPanel=resizedImage;
    }
}
