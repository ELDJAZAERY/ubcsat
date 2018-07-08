package satBCs;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Afficheur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Afficheur frame = new Afficheur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Afficheur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JTextPane textPane = new JTextPane();
		JScrollPane scroll = new JScrollPane(textPane);
		scroll.setBounds(10, 11, 503, 374);
		contentPane.add(scroll);
		
		textPane.setText(SatSolver.ResAffiche);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				retour();
				//sat s = new sat();
			}
		});
		btnOk.setBounds(409, 404, 89, 23);
		contentPane.add(btnOk);
	}
	
	private static void retour(){
		try {
			sat window = new sat();
			window.getframe().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
