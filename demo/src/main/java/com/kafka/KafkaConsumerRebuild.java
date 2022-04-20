package com.kafka;
 
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
 
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
 
public class KafkaConsumerRebuild extends Thread {
	
	private String topic;  
	
    public KafkaConsumerRebuild(String topic){  
        this.topic = topic;  
    }  
	
    public static void main(String[] args) {  
        new KafkaConsumerRebuild("test").start();// 使用kafka集群中创建好的主题 test   
    } 
	
    @Override  
    public void run() {
    	System.out.println("--开始消费信息--");
        ConsumerConnector consumer = createConsumer();  
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
        //设置消费线程数为1,如果是两个线程消费，则设置成2（注意如果是两个线程消费，但此处设置为1,那么只会有一个线程消费）
        topicCountMap.put(topic, 1);   
         Map<String, List<KafkaStream<byte[], byte[]>>>  messageStreams = consumer.createMessageStreams(topicCountMap); 
         List<KafkaStream<byte[], byte[]>> streams = messageStreams.get(topic);
         for (KafkaStream<byte[], byte[]> kafkaStream : streams) {
        	 ConsumerIterator<byte[], byte[]> iterator =  kafkaStream.iterator(); 
        	 while(iterator.hasNext()){  
        		 String message = new String(iterator.next().message());
        		 //将读取到的数据写入到文件中
        		 writefile(message,"E:\\temp\\");
        		 System.out.println("接收到: " + message);  
        	 }  
		}
    }  
    
    //创建消费者
    private ConsumerConnector createConsumer() {  
        Properties properties = new Properties();  
		//声明zookeeper
        properties.put("zookeeper.connect", "IP地址:2181,IP地址:2181,IP地址:2181");
		//设置超时时间
        properties.put("zookeeper.session.timeout.ms", "1000");
		//一个topic主题只能被一个组消费一次，要想重复消费，每次可以设置别的组名称，或在zk中移除此group
        properties.put("group.id", "ee");
		//初始化offset,largest表示从topic的的最新处开始处理,smallest表示从topic的头开始处理
        properties.put("auto.offset.reset","smallest");
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));  
     }  
    
    //写文件
    public static void writefile(String data, String path) {
		try {
			String format = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			path = path + "kafka.log." + format;
			File file = new File(path);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, true); // 如果追加方式用true
			StringBuffer sb = new StringBuffer();
			sb.append(data + "\n");
			out.write(sb.toString().getBytes("utf-8"));// 注意需要转换对应的字符集
			out.close();
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}
	}
}
