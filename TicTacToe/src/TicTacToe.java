import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class TicTacToe implements MouseListener {

    private JFrame frame = new JFrame();
    private JPanel panel;
    private JButton buttonLogin;
    private JTextField userText;
    private JPasswordField pwordText;
    private JTextField success;
    private JTextField gameComments;

    private JButton caseOne;
    private JButton caseTwo;
    private JButton caseThree;
    private JButton caseFour;
    private JButton caseFive;
    private JButton caseSix;
    private JButton caseSeven;
    private JButton caseEight;
    private JButton caseNine;


    public TicTacToe() {
        panel = new JPanel();
        panel.setLayout(null);


        JLabel userLabel = new JLabel("Identifiant");
        userLabel.setBounds(10, 70, 80,25);
        panel.add(userLabel);

        userText = new JTextField(50);
        userText.setBounds(100, 70, 165, 25);
        panel.add(userText);


        JLabel pwordLabel = new JLabel("Mot de passe");
        pwordLabel.setBounds(10, 200, 80,25);
        panel.add(pwordLabel);

        pwordText = new JPasswordField();
        pwordText.setBounds(100, 200, 165, 25);
        panel.add(pwordText);



        buttonLogin = new JButton("Log in");
        buttonLogin.setBounds(65, 300, 180, 30);
        buttonLogin.addMouseListener(this);
        panel.add(buttonLogin);

        success = new JTextField();
        success.setBounds(45, 350, 180, 30);



        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe - login");
        frame.setSize(350, 500);



        frame.setVisible(true);
    }

    public TicTacToe(Integer intOne) {
        panel = new JPanel();

        panel.setLayout(null);

        gameComments = new JTextField();
        gameComments.setBounds(200, 550, 180, 50);


        caseOne = new JButton("1");
        caseOne.setBounds(100, 50, 100, 100);
        caseOne.addMouseListener(this);
        panel.add(caseOne);

        caseTwo = new JButton("2");
        caseTwo.setBounds(225, 50, 100, 100);
        caseTwo.addMouseListener(this);
        panel.add(caseTwo);

        caseThree = new JButton("3");
        caseThree.setBounds(350, 50, 100, 100);
        caseThree.addMouseListener(this);
        panel.add(caseThree);

        caseFour = new JButton("4");
        caseFour.setBounds(100, 175, 100, 100);
        caseFour.addMouseListener(this);
        panel.add(caseFour);

        caseFive = new JButton("5");
        caseFive.setBounds(225, 175, 100, 100);
        caseFive.addMouseListener(this);
        panel.add(caseFive);

        caseSix = new JButton("6");
        caseSix.setBounds(350, 175, 100, 100);
        caseSix.addMouseListener(this);
        panel.add(caseSix);

        caseSeven = new JButton("7");
        caseSeven.setBounds(100, 300, 100, 100);
        caseSeven.addMouseListener(this);
        panel.add(caseSeven);

        caseEight = new JButton("8");
        caseEight.setBounds(225, 300, 100, 100);
        caseEight.addMouseListener(this);
        panel.add(caseEight);

        caseNine = new JButton("9");
        caseNine.setBounds(350, 300, 100, 100);
        caseNine.addMouseListener(this);
        panel.add(caseNine);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe - Let's play");
        frame.setSize(550, 650);
        frame.setLocationRelativeTo(null);


        frame.setVisible(true);
    }




    public static void main(String[] args) {
        new TicTacToe();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        JButton b = null;
        String buttonText = "";

        if(o instanceof JButton)
            b = (JButton)o;

        if(b != null)
            buttonText = b.getText();

        int buttonClick = e.getButton();

        switch (buttonText) {
            case "Log in":
                String userInput = userText.getText();
                String pwordInput = String.valueOf(pwordText.getPassword());

                if (userInput.equals("a") && pwordInput.equals("a")) {
                    success.setText("LOG IN OK");
                    panel.add(success);
                    new TicTacToe(1);
                } else {
                    success.setText("not logged in!");
                    panel.add(success);
                }
                break;
            case "1" :
                if (buttonClick == 1) {
                    caseOne.setText("X");
                } else if (buttonClick == 3) {
                    caseOne.setText("O");
                }
                break;
            case "2" :
                if (buttonClick == 1) {
                    caseTwo.setText("X");
                } else if (buttonClick == 3) {
                    caseTwo.setText("O");
                }
                break;
            case "3" :
                if (buttonClick == 1) {
                    caseThree.setText("X");
                } else if (buttonClick == 3) {
                    caseThree.setText("O");
                }
                break;
            case "4" :
                if (buttonClick == 1) {
                    caseFour.setText("X");
                } else if (buttonClick == 3) {
                    caseFour.setText("O");
                }
                break;
            case "5" :
                if (buttonClick == 1) {
                    caseFive.setText("X");
                } else if (buttonClick == 3) {
                    caseFive.setText("O");
                }
                break;
            case "6" :
                if (buttonClick == 1) {
                    caseSix.setText("X");
                } else if (buttonClick == 3) {
                    caseSix.setText("O");
                }
                break;
            case "7" :
                if (buttonClick == 1) {
                    caseSeven.setText("X");
                } else if (buttonClick == 3) {
                    caseSeven.setText("O");
                }
                break;
            case "8" :
                if (buttonClick == 1) {
                    caseEight.setText("X");
                } else if (buttonClick == 3) {
                    caseEight.setText("O");
                }
                break;
            case "9" :
                if (buttonClick == 1) {
                    caseNine.setText("X");
                } else if (buttonClick == 3) {
                    caseNine.setText("O");
                }
                break;
            default:
                gameComments.setText("Déjà changé");
                panel.add(gameComments);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
