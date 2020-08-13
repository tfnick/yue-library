package ai.yue.library.message.kafka.support;

/**
 * Created by on 17/8/26.
 */
public class YueKafkaProducerException extends RuntimeException {

    public YueKafkaProducerException(String message, Throwable e) {
        super(message, e);
    }

    public YueKafkaProducerException(Throwable e) {
        super(e);
    }

}
