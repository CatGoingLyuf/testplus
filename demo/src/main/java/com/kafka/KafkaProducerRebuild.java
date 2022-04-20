package com.kafka;
 
import java.util.Properties;  
  
import kafka.javaapi.producer.Producer;  
import kafka.producer.KeyedMessage;  
import kafka.producer.ProducerConfig;  
import kafka.serializer.StringEncoder;  

  
public class KafkaProducerRebuild extends Thread{  
  
    private String topic;  
      
    public KafkaProducerRebuild(String topic){  
        this.topic = topic;  
    }  
      
    public static void main(String[] args) {  
		// 使用kafka集群中创建好的主题 test 如果kafka集群中没有该主题，则会自动创建
        new KafkaProducerRebuild("test").start();
          
    }  
      
    @Override  
    public void run() {  
        Producer producer = createProducer();  
        int i=0;  
        while(true){
        	i++;
        	KeyedMessage<String, String> km = new KeyedMessage<String, String>(topic, "key-"+i, "我是生产信息"+i);
        	producer.send(km);
        	System.out.println("发送了: " + ++i);
        }  
    }  
  
    //创建生产者
    private Producer createProducer() {  
        Properties properties = new Properties();  
		//声明zookeeper  
        properties.put("zookeeper.connect", "IP地址:2181,IP地址:2181,IP地址:2181");
        properties.put("serializer.class", StringEncoder.class.getName());  
		// 声明kafka broker  
        properties.put("metadata.broker.list", "IP地址:9092,IP地址:9092,IP地址:9092");
        return new Producer<Integer, String>(new ProducerConfig(properties));  
     }  
 
} 
