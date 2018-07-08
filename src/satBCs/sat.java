package satBCs;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class sat {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sat window = new sat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sat() {
		initialize();
	}

	public JFrame getframe(){
		return frame;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("choisissez une base de connaisances");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					openFile();
					//JOptionPane.showMessageDialog(null,"Succes !!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"BCs non valide");
					System.out.println(e);
				}
				
			}
		});
		btnNewButton.setBounds(46, 92, 340, 35);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblEntrezUneFormule = new JLabel("Entrez une formule");
		lblEntrezUneFormule.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEntrezUneFormule.setBounds(10, 149, 151, 28);
		frame.getContentPane().add(lblEntrezUneFormule);
		
		textField = new JTextField();
		textField.setBounds(142, 152, 256, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Image imgEx = new ImageIcon(this.getClass().getResource("/execute.png")).getImage();
		JButton btnNewButton_1 = new JButton("R\u00E9soudre");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!SatSolver.setFormul(textField.getText())) 
						JOptionPane.showMessageDialog(null,"Formule Non Valide !!!");
					    frame.dispose();
					    Afficheur af = new Afficheur();
					    af.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Formule Non Valide !!!");
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(imgEx));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(276, 203, 134, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		
		Image img = new ImageIcon(this.getClass().getResource("/sat.png")).getImage();
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 0, 434, 81);
		frame.getContentPane().add(lblNewLabel);
			
	}
	
	public static void openFile() throws Exception {
		
		JFileChooser choser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CNF FILES", "cnf");
		choser.setFileFilter(filter);		
		
		choser.showOpenDialog(null);
		File f = choser.getSelectedFile();
		
		SatSolver.setPathBCs(f.getPath());

	}

}
