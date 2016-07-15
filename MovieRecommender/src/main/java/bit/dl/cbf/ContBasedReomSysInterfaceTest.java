package bit.dl.cbf;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.JTextComponent;

import org.grouplens.lenskit.ItemRecommender;
import org.grouplens.lenskit.scored.ScoredId;

import bit.dl.cbf.dao.ItemDAO;



public class ContBasedReomSysInterfaceTest extends JFrame{

	MyPanel mp = null;
	ItemRecommender irec;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ContBasedReomSysInterfaceTest grep = new ContBasedReomSysInterfaceTest();
	}
	
	public ContBasedReomSysInterfaceTest() {
		
		mp = new MyPanel(irec);
		
		this.add(mp);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("REGEX CHECK");
		
	}
	
	public ContBasedReomSysInterfaceTest(ItemRecommender irec) {
		
		this.irec = irec;
		
		mp = new MyPanel(irec);
		
		this.add(mp);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Recommender System");
		
	}

}

class MyPanel extends JPanel implements ActionListener {
	
	
	ItemRecommender irec;
	public static Entry<String, Long> userInfo;
//	public static Long userid;
	
	JTextField jtfUser;
	JTextArea jtaItem;
//	JButton jbCheck;
//	JTextField jtfResult;
//	JScrollPane jspGrepInput;
	JScrollPane jspItem;
	
	JLabel jlText1, jlText2;
	
	JButton jbSelUser, jbGenMovie;
	
	
	String regex = null;
	String string = null;
	
	public MyPanel(ItemRecommender irec) {
		
		this.irec = irec;
		
		this.setLayout(null);
		
		jlText1 = new JLabel("user name:");
		jlText1.setBounds(60, 30, 80, 20);
		
		jtfUser = new JTextField();
		jtfUser.setBounds(140, 30, 130, 20);
		
		jbSelUser = new JButton("select");
		jbSelUser.setBounds(290, 30, 70, 20);
		jbSelUser.addActionListener(this);
		
		

		
		jlText2 = new JLabel("recommended movies==>");
		jlText2.setBounds(60, 70, 180, 20);
		
		jbGenMovie = new JButton("generate");
		jbGenMovie.setBounds(240, 70, 100, 20);
		jbGenMovie.addActionListener(this);
		
		jtaItem = new JTextArea();
//		jtaStringInput.setColumns(15);
//		jtaStringInput.setLineWrap(true);
		
		jspItem = new JScrollPane(jtaItem);
		jspItem.setBounds(70, 110, 290, 100);

		
		this.add(jlText1);
		this.add(jtfUser);
		this.add(jbSelUser);
		
		this.add(jlText2);
		this.add(jbGenMovie);
		this.add(jspItem);

		
		new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(userInfo != null) {
					jtfUser.setText(userInfo.getValue() + ":" + userInfo.getKey());
//					jtaItem.setText("11sdasd");
				}
			}
		}).start();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
//		jtaItem.setText("dasdas");
		if(arg0.getSource() == jbGenMovie) {
			System.out.println("gen");
//			System.out.println("111");
			
			List<ScoredId> recs = irec.recommend(userInfo.getValue(), 5);
			
			if (recs.isEmpty()) {
				System.out.println("no recommendations for user");
                 jtaItem.setText("no recommendations for user " + userInfo.getValue() + ", do they exist?");
                 return;
             }
//             System.out.format("recommendations for user %d:\n", uid);
			
			String text = new String();
             for (ScoredId id: recs) {
            	 text += String.format("  %d: %s  %.4f\n", id.getId(), new ItemDAO(new File("data/movie-titles.csv")).getItemTitle(id.getId()), id.getScore());
//            	 text.concat(String.format("  %d: %.4f\n", id.getId(), id.getScore()));
//                 System.out.format();
            	//System.out.println(String.format("  %d: %.4f\n", id.getId(), id.getScore()));
//            	 System.out.format("  %d: %.4f\n", id.getId(), id.getScore());
//            	 System.out.println("1:" + new MOOCItemDAO(new File("data/movie-titles.csv")).getItemTitle(id.getId()));
             }
			
//             System.out.println(text);
             jtaItem.setText(text);
//             jtaItem.setText("12345");
             
		} else {
			System.out.println("select");
			new JList1();
			
		}
	}




	
	
	
}