import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Clock extends JFrame {
    SimpleDateFormat timeformat;
    SimpleDateFormat dayformat;
    SimpleDateFormat dateFormat;
    JLabel timelabel;
    JLabel daylabel;
    JLabel datelabel;
    String time;
    String day;
    String date;

    Clock(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Clock Program");
        this.setLayout(new FlowLayout());
        this.setSize(350,200);
        this.setResizable(false);
        this.setVisible(true);

        timeformat = new SimpleDateFormat("hh:mm:ss a");
        dayformat = new SimpleDateFormat("E");
        dateFormat = new SimpleDateFormat(" dd-MMMMM-yyyy");

        timelabel = new JLabel();
        timelabel.setFont(new Font("Verdana",Font.PLAIN,50));
        timelabel.setForeground(new Color(0x00ff00));
        timelabel.setBackground(Color.BLACK);
        timelabel.setOpaque(true);

        daylabel = new JLabel();
        daylabel.setFont(new Font("Verdana",Font.PLAIN,35));

        datelabel = new JLabel();
        datelabel.setFont(new Font("Verdana",Font.PLAIN,25));



        time = timeformat.format(Calendar.getInstance().getTime());
        timelabel.setText(time);

        this.add(timelabel);
        this.add(daylabel);
        this.add(datelabel);
        this.setLocationRelativeTo(null);

        settime();
    }
    public void settime(){
        while (true) {
            time = timeformat.format(Calendar.getInstance().getTime());
            timelabel.setText(time);

            day = dayformat.format(Calendar.getInstance().getTime());
            daylabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            datelabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
