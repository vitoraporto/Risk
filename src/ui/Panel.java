package ui;

import com.company.Match;
import com.company.Territory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener {
    private Match match;
    private JLabel textLable;
    private JTextField textField;
    private JButton button = new JButton("Enter");
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
        textLable = new JLabel("Input: ");
        add(textLable);
        add(textField);
        button.addActionListener(this);
        add(button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 100, this); // see javadoc for more info on the parameters
        drawCircles(g);
    }

    private void drawCircles(Graphics g) {
        Collection<Territory> territories = match.getBoard().getTerritories();
        for (Territory territory:territories){
            drawCircle(g,territory);
        }
    }

    private void drawCircle(Graphics g, Territory territory) {
        int latitude = territory.getLat();
        int longitude = territory.getLon();
        int armies = territory.getArmies();
        Color color = territory.getOwner().getColor();
        g.setColor(color);
        g.fillOval(1200 - longitude,700 - latitude,50,50);
        g.setColor(Color.cyan);
        g.drawString(Integer.toString(armies),1225 - longitude,725 - latitude);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(textField.getText());
    }
}
