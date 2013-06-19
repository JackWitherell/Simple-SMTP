/*
 *:3
 */

import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Date;
import javax.swing.*;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;

import javax.mail.*;

import javax.mail.internet.*;

import com.sun.mail.smtp.*;


public class Distribution extends JPanel{
    public static void main(String args[]) throws Exception {
        BufferedReader scanner;
        scanner=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What do you want the subject line to say?");
        String SubjectLine=new String(scanner.readLine());
        System.out.println("What do you want the body of the email to say?");
        String Text=new String(scanner.readLine());
        System.out.println("Who do you want to send this email to?");
        ArrayList<String> Email = new ArrayList<String>();
        Email.add(scanner.readLine());
        //String Email=new String(scanner.readLine());
        JFrame frame=new JFrame("Add Recipients?");
        //================JFrame styleFrame = new JFrame ("Hold on...");
        //================styleFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        //================StyleGUI gui = new StyleGUI();
        //================StyleFrame.getContentPane().add (gui.getPanel());
        //================styleFrame.pack();
        //================styleFrame.setVisible(true);
        
        //================JOptionPane.showMessageDialog(frame,"Eggs are not supposed to be green");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //JLabel label = new JLabel("Do you want to add a recipient?");
        //JFrame.setDefaultLookAndFeelDecorated(true);

        //label.setPreferredSize(new Dimension(350,150));
        //frame.getContentPane().add(label, BorderLayout.CENTER);
        
        
        //frame.pack();
        //frame.setVisible(true);
        int x=0;
        String emailfrom= ("ENTEREMAILADDRESSHERE");
        String emailfrompass= ("ENTEREMAILPASSWORDHERE");
        while (x==0){
        Object[] options = {"Yes, please", "No, that's all"};
        int n = JOptionPane.showOptionDialog(frame,
             "Would you like to add"
             + "another recipient?",
             "Hold on...",
             JOptionPane.YES_NO_CANCEL_OPTION,
             JOptionPane.QUESTION_MESSAGE,
             null,
             options,
             options[1]);
        if (n==0){
            System.out.println("Who else do you want to send this email to?");
        Email.add(scanner.readLine());
        }
        else {x=1;};
    }
    for (int k=0; k<Email.size(); k++){
        Properties props = System.getProperties();
        props.put("mail.smtps.host","smtp.gmail.com");
        props.put("mail.smtps.auth","true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailfrom));;
        msg.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(Email.get(k), false));
        msg.setSubject(SubjectLine);
        msg.setText(Text);
        //================msg.setHeader("prepare", "yourself");
        msg.setSentDate(new Date());
        SMTPTransport t =
            (SMTPTransport)session.getTransport("smtps");
        t.connect("smtp.gmail.com", emailfrom, emailfrompass);
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t.getLastServerResponse());
        t.close();
    }
}
}
