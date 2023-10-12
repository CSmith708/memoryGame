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
    private int firstCardIndex;
    private int secondCardIndex;

    public MemoryGame() {
        setTitle("Picture Memory Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imagePaths = new ArrayList<>();
        //add file paths to images
        imagePaths.add("presidents/president1.png");
        imagePaths.add("presidents/president2.png");
        imagePaths.add("presidents/president3.png");
        imagePaths.add("presidents/president4.png");
        imagePaths.add("presidents/president5.png");
        imagePaths.add("presidents/president6.png");
        imagePaths.add("presidents/president7.png");
        imagePaths.add("presidents/president8.png");
        imagePaths.add("presidents/president9.png");
        imagePaths.add("presidents/president10.png");
        imagePaths.add("presidents/president11.png");
        imagePaths.add("presidents/president12.png");
        imagePaths.add("presidents/president1.png");
        imagePaths.add("presidents/president2.png");
        imagePaths.add("presidents/president3.png");
        imagePaths.add("presidents/president4.png");
        imagePaths.add("presidents/president5.png");
        imagePaths.add("presidents/president6.png");
        imagePaths.add("presidents/president7.png");
        imagePaths.add("presidents/president8.png");
        imagePaths.add("presidents/president9.png");
        imagePaths.add("presidents/president10.png");
        imagePaths.add("presidents/president11.png");
        imagePaths.add("presidents/president12.png");

        cardImages = new ArrayList<>();
        for (String imagePath : imagePaths) {
            cardImages.add("");
        }

        Collections.shuffle(imagePaths);
        Collections.shuffle(cardImages);

        JPanel cardPannel = new JPanel(new GridLayout(4, 6));
        cardButtons = new JButton[24];

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

        Timer timer = new Timer(500, new ActionListener() {
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
                    } else {
                        cardButtons[firstCardIndex].setIcon(new ImageIcon("presidents/potusSeal.png"));
                        cardButtons[secondCardIndex].setIcon(new ImageIcon("presidents/potusSeal.png"));
                    }
                    firstCardIndex = -1;
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
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