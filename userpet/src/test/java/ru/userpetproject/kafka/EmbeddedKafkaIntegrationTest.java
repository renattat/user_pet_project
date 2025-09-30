package ru.userpetproject.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;
import ru.userpetproject.UserPetApplication;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserPetApplication.class)
public class EmbeddedKafkaIntegrationTest {

    @ClassRule
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.3.0"));

    @Test
    public void produceConsume() throws Exception {
        //given
        String bootstrap = kafka.getBootstrapServers();

        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        org.apache.kafka.clients.producer.KafkaProducer<Long, String> producer = new KafkaProducer<>(producerProps);
        org.apache.kafka.clients.consumer.KafkaConsumer<Long, String> consumer = new KafkaConsumer<>(consumerProps);

        //when
        producer.send(new ProducerRecord<>("demo-topic", 100L, "Hello Kafka!")).get();
        producer.close();
        consumer.subscribe(List.of("demo-topic"));
        ConsumerRecords<Long, String> records = consumer.poll(Duration.ofSeconds(5));

        //then
        assertEquals(1, records.count());
        assertEquals("Hello Kafka!", records.iterator().next().value());
        assertEquals(Long.valueOf(100L), records.iterator().next().key());
    }
}



