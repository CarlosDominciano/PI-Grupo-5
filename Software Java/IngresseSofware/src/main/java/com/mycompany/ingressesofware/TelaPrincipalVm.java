/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ingressesofware;

import com.github.britooo.looca.api.core.Looca;
import com.google.gson.Gson;
import com.mycompany.ingresse.coleta.dados.Componentes;
import com.mycompany.ingresse.coleta.dados.Conexao;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class TelaPrincipalVm extends javax.swing.JFrame {

    TelaLoginVM telaLoginVm = new TelaLoginVM();
    Componentes comps = new Componentes();
    Looca looca = new Looca();
    Conexao connect = new Conexao();
    List<Totem> verificacaoTotem = connect.getJdbc().query(String.format("SELECT * FROM totem WHERE id_processador='%s' AND serial_disco='%s' AND hostname='%s'",comps.getIdProcessador(), comps.getSerialDisco(), comps.getHostname()), new BeanPropertyRowMapper<>(Totem.class));
    Boolean totemCadastrado = verificacaoTotem.isEmpty() ? false : true;
    SlackIntegrationTest slackAlert = new SlackIntegrationTest();
    SlackRelatorio slackRelatorio = new SlackRelatorio();
    Boolean seguranca;
    Usuario sessao;
    Relatorio logAtual;
    Timer timer = new Timer();
    private Long segundo = 1000L;
    private Long minuto = segundo * 60;
    private Long hora = minuto * 60;
    private Boolean autorizar = false;

           
    
    /**
     * Creates new form TelaLogin
     */
    public TelaPrincipalVm(Usuario sessao) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalVm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalVm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalVm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalVm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();
        this.setLocationRelativeTo(null);
        this.sessao = sessao;

//        setExtendedState(MAXIMIZED_BOTH);
        ativadoDesativado.setForeground(Color.RED);
        ativadoDesativado.setText("Pausado...");
        setResizable(false);
        lblHostname.setText(comps.getHostname().toString());
        if(totemCadastrado){
        txtNomeUsuario.setText(String.format("Monitoramento do Totem Nº %d", verificacaoTotem.get(0).getIdTotem()));
        }
       
    }
    
    Relatorio gerarNovoRelatorio(Totem vinculo, Componentes compos){
        Relatorio log = new Relatorio(vinculo.getIdTotem(), compos.getProcessamento().intValue(), compos.regraTres(compos.getMemVolUso(), compos.getRam()), compos.regraTres(compos.getDiscoUso(), compos.getDisco()), compos.getQtdProcessos(), compos.getQtdServicos(), compos.getTemp());
        return log;
    }
    
    void enviarRelatorio(Relatorio relat){
      connect.getJdbc().update("INSERT INTO logs(fkTotem,pctg_processador,pctg_memoria_uso,pctg_disco_uso,qtd_processos,qtd_servicos,temp,data_hora) VALUES (?,?,?,?,?,?,?,?)", relat.getFkTotem(),relat.getPctgProcessador(),relat.getPctgMemoriaUso(),relat.getPctgDiscoUso(),relat.getQtdProcessos(),relat.getQtdServicos(),relat.getTemp(),relat.getDataHora());

    }
     
    void autoMonitorar(Boolean autorizado){
        
        if(autorizado){
        timer.scheduleAtFixedRate(new TimerTask(){
                @Override public void run(){
                    AlertaMonitoramento alertaMonitora = new AlertaMonitoramento();
                    Integer porcentagem1 = comps.getProcessamento().intValue();
                    Integer porcentagem3 = comps.regraTres(comps.getMemVolUso(), comps.getRam());
                    Integer porcentagem5 = comps.regraTres(comps.getDiscoUso(), comps.getDisco());
                    
                    logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
                    enviarRelatorio(logAtual);
                    
                    progres1.setValue(porcentagem1);
                    progres3.setValue(porcentagem3);
                    progres5.setValue(porcentagem5);
                    if (porcentagem1 > 1) {
                        alertaMonitora.setVisible(true);
                        slackAlert.sendMessageToSlack("Alerta: O nível de processamento (CPU) atingiu 50%");
                        alertaMonitora.textoAlertaMonitoramento1(porcentagem1);

                } 
                    if (porcentagem3 > 80) {
                    alertaMonitora.setVisible(true);
                    slackAlert.sendMessageToSlack("Alerta: Memória ram atingiu 80%");
                    alertaMonitora.textoAlertaMonitoramento3(porcentagem3);

                } 
                if (porcentagem5 > 80) {
                    alertaMonitora.setVisible(true);
                    slackAlert.sendMessageToSlack("Alerta: Armazenamento atingiu 80%");
                    alertaMonitora.textoAlertaMonitoramento5(porcentagem5);
                    }
                }
            }, segundo, minuto);
        }
        
        
    }
    
    void enviarRelatorio(){
      logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
      connect.getJdbc().update("INSERT INTO logs(fkTotem,pctg_processador,pctg_memoria_uso,pctg_disco_uso,qtd_processos,qtd_servicos,temp,data_hora) VALUES (?,?,?,?,?,?,?,?)", logAtual.getFkTotem(),logAtual.getPctgProcessador(),logAtual.getPctgMemoriaUso(),logAtual.getPctgDiscoUso(),logAtual.getQtdProcessos(),logAtual.getQtdServicos(),logAtual.getTemp(),logAtual.getDataHora());
      slackRelatorio.sendRelatorio(String.format("%s\n"
              + "_____________________\n"
              + "Relatório do totem:\n\n "
              + "Sistema: %s\n "
              + "Processador: %s\n "
              + "Processador em uso: %s\n "
              + "Memória em uso: %s\n "
              + "Disco em uso: %s\n "
              + "Quantidade de processos: %s\n "
              + "Quantidade de serviços: %s\n " 
              + "Temperatura: %s ",
              logAtual.getDataHora().toString(),
              looca.getSistema().toString(),
              looca.getProcessador().toString(),
              logAtual.getPctgProcessador().toString(),
              logAtual.getPctgMemoriaUso().toString(),
              logAtual.getPctgDiscoUso().toString(),
              logAtual.getQtdProcessos().toString(),
              logAtual.getQtdServicos().toString(),
              logAtual.getTemp().toString()));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        painelLogin = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        painelGeralBackground = new javax.swing.JPanel();
        ativadoDesativado = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblHostname = new javax.swing.JLabel();
        btnSair2 = new javax.swing.JToggleButton();
        txtNomeUsuario = new javax.swing.JLabel();
        btnMonitorarTotens2 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        progres1 = new javax.swing.JProgressBar();
        progres3 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        progres5 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        btnAddTotem = new javax.swing.JToggleButton();
        btnIniciarMonitoramento1 = new javax.swing.JToggleButton();
        btnIniciarMonitoramento = new javax.swing.JToggleButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(97, 0, 131));

        painelLogin.setBackground(new java.awt.Color(97, 0, 131));

        painelGeralBackground.setBackground(new java.awt.Color(97, 0, 120));
        painelGeralBackground.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        painelGeralBackground.setToolTipText("");

        ativadoDesativado.setFont(new java.awt.Font("Liberation Sans", 3, 12)); // NOI18N
        ativadoDesativado.setForeground(new java.awt.Color(204, 0, 0));
        ativadoDesativado.setText("Pausado...");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hostname:");

        lblHostname.setBackground(new java.awt.Color(255, 255, 255));
        lblHostname.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        lblHostname.setForeground(new java.awt.Color(255, 255, 255));
        lblHostname.setText("...");

        btnSair2.setBackground(new java.awt.Color(153, 0, 153));
        btnSair2.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnSair2.setForeground(new java.awt.Color(255, 255, 255));
        btnSair2.setText("X");
        btnSair2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSair2ActionPerformed(evt);
            }
        });

        txtNomeUsuario.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        txtNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtNomeUsuario.setText("Monitoramento do totem:");

        javax.swing.GroupLayout painelGeralBackgroundLayout = new javax.swing.GroupLayout(painelGeralBackground);
        painelGeralBackground.setLayout(painelGeralBackgroundLayout);
        painelGeralBackgroundLayout.setHorizontalGroup(
            painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelGeralBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelGeralBackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHostname, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelGeralBackgroundLayout.createSequentialGroup()
                        .addComponent(txtNomeUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ativadoDesativado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSair2)))
                .addGap(0, 387, Short.MAX_VALUE))
        );
        painelGeralBackgroundLayout.setVerticalGroup(
            painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelGeralBackgroundLayout.createSequentialGroup()
                .addGroup(painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ativadoDesativado)
                        .addComponent(txtNomeUsuario))
                    .addGroup(painelGeralBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSair2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblHostname))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        btnMonitorarTotens2.setBackground(new java.awt.Color(153, 0, 153));
        btnMonitorarTotens2.setFont(new java.awt.Font("Liberation Sans", 0, 36)); // NOI18N
        btnMonitorarTotens2.setForeground(new java.awt.Color(255, 255, 255));
        btnMonitorarTotens2.setText("Monitorar Totens");
        btnMonitorarTotens2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonitorarTotens2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(97, 0, 120));
        jPanel2.setForeground(new java.awt.Color(102, 102, 102));

        progres1.setStringPainted(true);

        progres3.setStringPainted(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cpu:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Memoria em uso: ");

        progres5.setStringPainted(true);

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Armazenamento usado:");

        btnAddTotem.setBackground(new java.awt.Color(153, 0, 153));
        btnAddTotem.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        btnAddTotem.setForeground(new java.awt.Color(255, 255, 255));
        btnAddTotem.setText("Adicionar totem");
        btnAddTotem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTotemActionPerformed(evt);
            }
        });

        btnIniciarMonitoramento1.setBackground(new java.awt.Color(153, 0, 153));
        btnIniciarMonitoramento1.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        btnIniciarMonitoramento1.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarMonitoramento1.setText("Enviar relatório");
        btnIniciarMonitoramento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarMonitoramento1ActionPerformed(evt);
            }
        });

        btnIniciarMonitoramento.setBackground(new java.awt.Color(153, 0, 153));
        btnIniciarMonitoramento.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        btnIniciarMonitoramento.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarMonitoramento.setText("Iniciar monitoria");
        btnIniciarMonitoramento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarMonitoramentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(progres3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(progres1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(progres5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnIniciarMonitoramento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(btnAddTotem, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                    .addComponent(btnIniciarMonitoramento1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 34, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progres1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progres3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progres5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddTotem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciarMonitoramento1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIniciarMonitoramento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelLoginLayout = new javax.swing.GroupLayout(painelLogin);
        painelLogin.setLayout(painelLoginLayout);
        painelLoginLayout.setHorizontalGroup(
            painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLoginLayout.createSequentialGroup()
                .addComponent(painelGeralBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(painelLoginLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnMonitorarTotens2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(painelLoginLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelLoginLayout.setVerticalGroup(
            painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLoginLayout.createSequentialGroup()
                .addComponent(painelGeralBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(366, 366, 366)
                .addGroup(painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLoginLayout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelLoginLayout.createSequentialGroup()
                        .addGap(724, 724, 724)
                        .addComponent(btnMonitorarTotens2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMonitorarTotens2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitorarTotens2ActionPerformed

    }//GEN-LAST:event_btnMonitorarTotens2ActionPerformed

    private void btnSair2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSair2ActionPerformed
        // TODO add your handling code here:
        ativadoDesativado.setForeground(Color.RED);
        ativadoDesativado.setText("Pausado...");

        telaLoginVm.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSair2ActionPerformed

    private void btnIniciarMonitoramentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarMonitoramentoActionPerformed

        if(totemCadastrado){
            ativadoDesativado.setForeground(Color.GREEN);
            ativadoDesativado.setText("Ativado...");
            AlertaMonitoramento alertaMonitora = new AlertaMonitoramento();
            Integer porcentagem1 = comps.getProcessamento().intValue();
            Integer porcentagem3 = comps.regraTres(comps.getMemVolUso(), comps.getRam());
            Integer porcentagem5 = comps.regraTres(comps.getDiscoUso(), comps.getDisco());

            if(autorizar == false){
                autorizar = true;
                autoMonitorar(autorizar);
            }

            progres1.setValue(porcentagem1);
            progres3.setValue(porcentagem3);
            progres5.setValue(porcentagem5);

            //slackAlert.sendMessageToSlack("Alert system");

            if (porcentagem1 > 1) {
                alertaMonitora.setVisible(true);
                slackAlert.sendMessageToSlack("Alerta: O nível de processamento (CPU) atingiu 50%");
                alertaMonitora.textoAlertaMonitoramento1(porcentagem1);

            } if (porcentagem3 > 80) {
                alertaMonitora.setVisible(true);
                slackAlert.sendMessageToSlack("Alerta: Memória ram atingiu 80%");
                alertaMonitora.textoAlertaMonitoramento3(porcentagem3);

            } if (porcentagem5 > 80) {
                alertaMonitora.setVisible(true);
                slackAlert.sendMessageToSlack("Alerta: Armazenamento atingiu 80%");
                alertaMonitora.textoAlertaMonitoramento5(porcentagem5);
            }
        }else{}
    }//GEN-LAST:event_btnIniciarMonitoramentoActionPerformed

    private void btnIniciarMonitoramento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarMonitoramento1ActionPerformed
        // TODO add your handling code here:
        if(totemCadastrado){
            enviarRelatorio();}else{}
    }//GEN-LAST:event_btnIniciarMonitoramento1ActionPerformed

    private void btnAddTotemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTotemActionPerformed
        if(totemCadastrado == false){
            Double discoDouble = (double) (Math.round((comps.getDisco()/1000000000)*1.0/1.0));
            Double ramDouble = (double) (Math.round((comps.getRam()/1000000000)*1.0/1.0));
            //connect.getJdbc().execute(String.format("INSERT INTO totem(fkFilial, ram_total, espaco_disco, processador, data_compra, id_processador) VALUES %d,%.1f,%.1f,%s,%s,%s", sessao.getFkFilial(),ramDouble,discoDouble,comps.getProcessador(),comps.getDataTotem(),comps.getIdProcessador()));
            connect.getJdbc().update("INSERT INTO totem(fkFilial, ram_total, espaco_disco, processador, data_compra, id_processador, serial_disco, hostname) VALUES (?,?,?,?,?,?,?,?)", sessao.getFkFilial(),ramDouble,discoDouble,comps.getProcessador(),comps.getDataTotem(),comps.getIdProcessador(), comps.getSerialDisco(),comps.getHostname());
            telaLoginVm.setVisible(true);
            dispose();
        }
        else{

        }
        //TelaAdicionarTotem addTotem = new TelaAdicionarTotem();
        //addTotem.setVisible(true);
    }//GEN-LAST:event_btnAddTotemActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//
//                new TelaPrincipal().setVisible(true);
//
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ativadoDesativado;
    private javax.swing.JToggleButton btnAddTotem;
    private javax.swing.JToggleButton btnIniciarMonitoramento;
    private javax.swing.JToggleButton btnIniciarMonitoramento1;
    private javax.swing.JToggleButton btnMonitorarTotens2;
    private javax.swing.JToggleButton btnSair2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblHostname;
    private javax.swing.JPanel painelGeralBackground;
    private javax.swing.JPanel painelLogin;
    private javax.swing.JProgressBar progres1;
    private javax.swing.JProgressBar progres3;
    private javax.swing.JProgressBar progres5;
    private javax.swing.JLabel txtNomeUsuario;
    // End of variables declaration//GEN-END:variables
}
