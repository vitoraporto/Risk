package com.company;

import ui.Panel;

import javax.swing.*;
import java.util.Scanner;

public class Risk extends JFrame {

    private Risk(Match match){
        initUI(match);
        setVisible(true);
        //todo: delete
        //match.play();
    }

    private void initUI(Match match) {

        JTextField textField = new JTextField(8);

        Panel panel = new Panel(match, textField);

        add(panel);

        setSize(1200, 900);

        setTitle("Risk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        System.out.println("How many players?");
        Scanner in = new Scanner(System.in);
        int numPlayers = in.nextInt();
        if (numPlayers > 2 && numPlayers <= 6) {
            try {
                Risk risk = new Risk(new Match(numPlayers));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("invalid number of players");
        }
    }
}
