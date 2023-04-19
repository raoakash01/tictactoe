import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

class TicTacToe implements ActionListener 
{  
    JFrame frame = new JFrame("MY TIC TAC TOE");
    JPanel t_panel = new JPanel();
    JPanel bt_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] btn = new JButton[9];
    int chance_flag = 0;
    boolean pl1_chance;
    JLabel date = new JLabel("DATE AND TIME",JLabel.CENTER);

    //creating grid
    TicTacToe() 
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(new Color(250, 184, 97));
        frame.setTitle("Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.add(date,BorderLayout.SOUTH);

        
        Font font2 = new Font(Font.SANS_SERIF,3,28);
        date.setFont(font2);
        LocalDate dt = LocalDate.now();
        LocalTime lt = LocalTime.now();

        date.setText(dt+"                "+lt);

        Thread date_thread = new Thread(
                ()->{
                    while(true){
                        date.setText(dt+"                    "+lt);
                    }
                }
            );
        date_thread.start();

        textfield.setBackground(new Color(0,0,0));
        textfield.setForeground(new Color(255,0,0));
        textfield.setFont(new Font("Serif", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("My Tic Tac Toe");
        textfield.setOpaque(true);

        t_panel.setLayout(new BorderLayout());
        t_panel.setBounds(0, 0, 800, 100);

        bt_panel.setLayout(new GridLayout(3, 3));
        bt_panel.setBackground(new Color(0, 0, 0));

        for (int i = 0; i < 9; i++) 
        {
            btn[i] = new JButton();
            bt_panel.add(btn[i]);
            btn[i].setFont(new Font("Serif", Font.BOLD, 120));
            btn[i].setFocusable(false);
            btn[i].addActionListener(this);
            btn[i].setBackground(Color.lightGray);
        }
        
        t_panel.add(textfield);
        frame.add(t_panel, BorderLayout.NORTH);
        frame.add(bt_panel);

        startGame();
    }
    
    public void startGame() 
    {
        try 
        {
            textfield.setText("Ready...");
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        int chance= 400 ;

        if (chance%2 == 0) 
        {
            pl1_chance = true;
            textfield.setText("Player X turn");
        } 
        else 
        {
            pl1_chance = false;
            textfield.setText("Player O turn");
        }
    }
    
    public void gameOver(String s)
    {
        chance_flag = 0;
        Object[] option={"Restart","Exit"};
        int n=JOptionPane.showOptionDialog(frame, "Game-Over\n"+s,"Game-Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
        if(n==0)
        {
            frame.dispose();
            new TicTacToe();
        }
        else
        {
            frame.dispose();
        }
    
    }

    // checking winning conditions 
    public void winning_conditions() 
    {
        if ((btn[0].getText() == "X") && (btn[1].getText() == "X") && (btn[2].getText() == "X")) 
        {
            xWins(0, 1, 2);
        }
        else if ((btn[0].getText() == "X") && (btn[4].getText() == "X") && (btn[8].getText() == "X")) 
        {
            xWins(0, 4, 8);
        }
        else if ((btn[0].getText() == "X") && (btn[3].getText() == "X") && (btn[6].getText() == "X")) 
        {
            xWins(0, 3, 6);
        }
        else if ((btn[1].getText() == "X") && (btn[4].getText() == "X") && (btn[7].getText() == "X")) 
        {
            xWins(1, 4, 7);
        }
        else if ((btn[2].getText() == "X") && (btn[4].getText() == "X") && (btn[6].getText() == "X")) 
        {
            xWins(2, 4, 6);
        }
        else if ((btn[2].getText() == "X") && (btn[5].getText() == "X") && (btn[8].getText() == "X")) 
        {
            xWins(2, 5, 8);
        }
       else if ((btn[3].getText() == "X") && (btn[4].getText() == "X") && (btn[5].getText() == "X")) 
       {
            xWins(3, 4, 5);
        }
       else if ((btn[6].getText() == "X") && (btn[7].getText() == "X") && (btn[8].getText() == "X")) 
       {
            xWins(6, 7, 8);
        }
      
        else if ((btn[0].getText() == "O") && (btn[1].getText() == "O") && (btn[2].getText() == "O")) 
        {
            oWins(0, 1, 2);
        }
        else if ((btn[0].getText() == "O") && (btn[3].getText() == "O") && (btn[6].getText() == "O")) 
        {
            oWins(0, 3, 6);
        }
        else if ((btn[0].getText() == "O") && (btn[4].getText() == "O") && (btn[8].getText() == "O")) 
        {
            oWins(0, 4, 8);
        }
        else if ((btn[1].getText() == "O") && (btn[4].getText() == "O") && (btn[7].getText() == "O")) 
        {
            oWins(1, 4, 7);
        }
        else if ((btn[2].getText() == "O") && (btn[4].getText() == "O") && (btn[6].getText() == "O")) 
        {
            oWins(2, 4, 6);
        }
        else if ((btn[2].getText() == "O") && (btn[5].getText() == "O") && (btn[8].getText() == "O")) 
        {
            oWins(2, 5, 8);
        }
        else if ((btn[3].getText() == "O") && (btn[4].getText() == "O") && (btn[5].getText() == "O")) 
        {
            oWins(3, 4, 5);
        } else if ((btn[6].getText() == "O") && (btn[7].getText() == "O") && (btn[8].getText() == "O")) 
        {
            oWins(6, 7, 8);
        }
        else if(chance_flag==9) 
        {
            textfield.setText("Game Draw!!");
             gameOver("Game Draw!!");
        }
    }

    // Player X wins
    public void xWins(int x1, int x2, int x3) 
    {
        
    	btn[x1].setBackground(Color.YELLOW);
        btn[x2].setBackground(Color.YELLOW);
        btn[x3].setBackground(Color.YELLOW);

        for (int i = 0; i < 9; i++) 
        {
            btn[i].setEnabled(false);
        }
        textfield.setText("Player X wins");
        gameOver("Player X Wins");
    }

    // Player O wins
    public void oWins(int x1, int x2, int x3) 
    {
        
        btn[x1].setBackground(Color.YELLOW);
        btn[x2].setBackground(Color.YELLOW);
        btn[x3].setBackground(Color.YELLOW);

        for (int i = 0; i < 9; i++) 
        {
            btn[i].setEnabled(false);
        }
        textfield.setText("Player O Wins");
        gameOver("Player O Wins");
    }
    
    // action after every turn
    
    public void actionPerformed(ActionEvent e) 
    {
        for (int i = 0; i < 9; i++) 
        {
            if (e.getSource() == btn[i]) 
            {
                if (pl1_chance) 
                {
                    if (btn[i].getText() == "") 
                    {
                        btn[i].setForeground(new Color(255, 0, 255));
                        btn[i].setText("X");
                        pl1_chance = false;
                        textfield.setText("O turn");
                        chance_flag++;
                        winning_conditions();
                    }
                } 
                else 
                {
                    if (btn[i].getText() == "") 
                    {
                        btn[i].setForeground(new Color(255, 255, 255));
                        btn[i].setText("O");
                        pl1_chance = true;
                        textfield.setText("X turn");
                        chance_flag++;
                        winning_conditions();
                    }
                }
            }
        }
    }
}


public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        
        //try{
       //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TicTacToe","root","!Radhe123"); 
       // }
        //catch(Exception ex){System.out.println(ex);}
       new TicTacToe();
    }
}