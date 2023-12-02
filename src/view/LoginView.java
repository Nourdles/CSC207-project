package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
/*Currently the CA5 view, update later */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";

    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;

    final JButton signup;
    private final LoginController loginController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller, SignupViewModel signupViewModel, ViewManagerModel viewManagerModel ) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.signupViewModel = signupViewModel;
        this.signupViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        setLayout(new BorderLayout());

        // Create a panel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("Login Screen");
        titlePanel.add(title);

        // Add the title panel to the NORTH
        add(titlePanel, BorderLayout.NORTH);

        // Create a panel for the input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2)); // Two rows, two columns

        // LabelTextPanel for Username
        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel("Username"), usernameInputField);
        inputPanel.add(usernameInfo);

        // Error label for Username
        inputPanel.add(usernameErrorField);

        // LabelTextPanel for Password
        LabelTextPanel passwordInfo = new LabelTextPanel(new JLabel("Password"), passwordInputField);
        inputPanel.add(passwordInfo);

        // Error label for Password
        inputPanel.add(passwordErrorField);

        // Add the input panel to the CENTER
        add(inputPanel, BorderLayout.CENTER);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout()); // FlowLayout for buttons in a row

        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttonsPanel.add(logIn);





        signup = new JButton(loginViewModel.SIGN_UP_BUTTON_LABEL);
        buttonsPanel.add(signup);


        add(buttonsPanel, BorderLayout.CENTER);

        // Add an image to the WEST
        ImageIcon imageIcon = new ImageIcon("src/logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(imageLabel, BorderLayout.WEST);
        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }

                    }
                }
        );

        signup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(signup)){
                            viewManagerModel.setActiveView(signupViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttonsPanel);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {

        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getUsernameError() != null){
            JOptionPane.showMessageDialog(this, state.getUsernameError());

        } else if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());

        }
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}