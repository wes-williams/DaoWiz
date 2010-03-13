/*
 * Created on Nov 4, 2004
 */

package com.wesleyware.daowiz.gui;


import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * GUI interface for creating DAO
 *
 * @author Wesley Williams
 *
 */
public class DaoWizGUI extends javax.swing.JFrame
{
    private FieldTableModel fieldTableModel = new FieldTableModel();
    private TableBean tableBean;;
    private File historyFile;
    
    private FileFilter daoWizFileFilter = new FileFilter()
    {
        
        public boolean accept(File pathName)
        {
            return pathName.getName().endsWith(".dao");
        }
        
        public String getDescription()
        {
            return " *.dao (DaoWiz files)";
        }
        
    };
    
    
    /** Creates new form DaoWizGenerator */
    public DaoWizGUI()
    {
        initComponents();
        
        initTable();
    }
    
    private void initTable()
    {
        jTableFields.getColumnModel().getColumn(FieldTableModel.FIELD_TYPE).setCellEditor(new DefaultCellEditor(new JComboBox(FieldTypes.getFieldTypeBeans())));
        jTableFields.getColumnModel().getColumn(FieldTableModel.COMMENTS).setMinWidth(175);
        jTableFields.getColumnModel().getColumn(FieldTableModel.IS_FK).setMaxWidth(30);
        jTableFields.getColumnModel().getColumn(FieldTableModel.IS_PK).setMaxWidth(30);
        jTableFields.getColumnModel().getColumn(FieldTableModel.HAS_DEFAULT_VALUE).setMaxWidth(50);
        jTableFields.getColumnModel().getColumn(FieldTableModel.IS_NOT_NULL).setMaxWidth(50);
        jTableFields.getColumnModel().getColumn(FieldTableModel.SCALE).setMaxWidth(50);
        jTableFields.getColumnModel().getColumn(FieldTableModel.SIZE).setMaxWidth(50);
        jTableFields.getColumnModel().getColumn(FieldTableModel.FIELD_TYPE).setMinWidth(80);
        jTableFields.getColumnModel().getColumn(FieldTableModel.FIELD_NAME).setMinWidth(75);
        jTableFields.getColumnModel().getColumn(FieldTableModel.VARIABLE_NAME).setMinWidth(75);
        
        
        MouseAdapter popupListener = new  MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                maybeShowPopup(e);
            }
            
            public void mouseReleased(MouseEvent e)
            {
                maybeShowPopup(e);
            }
            
            private void maybeShowPopup(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    jPopupMenuFields.show(e.getComponent(),
                    e.getX(), e.getY());
                }
            }
        };
        
        jScrollPaneFields.addMouseListener(popupListener);
        jTableFields.addMouseListener(popupListener);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {
        jPopupMenuFields = new javax.swing.JPopupMenu();
        jMenuItemAddField = new javax.swing.JMenuItem();
        jMenuItemRemoveFields = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPackage = new javax.swing.JTextField();
        jTextFieldTableName = new javax.swing.JTextField();
        jTextFieldClassName = new javax.swing.JTextField();
        jScrollPaneFields = new javax.swing.JScrollPane();
        jTableFields = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPaneNotes = new javax.swing.JScrollPane();
        jTextAreaNotes = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemFileNew = new javax.swing.JMenuItem();
        jMenuItemFileOpen = new javax.swing.JMenuItem();
        jMenuItemFileSave = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItemFileExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemEditNewField = new javax.swing.JMenuItem();
        jMenuItemEditRemoveFields = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemHelpInstructions = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItemHelpAbout = new javax.swing.JMenuItem();

        jMenuItemAddField.setText("Add New Field");
        jMenuItemAddField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemAddFieldActionPerformed(evt);
            }
        });

        jPopupMenuFields.add(jMenuItemAddField);

        jMenuItemRemoveFields.setText("Remove Selected Items");
        jMenuItemRemoveFields.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemRemoveFieldsActionPerformed(evt);
            }
        });

        jPopupMenuFields.add(jMenuItemRemoveFields);

        getContentPane().setLayout(null);

        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Package:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 20, 60, 15);

        jLabel2.setText("Table Name:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(280, 50, 80, 15);

        jLabel3.setText("Class Name:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 50, 90, 15);

        getContentPane().add(jTextFieldPackage);
        jTextFieldPackage.setBounds(120, 20, 370, 21);

        getContentPane().add(jTextFieldTableName);
        jTextFieldTableName.setBounds(360, 50, 130, 21);

        getContentPane().add(jTextFieldClassName);
        jTextFieldClassName.setBounds(120, 50, 130, 21);

        jTableFields.setModel(fieldTableModel);
        jScrollPaneFields.setViewportView(jTableFields);

        getContentPane().add(jScrollPaneFields);
        jScrollPaneFields.setBounds(20, 150, 720, 320);

        jLabel4.setText("Notes:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 80, 40, 15);

        jScrollPaneNotes.setViewportView(jTextAreaNotes);

        getContentPane().add(jScrollPaneNotes);
        jScrollPaneNotes.setBounds(120, 80, 370, 60);

        jMenuFile.setMnemonic('F');
        jMenuFile.setText("File");
        jMenuItemFileNew.setMnemonic('N');
        jMenuItemFileNew.setText("New");
        jMenuItemFileNew.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemFileNewActionPerformed(evt);
            }
        });

        jMenuFile.add(jMenuItemFileNew);

        jMenuItemFileOpen.setMnemonic('O');
        jMenuItemFileOpen.setText("Open");
        jMenuItemFileOpen.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemFileOpenActionPerformed(evt);
            }
        });

        jMenuFile.add(jMenuItemFileOpen);

        jMenuItemFileSave.setMnemonic('s');
        jMenuItemFileSave.setText("Save");
        jMenuItemFileSave.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemFileSaveActionPerformed(evt);
            }
        });

        jMenuFile.add(jMenuItemFileSave);

        jMenuFile.add(jSeparator1);

        jMenuItemFileExit.setMnemonic('x');
        jMenuItemFileExit.setText("Exit");
        jMenuItemFileExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemFileExitActionPerformed(evt);
            }
        });

        jMenuFile.add(jMenuItemFileExit);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setMnemonic('E');
        jMenuEdit.setText("Edit");
        jMenuItemEditNewField.setMnemonic('w');
        jMenuItemEditNewField.setText("New Field");
        jMenuItemEditNewField.setActionCommand("Add New Field");
        jMenuItemEditNewField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemEditNewFieldActionPerformed(evt);
            }
        });

        jMenuEdit.add(jMenuItemEditNewField);

        jMenuItemEditRemoveFields.setMnemonic('R');
        jMenuItemEditRemoveFields.setText("Remove Selected Fields");
        jMenuItemEditRemoveFields.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemEditRemoveFieldsActionPerformed(evt);
            }
        });

        jMenuEdit.add(jMenuItemEditRemoveFields);

        jMenuBar1.add(jMenuEdit);

        jMenuHelp.setMnemonic('H');
        jMenuHelp.setText("Help");
        jMenuItemHelpInstructions.setMnemonic('I');
        jMenuItemHelpInstructions.setText("Instructions");
        jMenuItemHelpInstructions.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemHelpInstructionsActionPerformed(evt);
            }
        });

        jMenuHelp.add(jMenuItemHelpInstructions);

        jMenuHelp.add(jSeparator2);

        jMenuItemHelpAbout.setMnemonic('A');
        jMenuItemHelpAbout.setText("About");
        jMenuItemHelpAbout.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItemHelpAboutActionPerformed(evt);
            }
        });

        jMenuHelp.add(jMenuItemHelpAbout);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }//GEN-END:initComponents

    private void jMenuItemHelpInstructionsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemHelpInstructionsActionPerformed
    {//GEN-HEADEREND:event_jMenuItemHelpInstructionsActionPerformed
        StringBuffer buf = new StringBuffer(1200);
        buf.append("This program generates most of the data access code needed to interface with the DaoWiz API.\n");
        buf.append("This code can be generated by hand. This progaram is provided only as a convenience to users.\n\n");
        buf.append("To begin using this tool just define your table structure, give Java variable names to each field, and\n");
        buf.append("provide comments about the purpose of the table and each field.\n\n");
        buf.append("You can add fields to the table by right clicking on the table and choosing the add new field menu\n");
        buf.append("item. This can also be done through the edit menu on the menu bar. All relevant fields must be\n");
        buf.append("completed before the Java source files can be created. This is done by saving the table configuration\n");
        buf.append("using the save option in the file menu. The file must be saved with a '.dao' extension, and it can be used\n");
        buf.append("to restore the table configuration at any time. If the table configuration is valid the program will prompt\n");
        buf.append("about creating the source files. These files will be created in the same directory as the '.dao' file. All code\n");
        buf.append("will be fully generated except for that dealing with foreign keys. This requires the user to relate DAOs together.\n\n");
        buf.append("That is all there is to it. I hope enjoy using this utility as much I as I enjoyed making it!");
  
        JOptionPane.showMessageDialog(this,buf.toString(),"Instructions",JOptionPane.INFORMATION_MESSAGE);   
    }//GEN-LAST:event_jMenuItemHelpInstructionsActionPerformed
    
    private void jMenuItemHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHelpAboutActionPerformed
        JOptionPane.showMessageDialog(this,"DaoWiz (DAO Wizard)\nVersion: 1.0\nAuthor: Wesley Williams","About",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItemHelpAboutActionPerformed
    
    private void jMenuItemEditRemoveFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditRemoveFieldsActionPerformed
        removeFields();
    }//GEN-LAST:event_jMenuItemEditRemoveFieldsActionPerformed
    
    private void jMenuItemEditNewFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditNewFieldActionPerformed
        addField();
    }//GEN-LAST:event_jMenuItemEditNewFieldActionPerformed
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exitOperation();
    }//GEN-LAST:event_formWindowClosing
    
    private void jMenuItemFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFileExitActionPerformed
        exitOperation();
        
    }//GEN-LAST:event_jMenuItemFileExitActionPerformed
    
    private void jMenuItemFileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFileSaveActionPerformed
        
        loadScreen();
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(daoWizFileFilter);
        
        if(historyFile != null)
            fileChooser.setSelectedFile(historyFile);
        
        if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            if(selectedFile==null)return;
            if(!selectedFile.getName().endsWith(".dao"))
            {
                JOptionPane.showMessageDialog(this,"File must have a .dao extension","Invalid File",JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if(!selectedFile.exists() || JOptionPane.showConfirmDialog(this,"Do you want to overwrite this file?\n(" +  selectedFile.getAbsolutePath() + ")","Overwrite",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                try
                {
                    ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(selectedFile)));
                    try
                    {
                        out.writeObject(tableBean);
                    }finally
                    {
                        out.close();
                    }
                    historyFile = selectedFile;
                    
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(this,"Unable to write to file","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            
            if(tableBean.isValid())
            {
                if(JOptionPane.showConfirmDialog(this,"Do you want to create the Data Access Objects?","Create DAO",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    String directory = selectedFile.getAbsolutePath();
                    directory = directory.substring(0,directory.indexOf(selectedFile.getName()));
                    
                    try
                    {
                        File baseDataBeanFile = new File(directory + tableBean.getClassName() + "Base.java");
                        if(!baseDataBeanFile.exists() || JOptionPane.showConfirmDialog(this,"Do you want to overwrite this file?\n(" +  directory + tableBean.getClassName() + "Base.java)" ,"Overwrite",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                            writeJavaFile(baseDataBeanFile,DaoCreator.printBaseRecord(tableBean));
                        File baseDaoFile = new File(directory + tableBean.getClassName() + "DaoBase.java");
                        if(!baseDaoFile.exists() || JOptionPane.showConfirmDialog(this,"Do you want to overwrite this file?\n(" +  directory + tableBean.getClassName() + "DaoBase.java)","Overwrite",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                            writeJavaFile(baseDaoFile,DaoCreator.printBaseDao(tableBean));
                        File dataBeanFile = new File(directory + tableBean.getClassName() + ".java");
                        if(!dataBeanFile.exists() || JOptionPane.showConfirmDialog(this,"Do you want to overwrite this file?\n(" +  directory + tableBean.getClassName() + ".java)","Overwrite",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                            writeJavaFile(dataBeanFile,DaoCreator.printExtensionRecord(tableBean));
                        File daoFile = new File(directory + tableBean.getClassName() + "Dao.java");
                        if(!daoFile.exists() || JOptionPane.showConfirmDialog(this,"Do you want to overwrite this file?\n(" +  directory + tableBean.getClassName() + "Dao.java)","Overwrite",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                            writeJavaFile(daoFile,DaoCreator.printExtensionDao(tableBean));
                        JOptionPane.showMessageDialog(this,"All files were saved successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(this,"Unable to write to file(s)","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                        
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Data Access Objects can not be created with the given definition.","Uncomplete DAO",JOptionPane.INFORMATION_MESSAGE);
                
            }
        }
        
    }//GEN-LAST:event_jMenuItemFileSaveActionPerformed
    
    private void writeJavaFile(File file,String data) throws Exception
    {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        try
        {
            out.print(data);
        }finally
        {
            out.close();
        }
        
    }
    
    
    private void jMenuItemFileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFileOpenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(daoWizFileFilter);
        
        
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            
            if(selectedFile==null)return;
            if(!selectedFile.getName().endsWith(".dao"))
            {
                JOptionPane.showMessageDialog(this,"File must have a .dao extension","Invalid File",JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try
            {
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(selectedFile)));
                try
                {
                    tableBean = (TableBean) in.readObject();
                }finally
                {
                    in.close();
                }
                
                this.populateScreen();
                historyFile = selectedFile;
                
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this,"Unable to load from file","Error",JOptionPane.ERROR_MESSAGE);
                
            }
            
        }
        
        
    }//GEN-LAST:event_jMenuItemFileOpenActionPerformed
    
    private void jMenuItemFileNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFileNewActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Are you sure?","New Screen",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            clearScreen();
    }//GEN-LAST:event_jMenuItemFileNewActionPerformed
    
    private void jMenuItemRemoveFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRemoveFieldsActionPerformed
        removeFields();
    }//GEN-LAST:event_jMenuItemRemoveFieldsActionPerformed
    
    private void jMenuItemAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddFieldActionPerformed
        addField();
    }//GEN-LAST:event_jMenuItemAddFieldActionPerformed
    
    private void removeFields()
    {
        if(jTableFields.isEditing() && !jTableFields.getCellEditor().stopCellEditing())
        {
            JOptionPane.showMessageDialog(this,"Please finish editing the table before removing a row","Invalid Value",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedRow = -1;
        while( (selectedRow = jTableFields.getSelectedRow())!=-1)
        {
            fieldTableModel.deleteField(selectedRow);
        }
        
        
    }
    
    
    private void addField()
    {
        if(jTableFields.isEditing() && !jTableFields.getCellEditor().stopCellEditing())
        {
            JOptionPane.showMessageDialog(this,"Please finish editing the table before adding a new row","Invalid Value",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        fieldTableModel.addField(null);
        
    }
    
    private void clearScreen()
    {
        jTextAreaNotes.setText("");
        jTextFieldClassName.setText("");
        jTextFieldTableName.setText("");
        jTextFieldPackage.setText("");
        
        if(jTableFields.isEditing())
            jTableFields.getCellEditor().cancelCellEditing();
        
        fieldTableModel.setFields(null);
        historyFile = null;
    }
    
    private void loadScreen()
    {
        if(tableBean==null)tableBean = new TableBean();
        
        tableBean.setPackageName(jTextFieldPackage.getText());
        tableBean.setTableName(jTextFieldTableName.getText());
        tableBean.setClassName(jTextFieldClassName.getText());
        tableBean.setNotes(jTextAreaNotes.getText());
        
        if(jTableFields.isEditing())
            jTableFields.getCellEditor().cancelCellEditing();
        
        tableBean.setFields(fieldTableModel.getFields());
        
    }
    
    private void populateScreen()
    {
        if(tableBean==null)return;
        
        jTextFieldPackage.setText(tableBean.getPackageName());
        jTextFieldTableName.setText(tableBean.getTableName());
        jTextFieldClassName.setText(tableBean.getClassName());
        jTextAreaNotes.setText(tableBean.getNotes());
        
        if(jTableFields.isEditing())
            jTableFields.getCellEditor().cancelCellEditing();
        
        fieldTableModel.setFields(tableBean.getFields());
        
    }
    
    /** Exit the Application */
    private void exitOperation()
    {
        if(JOptionPane.showConfirmDialog(this,"Are you sure you want to exit?","Exit",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        DaoWizGUI frame = new DaoWizGUI();
        frame.setSize(765,545);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() - frame.getWidth()) / 2;
        int y = (int) (screenSize.getHeight() - frame.getHeight()) / 2;
        frame.setLocation(x,y);
        
        frame.setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAddField;
    private javax.swing.JMenuItem jMenuItemEditNewField;
    private javax.swing.JMenuItem jMenuItemEditRemoveFields;
    private javax.swing.JMenuItem jMenuItemFileExit;
    private javax.swing.JMenuItem jMenuItemFileNew;
    private javax.swing.JMenuItem jMenuItemFileOpen;
    private javax.swing.JMenuItem jMenuItemFileSave;
    private javax.swing.JMenuItem jMenuItemHelpAbout;
    private javax.swing.JMenuItem jMenuItemHelpInstructions;
    private javax.swing.JMenuItem jMenuItemRemoveFields;
    private javax.swing.JPopupMenu jPopupMenuFields;
    private javax.swing.JScrollPane jScrollPaneFields;
    private javax.swing.JScrollPane jScrollPaneNotes;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableFields;
    private javax.swing.JTextArea jTextAreaNotes;
    private javax.swing.JTextField jTextFieldClassName;
    private javax.swing.JTextField jTextFieldPackage;
    private javax.swing.JTextField jTextFieldTableName;
    // End of variables declaration//GEN-END:variables
    
}
