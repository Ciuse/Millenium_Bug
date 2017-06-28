package it.polimi.ingsw.ps31.client.view.guiView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by giulia on 27/06/2017.
 */
public class PaintBackgroundPanel extends JPanel {
    private BufferedImage backgroundPanel;
    private BufferedImage backgroundJbutton;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
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

    public void paintJButton(JButton jButton,String string) {
        BufferedImage resizedImage = null;
        try{
            resizedImage = ImageIO.read(getClass().getResource(string));
        }catch (IOException e){
            System.err.println("Errore");
        }backgroundJbutton=resizedImage;
        ImageIcon imageIcon = new ImageIcon(resizedImage);
         jButton = new JButton(imageIcon);
    }
}
