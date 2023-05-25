/**
 * Copyright wendy512@yeah.net
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.github.stream.core.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.github.stream.core.Channel;
import io.github.stream.core.Sink;
import io.github.stream.core.SinkRunner;
import io.github.stream.core.Source;
import io.github.stream.core.channel.ChannelProcessor;

/**
 * @author wendy512@yeah.net
 * @date 2023-05-19 17:46:11
 * @since 1.0.0
 */
public class SimpleMaterializedConfiguration implements MaterializedConfiguration {

    private final Map<String, Source> sources = new HashMap<>();
    private final Map<String, List<SinkRunner>> sinkRunners = new ConcurrentHashMap<>();
    private final Map<String, List<Sink>> sinks = new ConcurrentHashMap<>();
    private final Map<String, List<Channel>> channels = new ConcurrentHashMap<>();
    private final Map<String, ChannelProcessor> channelProcessors = new ConcurrentHashMap<>();

    @Override
    public void addSource(String name, Source source) {
        sources.put(name, source);
    }

    @Override
    public void addSinkRunner(String name, SinkRunner sinkRunner) {
        sinkRunners.computeIfAbsent(name, k -> new ArrayList<>());
        sinkRunners.get(name).add(sinkRunner);
    }

    @Override
    public void addSink(String name, Sink sink) {
        sinks.computeIfAbsent(name, k -> new ArrayList<>());
        sinks.get(name).add(sink);
    }

    @Override
    public void addChannel(String name, Channel channel) {
        channels.computeIfAbsent(name, k -> new ArrayList<>());
        channels.get(name).add(channel);
    }

    @Override
    public void addChannelProcessor(String name, ChannelProcessor channelProcessor) {
        channelProcessors.put(name, channelProcessor);
    }

    @Override
    public Map<String, Source> getSources() {
        return sources;
    }

    @Override
    public Map<String, List<SinkRunner>> getSinkRunners() {
        return sinkRunners;
    }

    @Override
    public Map<String, List<Sink>> getSinks() {
        return sinks;
    }

    @Override
    public Map<String, List<Channel>> getChannels() {
        return channels;
    }

    @Override
    public Map<String, ChannelProcessor> getChannelProcessors() {
        return channelProcessors;
    }
}
