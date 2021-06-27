import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class GameOfLifeFrame extends JFrame {

	
	
    private JButton openFileBtn = new JButton("ѡ���ļ�");
    private JButton startGameBtn = new JButton("��ʼ��Ϸ");
    private JLabel durationPromtLabel = new JLabel("�����������(msΪ��λ)");
    private JTextField durationTextField = new JTextField();
    private JLabel frequencyLabel =new JLabel("�ݻ���������");
    private JTextField frequencyTextField= new JTextField(); 
    /**
     * ��Ϸ�Ƿ�ʼ�ı�־
     */
    private boolean isStart = false;

    /**
     * ��Ϸ�����ı�־
     */
    private boolean stop = false;

    private CellMatrix cellMatrix;
    private JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
    private JPanel gridPanel = new JPanel();

    private JTextField[][] textMatrix;


    /**
     * ����Ĭ�ϼ��200ms
     */
    private static final int DEFAULT_DURATION = 200;
    
    /**
     * ��ʾ����Ĭ��Ϊ100��
     */
    private static final int NUM=100;

    //�������
    private int duration = DEFAULT_DURATION;

    //��ʾ����
    private int num = NUM;
    
    public GameOfLifeFrame() {
        setTitle("������Ϸ");
        openFileBtn.addActionListener(new OpenFileActioner());
        startGameBtn.addActionListener(new StartGameActioner());

        buttonPanel.add(openFileBtn);
        buttonPanel.add(startGameBtn);
        buttonPanel.add(durationPromtLabel);
        buttonPanel.add(durationTextField);
        buttonPanel.add(frequencyLabel,"South");
        buttonPanel.add(frequencyTextField,"South");
        
        buttonPanel.setBackground(Color.WHITE);

        getContentPane().add("North", buttonPanel);

        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }


    public class OpenFileActioner implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fcDlg = new JFileChooser(".");
            fcDlg.setDialogTitle("��ѡ���ʼ�����ļ�");
            int returnVal = fcDlg.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                isStart = false;
                stop = true;
                startGameBtn.setText("��ʼ��Ϸ");
                String filepath = fcDlg.getSelectedFile().getPath();
                cellMatrix = Tool.initMatrixFromFile(filepath);
                initGridLayout();
                showMatrix();
                gridPanel.updateUI();
            }
        }


    }

    private void showMatrix() {

        int[][] matrix = cellMatrix.getMatrix();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == 1) {
                    textMatrix[y][x].setBackground(Color.BLACK);
                } else {
                    textMatrix[y][x].setBackground(Color.WHITE);
                }
            }
        }
  
    }

    /**
     * ������ʾ��gridlayout����
     */
    private void initGridLayout() {
        int rows = cellMatrix.getHeight();
        int cols = cellMatrix.getWidth();
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));
        textMatrix = new JTextField[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                JTextField text = new JTextField();
                textMatrix[y][x] = text;
                gridPanel.add(text);
            }
        }
        add("Center", gridPanel);
      
    }


    public class StartGameActioner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isStart) {

                //��ȡʱ��
                try {
                    duration = Integer.parseInt(durationTextField.getText().trim());
                } catch (NumberFormatException e1) {
                    duration = DEFAULT_DURATION;
                }
                
                //��ȡ����
                try {
                    num = Integer.parseInt(frequencyTextField.getText().trim());
                } catch (NumberFormatException e2) {
                	num = NUM;
                }

                new Thread(new GameControlTask()).start();
                isStart = true;
                stop = false;
                startGameBtn.setText("��ͣ��Ϸ");
            } else {
                stop = true;
                isStart = false;
                startGameBtn.setText("��ʼ��Ϸ");
            }
        }
        
    }

    public class GameControlTask implements Runnable {
    	
        public void run() {

            while (!stop&&num!=0) {
                cellMatrix.transform();
                showMatrix();

                try {
                    TimeUnit.MILLISECONDS.sleep(duration);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                num--;
                if(num==0)
                {
                	stop = true;
                    isStart = false;
                    startGameBtn.setText("��ʼ��Ϸ");
                }
            }
        }
    }

   public void setduration(int a)
    {
	   duration=a;
    }
   public void setnum(int a)
   {
	   num=a;
   }
   public void setisStart(boolean a)
   {
	   isStart=a;
   }
   public void setstop(boolean a)
   {
	   stop=a;
   }
   public void setcellMatrix(CellMatrix a)
   {
	   cellMatrix=a;
   }
   public int getnum()
   {
	   return num;
   }
   public int getduration()
   {
	  return duration;
   }
 
}