package io.pivotal;

import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.client.HttpClient;

/**
 *
 */
public class RabbitMqManagementIssue {

    public static void main(String[] args) {
        HttpClient client = HttpClient.create("localhost", 15672);

        System.out.println(client.get("/api/overview", r -> {
            r.addHeader("Authorization", "Basic Z3Vlc3Q6Z3Vlc3Q=");
            return r.send();
        }).block().status().code());

        System.out.println(client.put("/api/queues/%2F/test", r -> {
            r.addHeader("Authorization", "Basic Z3Vlc3Q6Z3Vlc3Q=");
            return r.sendString(Mono.just("{\"auto_delete\":false,\"durable\":true,\"arguments\":{}}"));
        }).block().status().code());

        System.out.println(client.put("/api/queues/ghost_vhost/test", r -> {
            r.addHeader("Authorization", "Basic Z3Vlc3Q6Z3Vlc3Q=");
            return r.sendString(Mono.just("{\"auto_delete\":false,\"durable\":true,\"arguments\":{}}"));
        }).block().status().code());
    }

}
