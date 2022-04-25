/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingressesofware;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

public class SlackRelatorio {

    private static String webHooksUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-3323550130833-3433128763203-3XEprbIQVQ0NdfduXVD4h6ix";
    private static String slackChannel = "relatorio";



    public static void sendRelatorio(String relatorio) {

        try {
            StringBuilder msgbuilder = new StringBuilder();
            msgbuilder.append(relatorio);

            Payload payload = Payload.builder().channel(slackChannel).text(msgbuilder.toString()).build();

            WebhookResponse wbResp = Slack.getInstance().send(webHooksUrl, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
