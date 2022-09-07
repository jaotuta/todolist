package br.com.jlcom.utils;

import br.com.jlcom.models.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Date;

public class DeadlineColumnCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        label.setHorizontalAlignment(CENTER);
        TaskTableModel taskModel = (TaskTableModel) table.getModel();
        Task task = taskModel.getTasks().get(row);
        if(task.getDeadLine().after(new Date())){
            label.setBackground(Color.green);
        } else {
            label.setBackground(Color.red);
        }
        return label;

    }


}
