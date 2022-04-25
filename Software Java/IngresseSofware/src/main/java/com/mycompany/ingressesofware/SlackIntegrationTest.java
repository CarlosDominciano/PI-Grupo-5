/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingressesofware;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class SlackIntegrationTest {
   
    private static String webHooksUrl = "https://hooks.slack.com/services/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";
    
    private static String oAuthToken = "xoxb-3323550130833-3445735356929-XLP9d8DIseR07ViJ3aQleTdw";
    private static String slackChannel = "alertachannel";
    
//    public static void main(String[] args) {;;
//       
//        sendMessageToSlack("Alert monitoring totens cine test");
//    }
    
    
    
    public static void sendMessageToSlack(String mensagem){
        try {
                 StringBuilder msgbuilder = new StringBuilder();
        msgbuilder.append(mensagem);
        
        Payload payload = Payload.builder().channel(slackChannel).text(msgbuilder.toString()).build();
        
        WebhookResponse wbResp = Slack.getInstance().send(webHooksUrl, payload);
        }catch(Exception e){
            e.printStackTrace();
        }
        
       
   
        
    }
}
