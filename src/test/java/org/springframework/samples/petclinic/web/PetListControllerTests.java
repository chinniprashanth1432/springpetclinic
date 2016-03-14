package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test class for the {@link PetController}
 *
 * @author Colin But
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/business-config.xml", "classpath:spring/tools-config.xml", "classpath:spring/mvc-core-config.xml"})
@WebAppConfiguration
@ActiveProfiles("spring-data-jpa")
public class PetListControllerTests {

    @Autowired
    private PetListController petListController;

    @Autowired
    private FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(petListController)
            .setConversionService(formattingConversionServiceFactoryBean.getObject())
            .build();
    }

    @Test
    public void testShowPetListXml() throws Exception {
        testShowPetList("/pets.xml");
    }

    @Test
    public void testShowPetListHtml() throws Exception {
        testShowPetList("/pets.html");
    }

    @Test
    public void testShowResourcesPetList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/pets.json").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        actions.andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.petList[0].id").value(1));
    }

    private void testShowPetList(String url) throws Exception {
        mockMvc.perform(get(url))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("pets"))
            .andExpect(view().name("pets/petList"));
    }
}
