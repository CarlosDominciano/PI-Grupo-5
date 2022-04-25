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
public class TelaPrincipal extends javax.swing.JFrame {

    TelaLogin telaLogin = new TelaLogin();
    Componentes comps = new Componentes();
    Looca looca = new Looca();
    Conexao connect = new Conexao();
    List<Totem> verificacaoTotem = connect.getJdbc().query(String.format("SELECT * FROM totem WHERE id_processador='%s'",comps.getIdProcessador()), new BeanPropertyRowMapper<>(Totem.class));
    Boolean totemCadastrado = verificacaoTotem.isEmpty() ? false : true;
    SlackIntegrationTest slackAlert = new SlackIntegrationTest();
    Boolean seguranca;
    Usuario sessao;
    Relatorio logAtual;
    Timer timer = new Timer();
    private Long segundo = 1000L;
    private Long minuto = segundo * 60;
    private Long hora = minuto * 60;

           
    
    /**
     * Creates new form TelaLogin
     */
    public TelaPrincipal(Usuario sessao) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        String processos = new Gson().toJson(compos.getProcessos());
        String servicos = new Gson().toJson(compos.getServicosAtv());
        Relatorio log = new Relatorio(vinculo.getIdTotem(), compos.getProcessamento().intValue(), compos.regraTres(compos.getMemVolUso(), compos.getRam()), compos.regraTres(compos.getDiscoUso(), compos.getDisco()), compos.getQtdProcessos(), compos.getQtdServicos(), compos.getTemp(), servicos, processos);
        return log;
    }
    
    void enviarRelatorio(Relatorio relat){
      relat = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
      connect.getJdbc().update("INSERT INTO logs(fkTotem,pctg_processador,pctg_memoria_uso,pctg_disco_uso,qtd_processos,qtd_servicos,temp,servicos,processos,data_hora) VALUES (?,?,?,?,?,?,?,?,?,?)", relat.getFkTotem(),relat.getPctgProcessador(),relat.getPctgMemoriaUso(),relat.getPctgDiscoUso(),relat.getQtdProcessos(),relat.getQtdServicos(),relat.getTemp(),relat.getServicos(),relat.getProcessos(),relat.getDataHora());

    }
    void enviarRelatorio(){
      connect.getJdbc().update("INSERT INTO logs(fkTotem,pctg_processador,pctg_memoria_uso,pctg_disco_uso,qtd_processos,qtd_servicos,temp,servicos,processos,data_hora) VALUES (?,?,?,?,?,?,?,?,?,?)", logAtual.getFkTotem(),logAtual.getPctgProcessador(),logAtual.getPctgMemoriaUso(),logAtual.getPctgDiscoUso(),logAtual.getQtdProcessos(),logAtual.getQtdServicos(),logAtual.getTemp(),logAtual.getServicos(),logAtual.getProcessos(),logAtual.getDataHora());
      slackAlert.sendMessageToSlack(String.format("Sistema: %s\n Processador: %s\n Memória em uso: %s\n Disco em uso: %s\n Quantidade de processos: %s\n %s Quantidade de serviços: %s\n Serviços: %s\n %s Processos: %s ", looca.getSistema().toString(),logAtual.getDataHora().toString(),logAtual.getPctgProcessador().toString(),logAtual.getPctgMemoriaUso().toString(),logAtual.getPctgDiscoUso().toString(),logAtual.getQtdProcessos().toString(),logAtual.getQtdServicos().toString(),logAtual.getTemp().toString(),logAtual.getServicos().toString(),logAtual.getProcessos().toString()));
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
        txtNomeUsuario = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSair2 = new javax.swing.JToggleButton();
        ativadoDesativado = new javax.swing.JLabel();
        lblHostname = new javax.swing.JLabel();
        btnMonitorarTotens2 = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        progres1 = new javax.swing.JProgressBar();
        progres3 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        progres5 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        btnAddTotem = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        btnAdicionarTotem = new javax.swing.JToggleButton();
        btnProcessos = new javax.swing.JToggleButton();
        btnAdicionarTotem4 = new javax.swing.JToggleButton();
        btnAdicionarTotem5 = new javax.swing.JToggleButton();
        btnAdicionarTotem6 = new javax.swing.JToggleButton();
        btnAdicionarTotem7 = new javax.swing.JToggleButton();
        jLabel12 = new javax.swing.JLabel();
        btnSistema = new javax.swing.JToggleButton();
        btnAdicionarTotem9 = new javax.swing.JToggleButton();
        btnAdicionarTotem10 = new javax.swing.JToggleButton();
        btnIniciarMonitoramento = new javax.swing.JToggleButton();
        btnIniciarMonitoramento1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMenu = new javax.swing.JTextArea();

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

        txtNomeUsuario.setFont(new java.awt.Font("Liberation Sans", 0, 36)); // NOI18N
        txtNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtNomeUsuario.setText("Monitoramento do totem");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hostname:");

        btnSair2.setBackground(new java.awt.Color(153, 0, 153));
        btnSair2.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnSair2.setForeground(new java.awt.Color(255, 255, 255));
        btnSair2.setText("Sair");
        btnSair2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSair2ActionPerformed(evt);
            }
        });

        ativadoDesativado.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        ativadoDesativado.setForeground(new java.awt.Color(204, 0, 0));
        ativadoDesativado.setText("Pausado...");

        lblHostname.setBackground(new java.awt.Color(255, 255, 255));
        lblHostname.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblHostname.setForeground(new java.awt.Color(255, 255, 255));
        lblHostname.setText("...");

        javax.swing.GroupLayout painelGeralBackgroundLayout = new javax.swing.GroupLayout(painelGeralBackground);
        painelGeralBackground.setLayout(painelGeralBackgroundLayout);
        painelGeralBackgroundLayout.setHorizontalGroup(
            painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelGeralBackgroundLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ativadoDesativado)
                    .addGroup(painelGeralBackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHostname, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeUsuario)
                .addGap(195, 195, 195)
                .addComponent(btnSair2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelGeralBackgroundLayout.setVerticalGroup(
            painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelGeralBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelGeralBackgroundLayout.createSequentialGroup()
                        .addGroup(painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblHostname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ativadoDesativado))
                    .addGroup(painelGeralBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSair2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone.png"))); // NOI18N

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
        btnAddTotem.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnAddTotem.setForeground(new java.awt.Color(255, 255, 255));
        btnAddTotem.setText("Adicionar totem");
        btnAddTotem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTotemActionPerformed(evt);
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addContainerGap(213, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(progres3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(progres1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(progres5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddTotem, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progres1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progres3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progres5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnAddTotem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(97, 0, 120));
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.setAutoscrolls(true);

        btnAdicionarTotem.setBackground(new java.awt.Color(153, 0, 153));
        btnAdicionarTotem.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnAdicionarTotem.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarTotem.setText("Memória ram");
        btnAdicionarTotem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTotemActionPerformed(evt);
            }
        });

        btnProcessos.setBackground(new java.awt.Color(153, 0, 153));
        btnProcessos.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnProcessos.setForeground(new java.awt.Color(255, 255, 255));
        btnProcessos.setText("Processos");
        btnProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessosActionPerformed(evt);
            }
        });

        btnAdicionarTotem4.setBackground(new java.awt.Color(153, 0, 153));
        btnAdicionarTotem4.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnAdicionarTotem4.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarTotem4.setText("Informação do disco");
        btnAdicionarTotem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTotem4ActionPerformed(evt);
            }
        });

        btnAdicionarTotem5.setBackground(new java.awt.Color(153, 0, 153));
        btnAdicionarTotem5.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnAdicionarTotem5.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarTotem5.setText("Processamento");
        btnAdicionarTotem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTotem5ActionPerformed(evt);
            }
        });

        btnAdicionarTotem6.setBackground(new java.awt.Color(153, 0, 153));
        btnAdicionarTotem6.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnAdicionarTotem6.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarTotem6.setText("Quantidade processos");
        btnAdicionarTotem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTotem6ActionPerformed(evt);
            }
        });

        btnAdicionarTotem7.setBackground(new java.awt.Color(153, 0, 153));
        btnAdicionarTotem7.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnAdicionarTotem7.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarTotem7.setText("Memória ram em uso");
        btnAdicionarTotem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTotem7ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Visualizar Hardware do totem:");

        btnSistema.setBackground(new java.awt.Color(153, 0, 153));
        btnSistema.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnSistema.setForeground(new java.awt.Color(255, 255, 255));
        btnSistema.setText("Sistema");
        btnSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSistemaActionPerformed(evt);
            }
        });

        btnAdicionarTotem9.setBackground(new java.awt.Color(153, 0, 153));
        btnAdicionarTotem9.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnAdicionarTotem9.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarTotem9.setText("Processador");
        btnAdicionarTotem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTotem9ActionPerformed(evt);
            }
        });

        btnAdicionarTotem10.setBackground(new java.awt.Color(153, 0, 153));
        btnAdicionarTotem10.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnAdicionarTotem10.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarTotem10.setText("Serviços");
        btnAdicionarTotem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTotem10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12)
                    .addComponent(btnAdicionarTotem7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionarTotem6, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(btnAdicionarTotem5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(btnAdicionarTotem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProcessos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionarTotem9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionarTotem4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionarTotem10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProcessos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnAdicionarTotem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdicionarTotem5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btnAdicionarTotem6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdicionarTotem7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnAdicionarTotem4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdicionarTotem9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnAdicionarTotem10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        btnIniciarMonitoramento.setBackground(new java.awt.Color(153, 0, 153));
        btnIniciarMonitoramento.setFont(new java.awt.Font("Liberation Sans", 0, 30)); // NOI18N
        btnIniciarMonitoramento.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarMonitoramento.setText("Iniciar monitoramento");
        btnIniciarMonitoramento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarMonitoramentoActionPerformed(evt);
            }
        });

        btnIniciarMonitoramento1.setBackground(new java.awt.Color(153, 0, 153));
        btnIniciarMonitoramento1.setFont(new java.awt.Font("Liberation Sans", 0, 30)); // NOI18N
        btnIniciarMonitoramento1.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarMonitoramento1.setText("Enviar relatório");
        btnIniciarMonitoramento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarMonitoramento1ActionPerformed(evt);
            }
        });

        txtMenu.setColumns(20);
        txtMenu.setRows(5);
        jScrollPane1.setViewportView(txtMenu);

        javax.swing.GroupLayout painelLoginLayout = new javax.swing.GroupLayout(painelLogin);
        painelLogin.setLayout(painelLoginLayout);
        painelLoginLayout.setHorizontalGroup(
            painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelGeralBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelLoginLayout.createSequentialGroup()
                .addGroup(painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLoginLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnMonitorarTotens2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelLoginLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelLoginLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel6)))
                .addGroup(painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelLoginLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLoginLayout.createSequentialGroup()
                                .addComponent(btnIniciarMonitoramento, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnIniciarMonitoramento1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelLoginLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        painelLoginLayout.setVerticalGroup(
            painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLoginLayout.createSequentialGroup()
                .addComponent(painelGeralBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLoginLayout.createSequentialGroup()
                        .addGroup(painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(painelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIniciarMonitoramento1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIniciarMonitoramento, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelLoginLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addComponent(painelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarMonitoramento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarMonitoramento1ActionPerformed
        // TODO add your handling code here:
        if(totemCadastrado){
            enviarRelatorio();}else{}
    }//GEN-LAST:event_btnIniciarMonitoramento1ActionPerformed

    private void btnIniciarMonitoramentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarMonitoramentoActionPerformed
        
        if(totemCadastrado){
            ativadoDesativado.setForeground(Color.GREEN);
            ativadoDesativado.setText("Ativado...");
          
            AlertaMonitoramento alertaMonitora = new AlertaMonitoramento();
            logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);

            Integer porcentagem1 = comps.getProcessamento().intValue();
            Integer porcentagem3 = comps.regraTres(comps.getMemVolUso(), comps.getRam());
            Integer porcentagem5 = comps.regraTres(comps.getDiscoUso(), comps.getDisco());

            progres1.setValue(porcentagem1);
            progres3.setValue(porcentagem3);
            progres5.setValue(porcentagem5);

            //slackAlert.sendMessageToSlack("Alert system");
            timer.scheduleAtFixedRate(new TimerTask(){
                @Override public void run(){
                    enviarRelatorio(logAtual);
                }
            }, 1000, minuto * 3);

            if (porcentagem1 > 80) {
                alertaMonitora.setVisible(true);
                alertaMonitora.textoAlertaMonitoramento1();

            } else if (porcentagem3 > 80) {
                alertaMonitora.setVisible(true);
                alertaMonitora.textoAlertaMonitoramento3();

            } else if (porcentagem5 > 80) {
                alertaMonitora.setVisible(true);
                alertaMonitora.textoAlertaMonitoramento5();
            }
        }else{}

    }//GEN-LAST:event_btnIniciarMonitoramentoActionPerformed

    private void btnAdicionarTotem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTotem9ActionPerformed
        // TODO add your handling code here:
        if(totemCadastrado){
         
            txtMenu.setText(looca.getSistema().toString());
            logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
        }else{}
    }//GEN-LAST:event_btnAdicionarTotem9ActionPerformed

    private void btnSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSistemaActionPerformed
        // TODO add your handling code here:
        if(totemCadastrado){
            txtMenu.setText(comps.getServicosAtv().toString());
            logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
        }else{}
    }//GEN-LAST:event_btnSistemaActionPerformed

    private void btnAdicionarTotem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTotem7ActionPerformed
        // TODO add your handling code here:
        if(totemCadastrado){
            Long result = comps.getMemVolUso()/1000000000;
            txtMenu.setText(result.toString()+" GB");
            logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
        }else{}
    }//GEN-LAST:event_btnAdicionarTotem7ActionPerformed

    private void btnAdicionarTotem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTotem6ActionPerformed
        // TODO add your handling code here:
        if(totemCadastrado){
            txtMenu.setText(comps.getQtdProcessos().toString()+" Processos");
            logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
        }else{}
    }//GEN-LAST:event_btnAdicionarTotem6ActionPerformed

    private void btnAdicionarTotem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTotem5ActionPerformed
        // TODO add your handling code here:
        if(totemCadastrado){
            txtMenu.setText(comps.getProcessamento().toString()+"% da capacidade da CPU");
            logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
        }else{}
    }//GEN-LAST:event_btnAdicionarTotem5ActionPerformed

    private void btnAdicionarTotem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTotem4ActionPerformed
        // TODO add your handling code here:
        if(totemCadastrado){
            Long result = comps.getDisco()/1000000000;
            txtMenu.setText(result.toString()+" GB");
            logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
        }else{}
    }//GEN-LAST:event_btnAdicionarTotem4ActionPerformed

    private void btnProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessosActionPerformed
        if(totemCadastrado){
            txtMenu.setText(comps.getProcessos().toString());
            logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
        }
        else{}

    }//GEN-LAST:event_btnProcessosActionPerformed

    private void btnAdicionarTotemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTotemActionPerformed
        // TODO add your handling code here:
        if(totemCadastrado){
            Long result = comps.getRam()/1000000000;
            txtMenu.setText(result.intValue()+" GB");
            logAtual = gerarNovoRelatorio(verificacaoTotem.get(0),comps);
        }else{

        }
    }//GEN-LAST:event_btnAdicionarTotemActionPerformed

    private void btnAddTotemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTotemActionPerformed
        if(totemCadastrado == false){
            Double discoDouble = (double) (Math.round((comps.getDisco()/1000000000)*1.0/1.0));
            Double ramDouble = (double) (Math.round((comps.getRam()/1000000000)*1.0/1.0));
            //connect.getJdbc().execute(String.format("INSERT INTO totem(fkFilial, ram_total, espaco_disco, processador, data_compra, id_processador) VALUES %d,%.1f,%.1f,%s,%s,%s", sessao.getFkFilial(),ramDouble,discoDouble,comps.getProcessador(),comps.getDataTotem(),comps.getIdProcessador()));
            connect.getJdbc().update("INSERT INTO totem(fkFilial, ram_total, espaco_disco, processador, data_compra, id_processador) VALUES (?,?,?,?,?,?)", sessao.getFkFilial(),ramDouble,discoDouble,comps.getProcessador(),comps.getDataTotem(),comps.getIdProcessador());
            telaLogin.setVisible(true);
            dispose();
        }
        else{

        }
        //TelaAdicionarTotem addTotem = new TelaAdicionarTotem();
        //addTotem.setVisible(true);
    }//GEN-LAST:event_btnAddTotemActionPerformed

    private void btnMonitorarTotens2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitorarTotens2ActionPerformed

    }//GEN-LAST:event_btnMonitorarTotens2ActionPerformed

    private void btnSair2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSair2ActionPerformed
        // TODO add your handling code here:
        ativadoDesativado.setForeground(Color.RED);
        ativadoDesativado.setText("Pausado...");

        telaLogin.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSair2ActionPerformed

    private void btnAdicionarTotem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTotem10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarTotem10ActionPerformed

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
    private javax.swing.JToggleButton btnAdicionarTotem;
    private javax.swing.JToggleButton btnAdicionarTotem10;
    private javax.swing.JToggleButton btnAdicionarTotem4;
    private javax.swing.JToggleButton btnAdicionarTotem5;
    private javax.swing.JToggleButton btnAdicionarTotem6;
    private javax.swing.JToggleButton btnAdicionarTotem7;
    private javax.swing.JToggleButton btnAdicionarTotem9;
    private javax.swing.JToggleButton btnIniciarMonitoramento;
    private javax.swing.JToggleButton btnIniciarMonitoramento1;
    private javax.swing.JToggleButton btnMonitorarTotens2;
    private javax.swing.JToggleButton btnProcessos;
    private javax.swing.JToggleButton btnSair2;
    private javax.swing.JToggleButton btnSistema;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHostname;
    private javax.swing.JPanel painelGeralBackground;
    private javax.swing.JPanel painelLogin;
    private javax.swing.JProgressBar progres1;
    private javax.swing.JProgressBar progres3;
    private javax.swing.JProgressBar progres5;
    private javax.swing.JTextArea txtMenu;
    private javax.swing.JLabel txtNomeUsuario;
    // End of variables declaration//GEN-END:variables
}
