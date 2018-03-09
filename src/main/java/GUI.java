import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * Created by eldar ibragimov on 9.3.18.
 */

public class GUI extends JFrame {
    private JButton linkbutton = new JButton("Select the file with Links");
    private JButton deadlinebutton = new JButton("Select the file with Deadlines");
    private JButton check = new JButton("Check");
    JTextArea jTextArea = new JTextArea();
    JLabel jTextFieldlink = new JLabel();
    JLabel jTextFielddeadline = new JLabel();
    String link;
    String deadline;
    Container container;


    public GUI() {
        super("Git Deadline Check");
        this.setBounds(400,200,700,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
    }

    public void run(){
        linkbutton.addActionListener(new linkButtonEventListener());
        deadlinebutton.addActionListener(new deadlineButtonEventListener());
        check.addActionListener(new checkButtonEventListener());
        container.setLayout(null);
        jTextArea.setBounds(20,150,700,500);
        jTextArea.setSize(new Dimension(700,400));
        linkbutton.setBounds(50,10,200,100);
        linkbutton.setSize(new Dimension(200,50));
        deadlinebutton.setBounds(450,10,200,100);
        deadlinebutton.setSize(new Dimension(200,50));
        check.setBounds(330,80,50,50);
        jTextFieldlink.setBounds(50,50,200,100);
        jTextFielddeadline.setBounds(450,50,200,100);
        container.add(jTextFieldlink);
        container.add(jTextFielddeadline);
        container.add(check);
        container.add(linkbutton);
        container.add(deadlinebutton);
        container.add(jTextArea);
    }

    class linkButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFileChooser linkfile;
            int l = -1;
            while (l!=0){
                linkfile = new JFileChooser();
                l = linkfile.showDialog(null, "Открыть файл с ссылками");
                link = linkfile.getCurrentDirectory()+"/"+linkfile.getSelectedFile().getName();
                jTextFieldlink.setText("File: "+link);
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
                jTextFielddeadline.setText("File: "+deadline);
            }
        }
    }

    class checkButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DataProvider a = new DataProvider();
            jTextArea.setText("");
            try {
                 jTextArea.setText(a.run(link,deadline));
            } catch (org.eclipse.egit.github.core.client.RequestException e1){
                jTextArea.setText("Opps !"+"\n"+"API rate limit exceeded for your IP"+"\n"+"Repeat after an hour");
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (java.lang.IllegalArgumentException e3){
                jTextArea.setText("Error !"+"\n"+"One of the files isn't match"+"\n"+"Please, check the files");
            }
            catch (java.lang.IndexOutOfBoundsException e4){
                jTextArea.setText("Error !"+"\n"+"One of the files isn't match"+"\n"+"Please, check the files");
            }
        }
    }

    public static void main(String[] args) {
        GUI app = new GUI();
        app.run();
        app.setVisible(true);
    }
}