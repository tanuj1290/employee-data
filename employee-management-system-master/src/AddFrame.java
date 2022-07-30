import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.regex.*;
import java.sql.*;
import java.text.CollationElementIterator;

class AddFrame extends JFrame {
    JPanel jp1, jp2;
    JLabel lbl1ID, lbl2Name, lb3Address, lb4Designation, lb5Salary;
    JTextField txtID, txtName, txtAddress, txtDesignation, txtSalary;
    JButton btnSave, btnBack;

    AddFrame() {
        super("  Add Employee  ");
        setSize(500, 150);
        // getContentPane().setBackground(Color.GREEN);
        setResizable(false); // the resize option is greyed out

        jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());

        lbl1ID = new JLabel("ID");
        txtID = new JTextField(5);// that is 3 coloumns

        lbl2Name = new JLabel("Name");
        txtName = new JTextField(19);// that is 10 coloumns

        lb3Address = new JLabel("Address");
        txtAddress = new JTextField(19);

        lb4Designation = new JLabel("Designation");
        txtDesignation = new JTextField(10);

        lb5Salary = new JLabel("Salary");
        txtSalary = new JTextField(10);

        // now add all this to Jpane
        jp1.add(lbl1ID);
        jp1.add(txtID);
        jp1.add(lbl2Name);
        jp1.add(txtName);
        jp1.add(lb3Address);
        jp1.add(txtAddress);
        jp1.add(lb4Designation);
        jp1.add(txtDesignation);
        jp1.add(lb5Salary);
        jp1.add(txtSalary);
// 
        // jp1.setBackground(Color.MAGENTA);
        // jp1.setForeground(Color.BLUE);

        // now add the pane to frame
        add(jp1,BorderLayout.NORTH);

        jp2 = new JPanel();
        jp2.setLayout(new FlowLayout());

        // save and back buttons

        btnSave = new JButton("Save");
        btnBack = new JButton("Back");

        jp2.add(btnSave);
        jp2.add(btnBack);
        // jp2.setBackground(Color.MAGENTA);


        add(jp2, BorderLayout.SOUTH);
        setLocationRelativeTo(null); // SETS THE WHOLE FRAME AT CENTRE
        setVisible(true);

        // back button should take us back to homeframe
        btnBack.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                HomeFrame h = new HomeFrame();
                dispose();
            }

        });

        // close button also should take us back to homeframe
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                HomeFrame h = new HomeFrame();
                dispose();
            }

        });

        btnSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                int id = 0;
                String name = "";
                String address = "";
                String designation = "";
                int salary = 0;

                // id validation
                try {
                    id = Integer.parseInt(txtID.getText());
                } // end try
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(new JDialog(), " Invalid id ");
                    // gets the focus back to id feild
                    txtID.setText("");
                    txtID.requestFocus();
                    return;
                } // end catch

                // id validation.id less than 0
                if (id <= 0) {
                    JOptionPane.showMessageDialog(new JDialog(), " Invalid id.ID should be greater than 0 ");
                    txtID.setText("");
                    txtID.requestFocus();
                    return;
                }

                name = txtName.getText();

                // name validation
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(new JDialog(), " Invalid name.Enter name ");
                    txtName.setText("");
                    txtName.requestFocus();
                    return;
                }

                try {
                    if (!name.matches("[a-zA-Z0-9_.-]{3,}")) {
                        JOptionPane.showMessageDialog(new JDialog(), " Invalid name. ");
                        txtName.setText("");
                        txtName.requestFocus();
                        return;
                    }

                } // end try
                catch (PatternSyntaxException pse) {
                }

                // ----------------------

                address = txtAddress.getText();

                // address validation
                if (address.length() == 0) {
                    JOptionPane.showMessageDialog(new JDialog(), " Invalid Address. \n Enter the Address again ");
                    txtName.setText("");
                    txtName.requestFocus();
                    return;
                }

                try {
                    if (!address.matches("[a-zA-Z0-9_.-]{3,}")) {
                        JOptionPane.showMessageDialog(new JDialog(), " Invalid Address. ");
                        txtName.setText("");
                        txtName.requestFocus();
                        return;
                    }

                } // end try
                catch (PatternSyntaxException pse) {
                }

                // ----------------

                designation = txtDesignation.getText();

                // designation validation
                if (designation.length() == 0) {
                    JOptionPane.showMessageDialog(new JDialog(), " Invalid Designation. \n Enter Designation ");
                    txtName.setText("");
                    txtName.requestFocus();
                    return;
                }

                try {
                    if (!name.matches("[a-zA-Z0-9_.-]{3,}")) {
                        JOptionPane.showMessageDialog(new JDialog(), " Invalid name. ");
                        txtName.setText("");
                        txtName.requestFocus();
                        return;
                    }

                } // end try
                catch (PatternSyntaxException pse) {
                }

                // ----------------

                // salary validation
                try {
                    salary = Integer.parseInt(txtSalary.getText());
                } // end try
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(new JDialog(), " Invalid Salary ");
                    // gets the focus back to id feild
                    txtID.setText("");
                    txtID.requestFocus();
                    return;
                } // end catch

                // salary validation salary less than 0
                if (salary < 0) {
                    JOptionPane.showMessageDialog(new JDialog(), " Invalid Salary, Salary must be Valid");
                    txtID.setText("");
                    txtID.requestFocus();
                    return;
                }

                // ----------------

                DatabaseHandler query = new DatabaseHandler();
                query.insert(id, name, address, designation, salary);
                txtID.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtDesignation.setText("");
                txtSalary.setText("");

                // HomeFrame h = new HomeFrame();  // return back to home screen
                // dispose();

            }

        });// end of btnsave actionhandler

    }
}