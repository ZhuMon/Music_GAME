package endproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.embed.swing.JFXPanel;

import org.magiclen.magicaudioplayer.*;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	/*
	 * public static void main(String[] args) { Main frame = new Main();
	 * frame.setVisible(true); //Selection(); }
	 */

	// private JLabel l;
	public static long begin_time;
	public static LinkedList<MyLabel> l = new LinkedList<MyLabel>();
	public static JButton pause = new JButton("Pause");

	public static JLabel assess = new JLabel(); // record "Perfect", "Good"...
	public static JLabel score = new JLabel();
	public static int grade;

	public static AudioPlayer mp3;
	public Main(String song) {
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		final JFXPanel fxPanel = new JFXPanel();

		pause.setActionCommand("pause");
		MyButtonListener buttonListener = new MyButtonListener();
		pause.addActionListener(buttonListener);
		pause.setFocusable(false);
		pause.setLocation(800, 50);
		pause.setSize(100, 30);
		add(pause);

		score.setLocation(750, 20);
		score.setSize(150, 30);
		score.setFont(new Font("New Romance", Font.BOLD, 32));

		add(score);
		Timer score_timer = new Timer();
		TimerTask check_score = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				score.setText(Integer.toString(grade));
			}

		};

		score_timer.schedule(check_score, 0, 50);

		assess.setLocation(330, 330);
		assess.setSize(120, 40);
		assess.setFont(new Font("New Romance", Font.BOLD, 32));
		assess.setOpaque(true);
		// assess.setBackground(Color.white);
		assess.setForeground(Color.GREEN);
		assess.setAlignmentX(CENTER_ALIGNMENT);
		assess.setAlignmentY(CENTER_ALIGNMENT);
		add(assess);

		File infile = new File("src/endproject/line.png");

		Image image = null;
		try {
			image = ImageIO.read(infile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel line = new JLabel(new ImageIcon(image));
		line.setLocation(0, 0);
		line.setSize(800, 700);
		add(line);
		
		JLabel wall = new JLabel();
		wall.setLocation(0, 0);
		wall.setSize(800, 120);
		wall.setOpaque(true);
		add(wall);
		
		JLabel highest = new JLabel();
		highest.setLocation(750, 150);
		highest.setSize(150, 30);
		highest.setFont(new Font("New Romance", Font.BOLD, 32));
		add(highest);

		begin_time = System.currentTimeMillis();
		System.out.println(song);
		Song sng = SongReader.readFile("src/4K-beatmaps/" + song + "/" + song + ".osx");

		File songFile;
		
		songFile = new File("src/4K-beatmaps/" + song + "/audio.wav");
		mp3 = AudioPlayer.createPlayer(songFile);
		mp3.play();

		/*if (mp3.isPlaying()) {
			System.out.println("Playing");

			int vol;
			// mp3.setVolume(50);
			vol = mp3.getVolume();
			System.out.println("vol:" + vol);
			//mp3.setAudioPosition(2000);
			System.out.println("length:" + mp3.getAudioLength());
			mp3.setAutoClose(true);
			System.out.println("auto:" + mp3.isAutoClose());
		}*/

		int high = 0;
		
		//l.add(new MyLabel(1, 2500, 0, this));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < sng.track.get(i).size(); j++) {
				l.add(new MyLabel(i, sng.track.get(i).get(j).start, sng.track.get(i).get(j).end, this));
				if(sng.track.get(i).get(j).end == 0) {
					high+=200;
				} else {
					high += (sng.track.get(i).get(j).end - sng.track.get(i).get(j).start)/40*200; 
				}
			}
		}

		highest.setText(Integer.toString(high));
		/*
		 * int i; int j; int b; Random r = new Random(); for(i = 0; i < 20; ++i) { j =
		 * (int)(Math.random()*4);
		 * 
		 * b = r.nextInt(2); if(b == 0) l.add(new MyLabel(j, 2500+i*1250, 0, this));
		 * else if(b == 1) l.add(new MyLabel(j, 2500+i*1250, 3700+i*1250, this));
		 * //pause.addKeyListener(l.get(i)); }
		 */

		

		

		/*Timer end_timer = new Timer();
		TimerTask endTask = new TimerTask() {
			@Override
			public void run() {
				JLabel endLabel = new JLabel();
				endLabel.setOpaque(true);
				// endLabel.setBackground(Color.white);
				endLabel.setForeground(Color.yellow);

				if (grade > 50000) {
					endLabel.setText("S");

				} else if (grade > 40000) {
					endLabel.setText("A");

				} else if (grade > 30000) {
					endLabel.setText("B");
				} else if (grade > 20000) {
					endLabel.setText("C");
				} else if (grade > 10000) {
					endLabel.setText("D");
				} else {
					endLabel.setText("F");
				}
				endLabel.setLocation(300, 200);
				endLabel.setSize(300, 300);
				endLabel.setFont(new Font("New Romance", Font.BOLD, 300));
				add(endLabel);
				System.out.println("grade");
			}
		};*/

		// end_timer.schedule(endTask, 27450);
		
		
	 
	}

}