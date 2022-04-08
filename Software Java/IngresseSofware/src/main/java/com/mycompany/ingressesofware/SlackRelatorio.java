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
    private static String oAuthToken = "xoxb-3323550130833-3367665686275-gZDbk5ZJVh8Nen3GleJXN1Os";
    private static String slackChannel = "relatoriomonitoramentochannel";

    public static void main(String[] args) {
        sendRelatorioToSlack("testeRelatorio");
    }

    public static void sendRelatorioToSlack(String relatorio) {

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
