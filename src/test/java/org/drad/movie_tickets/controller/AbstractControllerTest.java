package org.drad.movie_tickets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractControllerTest {

    private final static ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.registerModule(new Jdk8Module());
    }

    private MockMvc mockMvc;
    private final ExceptionHandlingController exceptionHandler = new ExceptionHandlingController();

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(getControllers().toArray())
              .setHandlerExceptionResolvers(getExceptionHandlers())
              .setMessageConverters(getMessageConverter().toArray(new HttpMessageConverter<?>[0]))
              .build();
    }

    private List<HandlerExceptionResolver> getExceptionHandlers() {
        final ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            @Override
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(final HandlerMethod handlerMethod,
                  final Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(exceptionHandler.getClass())
                      .resolveMethod(exception);
                if (method != null) {
                    return new ServletInvocableHandlerMethod(exceptionHandler, method);
                }
                return super.getExceptionHandlerMethod(handlerMethod, exception);
            }
        };

        exceptionResolver.setMessageConverters(getMessageConverter());
        exceptionResolver.afterPropertiesSet();
        return Collections.singletonList(exceptionResolver);
    }

    protected List<HttpMessageConverter<?>> getMessageConverter() {
        return Collections.singletonList(new MappingJackson2HttpMessageConverter(OBJECT_MAPPER));
    }

    protected ResultActions perform(HttpMethod method, String urlTemplate, String body, Object... uriVars)
          throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
              .request(method, urlTemplate, uriVars)
              .accept(MediaType.APPLICATION_JSON)
              .contentType(MediaType.APPLICATION_JSON);

        if (body != null) {
            requestBuilder = requestBuilder.content(body);
        }

        return mockMvc.perform(requestBuilder);
    }

    protected ResultActions performPut(String urlTemplate, String body, Object... uriVars) throws Exception {
        return perform(HttpMethod.PUT, urlTemplate, body, uriVars);
    }
    public abstract List<Object> getControllers();
}
