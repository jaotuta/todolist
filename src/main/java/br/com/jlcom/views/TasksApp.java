package br.com.jlcom.views;

import br.com.jlcom.controllers.ProjectController;
import br.com.jlcom.controllers.TaskController;
import br.com.jlcom.models.Project;
import br.com.jlcom.models.Task;
import br.com.jlcom.utils.ButtonColumnCellRenderer;
import br.com.jlcom.utils.DeadlineColumnCellRender;
import br.com.jlcom.utils.TaskTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TasksApp extends JFrame {


    ProjectController projectController;
    TaskController taskController;
    DefaultListModel projectModel;
    TaskTableModel taskModel;
    JTable table;
    JPanel emptyListPanel = new JPanel();
    JPanel tasksContentPanel = new JPanel();
    JLabel emptyListTaskLabel = new JLabel("Nao ha Tarefas, Clique no + para adicionar.");
    JScrollPane scroll = new JScrollPane();
    JPanel projectsContentPanel = new JPanel();
    JPanel totalGUI = new JPanel();
    JPanel headerPanel = new JPanel();
    JLabel headerLabel = new JLabel("Todo List");
    JPanel projectsPanel = new JPanel();
    JPanel titleContentPanel = new JPanel();
    JLabel projectsLabel = new JLabel("Projetos");
    JLabel tasksLabel = new JLabel("Tarefas");
    JPanel tasksPanel = new JPanel();
    JPanel contentPanel = new JPanel();
    JList projectJlist = new JList();


    public TasksApp() throws SQLException {
        initDataController();
        initCompModel();
    }
    public void initDataController() {
        projectController = new ProjectController();
        taskController = new TaskController();
    }
    public void initCompModel() throws SQLException {
        projectModel = new DefaultListModel();
        loadProjects();
        taskModel = new TaskTableModel();

        if(!projectModel.isEmpty()){
            projectJlist.setSelectedIndex(0);
            Project project = (Project) projectModel.get(0);
            loadTasks(project.getId());
        }

    }
    public void loadProjects() throws SQLException {

        List<Project> listaDeProjetos =  projectController.getAllProjects();
        projectModel.clear();
        for (int i=0; i < listaDeProjetos.size() ; i++) {

            projectModel.addElement(listaDeProjetos.get(i));
            listaDeProjetos.get(i);
        }
        projectJlist.setModel(projectModel);

    }
    public void loadTasks(int idProject) throws SQLException {
        List<Task> tasks = taskController.getAllTasks(idProject);
        taskModel.setTasks(tasks);

        showJTableTasks(!tasks.isEmpty());
    }

    private void showJTableTasks(boolean hasTasks) {
        if(hasTasks) {
            if(emptyListPanel.isVisible()) {
                emptyListPanel.setVisible(false);
                tasksContentPanel.remove(emptyListPanel);
            }
            tasksContentPanel.add(scroll);
            scroll.setVisible(true);

        }else {
            if(scroll.isVisible()) {
                scroll.setVisible(false);
                tasksContentPanel.remove(scroll);
            }
            tasksContentPanel.add(emptyListPanel);
            emptyListPanel.setVisible(true);
        }
    }
    public JPanel createContentPane (){

        totalGUI.setLayout(null);

        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(83, 154, 212));
        headerPanel.setLocation(10, 10);
        headerPanel.setSize(760, 175);
        totalGUI.add(headerPanel);

        headerLabel.setIcon(new ImageIcon("C:\\Users\\jaotu\\IdeaProjects\\todoapp\\src\\main\\resources\\correto.png"));
        headerLabel.setLocation(0, 0);
        headerLabel.setForeground(Color.white);
        headerLabel.setSize(760, 175);
        headerLabel.setHorizontalAlignment(0);
        headerPanel.add(headerLabel);



        titleContentPanel.setLayout(null);
        titleContentPanel.setLocation(10, 195);
        titleContentPanel.setSize(780, 50);
        totalGUI.add(titleContentPanel);

        projectsPanel.setBackground(new Color(48, 113, 166));
        projectsPanel.setLocation(0, 0);
        projectsPanel.setSize(150, 50);
        projectsPanel.setLayout(null);
        titleContentPanel.add(projectsPanel);

        projectsLabel.setIcon(new ImageIcon("C:\\Users\\jaotu\\IdeaProjects\\todoapp\\src\\main\\resources\\mais.png"));
        projectsLabel.setLocation(0, 0);
        projectsLabel.setForeground(Color.white);
        projectsLabel.setSize(150, 50);
        projectsLabel.setHorizontalAlignment(0);
        projectsPanel.add(projectsLabel);
        projectsLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NewProjects newProjectsScreen = new NewProjects();
                JDialog dialog = newProjectsScreen.init();
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            loadProjects();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                });

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

        tasksPanel.setBackground(new Color(48, 113, 166));
        tasksPanel.setLocation(160, 0);
        tasksPanel.setSize(600, 50);
        tasksPanel.setLayout(null);
        titleContentPanel.add(tasksPanel);

        tasksLabel.setIcon(new ImageIcon("C:\\Users\\jaotu\\IdeaProjects\\todoapp\\src\\main\\resources\\mais.png"));
        tasksLabel.setLocation(0, 0);
        tasksLabel.setForeground(Color.white);
        tasksLabel.setSize(600, 50);
        tasksLabel.setHorizontalAlignment(0);
        tasksLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NewTasks newTaskScreen = new NewTasks();
                int projectIndex = projectJlist.getSelectedIndex();
                Project project = (Project) projectModel.get(projectIndex);
                newTaskScreen.setProject(project);
                //newTaskScreen.createAndShowGUI();
                JDialog dialog = newTaskScreen.init();
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            loadTasks(project.getId());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                });

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
        tasksPanel.add(tasksLabel);


        contentPanel.setLayout(null);
        contentPanel.setLocation(10, 255);
        contentPanel.setSize(780, 495);
        totalGUI.add(contentPanel);

        projectsContentPanel.setBackground(Color.white);
        projectsContentPanel.setLocation(0, 0);
        projectsContentPanel.setSize(150, 495);
        contentPanel.add(projectsContentPanel);


        projectJlist.setSelectionBackground(new Color(174, 213, 245));
        projectJlist.setVisibleRowCount(10);
        projectJlist.setFixedCellHeight(30);
        projectJlist.setFixedCellWidth(130);
        projectJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projectJlist.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int projectIndex = projectJlist.getSelectedIndex();
                Project project = (Project) projectModel.get(projectIndex);
                try {
                    loadTasks(project.getId());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
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
        projectsContentPanel.add(projectJlist);

        tasksContentPanel.setBackground(Color.white);
        tasksContentPanel.setLocation(160, 0);
        tasksContentPanel.setSize(600, 495);
        tasksContentPanel.setLayout(null);
        tasksContentPanel.setBackground(Color.red);
        contentPanel.add(tasksContentPanel);

        emptyListPanel.setBackground(Color.white);
        emptyListPanel.setLocation(0, 0);
        emptyListPanel.setSize(600, 495);
        emptyListPanel.setLayout(null);
        tasksContentPanel.add(emptyListPanel);



        emptyListTaskLabel.setLocation(0, 0);
        emptyListTaskLabel.setIcon(new ImageIcon("C:\\Users\\jaotu\\IdeaProjects\\todoapp\\src\\main\\resources\\lista-de-desejos.png"));
        emptyListTaskLabel.setSize(600, 495);
        emptyListTaskLabel.setHorizontalAlignment(0);
        emptyListPanel.add(emptyListTaskLabel);

        // ---------------------------------------------------------
            scroll.setLocation(0,0);
            scroll.setSize(600, 495);
            tasksContentPanel.add(scroll);
            table = new JTable();
            scroll.setViewportView(table);

            table.setModel(taskModel);
            table.setRowHeight(40);
            table.setGridColor(Color.white);
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            table.getTableHeader().setBackground(new Color(48, 113, 166));
            table.getTableHeader().setForeground(Color.white);
            table.setSelectionMode(0);
            table.getColumnModel().getColumn(2).setCellRenderer(new DeadlineColumnCellRender());
            table.getColumnModel().getColumn(4).setCellRenderer(new ButtonColumnCellRenderer("caneta"));
            table.getColumnModel().getColumn(5).setCellRenderer(new ButtonColumnCellRenderer("bloquear"));
            table.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int rowIndex = table.rowAtPoint(e.getPoint());
                    int columnIndex = table.columnAtPoint(e.getPoint());

                    switch (columnIndex) {
                        case 3:
                            Task task = taskModel.getTasks().get(rowIndex);
                            taskController.update(task);
                            break;
                        case 4:
                            break;
                        case 5:
                            Task task2 = taskModel.getTasks().get(rowIndex);
                            try {
                                taskController.deleteById(task2.getIdTask());
                                JOptionPane.showMessageDialog(rootPane, "Tarefa Excluida com sucesso");
                                loadTasks(task2.getIdProject());
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;

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


        // ---------------------------------------------------------


        totalGUI.setOpaque(true);
        return totalGUI;
    }

    private static void createAndShowGUI() throws SQLException {

        JFrame frame = new JFrame("Tasks App");

        //Create and set up the content pane.
        TasksApp demo = new TasksApp();
        frame.setContentPane(demo.createContentPane());

        // The other bits and pieces that make our program a bit more stable.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}