
import Controller.BarangController;
import Model.Barang;
import View.BarangGUI;
import java.awt.EventQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author helmisatria
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Barang barang = new Barang();
                BarangGUI gui = new BarangGUI();
                BarangController controller = new BarangController(gui, barang);
                controller.getAllBarang();
                controller.initController();
                gui.setVisible(true);
            }
        });
    }
}
