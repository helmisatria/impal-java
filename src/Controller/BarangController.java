/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Database;
import Model.Barang;
import View.BarangGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author helmisatria
 */
public class BarangController {
    private BarangGUI gui;
    private Barang barang;
    private Database db;

    public BarangController(BarangGUI gui, Barang barang) {
        this.gui = gui;
        this.barang = barang;
        db = new Database();
    }
    
    public void initController() {
        this.gui.submitListener(new submitBarang());
        this.gui.editListener(new editBarang());
        this.gui.tableListener(new tableSelectedRow());
        this.gui.deleteListener(new deleteBarang());
    }
    
    public void getAllBarang() {
        DefaultTableModel dtm = (DefaultTableModel) gui.getTabel_barang().getModel();
        System.out.println(dtm.getRowCount());
        
        ArrayList<Barang> barangList = new ArrayList<>();
        Connection con = db.getConnection();
        
        String query = "SELECT * from Barang";
        Statement st;
        ResultSet result;
        
        try {
            st = con.createStatement();
            result = st.executeQuery(query);
            Barang barang;
            
            while (result.next()) {                
                barang = new Barang(result.getString("id"), result.getString("nama"), Integer.parseInt(result.getString("harga")));
                barangList.add(barang);
            }
            
            dtm.setRowCount(barangList.size());
            
            for (int i = 0; i < barangList.size(); i++) {
                gui.getTabel_barang().getModel().setValueAt(barangList.get(i).getNama(),i, 0);
                gui.getTabel_barang().getModel().setValueAt(barangList.get(i).getAlamat(), i, 1);
                gui.getTabel_barang().getModel().setValueAt(barangList.get(i).getHarga(), i, 2);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    class submitBarang implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = gui.getInput_nama().getText();
            String harga = gui.getInput_harga().getText();
            
            String query = "INSERT INTO Barang (nama, harga) VALUES (?, ?)";
            Connection con = db.getConnection();
            
            try {
                PreparedStatement newBarang = con.prepareStatement(query);
                newBarang.setString(1, nama);
                newBarang.setInt(2, Integer.parseInt(harga));
                newBarang.executeUpdate();
                getAllBarang();
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, ee);
            }
        }
    }
    
    class editBarang implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = gui.getInput_id().getText();
            String nama = gui.getInput_nama().getText();
            String harga = gui.getInput_harga().getText();
            
            String query = "UPDATE Barang SET nama = ?, harga = ? where id = ?";
            Connection con = db.getConnection();
            try {
                PreparedStatement updateBarang = con.prepareStatement(query);
                updateBarang.setString(1, nama);
                updateBarang.setInt(2, Integer.parseInt(harga));
                updateBarang.setInt(3, Integer.parseInt(id));
                updateBarang.executeUpdate();
                getAllBarang();
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, ee);
            }
        }
    }
    
    class deleteBarang implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = gui.getInput_id().getText();
            String query = "DELETE FROM Barang where id = ?";
            Connection con = db.getConnection();
            try {
                PreparedStatement updateBarang = con.prepareStatement(query);
                updateBarang.setString(1, id);
                updateBarang.executeUpdate();
                getAllBarang();
            } catch (Exception ee) {
//                JOptionPane.showMessageDialog(null, ee);
            }
        }
        
    }
    
    class tableSelectedRow implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = gui.getTabel_barang().getSelectedRow();
            Object id = gui.getTabel_barang().getValueAt(row, 0);
            Object nama = gui.getTabel_barang().getValueAt(row, 1);
            Object harga = gui.getTabel_barang().getValueAt(row, 2);
            gui.getInput_id().setText((String) id);
            gui.getInput_nama().setText((String) nama);
            gui.getInput_harga().setText((String) harga.toString());
        }
        
    }
}
