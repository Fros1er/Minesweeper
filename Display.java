import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Display extends JFrame{
	Container container;
	Map board;
	int count=81;
	public Display(){
		this.board=new Map(9,9,10);
        setLayout(new GridLayout(10,9,5,5));
        this.container=getContentPane();
        addHead();
        addMines();
        setTitle("MineSweeper");
        setVisible(true);
        setSize(500,550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
	public void init(){
		this.board=new Map(9,9,10);
		for(int i=9;i<90;i++){
			JButton button=(JButton) this.container.getComponent(i);
			button.setText("");
			button.setEnabled(true);
			count=81;
		}
	}
	public void addHead(){
		for(int i=0;i<9;i++){
        	JLabel label=new JLabel();
        	if(i==4){
        		JButton restart=new JButton("R");
        		restart.addActionListener(new ActionListener(){
        			public void actionPerformed(ActionEvent e){
        				init();
        			}
        		});
        		this.container.add(restart);
        	}
        	else{
        		this.container.add(label);
        	}
        }
	}
    public void addMines(){
    	for(int i=0;i<9;i++){
    		for(int j=0;j<9;j++){
    			JButton jb=new JButton();
    			jb.addMouseListener(new ButtonHandler(jb,i,j));
    			//jb.addActionListener(new ButtonHandler(jb,i,j));
                this.container.add(jb);
    		}
        }
    }
    public boolean check(int x,int y){
		if(x>=0&&x<this.board.x&&y>=0&&y<this.board.y){
			return true;
		}
		return false;
	}
    public void clear(int x,int y){
    	return;
    }
    public void clearzero(int x,int y){
    	for(int i=-1;i<2;i++){
    		for(int j=-1;j<2;j++){
    			//System.out.printf("x=%d y=%d num=%d\n",x+i,y+j,(x+i)*9+y+j+9);
    			if(check(x+i,y+j)){
    				JButton button=(JButton) this.container.getComponent((x+i)*9+y+j+9);
    				if(button.isEnabled()==false){
    					//System.out.println("Passed.");
    					continue;
    				}
    				if(this.board.map[x+i][y+j]==0){
    					button.setText("");
    					button.setEnabled(false);
    					//System.out.println("0");
    					if(i!=0||j!=0){
    						clearzero(x+i,y+j);
    					}
    				}
    				else{
    					button.setText(""+this.board.map[x+i][y+j]);
    					button.setEnabled(false);
    					//System.out.println("isnum");
    				}
    			}
    		}
    	}
    	int cnt=0;
    	for(int i=9;i<90;i++){
    		JButton button=(JButton) this.container.getComponent(i);
    		if(button.isEnabled()==true){
    			cnt++;
    		}
    		System.out.println("cnt="+cnt);
    		count=cnt;
    	}
    }
    public void lose(){
    	JOptionPane.showMessageDialog(null, "YOU LOSE!", null, JOptionPane.DEFAULT_OPTION);
    }
    public void win(){
    	JOptionPane.showMessageDialog(null, "YOU WIN!", null, JOptionPane.DEFAULT_OPTION);
    }
    public class ButtonHandler extends MouseAdapter{
    	JButton button;
    	int x;
    	int y;
    	public ButtonHandler(JButton b,int x,int y){
    		this.button=b;
    		this.x=x;
    		this.y=y;
    	}
    	public void mouseClicked(MouseEvent e){
    		//new Thread(new Runnable(){
				//public void run() {
					if(e.getButton()==MouseEvent.BUTTON1&&button.getText()!="F"&&button.isEnabled()==true){
						if(board.map[x][y]==-1){
							lose();
						}
						else{
							count--;
							System.out.println(count);
							if(count==9){
								win();
							}
						}
						if(board.map[x][y]==0){
							clearzero(x,y);
						}
						else{
							button.setText(""+board.map[x][y]);
						}
	                    button.setEnabled(false);
	        		}
	        		if(e.getButton()==MouseEvent.BUTTON3){
	        			if(button.isEnabled()==false){
	        				clear(x,y);
	        			}
	        			else{
	        				if(button.getText()=="F"){
	        					button.setText("");
	        				}
	        				else{
	        					button.setText("F");
	        				}
	        			}
	        		}
				//}
    		//}).start();
	        return;
    	}
    }
    public static void main(String[] args){
		new Display();
	}
}