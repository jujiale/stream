package io.github.stream.mqtt.sink;

import io.github.stream.core.lifecycle.AbstractLifecycleAware;
import io.github.stream.core.properties.AbstractProperties;
import io.github.stream.mqtt.MqttStateConfigure;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

/**
 * mqtt 发送者
 * @author wendy512@yeah.net
 * @date 2023-05-24 10:30:22
 * @since 1.0.0
 */
@Slf4j
public final class MqttSender extends AbstractLifecycleAware {

    private final MqttStateConfigure stateConfigure;

    private static volatile MqttSender instance;

    private MqttSender(AbstractProperties properties) {
        this.stateConfigure = new MqttStateConfigure();
        this.stateConfigure.configure(properties, false);
    }

    public static MqttSender getInstance(AbstractProperties properties) {
        if (null == instance) {
            synchronized (MqttSender.class) {
                if (null == instance) {
                    instance = new MqttSender(properties);
                    instance.start();
                }
            }
        }
        return instance;
    }

    public void send(String topic, String payload) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(stateConfigure.getQos());
        mqttMessage.setPayload(payload.getBytes(StandardCharsets.UTF_8));

        try {
            if (log.isDebugEnabled()) {
                log.debug("Send message {} to mqtt topic {}", payload, topic);
            }
            stateConfigure.getClient().publish(topic, mqttMessage);
        } catch (MqttException e) {
            log.error("Send message to mqtt error", e);
        }
    }

    @Override
    public void stop() {
        stateConfigure.stop();
        super.stop();
    }
}