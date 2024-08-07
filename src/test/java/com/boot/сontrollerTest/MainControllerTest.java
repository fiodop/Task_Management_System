//package com.boot.сontrollerTest;
//
//import com.boot.entity.Task;
//import com.boot.service.JwtService;
//import com.boot.service.TaskService;
//import com.boot.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import static net.bytebuddy.matcher.ElementMatchers.is;
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class MainControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private TaskService taskService;
//    @MockBean
//    private JwtService jwtService;
//    @MockBean
//    private UserService userService;
//    @Test
//    public void shouldReturnTasksWhenTokenIsValid() throws Exception{
//        String token = "ValidToken";
//        String username = "user123";
//        String authorizationHeader = "Bearer " + token;
//
//        UserDetails userDetails = new User(username,"password", new ArrayList<>());
//        List<Task> tasks = Arrays.asList(
//                new Task(1L, "Task 1", "Description 1", "NEW", new com.boot.entity.User(), username),
//                new Task(2L, "Task 2", "Description 2", "IN_PROGRESS", new com.boot.entity.User(), username));
//
//                Mockito.when(jwtService.extractUsername(token)).thenReturn(username);
//                Mockito.when(userService.getByUsername(username)).thenReturn((com.boot.entity.User) userDetails);
//                Mockito.when(jwtService.isTokenValid(token, userDetails)).thenReturn(true);
//                Mockito.when(taskService.getTasksByUsername(username)).thenReturn(tasks);
//
//
//
//        mockMvc.perform(get("/tasks/my-tasks")
//                        .header("Authorization", authorizationHeader)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)))
//                .andExpect((ResultMatcher) jsonPath("$[0].title", is("Task 1")))
//                .andExpect((ResultMatcher) jsonPath("$[1].title", is("Task 2")));
//
//        );
//    }
//
//}
