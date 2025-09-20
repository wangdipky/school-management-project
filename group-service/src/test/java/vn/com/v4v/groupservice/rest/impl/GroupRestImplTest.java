package vn.com.v4v.groupservice.rest.impl;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import vn.com.v4v.groupservice.dto.AddGroupDto;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
class GroupRestImplTest {

    @Autowired
    private MockMvc mockMvc;

    Gson gson = new Gson();

    @Test
    void searchGroup() throws Exception {

    }

    @ParameterizedTest
    @ValueSource(strings = {"950", "951", "952", "953", "954", "955", "956", "957", "958", "959", "960", "-1", })
    void detail(long groupId) throws Exception {


       MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/group/detail/" + groupId))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void addGroup() throws Exception {

        AddGroupDto dto = new AddGroupDto();
        dto.setCode("JUNIT_1");
        dto.setName("JUNIT_1");
        dto.setDescription("JUNIT_1");
        String json = gson.toJson(dto);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/group/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void addGroupDuplicateCode() throws Exception {

        AddGroupDto dto = new AddGroupDto();
        dto.setCode("JUNIT_1");
        dto.setName("JUNIT_1");
        dto.setDescription("JUNIT_1");
        String json = gson.toJson(dto);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/group/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void addGroupWithOutInput() throws Exception {

        AddGroupDto dto = new AddGroupDto();
        dto.setCode("");
        dto.setName("");
        dto.setDescription("");
        String json = gson.toJson(dto);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/group/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}