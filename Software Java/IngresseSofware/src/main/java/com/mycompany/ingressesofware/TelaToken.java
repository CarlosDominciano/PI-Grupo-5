/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ingressesofware;

import com.mycompany.ingresse.coleta.dados.Componentes;
import com.mycompany.ingresse.coleta.dados.Conexao;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author carlos.dominciano@VALEMOBI.CORP
 */
public class TelaToken extends javax.swing.JFrame {

    public Boolean getFecharLogin() {
        return fecharLogin;
    }
    
    
    private Usuario sessao;
    private Boolean fecharLogin;
    /**
     * Creates new form TelaToken
     */
    public TelaToken(Usuario sessao) {
        this.sessao = sessao;
        initComponents();
        this.setLocationRelativeTo(null);
        setTitle("Confirmação Token");
        this.fecharLogin = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblErro = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnConfirmacaoToken = new javax.swing.JButton();
        txtToken = new javax.swing.JTextField();
        lblTokenSeguranca = new javax.swing.JLabel();
        btnEnviarCodigo = new javax.swing.JButton();
        lblErro1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("asd");
        setBackground(new java.awt.Color(97, 0, 131));
        setFocusable(false);

        lblErro.setForeground(new java.awt.Color(255, 0, 0));

        jPanel1.setBackground(new java.awt.Color(97, 0, 131));

        btnConfirmacaoToken.setText("OK");
        btnConfirmacaoToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmacaoTokenActionPerformed(evt);
            }
        });

        txtToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTokenActionPerformed(evt);
            }
        });

        lblTokenSeguranca.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        lblTokenSeguranca.setForeground(new java.awt.Color(255, 255, 255));
        lblTokenSeguranca.setText("Digite o Token de segurança");

        btnEnviarCodigo.setText("Enviar código");
        btnEnviarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarCodigoActionPerformed(evt);
            }
        });

        lblErro1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        lblErro1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(lblErro1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(lblTokenSeguranca, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmacaoToken, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEnviarCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addComponent(txtToken)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnEnviarCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTokenSeguranca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirmacaoToken)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErro1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(lblErro, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("asd");
        getAccessibleContext().setAccessibleParent(lblTokenSeguranca);

        setSize(new java.awt.Dimension(429, 226));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    Integer randomToken = ThreadLocalRandom.current().nextInt(5023, 10023);

    Integer contadorErro = 0;
    private void btnConfirmacaoTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmacaoTokenActionPerformed
        Conexao connect = new Conexao();
        Componentes comps = new Componentes();
        TelaPrincipal telaPrincipal = new TelaPrincipal(sessao);
        List<Totem> verificacaoTotem = connect.getJdbc().query(String.format("SELECT * FROM totem WHERE id_processador='%s'",comps.getIdProcessador()), new BeanPropertyRowMapper<>(Totem.class));
        
        if (randomToken.toString().equals(txtToken.getText()) && (verificacaoTotem.isEmpty()||verificacaoTotem.get(0).getFkFilial()==sessao.getFkFilial())) 
        {
            telaPrincipal.setVisible(true);
            dispose();
            fecharLogin = true;
            //System.out.println(verificacaoTotem.isEmpty() == false && 
                    //verificacaoTotem.get(0).getFkFilial()==sessao.getFkFilial());
            

        } else {
           if(randomToken.toString().equals(txtToken.getText()) == false){
               if (contadorErro < 3) {
                lblErro1.setText("O token está incorreto");
             
                contadorErro++;
            }else if( contadorErro > 2 && contadorErro < 4) {
                 contadorErro++;
                lblErro1.setText("Falha, tente novamente");
                
            } else{
             //   contadorErro++;
                dispose();
                
            }
           }
            if(verificacaoTotem.isEmpty() == false && verificacaoTotem.get(0).getFkFilial()==sessao.getFkFilial()){
            
             if(randomToken.toString().equals(txtToken.getText()) == false){
               if (contadorErro < 3) {
                lblErro1.setText("O token está incorreto");
             
                contadorErro++;
            }else if( contadorErro > 2 && contadorErro < 4) {
                 contadorErro++;
                lblErro1.setText("Falha, tente novamente");
                
            } else{
             //   contadorErro++;
                dispose();
            }
            }
            }else{
           
            System.out.println("Caiu aqui");
            }
        }
    }//GEN-LAST:event_btnConfirmacaoTokenActionPerformed

    void fechar() {
        if (contadorErro > 2){
        dispose();    
        }
        

    }

    
    private void txtTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTokenActionPerformed

    }//GEN-LAST:event_txtTokenActionPerformed

    private void btnEnviarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarCodigoActionPerformed
        
        SlackToken.sendToken(randomToken.toString());    
        lblErro1.setText("O Código foi enviado");
     
    }//GEN-LAST:event_btnEnviarCodigoActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                     
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TelaToken.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TelaToken.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TelaToken.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TelaToken.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        
//       
//        
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TelaToken().setVisible(true);
//                 
//            }
//        });
//    }

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmacaoToken;
    private javax.swing.JButton btnEnviarCodigo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblErro;
    private javax.swing.JLabel lblErro1;
    private javax.swing.JLabel lblTokenSeguranca;
    private javax.swing.JTextField txtToken;
    // End of variables declaration//GEN-END:variables
}
