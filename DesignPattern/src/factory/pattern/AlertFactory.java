package factory.pattern;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AlertFactory {

    private final Map<String, AlertChannel> channelMap;

    public AlertFactory(List<AlertChannel> channels) {
        this.channelMap = channels.stream().collect(Collectors.toMap(AlertChannel::getType,
        		Function.identity()));
    }

    public AlertChannel getChannel(String type) {
        AlertChannel channel = channelMap.get(type);
        if (channel == null) {
            throw new IllegalArgumentException("Unsupported alert type: " + type);
        }
        return channel;
    }
}