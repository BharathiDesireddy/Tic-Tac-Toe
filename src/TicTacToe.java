import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {
    int boardwidth = 600;
    int boardheight = 650;


    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textlabel = new JLabel();
    JPanel textpanel = new JPanel();
    JPanel boardpanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String current = playerX;
    boolean gameOver = false;
    int count=0;
    
    TicTacToe() {

        frame.setVisible(true);
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textlabel.setBackground(Color.darkGray);
        textlabel.setForeground(Color.white);
        textlabel.setText("Tic-Tac-Toe");
        textlabel.setFont(new Font("Arial",Font.BOLD,50));
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setOpaque(true);

       
        textpanel.setLayout(new BorderLayout());
        textpanel.add(textlabel);
        frame.add(textpanel,BorderLayout.NORTH);

        boardpanel.setLayout(new GridLayout(3,3));
        boardpanel.setBackground(Color.darkGray);
        frame.add(boardpanel);

        for(int row = 0;row<3;row++){
            for(int col = 0;col<3;col++){
                JButton box = new JButton();
                board[row][col]=box;
                boardpanel.add(box);
                box.setBackground(Color.darkGray);
                box.setForeground(Color.white);
                box.setFont(new Font("Arial",Font.BOLD,120));
                box.setFocusable(false);

                box.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver){
                            return;
                        }
                        JButton box = (JButton) e.getSource();
                        if(box.getText()==""){
                            box.setText(current);
                            count++;
                            checkWinner();
                            if(!gameOver){
                                current = current==playerX?"O":"X";
                                textlabel.setText(current+"'s turn.");
                            }
                              
                        }
                        

                    }
                });
            }
        }

    }

    void checkWinner(){
        //horizontal checking
        for(int r=0;r<3;r++){
            if(board[r][0].getText()==""){
                continue;
            }
            if(board[r][0].getText()==board[r][1].getText() && board[r][1].getText()==board[r][2].getText()){
                for(int i=0;i<3;i++){
                    setWinner(board[r][i]);
                }
                gameOver=true;
                return;
            }
        }

        //vertical checking
        for(int c=0;c<3;c++){
            if(board[0][c].getText()==""){
                continue;
            }
            if(board[0][c].getText()==board[1][c].getText() && board[1][c].getText()==board[2][c].getText()){
                for(int i=0;i<3;i++){
                    setWinner(board[i][c]);
                }
                gameOver=true;
                return;
            }
        }

        //primary diagonal checking
        if(board[0][0].getText()==board[1][1].getText() 
        && board[1][1].getText()==board[2][2].getText() 
        && board[0][0].getText()!=""){
            for(int i =0;i<3;i++){
                setWinner(board[i][i]);
            }
            gameOver=true;
            return;
        }

        //secondary diagonal checking
        if(board[0][2].getText()==board[1][1].getText() 
        && board[1][1].getText()==board[2][0].getText() 
        && board[0][2].getText()!=""){
            for(int i =0;i<3;i++){
                setWinner(board[i][2-i]);
            }
            gameOver=true;
            return;
        }

        //checking tie
        if(count==9){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    setTie(board[i][j]);
                }
            }
            gameOver=true;
            return;
        }

    }

    void setWinner(JButton box){
        box.setBackground(Color.green);
        box.setForeground(Color.gray);
        textlabel.setText(current+" is the winner!");
    }

    void setTie(JButton box){
        box.setBackground(Color.gray);
        box.setForeground(Color.orange);
        textlabel.setText("it's a Tie!....");
    }

}
