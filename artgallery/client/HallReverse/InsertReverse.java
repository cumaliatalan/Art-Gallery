package client.HallReserve;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.HallReserveInformation;
import function.DatabaseConnection;

public class InsertReserve extends JFrame implements ActionListener {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = -1299800042097646249L;
	
	JTextField jt1,jt2,jt3,jt4,jt5,jt6;
    JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
    JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8;
    JButton jb1,jb2;

    public InsertReserve(){

        jt1 = new JTextField(8);
        jt2 = new JTextField(8);
        jt3 = new JTextField(8);
        jt4 = new JTextField(8);
        jt5 = new JTextField(8);

        jl1 = new JLabel("å±•å�…é¢„çº¦");
        jl2 = new JLabel("é¢„çº¦ç¼–å�·");
        jl3 = new JLabel("å±•å�…ç¼–å�·");
        jl4 = new JLabel("è‰ºæœ¯å®¶ç¼–å�·");
        jl5 = new JLabel("æ—¶é—´");
        jl6 = new JLabel("å±•ä½�ä¸ªæ•°");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();


        jb1 = new JButton("é¢„çº¦");
        jb2 = new JButton("æ›´æ–°");
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        jp1.add(jl1);

        jp2.add(jl2);
        jp2.add(jt1);
        jp2.add(jl3);
        jp2.add(jt2);

        jp3.add(jl4);
        jp3.add(jt3);
        jp3.add(jl5);
        jp3.add(jt4);
        
        jp4.add(jl6);
        jp4.add(jt5);

        jp5.add(jb1);
        jp5.add(jb2);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);

        this.setVisible(true);
        this.setTitle("è‰ºæœ¯ç”»å»Šç®¡ç�†ç³»ç»Ÿ");
        this.setBounds(700,300,600,400);
        this.setLayout(new GridLayout(6,4));
    }


    public void clear(){
        jt1.setText("");
        jt2.setText("");
        jt3.setText("");
        jt4.setText("");
        jt5.setText("");
    }

    public int verify1(){
        Connection con = null;
        ResultSet rs;
        int result = 0;
        try {
            con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from Hall where Hno = ?");
            ps.setString(1,jt2.getText());
            rs = ps.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null,"è¯¥å±•å�…ç¼–å�·å­˜åœ¨","æ��ç¤ºæ¶ˆæ�¯",JOptionPane.WARNING_MESSAGE);
                result = 1;
            }else {
                JOptionPane.showMessageDialog(null,"è¯¥å±•å�…ç¼–å�·ä¸�å­˜åœ¨ï¼Œè¯·é‡�æ–°è¾“å…¥","æ��ç¤ºæ¶ˆæ�¯",JOptionPane.WARNING_MESSAGE);
                result = 0;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    //è�·å�–å±•å�…å±•ä½�æ•°
    public int getHnum(){
        Connection con = null;
        ResultSet rs = null;
        int num1 = 0;
        try{
            con = DatabaseConnection.getConnection();
            String sql = "select Hnum from Hall where Hno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,jt2.getText());
            rs = ps.executeQuery();
            if (rs.next()){
                num1 = rs.getInt(1);
            }
            System.out.println("å±•ä½�æ•°æŸ¥è¯¢æˆ�åŠŸ");
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(num1);
        return num1;
    }

    //è�·å�–é¢„çº¦è®¢å�•å±•ä½�æ•°
    public int getRnum(){
        Connection con = null;
        ResultSet rs = null;
        int num2 = 0;
        @SuppressWarnings("unused")
		HallReserveInformation pi = new HallReserveInformation();
        try{
            con = DatabaseConnection.getConnection();
            String sql = "select Rnum from Reserve where Rno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,jt1.getText());
            rs = ps.executeQuery();
            if (rs.next()){
                num2 = rs.getInt(1);
            }
            System.out.println("å±•ä½�æ•°æŸ¥è¯¢æˆ�åŠŸ");
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(num2);
        return num2;
    }

    public void updateHall(){
        Connection con = null;
        int n1 = 0,n2 = 0,n3 = 0;
        try{
            con = DatabaseConnection.getConnection();
            String sql = "update Hall set Hnum = ? where Hno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            n1 = this.getHnum();
            n2 = this.getRnum();
            n3 = n1-n2;
            ps.setInt(1,n3);
            ps.setString(2,jt2.getText());
            ps.executeUpdate();
            System.out.println("å±•ä½�æ•°æ›´æ–°æˆ�åŠŸ");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void HallReserve() {
        Connection con = null;
        int result = 0;
        try{
            if (!jt1.getText().isEmpty() && !jt2.getText().isEmpty() && !jt4.getText().isEmpty() && !jt5.getText().isEmpty()) {
                con = DatabaseConnection.getConnection();
                String sql = "insert into Reserve values (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                HallReserveInformation pi = new HallReserveInformation();

                if (verify1() == 1) {
                    pi.setR_no(jt1.getText());
                    pi.setH_no(jt2.getText());
                    pi.setA_no(jt3.getText());
                    pi.setR_time(jt4.getText());
                    pi.setR_num((jt5.getText()));

                    ps.setString(1, pi.getR_no());
                    ps.setString(2, pi.getH_no());
                    ps.setString(3,pi.getA_no());
                    ps.setString(4, pi.getR_time());
                    ps.setString(5, pi.getR_num());

                    result = ps.executeUpdate();
                }
            }else {
                JOptionPane.showMessageDialog(null, "è¯·è¾“å…¥å®Œæ•´ä¿¡æ�¯", "æ��ç¤ºæ¶ˆæ�¯", JOptionPane.WARNING_MESSAGE);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }if (result == 1){
            JOptionPane.showMessageDialog(null,"é¢„çº¦æˆ�åŠŸ","æ��ç¤ºæ¶ˆæ�¯",JOptionPane.WARNING_MESSAGE);
            System.out.println("é¢„çº¦æˆ�åŠŸ");
            //clear();
        }else if (result == 0){
            JOptionPane.showMessageDialog(null,"é¢„çº¦å¤±è´¥","æ��ç¤ºæ¶ˆæ�¯",JOptionPane.WARNING_MESSAGE);
            System.out.println("é¢„çº¦å¤±è´¥");
            clear();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "æ›´æ–°"){
            updateHall();
        }else if (e.getActionCommand() == "é¢„çº¦"){
            HallReserve();

        }
    }
}
