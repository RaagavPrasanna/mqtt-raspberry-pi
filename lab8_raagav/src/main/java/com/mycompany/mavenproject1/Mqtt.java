/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 *
 * @author Raagav Prasanna
 */
public class Mqtt {

    private final Mqtt5BlockingClient client;
    private String username;
    
    
    public Mqtt(String username, String password) {
        final String host = "c00c5690cfd54ad69a89f60fef3b5be1.s2.eu.hivemq.cloud";
        this.username = username;
        
        
        // create an MQTT client
        client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost(host)
                .serverPort(8883)
                .sslWithDefaultConfig()
                .buildBlocking();

        
        // connect to HiveMQ Cloud with TLS and username/pw
        client.connectWith()
                .simpleAuth()
                .username(username)
                .password(UTF_8.encode(password))
                .applySimpleAuth()
                .send();

        System.out.println("Connected successfully");

        // subscribe to the topic "my/test/topic"

        
        client.subscribeWith()
                .topicFilter("BuzzerRaagavPrasanna")
                .send();
        
        client.subscribeWith()
                .topicFilter("BuzzerAidanCatriel")
                .send();
        
        client.subscribeWith()
                .topicFilter("BuzzerDanhHuynh")
                .send();
        
        client.subscribeWith()
                .topicFilter("CameraTakenRaagavPrasanna")
                .send();
        
        client.subscribeWith()
                .topicFilter("CameraTakenAidanCatriel")
                .send();

        client.subscribeWith()
                .topicFilter("CameraTakenDanhHuynh")
                .send();
                
        client.subscribeWith()
                .topicFilter("CameraPictureRaagavPrasanna")
                .send();
        
        client.subscribeWith()
                .topicFilter("CameraPictureAidanCatriel")
                .send();

        client.subscribeWith()
                .topicFilter("CameraPictureDanhHuynh")
                .send();
        
        // set a callback that is called when a message is received (using the async API style)
        client.toAsync().publishes(ALL, publish -> {
            System.out.println("Received message: "
                    + publish.getTopic() + " -> "
                    + UTF_8.decode(publish.getPayload().get()));

            // disconnect the client after a message was received
            //client.disconnect();
        });

//        // publish a message to the topic "my/test/topic"
//        client.publishWith()
//                .topic("my/test/topic")
//                .payload(UTF_8.encode("Hello"))
//                .send();
    }
    
    public void sendBuzzerMessage(String msg) {
        client.publishWith()
                .topic("Buzzer"+username)
                .payload(UTF_8.encode(msg))
                .send();
    }
    
    public void sendCameraTakenMessage(String msg) {
        client.publishWith()
                .topic("CameraTaken"+username)
                .payload(UTF_8.encode(msg))
                .send(); 
    }
    
    public void sendCameraPictureMessage(String msg) {
        client.publishWith()
                .topic("CameraPicture"+username)
                .payload(UTF_8.encode(msg))
                .send(); 
    }
    
    

//    public static void main(String[] args) throws Exception {
//
//        final String host = "c00c5690cfd54ad69a89f60fef3b5be1.s2.eu.hivemq.cloud";
//        final String username = "RaagavPrasanna";
//        final String password = "Raagav123";
//
//        // create an MQTT client
//        final Mqtt5BlockingClient client = MqttClient.builder()
//                .useMqttVersion5()
//                .serverHost(host)
//                .serverPort(8883)
//                .sslWithDefaultConfig()
//                .buildBlocking();
//
//        // connect to HiveMQ Cloud with TLS and username/pw
//        client.connectWith()
//                .simpleAuth()
//                .username(username)
//                .password(UTF_8.encode(password))
//                .applySimpleAuth()
//                .send();
//
//        System.out.println("Connected successfully");
//
//        // subscribe to the topic "my/test/topic"
//        client.subscribeWith()
//                .topicFilter("Test")
//                .send();
//
//        client.subscribeWith()
//                .topicFilter("Test2")
//                .send();
//
//        // set a callback that is called when a message is received (using the async API style)
//        client.toAsync().publishes(ALL, publish -> {
//            System.out.println("Received message: "
//                    + publish.getTopic() + " -> "
//                    + UTF_8.decode(publish.getPayload().get()));
//
//            // disconnect the client after a message was received
//            //client.disconnect();
//        });
//
////        // publish a message to the topic "my/test/topic"
////        client.publishWith()
////                .topic("my/test/topic")
////                .payload(UTF_8.encode("Hello"))
////                .send();
//    }
}
