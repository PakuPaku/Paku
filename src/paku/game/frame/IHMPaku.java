package paku.game.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

import paku.game.engine.GameEngine;
import paku.game.listener.MenuBarListener;
import paku.game.command.*;

/**
 * 
 * @author zimon
 * Main frame de l'appli, dans l'ideal need implantation d'un DP Observer dans le cas d'une lecture
 * de partie
 */
public class IHMPaku extends JFrame{
	
	private static final String FRAME_TITLE = "PakuPaku";
	private static final String FILE_MENU = "Fichier";
	private static final String EDIT_MENU = "Edition";
	private static final String HELP_MENU = "?";
	
	public static final String NEW = "Nouveau";
	public static final String OPEN = "Ouvrir...";
	public static final String SAVE = "Enregistrer";
	public static final String SAVE_AS = "Enregistrer sous...";
	public static final String QUIT = "Quitter";
	public static final String CANCEL = "Annuler";
	public static final String RESTORE = "Rétablir";
	public static final String CUT = "Couper";
	public static final String COPY = "Copier";
	public static final String PASTE = "Coller";
	public static final String HELP = "Aide";
	public static final String ABOUT = "A propos";
	
	public static JFileChooser chooser;
	public static File root;
	private Hashtable<String,Command> commands;
	private GameEngine engine;

	public IHMPaku (Hashtable<String,Command> commands, GameEngine engine){
		super(FRAME_TITLE);
		this.commands = commands;
		this.engine = engine;
		
		
		// default minimum size
		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		this.setSize(d.width, d.height);
		JPanel pan = new JPanel();
		//pan.setBounds(0, 0, d.width, d.height);
		//this.setContentPane(pan);
		
		// sets the frame location to be centered in screen
		
		this.setLocationRelativeTo(null);
		
		// make this frame the application frame
		// so that when you close it, everything stops
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	

		this.drawContent();
		this.setVisible(true);
		
	}
	
	private void drawContent() {
		IHMPanel panel = new IHMPanel();
		//this.getContentPane().add(panel, BorderLayout.CENTER);
		
		drawMenu();
		drawTree();
		
		// TODO
	}
	
	/**
	 * Initialize and draw the Menu Bar of the frame
	 */
	private void drawMenu() {
		// menu bar of the application
		JMenuBar menuBar = new JMenuBar();
		
		// menu bar listener
		MenuBarListener menuListener = new MenuBarListener(this);
		
		// menu item "Fichier"
		JMenu fileMenu = new JMenu(FILE_MENU);
		JMenuItem newItem = new JMenuItem(NEW);
		newItem.addActionListener(menuListener);
		fileMenu.add(newItem);
		JMenuItem openItem = new JMenuItem(OPEN);
		openItem.addActionListener(menuListener);
		fileMenu.add(openItem);
		fileMenu.addSeparator(); // separate implied groups with a graphical bar
		JMenuItem saveItem = new JMenuItem(SAVE);
		saveItem.addActionListener(menuListener);
		fileMenu.add(saveItem);
		JMenuItem saveAsItem = new JMenuItem(SAVE_AS);
		saveAsItem.addActionListener(menuListener);
		fileMenu.add(saveAsItem);
		fileMenu.addSeparator(); // separate implied groups with a graphical bar
		JMenuItem quitItem = new JMenuItem(QUIT);
		quitItem.addActionListener(menuListener);
		fileMenu.add(quitItem);
		menuBar.add(fileMenu);
		
		// menu item "Edition"
		JMenu editMenu = new JMenu(EDIT_MENU);
		JMenuItem cancelItem = new JMenuItem(CANCEL);
		cancelItem.addActionListener(menuListener);
		editMenu.add(cancelItem);
		JMenuItem restoreItem = new JMenuItem(RESTORE);
		restoreItem.addActionListener(menuListener);
		editMenu.add(restoreItem);
		editMenu.addSeparator(); // separate implied groups with a graphical bar
		JMenuItem cutItem = new JMenuItem(CUT);
		cutItem.addActionListener(menuListener);
		editMenu.add(cutItem);
		JMenuItem copyItem = new JMenuItem(COPY);
		copyItem.addActionListener(menuListener);
		editMenu.add(copyItem);
		JMenuItem pasteItem = new JMenuItem(PASTE);
		pasteItem.addActionListener(menuListener);
		editMenu.add(pasteItem);
		menuBar.add(editMenu);
		
		// menu item "?"
		JMenu helpMenu = new JMenu(HELP_MENU);
		JMenuItem helpItem = new JMenuItem(HELP);
		helpItem.addActionListener(menuListener);
		helpMenu.add(helpItem);
		JMenuItem aboutItem = new JMenuItem(ABOUT);
		aboutItem.addActionListener(menuListener);
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);
		
		// adding the menubar to the frame
		this.setJMenuBar(menuBar);
	}
	
	public void newFile(){
		
		NewFileFrame newFile = new NewFileFrame(commands);
		newFile.setVisible(true);
		
		// sets the frame location to be centered in screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point screenCenter = new Point(screenSize.width/2, screenSize.height/2);
		Point frameTopLeftCornerPoint = new Point(
				screenCenter.x- (getWidth()/3),
				screenCenter.y - (getHeight()/3));
		newFile.setLocation(frameTopLeftCornerPoint);
		
		// make this frame the application frame
		// so that when you close it, everything stops
		
		
	}
	public void drawMap(){
		
	}
	
	public void openFile(){
		
		chooser = new JFileChooser();
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			
		}
	}
	
	public void drawTree(){
		
		
		root = new File("./");
		
		// Create a TreeModel object to represent our tree of files
	    FileTreeModel model = new FileTreeModel(root);

	    // Create a JTree and tell it to display our model
	    JTree tree = new JTree();
	    tree.setModel(new FileTreeModel(root));

	    // The JTree can get big, so allow it to scroll.
	    JScrollPane scrollpane = new JScrollPane(tree);
	    
	    // Display it all in a window and make the window appear
	
	    this.getContentPane().add(scrollpane, BorderLayout.WEST);

	}
	

}
