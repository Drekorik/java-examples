package org.fireplume.controller;

import org.fireplume.services.SomeService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SomeControllerTest {

    @InjectMocks
    private SomeController someController;

    @Mock
    private SomeService someService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(someController).build();
    }

    @Test
    public void testGetPage() {
    }

    @Test
    public void testGetPageWithData() {
    }

    @Test
    public void testGetString() throws Exception {
        // given
        String input = "some string";
        String output = input.replace(" ", "");
        when(someService.getStringWithoutSpaces(eq(input))).thenReturn(output);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/convert?str=%s", input)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        // then
        MockHttpServletResponse response = mvcResult.getResponse();
        MatcherAssert.assertThat(response.getStatus(), Is.is(200));
        MatcherAssert.assertThat(response.getContentAsString(), Is.is(output));
    }
}