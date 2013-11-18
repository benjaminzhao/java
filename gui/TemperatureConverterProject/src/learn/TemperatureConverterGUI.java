/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TemperatureConverterGUI.java
 *
 * Created on 2013-10-23, 12:18:32
 */
package learn;

import	java.lang.*;
import	java.util.*;
import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import  javax.swing.event.*;
import  javax.swing.text.*;
import  javax.swing.undo.*;
/**
 *
 * @author benjamin
 */
public class TemperatureConverterGUI extends javax.swing.JFrame {

	protected UndoManager undolist = new UndoManager();
	AbstractDocument doc1;
	AbstractDocument doc2;
	
	/** Creates new form TemperatureConverterGUI */
	public TemperatureConverterGUI() {
		initComponents();
			
		doc1 = (AbstractDocument) Celsius_TextField.getDocument();;
		doc1.addUndoableEditListener(new MyUndoableEditListener());
//		doc2 = (AbstractDocument) Fahrenheit_TextField.getDocument();;
//		doc2.addUndoableEditListener(new MyUndoableEditListener());
		
		this.addKeyListener(new KeyAdapter(){
			public void KeyPressed(KeyEvent e){
				System.out.println("key pressed");
				int keycode = e.getKeyCode();
				System.out.println(keycode);	
			}
		});
		
//		Exit_MenuItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				
//			}
//		});
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        Main_Panel = new javax.swing.JPanel();
        Celsius_TextField = new javax.swing.JTextField();
        Fahrenheit_TextField = new javax.swing.JTextField();
        Celsius_Label = new javax.swing.JLabel();
        Fahrenheit_Label = new javax.swing.JLabel();
        X_Label = new javax.swing.JLabel();
        Y_Label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        Open_Menu = new javax.swing.JMenu();
        Open_MenuItem = new javax.swing.JMenuItem();
        Exit_MenuItem = new javax.swing.JMenuItem();
        Edit_Menu = new javax.swing.JMenu();
        Undo_MenuItem = new javax.swing.JMenuItem();
        Redo_MenuItem = new javax.swing.JMenuItem();
        About_Menu = new javax.swing.JMenu();
        About_MenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TemperatureConverter");
        setBackground(new java.awt.Color(255, 255, 153));
        setResizable(false);

        Main_Panel.setBackground(new java.awt.Color(255, 204, 0));
        Main_Panel.setPreferredSize(new java.awt.Dimension(150, 80));

        Celsius_TextField.setColumns(10);
        Celsius_TextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        Celsius_TextField.setToolTipText("input number");
        Celsius_TextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Celsius_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Celsius_TextFieldKeyReleased(evt);
            }
        });

        Fahrenheit_TextField.setColumns(10);
        Fahrenheit_TextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        Fahrenheit_TextField.setToolTipText("input number");
        Fahrenheit_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Fahrenheit_TextFieldKeyReleased(evt);
            }
        });

        Celsius_Label.setText("Celsius");
        Celsius_Label.setToolTipText("");

        Fahrenheit_Label.setText("Fahrenheit");

        javax.swing.GroupLayout Main_PanelLayout = new javax.swing.GroupLayout(Main_Panel);
        Main_Panel.setLayout(Main_PanelLayout);
        Main_PanelLayout.setHorizontalGroup(
            Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Main_PanelLayout.createSequentialGroup()
                        .addComponent(Celsius_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Celsius_Label))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Main_PanelLayout.createSequentialGroup()
                        .addComponent(Fahrenheit_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Fahrenheit_Label)))
                .addContainerGap())
        );

        Main_PanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Celsius_TextField, Fahrenheit_TextField});

        Main_PanelLayout.setVerticalGroup(
            Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_PanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Celsius_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Celsius_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Fahrenheit_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fahrenheit_Label))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        Main_PanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Celsius_TextField, Fahrenheit_TextField});

        Celsius_TextField.getAccessibleContext().setAccessibleName("");

        X_Label.setText("X=0");

        Y_Label.setText("Y=0");

        jButton1.setText("Brose");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        MenuBar.setBackground(new java.awt.Color(102, 102, 0));

        Open_Menu.setText("File");

        Open_MenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        Open_MenuItem.setText("Open");
        Open_Menu.add(Open_MenuItem);

        Exit_MenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, java.awt.event.InputEvent.CTRL_MASK));
        Exit_MenuItem.setText("Exit");
        Exit_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exit_MenuItemActionPerformed(evt);
            }
        });
        Open_Menu.add(Exit_MenuItem);

        MenuBar.add(Open_Menu);

        Edit_Menu.setText("Edit");

        Undo_MenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        Undo_MenuItem.setText("Undo");
        Undo_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Undo_MenuItemActionPerformed(evt);
            }
        });
        Edit_Menu.add(Undo_MenuItem);

        Redo_MenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        Redo_MenuItem.setText("Redo");
        Redo_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Redo_MenuItemActionPerformed(evt);
            }
        });
        Edit_Menu.add(Redo_MenuItem);

        MenuBar.add(Edit_Menu);

        About_Menu.setText("About");
        About_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                About_MenuActionPerformed(evt);
            }
        });

        About_MenuItem.setText("About");
        About_MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                About_MenuItemActionPerformed(evt);
            }
        });
        About_Menu.add(About_MenuItem);

        MenuBar.add(About_Menu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Y_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(86, 86, 86))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(X_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Main_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(X_Label)
                    .addComponent(jButton1))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Y_Label)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        X_Label.getAccessibleContext().setAccessibleName("0");

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void Celsius_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Celsius_TextFieldKeyReleased
		// TODO add your handling code here:
		char key = evt.getKeyChar();
		if ( (key>='a' && key<='z') || (key>='A' && key<='Z') || (key == '/') )
		{
			Celsius_TextField.setEditable(false);
		}
		else
		{
			//Celsius_TextField.setText(Character.toString(key));
			Celsius_TextField.setEditable(true);
			String input = Celsius_TextField.getText();
			if(!input.isEmpty())
			{
				double tempFahrenheit = Double.parseDouble(input)*1.8+32;
				Fahrenheit_TextField.setText(Double.toString(tempFahrenheit));
			}
		}
	}//GEN-LAST:event_Celsius_TextFieldKeyReleased

	private void Fahrenheit_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Fahrenheit_TextFieldKeyReleased
		// TODO add your handling code here:
		char key = evt.getKeyChar();
		if ( (key>='a' && key<='z') || (key>='A' && key<='Z') || (key == '/') )
		{
			Fahrenheit_TextField.setEditable(false);
		}
		else
		{
			//Celsius_TextField.setText(Character.toString(key));
			Fahrenheit_TextField.setEditable(true);
			String input = Fahrenheit_TextField.getText();
			if(!input.isEmpty())
			{
				double tempCelsius= (Double.parseDouble(input)-32)/1.8;
				Celsius_TextField.setText(Double.toString(tempCelsius));
			}
		}
	}//GEN-LAST:event_Fahrenheit_TextFieldKeyReleased

	private void Undo_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Undo_MenuItemActionPerformed
		// TODO add your handling code here:
		try{
			undolist.undo();
		}catch(CannotUndoException ex){
			System.out.println("Unable to undo: "+ex);
			ex.printStackTrace();
		}
		updateUndoState();
		updateRedoState();
	}//GEN-LAST:event_Undo_MenuItemActionPerformed

	private void Redo_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Redo_MenuItemActionPerformed
		// TODO add your handling code here:
		try{
			undolist.redo();
		}catch(CannotRedoException ex){
			System.out.println("Unable to redo: "+ex);
			ex.printStackTrace();
		}
		updateRedoState();
		updateUndoState();
	}//GEN-LAST:event_Redo_MenuItemActionPerformed

	private void Exit_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Exit_MenuItemActionPerformed
		// TODO add your handling code here:
		System.exit(0);
	}//GEN-LAST:event_Exit_MenuItemActionPerformed

	private void About_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_About_MenuActionPerformed
		// TODO add your handling code here:
		
	}//GEN-LAST:event_About_MenuActionPerformed

	private void About_MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_About_MenuItemActionPerformed
		// TODO add your handling code here:
		System.out.println("about clicked...");
		JFrame about = new JFrame("About");
		about.setSize(200, 200);
		about.setLayout(null);
		about.setResizable(false);
		about.setLocationRelativeTo(null);
		about.setVisible(true);
	}//GEN-LAST:event_About_MenuItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser a = new JFileChooser();
        int ret = a.showOpenDialog(TemperatureConverterGUI.this);
        if (ret == JFileChooser.APPROVE_OPTION){
            jLabel1.setText( a.getSelectedFile().toString() );
        }
    }//GEN-LAST:event_jButton1ActionPerformed

	protected class MyUndoableEditListener implements UndoableEditListener{
		public void undoableEditHappened(UndoableEditEvent e) {
			undolist.addEdit(e.getEdit());
			updateUndoState();
			updateRedoState();
		}
	}
	protected void updateUndoState(){
		if (undolist.canUndo()){
			Undo_MenuItem.setEnabled(true);
			//Undo_MenuItem.putValue(Action.NAME, undo.getUndoPresentationName());
		}else{
			Undo_MenuItem.setEnabled(false);
			//Undo_MenuItem.putValue(Action.NAME, "Undo");
		}
	}

	protected void updateRedoState(){
		if (undolist.canRedo()){
			Redo_MenuItem.setEnabled(true);
			//Redo_MenuItem.putValue(Action.NAME, undo.getRedoPresentationName());
		}else{
			Redo_MenuItem.setEnabled(false);
			//Redo_MenuItem.putValue(Action.NAME, "Redo");
		}
	}

//	PointerInfo a =MouseInfo.getPointerInfo();
//	Point b = getLocation();
//	int x = (int) b.getX();
//	int y = (int) b.getY();
//		X_Label.setText(x);
//		Y_Label.setText(y);
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TemperatureConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TemperatureConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TemperatureConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TemperatureConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new TemperatureConverterGUI().setVisible(true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu About_Menu;
    private javax.swing.JMenuItem About_MenuItem;
    private javax.swing.JLabel Celsius_Label;
    private javax.swing.JTextField Celsius_TextField;
    private javax.swing.JMenu Edit_Menu;
    private javax.swing.JMenuItem Exit_MenuItem;
    private javax.swing.JLabel Fahrenheit_Label;
    private javax.swing.JTextField Fahrenheit_TextField;
    private javax.swing.JPanel Main_Panel;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu Open_Menu;
    private javax.swing.JMenuItem Open_MenuItem;
    private javax.swing.JMenuItem Redo_MenuItem;
    private javax.swing.JMenuItem Undo_MenuItem;
    private javax.swing.JLabel X_Label;
    private javax.swing.JLabel Y_Label;
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
