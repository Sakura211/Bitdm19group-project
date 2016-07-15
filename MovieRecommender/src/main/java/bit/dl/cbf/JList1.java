package bit.dl.cbf;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bit.dl.cbf.dao.UserDAO;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import java.util.Vector;
import java.util.Map.Entry;

public class JList1
{
	JFrame f;
    public JList1() {
    	
    	 ObjectSet<Entry<String, Long>> my = new UserDAO(new File("data/users.csv")).getEntrySet();
         
        
    	
        f = new JFrame("Select a user");
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
//        String[] s = {"美国","日本","大陆","英国","法国"};
        Vector v = new Vector();
        
        for(Entry<String, Long> entry: my) {
        	
        	v.addElement(entry);
        	
        	//System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        
//        JList list1 = new JList(s);
//        list1.setBorder(BorderFactory.createTitledBorder("您最喜欢到哪个国家玩呢？"));
        
        JList list2 = new JList(v); 
        list2.setBorder(BorderFactory.createTitledBorder("username=>userId"));

        list2.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				MyPanel.userInfo = (Entry<String, Long>) ((JList)e.getSource()).getSelectedValue();
				
			}
		});
        
        JButton jbOK = new JButton("OK");
        
        jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.exit(0);
				f.dispose();
			}
		});
        
//        contentPane.add(new JScrollPane(list1));
        contentPane.add(new JScrollPane(list2));
        contentPane.add(jbOK);
        f.setLocationRelativeTo(null);
        f.pack();
        f.show();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.addWindowStateListener
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                    f.dispose();
            }
        });
    }
}
