package il.ac.shenkar.view;

import il.ac.shenkar.model.costItem;
import il.ac.shenkar.model.costManagerException;
import il.ac.shenkar.viewmodel.IViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



/**
 *
 * The View class is implementing the IView interface.
 *
 * It has two variables -
 *      IViewModel vm
 *      ApplicationUI ui
 *
 *  The vm var is the connection to the viewmodel
 *  The ui var is setting the view components and ui.
 *
 *
 * */
public class View implements iView {

    private IViewModel vm;
    private GUI ui;

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    @Override
    public void showItems(List<costItem> vec) {ui.showItems(vec);}

    @Override
    public void showReportItems(List<costItem> items) {ui.showReportItems(items);}

    @Override
    public void showMessage(String text) { ui.showMessage(text); }


    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                View.this.ui = new GUI();
                View.this.ui.GIU();
            }
        });
    }


    public class GUI {

        private final JFrame frame_login = new JFrame("Login");

        //Creating the Frame
       private final JFrame frame = new JFrame("Cost Item Project");
        //Creating the MenuBar and adding components
        private final JPanel paneltop = new JPanel();
        private final JButton deleteButton = new JButton("Delete item");
        private final JLabel id = new JLabel("Id");
        private final JTextField id_tf = new JTextField(10); // accepts upto 10 characters
        private final JButton report = new JButton("Report");
        private final JButton getItems = new JButton("Get items");
        private final JButton logout = new JButton("Log out");
        private final JLabel reportMassage = new JLabel("Massage");
        private final JTextField reportTf = new JTextField(20); // accepts upto 10 characters

        //Creating the panel at bottom and adding components
        private final JPanel panel = new JPanel();
        private final JLabel label = new JLabel("Item Name");
        private final JTextField tf = new JTextField(10); // accepts upto 10 characters
        private final JLabel description = new JLabel(" Description");
        private final JTextField descriptionTf = new JTextField(10); // accepts upto 10 characters
        private final JLabel category = new JLabel("Category");
        String[] choices = { "FOOD", "GROCERY", "UTILITIES", "OTHER"};
        private final JComboBox<String> catagoryTf = new JComboBox<String>(choices);
        private final JLabel currency = new JLabel("Currency");
        String[] currencyChoices = { "USD", "EUR", "GBP", "NIS"};
        private final JComboBox<String> currencyTf = new JComboBox<String>(currencyChoices);
        private final JLabel date = new JLabel("Date");
        private final JTextField dateTf = new JTextField(10); // accepts upto 10 characters
        private final JButton send = new JButton("Add Item");
        private final JLabel price = new JLabel("Price ");
        private final JTextField price_tf = new JTextField(8);

        // Text Area at the Center
        JTextArea ta = new JTextArea();
        JTextArea ta_Report = new JTextArea();


/**
Building all components and adding them in the right place
 */
        public void GIU() {
            // Components Added using Flow Layout
            frame_login.setSize(700, 600);
            frame_login.setDefaultCloseOperation(frame_login.EXIT_ON_CLOSE);
            placeComponents(frame_login);
            frame_login.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 600);
            panel.add(label);
            panel.add(tf);
            panel.add(description);
            panel.add(descriptionTf);
            panel.add(price);
            panel.add(price_tf);
            panel.add(category);
            panel.add(catagoryTf);
            panel.add(currency);
            panel.add(currencyTf);
            panel.add(date);
            panel.add(dateTf);
            panel.add(send);
            panel.setBackground(Color.PINK);
            //building panel1
            paneltop.setBackground(Color.PINK);
            paneltop.add(id);
            paneltop.add(id_tf);
            paneltop.add(deleteButton);

            paneltop.add(reportMassage);
            paneltop.add(reportTf);
            paneltop.add(report);
            paneltop.add(getItems);
            paneltop.add(logout);

            ta.setBackground(Color.LIGHT_GRAY);
            //Adding Components to the frame.
            frame.getContentPane().

                    add(BorderLayout.SOUTH, panel);
            frame.getContentPane().

                    add(BorderLayout.NORTH, paneltop);
            frame.getContentPane().

                    add(BorderLayout.CENTER, ta);


            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int iD_Delete = Integer.parseInt(id_tf.getText());
                        vm.deleteId(iD_Delete);
                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered id... " + ex.getMessage());
                    }
                }
            });

            getItems.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        vm.getItems();
                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered id... " + ex.getMessage());
                    }
                }
            });
            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                        System.out.println("logout");
                        frame_login.setVisible(true);
                        frame.setVisible(false);
                }
            });
            report.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        System.out.println("report");
                        JFrame frame_report = new JFrame("Report");
                        frame_report.setSize(500, 400);
                        frame_report.setDefaultCloseOperation(frame_login.EXIT_ON_CLOSE);
                        placeComponentsReport(frame_report);
                        frame_report.setVisible(true);
                }

                /**
                 *
                 * this function builds the report window that is activated by clicking on show report
                 */
                private void placeComponentsReport(JFrame frame_report) {
                    frame_report.setLayout(null);
                    JLabel First_date = new JLabel("First Date : ");
                    First_date.setBounds(10, 0, 100, 100);
                    frame_report.add(First_date);

                    JTextField firstDateText = new JTextField(20);
                    firstDateText.setBounds(83, 35, 150, 30);
                    frame_report.add(firstDateText);


                    JLabel sec_date = new JLabel("second Date : ");
                    sec_date.setBounds(240, 0, 100, 100);
                    frame_report.add(sec_date);

                    JTextField secDateText = new JTextField(20);
                    secDateText.setBounds(333, 35, 150, 30);
                    frame_report.add(secDateText);


                    JButton report_Button = new JButton("getReports");
                    report_Button.setBounds(200, 70, 100, 35);
                    frame_report.add(report_Button);

                    ta_Report.setBackground(Color.LIGHT_GRAY);
                    ta_Report.setBounds(35,120, 420,200);
                    frame_report.add(ta_Report);


                    JButton Close = new JButton("Close");
                    Close.setBounds(200, 320, 100, 35);
                    frame_report.add(Close);


                    report_Button.addActionListener(new ActionListener() {      //action listener for the report button
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            vm.getReports(firstDateText.getText() , secDateText.getText());
                        }
                    });
                    Close.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame_report.setVisible(false);
                        }
                    });
                }
            });

            send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                        String name_ = tf.getText();
                        String description = descriptionTf.getText();
                        if (description == null || description.length() == 0) {
                            System.out.println("error in description");
                        }
                        String costDate = "";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
                        costDate = dateTf.getText();
                        String currency = String.valueOf(currencyTf.getSelectedItem());
                        String category = String.valueOf(catagoryTf.getSelectedItem());
                        int price= Integer.parseInt(price_tf.getText());
                        costItem item = new costItem(name_, description,price,currency ,category , java.sql.Date.valueOf(costDate) );
                        vm.addCostItem(item);
                }
            });
        }
        //this function places the components of the login page
        private void placeComponents(JFrame frame_login) {
            frame_login.setLayout(null);

            JLabel userLabel = new JLabel("User");
            userLabel.setBounds(190, 140, 100, 100);
            frame_login.add(userLabel);

            JTextField userText = new JTextField(40);
            userText.setBounds(250, 170, 200, 40);
            frame_login.add(userText);

            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setBounds(180, 180, 100, 100);
            frame_login.add(passwordLabel);

            JPasswordField passwordText = new JPasswordField(20);
            passwordText.setBounds(250, 210, 200, 40);
            frame_login.add(passwordText);

            JButton loginButton = new JButton("login");
            loginButton.setBounds(200, 250, 100, 35);
            frame_login.add(loginButton);

            JButton registerButton = new JButton("register");
            registerButton.setBounds(350, 250, 100, 35);
            frame_login.add(registerButton);


            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean if_exist = false;
                    System.out.println(userText.getText());
                    System.out.println(passwordText.getText());
                    try {
                         if_exist = vm.logIn(userText.getText(), passwordText.getText());
                    } catch (costManagerException ex) {
                        ex.printStackTrace();
                    }

                    if (if_exist == true){
                        JOptionPane.showMessageDialog((Component) e.getSource(),
                                "Hello " + userText.getText() +  "  Welcome to our Project");
                        frame_login.setVisible(false);
                        frame.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog((Component) e.getSource(),
                                "soryy " + userText.getText() +  "you account does not exist ");
                        frame_login.setVisible(true);
                        frame.setVisible(false);
                    }
                }

            });
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean if_exist = false;
                    System.out.println(userText.getText());
                    System.out.println(passwordText.getText());
                    try {
                        if_exist = vm.CreateNewUser(userText.getText(), passwordText.getText());
                    } catch (costManagerException ex) {
                        ex.printStackTrace();
                    }
                    if (if_exist == true){
                        JOptionPane.showMessageDialog((Component) e.getSource(),
                                "Hello " + userText.getText() +  "  Welcome to our Project Adam And Ayham");
                        frame_login.setVisible(false);
                        frame.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog((Component) e.getSource(),
                                "soryy " + userText.getText() +  "you account does not exist ");
                        frame_login.setVisible(true);
                        frame.setVisible(false);
                    }
                }
            });

        }

        // Shows all items to the user from DB
        public void showItems(List<costItem> items) {
            System.out.println("show Items\n");
            StringBuilder sb = new StringBuilder();
            for (costItem item : items) {
                sb.append(item.toString());
                sb.append("\n");
            }
            String text = sb.toString();
            if (SwingUtilities.isEventDispatchThread()) {
                ta.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ta.setText(text);
                    }
                });
            }
        }
        // Shows the user the informative message about the actions that he did in the screen
        public void showMessage(String text) {
            if (SwingUtilities.isEventDispatchThread()) {
                ta.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ta.setText(text);
                    }
                });}
        }
        // shows the items in between two given dates
        public void showReportItems(List<costItem> items) {
            System.out.println("show Report item\n");
            StringBuilder sb = new StringBuilder();
            for (costItem item : items) {
                sb.append(item.toString());
                sb.append("\n");
            }
            String text = sb.toString();

            if (SwingUtilities.isEventDispatchThread()) {
                ta_Report.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ta_Report.setText(text);
                    }
                });
            }
        }

    }
}



