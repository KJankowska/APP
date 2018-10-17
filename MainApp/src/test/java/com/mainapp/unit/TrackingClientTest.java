package com.mainapp.unit;

import com.mainapp.tracking.Event;
import com.mainapp.tracking.TrackingClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import static com.mainapp.generator.DataGenerator.generateEvents;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TrackingClientTest {

    @Mock
    Client mockHttpClient;
    @Mock
    WebTarget mockWebtarger;
    @Mock
    Invocation.Builder mockInvocationBuilder;

    TrackingClient trackingClient;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        trackingClient = new TrackingClient(mockHttpClient, "8081");
    }

    @Test
    public void registerEventTest()
    {
        // given
        given(mockHttpClient.target(anyString())).willReturn(mockWebtarger);
        given(mockWebtarger.path(anyString())).willReturn(mockWebtarger);
        given(mockWebtarger.request(MediaType.APPLICATION_JSON_TYPE)).willReturn(mockInvocationBuilder);

        // when
        trackingClient.registerEvent(any(Event.class));

        // then
        verify(mockHttpClient, times(1)).target(anyString());
        verify(mockWebtarger, times(1)).path(anyString());
        verify(mockWebtarger, times(1)).request(MediaType.APPLICATION_JSON_TYPE);
    }

    @Test
    public void getEventLogsTest()
    {
        // given
        given(mockHttpClient.target(anyString())).willReturn(mockWebtarger);
        given(mockWebtarger.path(anyString())).willReturn(mockWebtarger);
        given(mockWebtarger.request(MediaType.APPLICATION_JSON_TYPE)).willReturn(mockInvocationBuilder);
        given(mockInvocationBuilder.get(any(GenericType.class))).willReturn(generateEvents());

        // when
        trackingClient.getEventLogs();

        // then
        verify(mockHttpClient, times(1)).target(anyString());
        verify(mockWebtarger, times(1)).path(anyString());
        verify(mockWebtarger, times(1)).request(MediaType.APPLICATION_JSON_TYPE);
        verify(mockInvocationBuilder, times(1)).get(any(GenericType.class));
    }
}
