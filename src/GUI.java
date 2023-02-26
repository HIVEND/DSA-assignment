import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

    public class GUI extends JFrame {

        private JLabel taskLabel;
        private JTextField taskField;
        private JButton addButton;
        private JButton removeButton;
        private JButton resumeButton;
        private JTextArea taskList;

        private Connection conn;
        private Statement stmt;

        private ArrayList<String> tasks;

        public GUI() {
            // Set up the GUI components
            setTitle("Database Batch Processing App");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(500, 500));

            taskLabel = new JLabel("Add Task:");
            taskField = new JTextField();
            addButton = new JButton("Add");
            removeButton = new JButton("Remove");
            resumeButton = new JButton("Resume");
            taskList = new JTextArea();

            JPanel controlPanel = new JPanel(new GridLayout(1, 4));
            controlPanel.add(taskLabel);
            controlPanel.add(taskField);
            controlPanel.add(addButton);
            controlPanel.add(removeButton);
            controlPanel.add(resumeButton);

            JScrollPane taskListPane = new JScrollPane(taskList);

            add(controlPanel, BorderLayout.NORTH);
            add(taskListPane, BorderLayout.CENTER);

            pack();
            setVisible(true);

            // Set up the database connection
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "username", "password");
                stmt = conn.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }

            tasks = new ArrayList<String>();

            // Add action listeners to the buttons
            addButton.addActionListener(e -> addTask());
            removeButton.addActionListener(e -> removeTask());
            resumeButton.addActionListener(e -> resumeTask());
        }

        private void addTask() {
            String task = taskField.getText();
            if (!task.isEmpty()) {
                tasks.add(task);
                taskList.append(task + "\n");
                taskField.setText("");
            }
        }

        private void removeTask() {
            int index = taskList.getX();
            if (index >= 0) {
                tasks.remove(index);
                taskList.setText("");
                for (String task : tasks) {
                    taskList.append(task + "\n");
                }
            }
        }

        private void resumeTask() {
            int index = taskList.getX();
            if (index >= 0) {
                String task = tasks.get(index);
                try {
                    stmt.executeUpdate(task);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            GUI app = new GUI();

    }
}
