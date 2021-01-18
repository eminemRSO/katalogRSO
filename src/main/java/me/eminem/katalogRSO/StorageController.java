package me.eminem.katalogRSO;

import org.modelmapper.ModelMapper;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class StorageController {

    TaskService taskService;

    ModelMapper modelMapper;

    public StorageController(TaskService taskService) {
        modelMapper = new ModelMapper();
        this.taskService = taskService;
    }



    @GetMapping(path="/tasks")
    public Stream<TaskDTO> getAllTasks(){
        return taskService.getAllTasks().stream().map(task -> modelMapper.map(task,TaskDTO.class));
    }


    //hmm
    @Retryable(maxAttempts = 5)
    @GetMapping(path="/tasksRetry")
    public Stream<TaskDTO> getAllTasksRetry() throws Exception {
        throw new Exception("Exception message");
        //return taskService.getAllTasks().stream().map(task -> modelMapper.map(task,TaskDTO.class));
    }
    @Recover
    public String weRecovered(){
        return "we fall backed to this after 5 tries";
    }

//    @HystrixCommand(fallbackMethod = "reliable")
//    @GetMapping(path="/fallbackTest")
//    public String readingList() {
//        URI uri = URI.create("http://localhost:8090/recommended");
//
//        return this.restTemplate.getForObject(uri, String.class);
//    }
//
//    public String fallback(){
//        return taskService.getAllTasks().stream().map(task -> modelMapper.map(task,TaskDTO.class));
//    }

}