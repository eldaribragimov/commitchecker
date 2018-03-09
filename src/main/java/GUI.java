import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * Created by eldar ibragimov on 9.3.18.
 */

public class GUI extends JFrame {
    private JButton linkbutton = new JButton("Select links file");
    private JButton deadlinebutton = new JButton("Select deadline file");
    private JButton check = new JButton("Check");
    JTextArea jLabel = new JTextArea();
    String link;
    String deadline;
    Container container;


    public GUI() {
        super("Git Deadline Check");
        this.setBounds(400,300,700,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
    }

    public void run(){
        linkbutton.addActionListener(new linkButtonEventListener());
        deadlinebutton.addActionListener(new deadlineButtonEventListener());
        check.addActionListener(new checkButtonEventListener());
        container.setLayout(null);
        jLabel.setBounds(20,150,700,500);
        jLabel.setSize(new Dimension(700,400));
        linkbutton.setBounds(50,10,200,100);
        linkbutton.setSize(new Dimension(200,50));
        deadlinebutton.setBounds(450,10,200,100);
        deadlinebutton.setSize(new Dimension(200,50));
        check.setBounds(330,80,50,50);
        container.add(check);
        container.add(linkbutton);
        container.add(deadlinebutton);
        container.add(jLabel);
    }

    class linkButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFileChooser linkfile;
            int l = -1;
            while (l!=0){
                linkfile = new JFileChooser();
                l = linkfile.showDialog(null, "Открыть файл с ссылками");
                link = linkfile.getCurrentDirectory()+"/"+linkfile.getSelectedFile().getName();
                linkbutton.setEnabled(false);
            }
        }
    }

    class deadlineButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser deadlinefile;
            int l = -1;
            while (l!=0){
                deadlinefile = new JFileChooser();
                l = deadlinefile.showDialog(null, "Открыть файл с дедлайнами");
                deadline = deadlinefile.getCurrentDirectory()+"/"+deadlinefile.getSelectedFile().getName();
                deadlinebutton.setEnabled(false);
            }
        }
    }

    class checkButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DataProvider a = new DataProvider();
            String s="";
            try {
                 s = a.run(link,deadline);
            } catch (IOException e1) {
                e1.printStackTrace();
                s = "API rate limit exceeded for your IP";
            }
            jLabel.setText(s);
        }
    }

    public static void main(String[] args) {
        GUI app = new GUI();
        app.run();
        app.setVisible(true);
    }
}