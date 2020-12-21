package ChatApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BDDpage implements ActionListener {
    JFrame fram;
    Agent agent;
    JButton bouton;
    JTextField pmdp;
    JTextField plogin;

    public BDDpage (Agent agent){
        this.agent=agent;

        //gestion fenetre
        fram=new JFrame("Inscription Utilisateur ");
        fram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fram.getContentPane().setPreferredSize(new Dimension(200,100));

        //gestion et création des composants
        JPanel panel1= new JPanel(new GridLayout(2,2));
        JPanel panel2= new JPanel();
        JLabel login= new JLabel("Login :");
        JLabel mdp=new JLabel("Mot de passe :");
        plogin=new JTextField();
        pmdp =new JTextField();
        JButton bouton=new JButton("Envoyer");
        bouton.addActionListener(this);
        bouton.setForeground(Color.GRAY);
        login.setForeground(Color.DARK_GRAY);
        mdp.setForeground(Color.DARK_GRAY);

        ////ajout composant panels
        panel1.add(login,BorderLayout.LINE_START);
        panel1.add(plogin,BorderLayout.CENTER);
        panel1.add(mdp,BorderLayout.LINE_START);
        panel1.add(pmdp,BorderLayout.CENTER);
        panel2.add(bouton,BorderLayout.CENTER);

        //ajout panels dans la fenetre
        fram.getContentPane().add(panel1, BorderLayout.PAGE_START);
        fram.getContentPane().add(panel2, BorderLayout.CENTER);

        //gestion fenetre
        fram.pack();
        fram.setLocationRelativeTo(null);
    }


    //TODO: a finir quand BDD prete
    public void actionPerformed(ActionEvent e) {
        String getvalue_login= plogin.getText();
        String getValue_mdp=pmdp.getText();
        if (e.getSource()==bouton){

        }
    }


    public JFrame getFrame() { return fram; }
}