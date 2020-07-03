package ui;

import com.company.Match;
import com.company.Player;
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
    private JLabel player;

    public Panel(Match match, JTextField textField) {
        try {
            image = ImageIO.read(new File("/Users/vitorporto/IdeaProjects/Risk/images/Risk_game_map_fixed_with_names.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("image path not found");
        }
        this.match = match;
        match.setPanel(this);
        this.textField = textField;
        add(new JLabel("Current Player:"));
        player = new JLabel("");
        add(player);
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

    public void refresh() {
        paintComponent(this.getGraphics());
    }

    public void setPlayer(Player player) {
        Color color = player.getColor();
        if (color == Color.black){
            this.player.setText("Black");
        } else if (color == Color.white){
            this.player.setText("White");
        } else if (color == Color.red){
            this.player.setText("Red");
        } else if (color == Color.blue){
            this.player.setText("Blue");
        } else if (color == Color.green){
            this.player.setText("Green");
        } else if (color == Color.yellow){
            this.player.setText("Yellow");
        }
    }
}
