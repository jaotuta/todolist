package br.com.jlcom.views;

import br.com.jlcom.controllers.TaskController;
import br.com.jlcom.models.Project;
import br.com.jlcom.models.Task;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTasks extends JDialog {

    JPanel panelForTextFields = new JPanel();
    JLabel passwordLabel = new JLabel("Descricao");
    JTextField taskNameField = new JTextField(8);
    JTextArea taskDescriptionArea = new JTextArea("",5,30);
    JButton saveTaskButton;
    JDialog dialogTasks = new JDialog();
    TaskController taskController;
    JLabel titleLabel = new JLabel("Adicionando nova Tarefa.");
    JPanel totalGUI = new JPanel();
    JPanel painelTextos = new JPanel();
    JLabel usernameLabel = new JLabel("Nome Tarefa");
    JLabel prazo = new JLabel("Prazo");
    JLabel notas = new JLabel("Notas");
    MaskFormatter mascaraData = null;
    JFormattedTextField deadLine;
    JPanel panelForButtons = new JPanel();
    JTextArea notasField = new JTextArea("", 5, 30);
    Project project;

    public void setProject(Project project) {
        this.project = project;
    }


    NewTasks(){
        taskController = new TaskController();
    }

    public JDialog init() {
        dialogTasks.setContentPane(createContentPane());
        dialogTasks.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogTasks.setSize(350, 450);
        dialogTasks.setLocationRelativeTo(null);
        dialogTasks.setResizable(false);
        dialogTasks.setFocusableWindowState(true);
        dialogTasks.setModalityType(ModalityType.APPLICATION_MODAL);
        dialogTasks.setVisible(true);

        return dialogTasks;

    }


    public JPanel createContentPane (){

        totalGUI.setLayout(null);

        titleLabel.setIcon(new ImageIcon("C:\\Users\\jaotu\\IdeaProjects\\todoapp\\src\\main\\resources\\lista-de-desejos.png"));
        titleLabel.setLocation(0,0);
        titleLabel.setSize(350, 50);
        titleLabel.setHorizontalAlignment(0);
        totalGUI.add(titleLabel);

        painelTextos.setLayout(null);
        painelTextos.setLocation(15, 55);
        painelTextos.setSize(100, 230);
        totalGUI.add(painelTextos);

        // Username Label
        usernameLabel.setLocation(0, 0);
        usernameLabel.setSize(100, 40);
        usernameLabel.setHorizontalAlignment(2);
        painelTextos.add(usernameLabel);

        passwordLabel.setLocation(0, 40);
        passwordLabel.setSize(100, 40);
        passwordLabel.setHorizontalAlignment(2);
        painelTextos.add(passwordLabel);

        // prazo Label
        prazo.setLocation(0, 150);
        prazo.setSize(100, 40);
        prazo.setHorizontalAlignment(2);
        painelTextos.add(prazo);

        // notas label
        notas.setLocation(0, 190);
        notas.setSize(100, 40);
        notas.setHorizontalAlignment(2);
        painelTextos.add(notas);


        // TextFields Panel Container
        panelForTextFields.setLayout(null);
        panelForTextFields.setLocation(120, 60);
        panelForTextFields.setSize(200, 300);
        totalGUI.add(panelForTextFields);

        // Nome Textfield
        taskNameField.setLocation(0, 0);
        taskNameField.setSize(200, 30);
        taskNameField.setBorder(BorderFactory.createEmptyBorder());
        panelForTextFields.add(taskNameField);

        // Descrição Textfield
        taskDescriptionArea.setLocation(0, 40);
        taskDescriptionArea.setSize(200, 100);
        taskDescriptionArea.setLineWrap(true);
        taskDescriptionArea.setWrapStyleWord(true);
        panelForTextFields.add(taskDescriptionArea);

        try{
            mascaraData = new MaskFormatter("##/##/####");
        } catch (ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
        }
        deadLine = new JFormattedTextField(mascaraData);
        deadLine.setLocation(0, 150);
        deadLine.setSize(200, 30);
        deadLine.setBorder(BorderFactory.createEmptyBorder());
        panelForTextFields.add(deadLine);


        panelForButtons.setLayout(null);
        panelForButtons.setLocation(30, 370);
        panelForButtons.setSize(300, 50);
        totalGUI.add(panelForButtons);

        // Login Textfield
        notasField.setLocation(0, 190);
        notasField.setSize(200, 100);
        notasField.setLineWrap(true);
        notasField.setWrapStyleWord(true);
        panelForTextFields.add(notasField);



        // Button for Logging in
        saveTaskButton = new JButton("Add Tarefa");
        saveTaskButton.setLocation(160, 0);
        saveTaskButton.setSize(130, 30);
        saveTaskButton.setHorizontalAlignment(0);
        saveTaskButton.setBackground(new Color(48, 113, 166));
        saveTaskButton.setForeground(Color.white);
        saveTaskButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Task task = new Task();
                    task.setName(taskNameField.getText());
                    task.setDescription(taskDescriptionArea.getText());
                    task.setIdProject(project.getId());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date deadLineFormated = null;
                    deadLineFormated = dateFormat.parse(deadLine.getText());
                    task.setDeadLine(deadLineFormated);
                    task.setNotes(notasField.getText());
                    task.setStatus(false);
                    taskController.save(task);
                    JOptionPane.showMessageDialog(rootPane,"Task add com sucesso");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(rootPane,"Deu erro na add da tarefa, corre aqui!");
                }finally {
                    dialogTasks.dispose();
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
        panelForButtons.add(saveTaskButton);
        totalGUI.setOpaque(true);
        return totalGUI;
    }



    void createAndShowGUI() {

        init();

    }




}
