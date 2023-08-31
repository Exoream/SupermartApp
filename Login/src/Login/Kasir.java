package Login;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Kasir extends javax.swing.JFrame {
    private DefaultTableModel model;
    ArrayList<String> dipilih = new ArrayList<>();
    
public class HitungTotalBelanjaTabel {
    
    private int[] getHargaJumlah(int row) throws Exception {
        int harga = Integer.parseInt(txTable.getValueAt(row, 5).toString());
        int jumlah = Integer.parseInt(txTable.getValueAt(row, 6).toString());
        return new int[]{harga, jumlah};
    }

    
    private int hitungTotalBelanja() throws Exception {
        int total = 0;
        int rows = txTable.getRowCount();
        for (int i = 0; i < rows; i++) {
            int[] hargaJumlah = getHargaJumlah(i);
            int harga = hargaJumlah[0];
            int jumlah = hargaJumlah[1];
            total += harga * jumlah;
        }
        return total;
    }

    public void tampilkanTotalBelanja() {
        try {
            int total = hitungTotalBelanja();
            txTotal.setText(String.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


int checkStok(){
    int jumlah = Integer.parseInt(txJumlah.getText());
    String nama = txBarang.getText();
    int stok = 0;
    
    try{
        Connection c =(Connection)Koneksi.DBConnection();
        PreparedStatement p = c.prepareStatement("SELECT stok from barang where nama=?");
        p.setString(1, nama);
        ResultSet r = p.executeQuery();
        
        if(r.next()){
            stok = r.getInt(1);
        }
        r.close();
        p.close();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    return stok;
}

void stokCukup(){
    int jumlah = Integer.parseInt(txJumlah.getText());
    int harga = Integer.parseInt(txHarga.getText());
    int stok = checkStok();
    
    if(stok >= jumlah){
        int total = jumlah * harga;
        txTotal.setText(String.valueOf(total));
        getData();
    }else{
        JOptionPane.showMessageDialog(null, "Stok Barang Tidak Mencukupi");
    }
}
   

void idTransaksi(){
     try{
            String sql = "SELECT * FROM tr ORDER BY notransaksi DESC LIMIT 1";
            Connection c =(Connection)Koneksi.DBConnection();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(sql);
            
            if (r.next()){
                String id = r.getString("notransaksi").substring(1);
                String a = "" + (Integer.parseInt(id) + 1);
                String n = "";
                
                if (a.length() == 1){
                    n = "01";
                }
                else if(a.length() == 2){
                    n = "0";
                }
                txTransaksi.setText("B" + n + a);
            }
            else {
                txTransaksi.setText("B011");
            }
        }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
        }
}
    
    void tanggal(){
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        
        txTanggal.setText(s.format(date));
    }
    
    void jumlahTransaksi() {    
        stokCukup();
        HitungTotalBelanjaTabel h = new HitungTotalBelanjaTabel();
        h.tampilkanTotalBelanja();
        
    }
    
    void setData(){
        
        DefaultTableModel model = (DefaultTableModel) txTable.getModel();
  
        try{
            txTable.setModel(model);
            model.addColumn("No Transaksi");
            model.addColumn("Kode Barang");
            model.addColumn("Nama Kasir");
            model.addColumn("Tanggal");
            model.addColumn("Nama Barang");
            model.addColumn("Harga");
            model.addColumn("Jumlah");
       
          }catch(Exception e) {
              System.out.println("Ada Eror");
          }
    }
    
    void getData(){
        DefaultTableModel model = (DefaultTableModel) txTable.getModel();
        Object[] obj = new Object[7];
            obj [0] = txTransaksi.getText();
            obj [1] = txKode.getText();
            obj [2] = txKasir.getSelectedItem();
            obj [3] = txTanggal.getText();
            obj [4] = txBarang.getText();
            obj [5] = txHarga.getText();
            obj [6] = txJumlah.getText();
              
            model.addRow(obj);
    }
    
    void refresh(){
        txKode.setText("");
        txBarang.setText("");
        txHarga.setText("");
        txJumlah.setText("");
    }
    
    void hapus(){
        DefaultTableModel model = (DefaultTableModel) txTable.getModel();
        
        if(model.getRowCount() > 0){
            model.removeRow(0);
        }
    }


    public Kasir() {
        model = new DefaultTableModel();
       
        initComponents();
        idTransaksi();
        tanggal();
        setData();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txTransaksi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txKasir = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txKode = new javax.swing.JTextField();
        txCari = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txBarang = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txHarga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txJumlah = new javax.swing.JTextField();
        txTambah = new javax.swing.JButton();
        txHapus = new javax.swing.JButton();
        txSimpan = new javax.swing.JButton();
        txTHarga = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txBayar = new javax.swing.JLabel();
        txMembayar = new javax.swing.JTextField();
        txTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txKembali = new javax.swing.JTextField();
        txTanggal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txTable = new javax.swing.JTable();
        txBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(25, 118, 211));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU KASIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(287, 287, 287))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("No. Transaksi");

        txTransaksi.setEnabled(false);
        txTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTransaksiActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Date");
        jLabel3.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Nama Kasir");

        txKasir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Hilal", "Zulvan", "Ikmal", "Yusuf", "Ihzan" }));
        txKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txKasirActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Kode Barang");

        txKode.setEnabled(false);
        txKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txKodeActionPerformed(evt);
            }
        });

        txCari.setText("Cari");
        txCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCariActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Nama Barang");

        txBarang.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Harga");

        txHarga.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Jumlah");

        txJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txJumlahActionPerformed(evt);
            }
        });

        txTambah.setBackground(new java.awt.Color(255, 204, 0));
        txTambah.setText("Tambah");
        txTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTambahActionPerformed(evt);
            }
        });

        txHapus.setBackground(new java.awt.Color(255, 102, 102));
        txHapus.setForeground(new java.awt.Color(255, 255, 255));
        txHapus.setText("Hapus");
        txHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txHapusActionPerformed(evt);
            }
        });

        txSimpan.setBackground(new java.awt.Color(25, 118, 211));
        txSimpan.setForeground(new java.awt.Color(255, 255, 255));
        txSimpan.setText("Simpan");
        txSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txSimpanActionPerformed(evt);
            }
        });

        txTHarga.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txTHarga.setText("Total Harga");

        txBayar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txBayar.setText("Bayar");

        txMembayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMembayarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Kembalian");

        txTanggal.setEnabled(false);
        txTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTanggalActionPerformed(evt);
            }
        });

        txTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(txTable);

        txBack.setText("Kembali");
        txBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(txTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4))
                                            .addComponent(txTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txKode, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7)
                                            .addComponent(txBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(txSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txKasir, 0, 150, Short.MAX_VALUE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(104, 104, 104))
                                    .addComponent(txJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(59, 59, 59))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txTHarga)
                            .addComponent(txBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txMembayar)
                            .addComponent(txKembali))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(224, 224, 224)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(408, 408, 408)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txBack, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txKode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txTHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txMembayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txBayar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTransaksiActionPerformed

    private void txKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txKasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txKasirActionPerformed

    private void txJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txJumlahActionPerformed

    private void txCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCariActionPerformed
        new DataBarang().setVisible(true);
        dipilih.clear();
    }//GEN-LAST:event_txCariActionPerformed

    private void txTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTambahActionPerformed
        jumlahTransaksi();
        refresh();
    }//GEN-LAST:event_txTambahActionPerformed

    private void txHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txHapusActionPerformed
        DefaultTableModel model = (DefaultTableModel) txTable.getModel();
        
        int row = txTable.getSelectedRow();
        model.removeRow(row);
        HitungTotalBelanjaTabel h = new HitungTotalBelanjaTabel();
        h.tampilkanTotalBelanja();
        
        txMembayar.setText("0");
        txKembali.setText("0");
    }//GEN-LAST:event_txHapusActionPerformed

    private void txMembayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMembayarActionPerformed
        int total;
        int bayar;
        int kembali;
        
        total = Integer.parseInt(txTotal.getText());
        bayar = Integer.parseInt(txMembayar.getText());
        
        if(total > bayar){
            JOptionPane.showMessageDialog(null, "Uang Tidak Cukup");
        }else{
            kembali = bayar - total;
            txKembali.setText(String.valueOf(kembali));
        }
    }//GEN-LAST:event_txMembayarActionPerformed

    private void txSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txSimpanActionPerformed
        DefaultTableModel model = (DefaultTableModel) txTable.getModel();
        
        String notransaksi = txTransaksi.getText();
        String kasir = (String) txKasir.getSelectedItem();
        String tanggal = txTanggal.getText();
        
        
        try{
            String sql = "INSERT INTO tr VALUES (?, ?, ?)";
            Connection c =(Connection)Koneksi.DBConnection();
            PreparedStatement p = c.prepareStatement(sql);
            
            p.setString(1, notransaksi);
            p.setString(2, kasir);
            p.setString(3, tanggal);
            p.execute();
            JOptionPane.showMessageDialog(null, "Data Pembelanjaan Sudah Tersimpan");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        try{
            int table = txTable.getRowCount();
            
            for(int i = 0; i < table; i++){
                String sql = "INSERT INTO transaksi(no_transaksi, kode, nama, harga, jumlah) VALUES ('" + txTable.getValueAt(i, 0) + "', '" + txTable.getValueAt(i, 1) + "', '" + txTable.getValueAt(i, 4) + "', '" + txTable.getValueAt(i, 5) + "', '" + txTable.getValueAt(i, 6) + "')";
                Connection c =(Connection)Koneksi.DBConnection();
                PreparedStatement p = c.prepareStatement(sql);
                
                p.execute();
                this.setVisible(false);
                new Kasir().setVisible(true);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_txSimpanActionPerformed

    private void txKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txKodeActionPerformed

    private void txTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTanggalActionPerformed

    private void txBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txBackActionPerformed
        dispose();
        new Main().setVisible(true);
    }//GEN-LAST:event_txBackActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton txBack;
    public static javax.swing.JTextField txBarang;
    private javax.swing.JLabel txBayar;
    private javax.swing.JButton txCari;
    private javax.swing.JButton txHapus;
    public static javax.swing.JTextField txHarga;
    public static javax.swing.JTextField txJumlah;
    private javax.swing.JComboBox<String> txKasir;
    private javax.swing.JTextField txKembali;
    public static javax.swing.JTextField txKode;
    private javax.swing.JTextField txMembayar;
    private javax.swing.JButton txSimpan;
    private javax.swing.JLabel txTHarga;
    private javax.swing.JTable txTable;
    private javax.swing.JButton txTambah;
    private javax.swing.JTextField txTanggal;
    private javax.swing.JTextField txTotal;
    public static javax.swing.JTextField txTransaksi;
    // End of variables declaration//GEN-END:variables
}
