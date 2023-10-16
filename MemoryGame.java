import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


public class MemoryGame extends JFrame {
    private ArrayList<String> imagePaths;
    private ArrayList<String> cardImages;
    private JButton[] cardButtons;
    private int numberOfMatches;
    private int firstCardIndex = -1;
    private int secondCardIndex;

    private int gridRow;
    private int gridCol;
    private int gridButtons;
    private static boolean completionStatus = false;


    //JOptionPane to get user input on grid size. Code from JavaDoc https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
    Object[] possibleValues = { "Twelve", "Twenty-Four"};
    Object gridSize = JOptionPane.showInputDialog(null,
            "Choose your grid size", "Game Initialization",
            JOptionPane.QUESTION_MESSAGE, null,
            possibleValues, possibleValues[0]);

    public MemoryGame() {
        setTitle("Picture Memory Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imagePaths = new ArrayList<>();
        //add file paths to images
        switch (gridSize.toString()) {
            case "Twelve" -> {
                for (int i = 1; i < 7; i++) {
                    imagePaths.add("presidents/president" + i + ".png");
                    imagePaths.add("presidents/president" + i + ".png");

                }
                gridRow = 4;
                gridCol = 3;
                gridButtons = 12;
            }
            case "Twenty-Four" -> {
                for (int i = 1; i < 13; i++) {
                    imagePaths.add("presidents/president" + i + ".png");
                    imagePaths.add("presidents/president" + i + ".png");
                }
                gridRow = 4;
                gridCol = 6;
                gridButtons = 24;
            }
        }

        cardImages = new ArrayList<>();
        for (String imagePath : imagePaths) {
            cardImages.add("");
        }

        Collections.shuffle(imagePaths);
        Collections.shuffle(cardImages);



        JPanel cardPannel = new JPanel(new GridLayout(gridRow, gridCol));
        cardButtons = new JButton[gridButtons];

        for (int i = 0; i < cardButtons.length; i++) {
            final int index = i;
            cardButtons[i] = new JButton();
            cardButtons[i].setIcon(new ImageIcon("presidents/potusSeal.png"));
            cardButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCardClick(index);
                }
            });
            cardPannel.add(cardButtons[i]);
        }
    add(cardPannel);
    }


    private void handleCardClick(int index) {
        if (cardButtons[index].getIcon() == null) {
            return;
        }

        if (firstCardIndex == -1) {
            firstCardIndex = index;
            cardButtons[firstCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));
        } else {
            secondCardIndex = index;
            cardButtons[secondCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));
        }
            Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (imagePaths.get(firstCardIndex).equals(imagePaths.get(secondCardIndex))) {
                    cardButtons[firstCardIndex].setIcon(null);
                    cardButtons[secondCardIndex].setIcon(null);
                    cardImages.set(firstCardIndex, null);
                    cardImages.set(secondCardIndex, null);
                    numberOfMatches++;

                    if (numberOfMatches == imagePaths.size() / 2) {
                        JOptionPane.showMessageDialog(null, "Congratualtions, You Finished The Game!!!");
                        System.exit(0);

                    }
                } else {
                    cardButtons[firstCardIndex].setIcon(new ImageIcon("presidents/potusSeal.png"));
                    cardButtons[secondCardIndex].setIcon(new ImageIcon("presidents/potusSeal.png"));
                }
                firstCardIndex = -1;
            }
            });
            timer.setRepeats(false);
            timer.restart();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MemoryGame().setVisible(true);
            }
        });
    }

}
