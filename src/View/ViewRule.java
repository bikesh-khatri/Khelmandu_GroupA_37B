/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ViewRule extends JFrame {
    private HashMap<String, String> gameRules;
    private JComboBox<String> gameSelector;
    private JTextArea ruleDisplay;

    public ViewRule() {
        setTitle("Khelmandu - Indoor Games Rules");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize game rules
        gameRules = new HashMap<>();
        gameRules.put("Futsal", "Team Size: 5 players, Match Duration: 2 x 30 min...");
        gameRules.put("Badminton", "Scoring System: Best of 3 games, First to 21 wins...");
        gameRules.put("Indoor Cricket", "Team Size: 5-7 players, Modified bowling styles...");
        gameRules.put("Basketball", "Team Size: 5 players, Scoring: Field goal = 2 points...");
        gameRules.put("Table Tennis", "Scoring: Best of 5 or 7 games, Each game up to 11 points...");

        // GUI Components
        gameSelector = new JComboBox<>(gameRules.keySet().toArray(new String[0]));
        ruleDisplay = new JTextArea(10, 40);
        ruleDisplay.setEditable(false);

        JButton showRulesBtn = new JButton("Show Rules");
        showRulesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGame = (String) gameSelector.getSelectedItem();
                ruleDisplay.setText(gameRules.get(selectedGame));
            }
        });

        // Layout
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select a game: "));
        topPanel.add(gameSelector);
        topPanel.add(showRulesBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(ruleDisplay), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewRule();
    }
}