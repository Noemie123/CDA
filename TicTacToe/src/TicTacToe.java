import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


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

    private ArrayList<String> arrNumbers = new ArrayList<>() {
        {
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
        }
    };


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


    public static ArrayList<String> checkGame(JButton caseOne, JButton caseTwo, JButton caseThree, JButton caseFour, JButton caseFive, JButton caseSix, JButton caseSeven, JButton caseEight, JButton caseNine) {
        ArrayList<String> arrResult = new ArrayList<>();

        if (caseOne.getText().equals(caseTwo.getText()) && caseTwo.getText().equals(caseThree.getText())) { // 1 - 2 -3
            arrResult.add("true");
            arrResult.add(caseOne.getText());
        } else if (caseFour.getText().equals(caseFive.getText()) && caseFive.getText().equals(caseSix.getText())) { // 4 - 5 - 6
            arrResult.add("true");
            arrResult.add(caseFour.getText());
        } else if (caseSeven.getText().equals(caseEight.getText()) && caseEight.getText().equals(caseNine.getText())) { // 6 - 7 - 8
            arrResult.add("true");
            arrResult.add(caseSeven.getText());
        } else if (caseOne.getText().equals(caseFour.getText()) && caseFour.getText().equals(caseSeven.getText())) { // 1 - 4 - 7
            arrResult.add("true");
            arrResult.add(caseOne.getText());
        } else if (caseTwo.getText().equals(caseFive.getText()) && caseFive.getText().equals(caseEight.getText())) { // 2 - 5 - 8
            arrResult.add("true");
            arrResult.add(caseTwo.getText());
        } else if (caseThree.getText().equals(caseSix.getText()) && caseSix.getText().equals(caseNine.getText())) { // 3 - 6 - 9
            arrResult.add("true");
            arrResult.add(caseThree.getText());
        } else if (caseOne.getText().equals(caseFive.getText()) && caseFive.getText().equals(caseNine.getText())) { // 1 - 5 - 9
            arrResult.add("true");
            arrResult.add(caseOne.getText());
        } else if (caseThree.getText().equals(caseFive.getText()) && caseFive.getText().equals(caseSeven.getText())) { // 3 - 5 - 7
            arrResult.add("true");
            arrResult.add(caseThree.getText());
        }

        if (!caseOne.getText().equals("1") && !caseTwo.getText().equals("2") && !caseThree.getText().equals("3") && !caseFour.getText().equals("4") && !caseFive.getText().equals("5") && !caseSix.getText().equals("6") && !caseSeven.getText().equals("7") && !caseEight.getText().equals("8") && !caseNine.getText().equals("9")) {
            arrResult.add("true");
            arrResult.add("1");
        }
        if (arrResult.isEmpty()) {
            arrResult.add("false");
            arrResult.add("0");
        }


        return arrResult;
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




        switch (buttonText) {
            case "Log in":
                String userInput = userText.getText();
                String pwordInput = String.valueOf(pwordText.getPassword());

                if (userInput.equals("nono") && pwordInput.equals("mdp")) {
                    success.setText(userInput + ", vous êtes connecté !");
                    success.setEnabled(false);
                    panel.add(success);
                    new TicTacToe(1);
                } else {
                    success.setText("Mauvais identifiants !");
                    success.setEnabled(false);
                    panel.add(success);
                }

                break;
            case "1" :
                if (!caseOne.getText().equals("O")) {
                    caseOne.setText("X");
                    arrNumbers.remove(findIndex(buttonText, arrNumbers));
                }
                break;
            case "2" :
                if (!caseTwo.getText().equals("O")) {
                    caseTwo.setText("X");
                    arrNumbers.remove(findIndex(buttonText, arrNumbers));
                }
                break;
            case "3" :
                if (!caseThree.getText().equals("O")) {
                    caseThree.setText("X");
                    arrNumbers.remove(findIndex(buttonText, arrNumbers));
                }
                break;
            case "4" :
                if (!caseFour.getText().equals("O")) {
                    caseFour.setText("X");
                    arrNumbers.remove(findIndex(buttonText, arrNumbers));
                }
                break;
            case "5" :
                if (!caseFive.getText().equals("O")) {
                    caseFive.setText("X");
                    arrNumbers.remove(findIndex(buttonText, arrNumbers));
                }
                break;
            case "6" :
                if (!caseSix.getText().equals("O")) {
                    caseSix.setText("X");
                    arrNumbers.remove(findIndex(buttonText, arrNumbers));
                }
                break;
            case "7" :
                if (!caseSeven.getText().equals("O")) {
                    caseSeven.setText("X");
                    arrNumbers.remove(findIndex(buttonText, arrNumbers));
                }
                break;
            case "8" :
                if (!caseEight.getText().equals("O")) {
                    caseEight.setText("X");
                    arrNumbers.remove(findIndex(buttonText, arrNumbers));
                }
                break;
            case "9" :
                if (!caseNine.getText().equals("O")) {
                    caseNine.setText("X");
                    arrNumbers.remove(findIndex(buttonText, arrNumbers));
                }
                break;
            default:
                gameComments.setText("Déjà changé");
                gameComments.setEnabled(false);
                panel.add(gameComments);
        }



        if (!buttonText.equals("Log in")) {
            ArrayList<String> checkGame1 = checkGame(caseOne, caseTwo, caseThree, caseFour, caseFive, caseSix, caseSeven, caseEight, caseNine);
            if (checkGame1.get(0).equals("false") && (!buttonText.equals("X") && !buttonText.equals("O"))) {
                int pickIndex = computerPick(arrNumbers);
                String computerPick = arrNumbers.get(pickIndex);

                System.out.println(computerPick);

                arrNumbers.remove(pickIndex);


                if (computerPick.equals("1")) {
                    caseOne.setText("O");
                } else if (computerPick.equals("2")) {
                    caseTwo.setText("O");
                } else if (computerPick.equals("3")) {
                    caseThree.setText("O");
                } else if (computerPick.equals("4")) {
                    caseFour.setText("O");
                } else if (computerPick.equals("5")) {
                    caseFive.setText("O");
                } else if (computerPick.equals("6")) {
                    caseSix.setText("O");
                } else if (computerPick.equals("7")) {
                    caseSeven.setText("O");
                } else if (computerPick.equals("8")) {
                    caseEight.setText("O");
                } else {
                    caseNine.setText("O");
                }



            }




            ArrayList<String> checkGame = checkGame(caseOne, caseTwo, caseThree, caseFour, caseFive, caseSix, caseSeven, caseEight, caseNine);
            if (checkGame.get(0).equals("true")) {
                caseOne.setEnabled(false);
                caseTwo.setEnabled(false);
                caseThree.setEnabled(false);
                caseFour.setEnabled(false);
                caseFive.setEnabled(false);
                caseSix.setEnabled(false);
                caseSeven.setEnabled(false);
                caseEight.setEnabled(false);
                caseNine.setEnabled(false);
                if (checkGame.get(1).equals("1")) {
                    gameComments.setText("Personne n'a gagné.");
                } else {
                    gameComments.setText(checkGame.get(1) + " a gagné.");
                }
            }
            gameComments.setEnabled(false);
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

    public static int computerPick(ArrayList<String> arrNumbers) {

        Integer max = arrNumbers.size()-1;
        return (int) Math.floor (Math.random()* (max + 1));

    }

    public static int findIndex(String pickedStr, ArrayList<String> arrNumbers) {
        for (int i = 0; i < arrNumbers.size(); i++) {
            if (pickedStr.equals(arrNumbers.get(i))) {
                return i;
            }
        }

        return -1;
    }

}
