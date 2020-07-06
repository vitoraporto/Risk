package ui;

import com.company.Match;
import com.company.Phase;
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
    private JLabel question;
    private JTextField textField;
    private JButton button = new JButton("Enter");
    private BufferedImage image;
    private JLabel player;
    private JLabel explanation;
    private JLabel phase;

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
        setPlayer(match.getCurrPlayer());
        phase = new JLabel("");
        add(phase);
        explanation = new JLabel("");
        add(explanation);
        question = new JLabel("Input: ");
        add(question);
        add(textField);
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        match.processInput(textField.getText());
        refresh();
    }

    private void refresh() {
        paintComponent(this.getGraphics());
    }

    public void setAllLabels(Phase p, Player player){
        setPlayer(player);
        setPhaseAndQuestion(p);
        setExplanation(player, p);
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

    private void setPlayer(Player player) {
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

    private void setQuestion(String s) {
        question.setText(s);
    }

    private void setExplanation(String s){
        explanation.setText(s);
    }

    private void setPhase(String s) {
        phase.setText(s);
    }

    private void setExplanation(Player player, Phase p) {
        if (p == Phase.initialPlaceArmiesCountry || p == Phase.initialPlaceArmiesNumber || p == Phase.PlaceArmiesCountry || p == Phase.PlaceArmiesNumber){
            setExplanation("You have " + player.getArmiesAvailable() + " armies available");
        } else {
            setExplanation(""); //todo later
        }
    }

    private void setPhaseAndQuestion(Phase p) {
        String s = "";
        String question = "";
        if (p == Phase.initialPlaceArmiesCountry){
            s = "Initial place armies:";
            question = "Where to place armies?";
        } else if (p == Phase.initialPlaceArmiesNumber){
            s = "Initial place armies:";
            question = "How many armies do you want to place at that territory?";
        } else if (p == Phase.PlaceArmiesCountry){
            s = "Place armies:";
            question = "Where to place armies?";
        } else if (p == Phase.PlaceArmiesNumber){
            s = "Place armies:";
            question = "How many armies do you want to place at that territory?";
        } else if (p == Phase.AttackingFrom){
            s = "Attacking:";
            question = "Where do you want to attack from? (press enter with input empty to fortify)";
        } else if (p == Phase.AttackingNo){
            s = "Attacking:";
            question = "How many armies are you using?";
        } else if (p == Phase.AttackingTo){
            s = "Attacking:";
            question = "Where do you want to attack?";
        } else if (p == Phase.FortifyingFrom){
            s = "Fortifying:";
            question = "Where to transfer armies from?";
        } else if (p == Phase.FortifyingNo){
            s = "Fortifying:";
            question = "How many armies to transfer?";
        } else if (p == Phase.FortifyingTo){
            s = "Fortifying:";
            question = "Where to transfer armies?";
        }
        setPhase(s);
        setQuestion(question);
    }
}
