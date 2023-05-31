/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.*;
import model.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC PRAKTIKUM
 */
public class Controller {
    Model model;
    View view;
    
    private String data[][];
    private String marker;
    
    public Controller (Model model, View view){
        this.model = model;
        this.view = view;
        data = model.readDataUser();
        
        view.table.setModel(new DefaultTableModel(data, new String[]{
            "Judul", "Alur", "Penokohan", "Akting", "Nilai"
        }) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        view.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.table.getSelectedRow();
                String judul = view.table.getValueAt(row, 0).toString();
                String alur = view.table.getValueAt(row, 1).toString();
                String penokohan = view.table.getValueAt(row, 2).toString();
                String akting = view.table.getValueAt(row, 3).toString();
               
                
                view.setJudul(judul);
                view.setAlur(alur);
                view.setPenokohan(penokohan);
                view.setAkting(akting);
                
             }
        });
        
        view.input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
               String judul = view.getJudul();
               double alur = Double.parseDouble(view.getAlur() );
               double penokohan = Double.parseDouble(view.getPenokohan() );
               double akting = Double.parseDouble(view.getAkting());
                             
               double nilai = (alur+penokohan+akting)/3;
                
               
                    model.insertFilm(judul, alur, penokohan, akting, nilai);
                
                    marker = "";
                    
                    data = model.readDataUser();
                    view.table.setModel(new DefaultTableModel(data, new String[]{
                        "Judul", "Alur", "Penokohan", "Akting", "Nilai"
                    }) {
                        boolean[] canEdit = new boolean[]{
                            false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit[columnIndex];
                        }
                    });
                    
                }        
        });
        
        
       
    }
    
}


