/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingresse.cluster;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;


/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class SlackToken {
    private static String webHooksUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxp-3323550130833-3296337572615-3421540740759-f65f00d1ab0e2c201e0d5aa5184e9b0e";
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
