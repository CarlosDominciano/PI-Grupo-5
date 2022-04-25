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
public class SlackToken {
    private static String webHooksUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxp-3323550130833-3296337572615-3435456757794-d88efd095b9c6649bacf5630a273f9fa";
    private static String slackChannel = "tokenchannel";
    
    
    public static void sendToken(String token) {

        try {
            StringBuilder msgbuilder = new StringBuilder();
            msgbuilder.append(token);

            Payload payload = Payload.builder().channel(slackChannel).text(msgbuilder.toString()).build();

            WebhookResponse wbResp = Slack.getInstance().send(webHooksUrl, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
