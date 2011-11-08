package paku.game.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import paku.game.command.*;

public class NewFileFrame extends JFrame implements ActionListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	private int dim, debut, max;
	private JLabel l_dimension, l_paku, l_duree, l_apply, l_typeA, l_typeB, l_typeC, l_sPakus;
	private JTextField duree;
	private JCheckBox cb_duree, cb_applyCollu, cb_typeA, cb_typeB, cb_typeC;
	private JSpinner dimension, nbPaku, s_nbPakus;
	private JButton valider, cancel;
	private SpinnerNumberModel spinner, spinner2;
	private Hashtable<String,Command> commands;

	public NewFileFrame(Hashtable<String,Command> commands){

		super("Paku Paku - Nouvelle partie...");
		this.commands = commands;

		// default minimum size
		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();

		this.setSize(d.width/3, d.height/2);


		JPanel panel = new JPanel ();
		panel.setLayout(new BorderLayout(20,20));

		JPanel centre = new JPanel();
		centre.setLayout(new BoxLayout(centre, BoxLayout.PAGE_AXIS));

		dimension = new JSpinner(new SpinnerNumberModel(50, 0, 100, 1));
		dimension.setName("Dimension");
		dimension.addChangeListener(this);
		spinner = new SpinnerNumberModel(debut, 0, max, 1);
		nbPaku = new JSpinner(spinner);
		nbPaku.setName("NbPakus");
		nbPaku.addChangeListener(this);
		dimension.setSize(new Dimension(100,20));
		nbPaku.setSize(new Dimension(100,20));

		JPanel pGeneral = new JPanel();
		pGeneral.setLayout(new SpringLayout());
		pGeneral.setBorder(BorderFactory.createTitledBorder("Général"));

		l_dimension = new JLabel("Dimension de la grille ");
		l_paku = new JLabel("Nombre de Pakus  ");
		pGeneral.add(l_dimension);
		pGeneral.add(dimension);
		pGeneral.add(l_paku);
		pGeneral.add(nbPaku);

		SpringUtilities.makeCompactGrid(pGeneral, 2, 2, 6, 6, 6, 6);
		//		SpringUtilities.printSizes(centre);

		JPanel pDuree = new JPanel();
		pDuree.setLayout(new GridLayout(1,2));
		pDuree.setBorder(BorderFactory.createTitledBorder("Durée"));

		JPanel pDureeLeft = new JPanel();

		cb_duree = new JCheckBox();
		cb_duree.addActionListener(this);
		duree = new JTextField();
		duree.setEditable(false);
		duree.setSize(new Dimension(100,20));

		l_duree = new JLabel("Nombre de parties");

		pDureeLeft.add(cb_duree);
		pDureeLeft.add(l_duree);
		pDuree.add(pDureeLeft);
		pDuree.add(duree);

		JPanel pCollusion = new JPanel();
		pCollusion.setLayout(new BoxLayout(pCollusion, BoxLayout.PAGE_AXIS));
		pCollusion.setBorder(BorderFactory.createTitledBorder("Collusion"));

		JPanel pCollUp = new JPanel();
		pCollUp.setLayout(new GridLayout(4,2));

		cb_applyCollu = new JCheckBox();
		cb_applyCollu.addActionListener(this);
		cb_typeA = new JCheckBox();
		cb_typeA.setEnabled(false);
		cb_typeA.addActionListener(this);
		cb_typeB = new JCheckBox();
		cb_typeB.addActionListener(this);
		cb_typeB.setEnabled(false);
		cb_typeC = new JCheckBox();
		cb_typeC.addActionListener(this);
		cb_typeC.setEnabled(false);

		l_apply = new JLabel("Appliquer une collusion");
		l_typeA = new JLabel("Type A");
		l_typeB = new JLabel("Type B");
		l_typeC = new JLabel("Type C");

		pCollUp.add(cb_applyCollu);
		pCollUp.add(l_apply);
		pCollUp.add(cb_typeA);
		pCollUp.add(l_typeA);
		pCollUp.add(cb_typeB);
		pCollUp.add(l_typeB);
		pCollUp.add(cb_typeC);
		pCollUp.add(l_typeC);

		JPanel pCollDown = new JPanel();
		pCollDown.setLayout(new GridLayout(1,2));

		spinner2 = new SpinnerNumberModel(3,0,10,1);
		s_nbPakus = new JSpinner(spinner2); //valeur,min,max,pas
		s_nbPakus.setName("PakusColludeurs");
		s_nbPakus.setEnabled(false);
		l_sPakus = new JLabel("Nombre de pakus impliqués");

		pCollDown.add(l_sPakus);
		pCollDown.add(s_nbPakus);

		pCollusion.add(pCollUp);
		pCollusion.add(pCollDown);

		centre.add(pGeneral);
		centre.add(pDuree);
		centre.add(pCollusion);

		JPanel nord = new JPanel();
		JPanel sud = new JPanel();
		JPanel est = new JPanel();
		JPanel ouest = new JPanel();

		valider = new JButton("Valider");
		cancel = new JButton("Annuler");
		valider.addActionListener(this);
		cancel.addActionListener(this);
		sud.add(valider);
		sud.add(cancel);

		panel.add("North", nord);
		panel.add("South", sud);
		panel.add("East", est);
		panel.add("West", ouest);
		panel.add("Center", centre);
		panel.setSize(400, 400);
		this.setContentPane(panel);

	}

	public Hashtable<String,String> getParams(){
		Hashtable<String,String> params = new Hashtable<String,String>();
		if(dimension.getValue().equals(0) || nbPaku.getValue().equals(0)){
			System.out.println("Manque des params: exit");
			System.exit(0);
			
		}
		else {
			params.put("Dimension",""+dimension.getValue());
			params.put("nbPaku",""+nbPaku.getValue());
			params.put("nbPartie",""+duree.getText());
		}
		return params;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cb_duree){
			if(cb_duree.isSelected())
				duree.setEditable(true);
			if(!cb_duree.isSelected())
				duree.setEditable(false);
		}
		if(e.getSource() == cb_applyCollu){
			if(cb_applyCollu.isSelected()){
				cb_typeA.setEnabled(true);
				cb_typeB.setEnabled(true);
				cb_typeC.setEnabled(true);
				s_nbPakus.setEnabled(true);
			}
			if(!cb_applyCollu.isSelected()){
				cb_typeA.setEnabled(false);
				cb_typeB.setEnabled(false);
				cb_typeC.setEnabled(false);
				s_nbPakus.setEnabled(false);
			}
		}
		if(e.getSource() == valider){
			System.out.println("VALIDER !");
			((NewSample) commands.get(NewSample.NAME)).setIHM(this);
			commands.get(NewSample.NAME).execute();
			this.setVisible(false);
		}
		if(e.getSource() == cancel){
			this.setVisible(false);
		}
	}

	public void stateChanged(ChangeEvent e) {
		JSpinner spinner = (JSpinner) e.getSource();
		if(spinner.getName() == "Dimension"){
			dim = (Integer) spinner.getModel().getValue();
			debut = dim-(dim*dim/110);
			max = dim-(dim*dim/200);
			this.spinner.setValue(debut);
			this.spinner.setMaximum(max);
		}
		if(spinner.getName() == "NbPakus"){
			this.spinner2.setValue(this.spinner.getValue());
			this.spinner2.setMaximum((Comparable) this.spinner.getValue());
		}
	}

}
