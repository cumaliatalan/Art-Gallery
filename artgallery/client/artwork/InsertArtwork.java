/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artgallery.client.artwork;

/**
 *
 * @author tr
 */

import entity.ArtWork;
import function.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertArtwork extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1575659872387695114L;

	JTextField jtf1, jtf2, jtf3, jtf4, jtf6 = null;
	JLabel jl1, jl2, jl3, jl4, jl6, jl7 = null;
	JPanel jp1, jp2, jp4, jp5, jp6, jp7, jp8 = null;
	JButton jb1, jb2;

	public InsertArtwork() {

		jl7 = new JLabel(" è‰ºæœ¯ç”»å»Šç®¡ç�†ç³»ç»Ÿ");

		jl1 = new JLabel(" è‰ºæœ¯å“�ç¼–å�·ï¼š");
		jl2 = new JLabel(" å��ç§°ï¼š");
		jl3 = new JLabel(" ç±»åˆ«ï¼š");
		jl4 = new JLabel(" å”®ä»·ï¼š");

		jb1 = new JButton("æ·»åŠ ");
		// jb2 = new JButton("è¿”å›�");

		jb1.addActionListener(this);
		// jb2.addActionListener(this);

		jtf1 = new JTextField(6);
		jtf2 = new JTextField(6);
		jtf3 = new JTextField(6);
		jtf4 = new JTextField(6);
		// jtf5 = new JTextField(6);

		jp1 = new JPanel();
		jp2 = new JPanel();
		// jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp7 = new JPanel();
		jp8 = new JPanel();

		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jl2);
		jp1.add(jtf2);

		jp2.add(jl3);
		jp2.add(jtf3);
		jp2.add(jl4);
		jp2.add(jtf4);

		// jp3.add(jl5);
		// jp3.add(jtf5);

		jp5.add(jb1);
		// jp5.add(jb2);

		jp6.add(jl7);

		this.add(jp6);
		this.add(jp1);
		this.add(jp2);
		// this.add(jp3);
		this.add(jp5);

		this.setVisible(true);
		;
		this.setTitle("è‰ºæœ¯ç”»å»Šç®¡ç�†ç³»ç»Ÿ");
		this.setLayout(new GridLayout(5, 4));
		this.setBounds(670, 300, 700, 500);
	}

	public void insertinformation() {
		Connection con = null;
		int result = 0;
		try {
			if (!jtf1.getText().isEmpty() && !jtf2.getText().isEmpty() && !jtf3.getText().isEmpty()
					&& !jtf4.getText().isEmpty()) {
				con = DatabaseConnection.getConnection();
				String sql = "insert into Artwork values(?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ArtWork aw = new ArtWork();

				aw.setAw_no(jtf1.getText());
				aw.setAw_name(jtf2.getText());
				aw.setAw_kind(jtf3.getText());
				aw.setAw_price(jtf4.getText());
				aw.setAw_sold("0");

				ps.setString(1, aw.getAw_no());
				ps.setString(2, aw.getAw_name());
				ps.setString(3, aw.getAw_kind());
				ps.setString(4, aw.getAw_price());
				ps.setString(5, aw.getAw_sold());
				result = ps.executeUpdate();

			} else {
				JOptionPane.showMessageDialog(null, "è¯·è¾“å…¥å®Œæ•´ä¿¡æ�¯", "æ��ç¤ºæ¶ˆæ�¯", JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "æ·»åŠ æˆ�åŠŸ", "æ��ç¤ºæ¶ˆæ�¯", JOptionPane.WARNING_MESSAGE);
			System.out.println("æ·»åŠ æˆ�åŠŸ");
			clear();
		} else if (result == 0) {
			JOptionPane.showMessageDialog(null, "æ·»åŠ å¤±è´¥", "æ��ç¤ºæ¶ˆæ�¯", JOptionPane.WARNING_MESSAGE);
			System.out.println("æ·»åŠ å¤±è´¥");
		}
	}

	public void clear() {
		jtf1.setText("");
		jtf2.setText("");
		jtf3.setText("");
		jtf4.setText("");
		// jtf5.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "æ·»åŠ ") {
			insertinformation();
		}
	}
}

