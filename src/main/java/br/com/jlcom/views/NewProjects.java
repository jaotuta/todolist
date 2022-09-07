package br.com.jlcom.views;

import br.com.jlcom.controllers.ProjectController;
import br.com.jlcom.models.Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class NewProjects extends JDialog  {


    JPanel textPanel, panelForTextFields, completionPanel;
    JLabel titleLabel, usernameLabel, passwordLabel, userLabel, passLabel;
    JTextField projetoJTextField;
    JTextArea projectDescriptionJTextArea;
    JButton saveProjectButton;

    JDialog dialogProject = new JDialog();
    ProjectController projectController;

    public NewProjects() {
        projectController = new ProjectController();
    }
    public JDialog init() {
        dialogProject.setContentPane(createContentPane());
        dialogProject.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogProject.setSize(350, 300);
        dialogProject.setLocationRelativeTo(null);
        dialogProject.setResizable(false);
        dialogProject.setFocusableWindowState(true);
        dialogProject.setModalityType(ModalityType.APPLICATION_MODAL);
        dialogProject.setVisible(true);

        return dialogProject;
    }
    public JPanel createContentPane (){

        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        titleLabel = new JLabel("Adicionando novo projeto.");
        titleLabel.setIcon(new ImageIcon("C:\\Users\\jaotu\\IdeaProjects\\todoapp\\src\\main\\resources\\lista-de-desejos.png"));
        titleLabel.setLocation(0,0);
        titleLabel.setSize(350, 50);
        titleLabel.setHorizontalAlignment(0);
        totalGUI.add(titleLabel);

        // Creation of a Panel to contain the JLabels
        textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setLocation(15, 55);
        textPanel.setSize(100, 80);
        totalGUI.add(textPanel);

        // Username Label
        usernameLabel = new JLabel("Nome Projeto");
        usernameLabel.setLocation(0, 0);
        usernameLabel.setSize(100, 40);
        usernameLabel.setHorizontalAlignment(2);
        textPanel.add(usernameLabel);

        // Login Label
        passwordLabel = new JLabel("Descricao");
        passwordLabel.setLocation(0, 40);
        passwordLabel.setSize(100, 40);
        passwordLabel.setHorizontalAlignment(2);
        textPanel.add(passwordLabel);

        // TextFields Panel Container
        panelForTextFields = new JPanel();
        panelForTextFields.setLayout(null);
        panelForTextFields.setLocation(120, 60);
        panelForTextFields.setSize(200, 145);
        totalGUI.add(panelForTextFields);

        // Campo para o nome do Projeto
        projetoJTextField = new JTextField(8);
        projetoJTextField.setLocation(0, 0);
        projetoJTextField.setSize(200, 30);
        projetoJTextField.setBorder(BorderFactory.createEmptyBorder());
        panelForTextFields.add(projetoJTextField);
        projetoJTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                projetoJTextField.setBackground(Color.white);
                projetoJTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(projetoJTextField.getText().isEmpty()){
                    projetoJTextField.setBackground(new Color(255, 133, 133));
                    projetoJTextField.setText("Campo Obrigatorio");
                    projetoJTextField.setForeground(Color.white);
                    projetoJTextField.setFont(new Font("Arial", 1, 12));
                    projetoJTextField.setBorder(new EmptyBorder(0,5,0,0));
                }
            }
        });
        // Campo para o descrição do Projeto
        projectDescriptionJTextArea = new JTextArea("",5,30);
        projectDescriptionJTextArea.setLocation(0, 40);
        projectDescriptionJTextArea.setSize(200, 100);
        projectDescriptionJTextArea.setLineWrap(true);
        projectDescriptionJTextArea.setWrapStyleWord(true);
        panelForTextFields.add(projectDescriptionJTextArea);

        // Button Panel Container
        JPanel panelForButtons = new JPanel();
        panelForButtons.setLayout(null);
        panelForButtons.setLocation(30, 220);
        panelForButtons.setSize(300, 50);
        totalGUI.add(panelForButtons);

        // Button for Logging in
        saveProjectButton = new JButton("Add Projeto");
        saveProjectButton.setLocation(160, 0);
        saveProjectButton.setSize(130, 30);
        saveProjectButton.setHorizontalAlignment(0);
        saveProjectButton.setBackground(new Color(83, 154, 212));
        saveProjectButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    if (!projetoJTextField.getText().equals("")) {
                        Project project = new Project();
                        project.setName(projetoJTextField.getText());
                        project.setDescription(projectDescriptionJTextArea.getText());
                        projectController.save(project);
                        JOptionPane.showMessageDialog(rootPane,"Projeto salvo com sucesso");
                        dialogProject.dispose();

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "O campo nome eh obrigatorio");
                    }

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(rootPane,"Deu erro, corre aqui!");
                    System.out.println(exception);
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        panelForButtons.add(saveProjectButton);

        totalGUI.setOpaque(true);
        return totalGUI;
    }

    void createAndShowGUI() {
        NewProjects demo = new NewProjects();
        demo.init();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NewProjects projects = new NewProjects();
                projects.createAndShowGUI();
            }
        });
    }



}
