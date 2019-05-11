/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.SystemAdminWorkArea;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.EventEnterprise;
import Business.WorkQueue.WorkRequest;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author KKK
 */
public class SendHospitalRequest extends javax.swing.JPanel {

    /**
     * Creates new form SendHospitalRequest
     */
    private JPanel userProcessContainer;
    private EcoSystem eco;
    private static int funds = 0;
    int count = 0;

    public SendHospitalRequest(JPanel container, EcoSystem eco) {
        initComponents();
        this.userProcessContainer = container;
        this.eco = eco;
        initComponents();
        populateTable();
        populateJComboBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        fundcollected = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        hospitalComboBox = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient Name", "Patient ", "Priority", "Age", "Request Type", "Status", "Disease", "Hospital Fee"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);

        jLabel1.setText("Funds Collected: ");

        fundcollected.setText("jLabel2");

        jButton1.setText("Assign To Hospital");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        hospitalComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hospitalComboBoxActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(fundcollected))
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hospitalComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fundcollected))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hospitalComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(214, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Enterprise enter = (Enterprise) hospitalComboBox.getSelectedItem();
        int selectedRow = workRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please make a selection");
            return;
        }
        WorkRequest request = (WorkRequest) workRequestJTable.getValueAt(selectedRow, 4);
        if ((funds - request.getApproxPatientFee()) >= 0) {
            System.out.println(request.getStatus());
            if (request.getStatus().equals("Hospital Sent")) {
                JOptionPane.showMessageDialog(null, "Patient Already Added");
            } else if (request.getStatus().equals("Funding Process is going on")) {
                for (UserAccount user : enter.getUserAccountDirectory().getUserAccountList()) {
                    if (user.getUsername().equals("adminhosp")) {
                        // System.out.println(user);
                        //System.out.println(request.getStatus());
                        //System.out.println(user.getWorkQueue().getWorkRequestList());
                        request.setStatus("Hospital Sent");
                        request.setTypeOfRequest("Hospital Patient");
                        user.getWorkQueue().getWorkRequestList().add(request);
                        //System.out.println(request.getStatus());
                        JOptionPane.showMessageDialog(null, "Patient Successfully Added To Hospital");
                        populateTable();
                    }
                }
                for (Organization organ : enter.getOrganizationDirectory().getOrganizationList()) {
                    for (UserAccount user : organ.getUserAccountDirectory().getUserAccountList()) {
                        if (user.getRole().toString().equals("ReceptionistRole")) {
                            System.out.println(user.getWorkQueue().getWorkRequestList());
                            System.out.println(request.getStatus());
                            user.getWorkQueue().getWorkRequestList().add(request);
                            System.out.println(request.getStatus());
                            JOptionPane.showMessageDialog(null, "Patient Successfully Added To Receptionist");
                            populateTable();
                        }
                    }
                }
            } else if (request.getStatus().equals("Treatment Completed")) {
                JOptionPane.showMessageDialog(null, "Treatment is already completed");
            } else if (request.getStatus().equals("Assigned To Doctor")) {
                JOptionPane.showMessageDialog(null, "Patient has been assigned to Doctor already");
            } else if (request.getStatus().equals("Pendind")) {
                JOptionPane.showMessageDialog(null, "Pending from doctor");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient Funds");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void hospitalComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hospitalComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hospitalComboBoxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        Component[] componentArray = userProcessContainer.getComponents();
        Component component = componentArray[componentArray.length - 1];
        SystemAdminWorkAreaJPanel sysAdminwjp = (SystemAdminWorkAreaJPanel) component;
        sysAdminwjp.populateTree();
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fundcollected;
    private javax.swing.JComboBox hospitalComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
public void populateTable() {
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();

        model.setRowCount(0);
//        for (UserAccount user : nurseOrganization.getUserAccountDirectory().getUserAccountList()) {
//            System.out.print(user.getRole());
//        }
        System.out.println("Hey");
        for (Network net : eco.getNetworkList()) {
            for (Enterprise enter : net.getEnterpriseDirectory().getEnterpriseList()) {
                if (enter.getEnterpriseType().equals(Enterprise.EnterpriseType.Event)) {
                    System.out.println(enter.getEnterpriseType());
                    for (UserAccount user : enter.getUserAccountDirectory().getUserAccountList()) {
                        System.out.println(user.getRole().toString());
                        if (user.getUsername().equals("adminfund")) {
                            count++;
                            for (WorkRequest req : user.getWorkQueue().getWorkRequestList()) {
                                System.out.println(req);
                                if (req.getTypeOfRequest().equals("Hospital Patient") || (req.getTypeOfRequest().equals("Fund Patient"))) {
                                    Object[] row = new Object[8];
                                    row[0] = req.getPatientFirstname();
                                    row[1] = req.getPatientLastname();
                                    row[2] = req.getPpriority();
                                    row[3] = req.getPage();
                                    row[4] = req;
                                    row[5] = req.getStatus();
                                    row[6] = req.getPdiagnosis();
                                    row[7] = req.getApproxPatientFee();
                                    model.addRow(row);
                                }
                            }
                        }
                    }
                    if (count == 1) {
                        EventEnterprise event = (EventEnterprise) enter;
                        int temp = event.getFundsCollected();
                        System.out.println(temp);
                        if (temp != funds) {
                            funds = temp;
                        }
                        System.out.println(funds);
                        fundcollected.setText(Integer.toString(funds));
                    }
                }
            }
        }
    }

    public void populateJComboBox() {
        hospitalComboBox.removeAllItems();
        for (Network network : eco.getNetworkList()) {
            for (Enterprise enter : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enter.getEnterpriseType().equals(Enterprise.EnterpriseType.Hospital)) {
                    System.out.print(enter.getName());
                    hospitalComboBox.addItem(enter);
                }
            }
        }
    }
}
