/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author Raagav Prasanna
 */
public class Mqtt {

    private final Mqtt5BlockingClient client;
    private String username;
    
    Keys k;
    
    
    public Mqtt(String username, String password, String keyStoreFilePath, String keyStorePassword) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException{
        final String host = "c00c5690cfd54ad69a89f60fef3b5be1.s2.eu.hivemq.cloud";
        this.username = username;
        
        k = new Keys(keyStorePassword, keyStoreFilePath);
        
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
        
        client.subscribeWith()
                .topicFilter("KeyRaagavPrasanna")
                .send();
        
        client.subscribeWith()
                .topicFilter("KeyAidanCatriel")
                .send();
                
        client.subscribeWith()
                .topicFilter("KeyDanhHuynh")
                .send();
        
        
        client.subscribeWith()
                .topicFilter("TemperatureTakenRaagavPrasanna")
                .send();
        
        client.subscribeWith()
                .topicFilter("TemperatureTakenAidanCatriel")
                .send();
                
        client.subscribeWith()
                .topicFilter("TemperatureTakenDanhHuynh")
                .send();
        
        
        client.subscribeWith()
                .topicFilter("TemperatureDataRaagavPrasanna")
                .send();
        
        client.subscribeWith()
                .topicFilter("TemperatureDataAidanCatriel")
                .send();
                
        client.subscribeWith()
                .topicFilter("TemperatureDataDanhHuynh")
                .send();
        
        // set a callback that is called when a message is received (using the async API style)
        client.toAsync().publishes(ALL, publish -> {
            String payload = "";
            try {
                payload = k.verifyAndReturnInput(UTF_8.decode(publish.getPayload().get()).toString());
                
                if(publish.getTopic().toString().equals("BuzzerRaagavPrasanna")) {
                    App.screen.textAreaForBuzzerRaagav.setText(payload);
                } else if(publish.getTopic().toString().equals("CameraTakenRaagavPrasanna")) {
                    App.screen.textAreaForMotionDetectorRaagav.setText(payload);
                } else if(publish.getTopic().toString().equals("TemperatureDataRaagavPrasanna")) {
                    String[] splitData = payload.split(",");
                    
                    String data = "Temperature: " + splitData[0] +" Humidity: " +splitData[1];
                    
                    App.screen.textAreaForTemperatureAndHumidityRaagav.setText(data);
                } else if(publish.getTopic().toString().equals("CameraPictureRaagavPrasanna")) {
                    System.out.println("Entered topic");
                    
                    
                    byte[] decodedBytes = Base64.getDecoder().decode(payload);

                    ByteArrayInputStream bais = new ByteArrayInputStream(decodedBytes);
                    
                    Image image = new Image(bais);
                    
                    App.screen.ImageTileForCameraRaagav.setImage(image);
                }
                
                else if(publish.getTopic().toString().equals("BuzzerAidanCatriel")) {
                    App.screen.textAreaForBuzzerAidan.setText(payload);
                } else if(publish.getTopic().toString().equals("CameraTakenAidanCatriel")) {
                    App.screen.textAreaForMotionDetectorAidan.setText(payload);
                } else if(publish.getTopic().toString().equals("TemperatureDataAidanCatriel")) {
                    String[] splitData = payload.split(",");
                    
                    String data = "Temperature: " + splitData[0] +" Humidity: " +splitData[1];
                    
                    App.screen.textAreaForTemperatureAndHumidityAidan.setText(data);
                } else if(publish.getTopic().toString().equals("CameraPictureAidanCatriel")) {
     
                    byte[] decodedBytes = Base64.getDecoder().decode(payload);

                    ByteArrayInputStream bais = new ByteArrayInputStream(decodedBytes);
                    
                    Image image = new Image(bais);
                    
                    App.screen.ImageTileForCameraAidan.setImage(image);
                }
                
                
                
                
                else if(publish.getTopic().toString().equals("BuzzerDanhHuynh")) {
                    App.screen.textAreaForBuzzerDanh.setText(payload);
                } else if(publish.getTopic().toString().equals("CameraTakenDanhHuynh")) {
                    App.screen.textAreaForMotionDetectorDanh.setText(payload);
                } else if(publish.getTopic().toString().equals("TemperatureDataDanhHuynh")) {
                    String[] splitData = payload.split(",");
                    
                    String data = "Temperature: " + splitData[0] +" Humidity: " +splitData[1];
                    
                    App.screen.textAreaForTemperatureAndHumidityDanh.setText(data);
                } else if(publish.getTopic().toString().equals("CameraPictureDanhHuynh")) {
     
                    byte[] decodedBytes = Base64.getDecoder().decode(payload);

                    ByteArrayInputStream bais = new ByteArrayInputStream(decodedBytes);
                    
                    Image image = new Image(bais);
                    
                    App.screen.ImageTileForCameraDanh.setImage(image);
                }
                
                
                
                System.out.println("Received message: "
                    + publish.getTopic() + " -> "
                    + payload);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Mqtt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Mqtt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SignatureException ex) {
                Logger.getLogger(Mqtt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Mqtt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyStoreException ex) {
                Logger.getLogger(Mqtt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnrecoverableKeyException ex) {
                Logger.getLogger(Mqtt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchProviderException ex) {
                Logger.getLogger(Mqtt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
    
    public void sendPublicKey() {
        
        byte[] byteKey = k.getPublicKey().getEncoded();
        String strKey = Base64.getEncoder().encodeToString(byteKey);
        
        client.publishWith()
                .topic("Key"+username)
                .payload(UTF_8.encode(strKey))
                .send(); 
    }
    
    public void sendTemperatureTakenMessage(String msg) {
        client.publishWith()
                .topic("TemperatureTaken"+username)
                .payload(UTF_8.encode(msg))
                .send();
    }
    
    public void sendTemperatureDataMessage(String msg) {
        client.publishWith()
                .topic("TemperatureData"+username)
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
