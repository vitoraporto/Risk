package ui;

import com.company.Match;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

public class Panel extends JPanel {
    private Match match;
    private JTextField textField;
    private BufferedImage image;

    public Panel(Match match, JTextField textField) {
        try {
            image = ImageIO.read(new File("/Users/vitorporto/IdeaProjects/Risk/images/Risk_game_map_fixed_with_names.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("image path not found");
        }
        this.match = match;
        this.textField = textField;
        add(textField);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }
}
